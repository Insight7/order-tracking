

## Setup
docker-compose -f docker-compose.yaml up -d

docker run -d -p 80:8080 -v swagger.yaml:/tmp/swagger.yaml -e SWAGGER_FILE=tmp/swagger.json swaggerapi/swagger-editor

