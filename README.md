

## Setup
docker-compose -f docker-compose.yaml up -d

docker run -d -p 80:8080 -v swagger.yaml:/tmp/swagger.yaml -e SWAGGER_FILE=tmp/swagger.json swaggerapi/swagger-editor

## TODOS
* [ ] Add Test cases
    * [ ] Orders listing
    * [ ] Orders update lifecycle and data
    * [ ] Orders Listing pagination
    * [ ] Tracking Listing
    * [ ] Tracking Pagination
    * [ ] Tracking updates
* [ ]  Implements Methods
    * [ ] Orders Rest Layer
    * [ ] Orders Service Layer
    * [ ] Rest Exceptions
    * [ ] Tracking Rest Layer
    * [ ] Tracking Service Layer
* [ ] Complete Swagger file
* [ ] Document its feature and uses