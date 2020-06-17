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
        const client = this.client();
        const scripts = process.env.CASSANDRA_INTIAL_SCRIPTS.split("|");

        scripts.forEach(script => {
            this.execute(client ,script, {}, () => {});
            console.log(`Executing: ${script}`);
        });

        client.shutdown();
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

    execute(client, command, params, callback) {
        client.execute(command, params, {prepare: true}, callback);
    }
}
