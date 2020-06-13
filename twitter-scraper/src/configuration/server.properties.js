'use strict';

exports.normalizePort = (portNumber) => {
    const port = parseInt(portNumber);

    if (isNaN(port)) {
        return portNumber;
    }

    if (port >= 0) {
        return port;
    }

    return false;
}

exports.errorHandler = (err) => {

    if (err.syscall !== 'listen') {
        throw err;
    }

    const bind = typeof port == 'string' ? 'Pipe ' + port : 'Port ' + port;

    switch (error.code) {
        case "EACCES":
            console.error("Hey man access denied, " + bind + " requires elevated privileges.");
            process.exit(1);
            break;
        case "EADDRINUSE":
            console.error("We have a problems, " + bind + " is already in use.");
            process.exit(1);
            break;
        default:
            throw error;
    }
}

exports.closeHandler = (evt) => {
    console.log("O serviço foi encerrado.");
}