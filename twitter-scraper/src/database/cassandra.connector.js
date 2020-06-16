const cassandra = require('cassandra-driver'); 

module.exports = class CassandraConnector {

    constructor(username, password, keyspace, hosts){
        this.username = username;
        this.password = password;
        this.keyspace = keyspace;
        this.hosts = hosts;
    }

    client() {
        console.log("Creating cassandra connection client ...");
        
        return new cassandra.Client({
            contactPoints: this.contactPoints, 
            authProvider: authenticationProvider(), 
            keyspace: this.keyspace
        });
    }

    authenticationProvider() {
        return new cassandra.auth.PlainTextAuthProvider(this.username, this.password);
    }

}
