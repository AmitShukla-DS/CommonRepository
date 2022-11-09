cd user-management
docker build -t user-management-server-docker .
docker container run --name user-management  -p 8001:8001 user-management-server-docker