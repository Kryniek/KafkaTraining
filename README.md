# KafkaTraining

## KafkaDemo
Demo Spring boot application with connection to kafka docker container.

### Start containers
Run containers in KafkaDemo directory: `docker-compose up -d`, it will run Kafka with Zookeeper.

To see logs of detached container: `docker logs kafka` or `docker logs zookeeper`.

### Usage
Build Spring boot application: `mvn clean install` and then run: `mvn spring-boot:run`.

Exposed endpoints:
 - POST: http://localhost:8080/api/strings - sends string message to kafka, body:
 ```
"string"
 ```
 - POST: http://localhost:8080/api/messages - sends object message to kafka
  ```
{
    "id": "string",
    "field1": "string",
    "field2": "string"
}
  ```
 - POST: http://localhost:8080/api/users - sends another one object message to kafka
  ```
{
    "id": "string",
    "field1": "string",
    "field2": "string",
    "field3": "string"
}
  ```

Also you can modify property file: `application.yml`, set some of below properties to `true` to send 10.000 messages to consumer at a time. Properties:
 - `enable-string-producer-tester: true`
 - `enable-message-producer-tester: true`
 - `enable-user-producer-tester: true`

## kafka-connect-docker
It's external repository that gives ability to run kafka commands in dockerized kafka shell.

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