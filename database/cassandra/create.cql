CREATE KEYSPACE IF NOT EXISTS "twittwe_scraper_space" WITH replication = {'class': 'SimpleStrategy', 'replication_factor'3};

sed 's/authenticator: AllowAllAuthenticator/authenticator: PasswordAuthenticator/g' /etc/cassandra/cassandra.yaml > /etc/cassandra/cassandra.yaml.changed && mv /etc/cassandra/cassandra.yaml.changed /etc/cassandra/cassandra.yaml