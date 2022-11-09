cd modbusservice
docker build -t modbus-service-docker .
docker container run --name modbus-service  -p 8006:8006 modbus-service-docker