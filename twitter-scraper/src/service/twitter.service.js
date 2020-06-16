'user strict'

const fetch = require('node-fetch');
const Bluebird = require('bluebird');
const http = require('../utils/http-codes.js');
const CassandraConnector = require('../database/cassandra.connector.js');

fetch.promisse = Bluebird;

const API_HOST = process.env.TWITTER_HOST;
const AUTHENTICATION_PATH="oauth2/token";
const SEARCH_PATH="1.1/search/tweets.json";

exports.authenticate = () => {
    const authenticationPath = `${API_HOST}/${AUTHENTICATION_PATH}?grant_type=client_credentials`;

    const options = {
        method: http.POST.method,
        headers: { 
            'Content-Type': 'application/json',
            'Authorization': `Basic ${process.env.TWITTER_CREDENTIALS}`
        }
    }

    console.log("Authenticating on twitter service ...");

    return fetch(authenticationPath, options);
}

exports.searchData = (token, tag) => {
    const searchPath = `${API_HOST}/${SEARCH_PATH}?q=%23${tag}`;

    console.log(searchPath);

    const options = {
        method: http.GET.method,
        headers: { 
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
    }

    console.log("Searching tags on twitter service ...");

    return fetch(searchPath, options);
}

exports.saveData = (content) => {
    const cassandra = new CassandraConnector(
        process.env.CASSANDRA_DATABASE_USERNAME,
        process.env.CASSANDRA_DATABASE_PASSWORD,
        process.env.CASSANDRA_DATABASE_KEYSPACE,
        process.env.CASSANDRA_DATABASE_HOSTS
    );

}

exports.mapResult = (response, tag) => {
    console.log(`Mapping results to process for tag: #${tag} ...`);

    return response.statuses.map(item => {
        return {
            filterTag: tag,
            text: item.text,
            hashtags: item.entities.hashtags,
            favorited: item.favorited,
            favouriteds: item.favorite_count,
            retweeted: item.retweeted,
            retweets: item.retweet_count,
            language: item.lang,
            source: item.source,
            mentions: item.entities.user_mentions.map(user => {
                return {
                    id: user.id,
                    name: user.name,
                    description: user.description
                }
            }),
            user: {
                id: item.user.id,
                name: item.user.name,
                address: item.user.screen_name,
                description: item.user.description,
                url: item.user.url,
                followers: item.user.followers_count,
                friends: item.user.friends_count
            }
        }
    });
}