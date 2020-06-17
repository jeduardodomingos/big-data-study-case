'use strict'

const service = require('../service/twitter.service.js');

exports.search = async (req, res, next) => {
    const tag = req.query.tag;  

    console.log(`Initializing process for tag: #${tag} ...`);
       
    try {
        const authentication = await service.authenticate();
        const authenticationResponse = await authentication.json();

        let token = authenticationResponse.access_token;

        const search = await service.searchData(token, tag);
        const searchResponse = await search.json();

        let response = service.mapResult(searchResponse, tag);

        service.saveData(response);

        res.status(200).send(response);
        
    } catch (err) {
        res.status(500)
           .send({code: 500, message: "An error has ocurred.", details: err});
    }

};