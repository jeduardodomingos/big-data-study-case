'use strict'
require('dotenv/config');

const express = require('express');
const http = require('http');
const properties = require('./configuration/server.properties.js');

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
});

server.on('error', properties.errorHandler);
server.on('close', properties.closeHandler);
