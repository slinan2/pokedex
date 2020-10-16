# Pokedex
## Prerequisites

1. `Java 11` must be installed.

### Running locally
```sh
$ git clone https://github.com/slinan2/pokedex
$ ./mvnw spring-boot:run
$ ./mvnw spring-boot:test
```
### Extras
- Instead of a cache layer, a full JPA integration was coded. By default, an "in memory DB" (H2) runs in the development environment.
- Integration tests were coded (due to time restrictions, I these tests as they have a good coverage). **Line coverage of 82%**
- Small front-end coded in Vue.js
- I called the Pokemon API directly with the Java 11 HTTPClient to get the basic information of a Pokemon. Additionally and due to time constraints, https://github.com/PokeAPI/pokekotlin was also used to access the API
