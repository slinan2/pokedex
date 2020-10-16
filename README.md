# Pokedex
## Prerequisites

1. `Java 11` must be installed.

### Running in AWS
Front-end: http://ec2-52-34-160-15.us-west-2.compute.amazonaws.com:8080/ 

Back-end: http://ec2-52-34-160-15.us-west-2.compute.amazonaws.com:8080/api/pokemon/14

Details on how the project is running live are here: https://github.com/slinan2/pokedex/wiki

### Running locally
```sh
$ git clone https://github.com/slinan2/pokedex
$ ./mvnw spring-boot:run
$ ./mvnw spring-boot:test
```
### Extras
- Instead of a cache layer, a full JPA integration was coded. By default, an "in memory DB" (H2) runs in the development environment.
- Integration tests were coded (due to time restrictions I couldn't code unit tests). **Line coverage of 82%** This is the summary https://slinan2.github.io/pokedex/index.html
- Small front-end coded in Vue.js
- I called the Pokemon API directly with the Java 11 HTTPClient to get the basic information of a Pokemon. Additionally and due to time constraints, https://github.com/PokeAPI/pokekotlin was also used to access the API
