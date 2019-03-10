Feel free to complete this test task, override or change everything

For start app: 
1. Upadte tmdb.apikey in src\main\resources\application.properties
2. Run mvn clean install
3. Run mvn spring-boot:run

### Register user
curl -X POST --data "email=test@test2.com&password=test" http://localhost:8080/user/register/ -v

### View popular movies
curl -X POST --data "email=test@test2.com&password=test" http://localhost:8080/movie/popular/ -v

Use any RESTClient for test
### Add actor
URL: http://localhost:8080/user/{userId}/actor?actorId=33
Method: PUT
Path variable 'userId' : id of user which adding actor
Request param 'actorId': id of actor

### Delete actor
URL: http://localhost:8080/user/{userId}/actor?actorId=33
Method: DELETE
Path variable 'userId' : id of user which adding actor
Request param 'actorId': id of actor

### Mark movie as watched
URL: http://localhost:8080/movie/{movieId}/watch
Method: PUT
Path variable 'movieId' : id of movie

### Search movie by year and favorite actor in unwatched films
URL: http://localhost:8080/movie/search?year=2019&actorName=Brad Pit&watched=false
Method: GET
Request param 'year' : year of movie's release
Request param 'actorName' : name of actor
Request param 'watched' : false - for unwatched movies
