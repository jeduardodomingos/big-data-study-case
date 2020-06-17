'use strict'
require('dotenv/config');

const express = require('express');
const http = require('http');
const properties = require('./configuration/server.properties.js');
const CassandraConnector = require('./database/cassandra.connector.js');

const app = express();
const port = properties.normalizePort(process.env.PORT || 3000);
const hostname = process.env.HOST;

const twitterRoute = require("./routes/twitter.route.js");

app.use(express.json());

app.use("/api", twitterRoute);

app.use((error, req, res, next) => {
    res.status(500).json({ error });
});

const server = http.createServer(app);

app.set("port", port);

server.listen(port, () => {
    console.log(`Serviço em execução em http://${hostname}:${port}/api/`);
    initializeDatabase();
});

server.on('error', properties.errorHandler);
server.on('close', properties.closeHandler);

function initializeDatabase() {
    console.log("Initializing cassandra database ...");

    const cassandra = new CassandraConnector(
        process.env.CASSANDRA_DATABASE_USERNAME,
        process.env.CASSANDRA_DATABASE_PASSWORD,
        process.env.CASSANDRA_DATABASE_KEYSPACE,
        process.env.CASSANDRA_DATABASE_HOSTS,
        process.env.CASSANDRA_DATABASE_LOCAL_DATACENTER
    );

    cassandra.configure();
}