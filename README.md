# spring-demo

Mongodb
Install Mongodb from Docker Hub

docker pull bitnami/mongodb:8.0.1

Start Mongodb server at port 27017 with root username and password: root/root

docker run -d --name mongodb-8.0.1 -p 27017:27017 -e MONGODB_ROOT_USER=root -e MONGODB_ROOT_PASSWORD=root bitnami/mongodb:8.0.1