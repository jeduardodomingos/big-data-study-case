'use strict'
const express = require('express');
const controller = require('../controller/twitter.controller.js');
const http = require('../utils/http-defaults.js');

const router = express.Router();

router.get("/v1/search", http.headers, controller.search);

module.exports = router;