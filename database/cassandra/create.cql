CREATE ROLE tsa WITH PASSWORD = '1234' AND SUPERUSER = true AND LOGIN = true;

ALTER ROLE cassandra WITH PASSWORD='1234'AND SUPERUSER=false;

CREATE KEYSPACE IF NOT EXISTS twitter_scraper_space WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 3};

CREATE TYPE IF NOT EXISTS twitter_scraper_space.hashtags(content varchar,indices LIST<bigint>);

CREATE TYPE IF NOT EXISTS twitter_scraper_space.users(id bigint, name varchar, address varchar, description varchar, url varchar, followers bigint, friends bigint);

CREATE TYPE IF NOT EXISTS twitter_scraper_space.mentions(id bigint,name varchar);

CREATE TABLE IF NOT EXISTS twitter_scraper_space.tweets (id bigint, key_tag varchar, post_content varchar, tag_composition FROZEN<hashtags>, user FROZEN<users>, favorited Boolean, favouriteds bigint, retweeted Boolean, retweets bigint, language varchar, source varchar, mention FROZEN<mentions>, createdAt timestamp, updatedAt timestamp, PRIMARY KEY(id));