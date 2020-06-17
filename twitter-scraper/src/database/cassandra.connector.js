const cassandra = require('cassandra-driver'); 

module.exports = class CassandraConnector {

    constructor(username, password, keyspace, hosts, datacenter){
        this.username = username;
        this.password = password;
        this.keyspace = keyspace;
        this.hosts = hosts.split(",");
        this.datacenter = datacenter;
    }

    configure() {
        const scripts = process.env.CASSANDRA_INTIAL_SCRIPTS;
        let list = scripts.split("|");

        list.forEach(script => {
            this.execute(script, {}, () => {});
            console.log(`Executing initializer script for ${script}`);
        });
    }

    client() {
       return new cassandra.Client({
            contactPoints: this.hosts, 
            authProvider: this.authenticationProvider(), 
            keyspace: this.keyspace,
            localDataCenter: this.datacenter
        });
    }

    authenticationProvider() {
        return new cassandra.auth.PlainTextAuthProvider(this.username, this.password);
    }

    execute(command, params, callback) {
        console.log("Executing cassandra command ...");
       
        let client = this.client();
        client.execute(command, params, {prepare: true}, callback);
        client.shutdown();
    }
}
