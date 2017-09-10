# Messaging Application

This is a basic messaging application using rabbit MQ.

To run please use docker compose from the root of the project.

To send messages hit the producer using the url below replacing {number} with the required number of messages..
```
http://localhost:8080/send/{number}
```

To List the stored Data hit the subscribers endpoint using the url below.
```
http://localhost:8081/data
```