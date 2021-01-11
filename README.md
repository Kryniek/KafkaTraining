# KafkaTraining

## kafka-connect-docker
It's external repository that gaves ability to run kafka commands in dockerized kafka shell.

Repository: https://github.com/liliankasem/kafka-connect-docker

### Start container
For windows: `docker-compose -f docker-compose-confluent.yml up -d`.
For linux and mac: `docker-compose up -d`.

To see logs of detached container: `docker logs kafka`.

### Usage
First connect to docker container bash: `docker exec -it kafka bash`.
Now you can play. Examples:
 - create topic: 
 ```
kafka-topics --create --topic test --partitions 1 --replication-factor 1 --if-not-exists --zookeeper zookeeper:2181
```
 - start producer:
 ```
kafka-console-producer --bootstrap-server localhost:9092 --topic test
```
 - start consumer:
 ```
kafka-console-consumer --bootstrap-server localhost:9092 --topic test --from-beginning --max-messages 42
```