'user strict'

const fetch = require('node-fetch');
const Bluebird = require('bluebird');
const http = require('../utils/http-codes.js');

fetch.promisse = Bluebird;

const API_HOST = process.env.TWITTER_HOST;
const AUTHENTICATION_PATH="/oauth2/token";
const SEARCH_PATH="/1.1/search/tweets.json";

exports.authenticate = () => {
    const authenticationPath = `${API_HOST}/${AUTHENTICATION_PATH}?grant_type=client_credentials`;

    const options = {
        method: http.POST.method,
        headers: { 
            'Content-Type': 'application/json',
            'Authorization': `Basic ${process.env.TWITTER_CREDENTIALS}`
        }
    }

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

    return fetch(searchPath, options);
}