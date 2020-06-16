'use strict'

const service = require('../service/twitter.service.js');

exports.search = (req, res, next) => {
    const tag = req.query.tag;     

    service.authenticate()
        .then(response => response.json())
        .then(responseBody => {
            let token = responseBody.access_token;
            service.searchData(token, tag)
                .then(response => response.json())
                .then(responseBody => {
                    res.status(200).send(responseBody);
                });
        });
};