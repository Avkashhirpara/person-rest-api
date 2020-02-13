# person-rest-api
 
[![CircleCI](https://circleci.com/gh/Avkashhirpara/person-rest-api.svg?style=svg)](https://circleci.com/gh/Avkashhirpara/person-rest-api)


## Person-rest-api is created using following tools and technology

- spring boot rest
- spring data jpa
- h2 
- jUnit 5
- java 1.8
- swagger ui 
- vagrant 
- circleci

## Database entities and relation
 
### person

| ID | FIRST_NAME | LAST_NAME | FAVOURITE_COLOUR| AGE |
|---|----------|--------------|------------------|---|
|1|Peter | Thomas | red| 30|
### hobby
| ID | NAME | 
|---|--------|
|1|chess|
### person-hobby 
 | HOBBY_ID | PERSON_ID | 
 |---|--------|
|1|1|
## How to run,test ?

#### 1. Clone repository
git clone https://github.com/Avkashhirpara/person-rest-api.git

#### 2. Clone repository
run vagrant command inside cloned directory
 
 `vagrant up `  

vagrant instance will automatically clone,build and deploy APIs. 
vagrant instance is preconfigured with port forward.
once system is up and running, you can browse UI on http://localhost:8080/swagger-ui.html. 

<i>OR</i>

For pre-installed maven and java system, run ` mvn spring-boot : run ` command.</br>

you can browse UI on http://localhost:8080/swagger-ui.html.

## API structure. 

### Hobby-Controller 

| http method | api url | description|
|-------------|----------|-----------| 
| GET         | /api/v1/hobbies | View a list of all hobbies |
| POST | /api/v1/hobbies | Create Hobby |
| GET | /api/v1/hobbies/{id} | Retrieve Hobby by hobbyId |
| PUT | /api/v1/hobbies/{id}| Update Hobby| 
| DELETE| /api/v1/hobbies/{id} | Remove hobby from System |



### Person Controller

| http method | api url | description|
|-------------|----------|-----------|
|GET | /api/v1/persons |View a list of all persons |
| POST |/api/v1/persons | Create Person |
| GET | /api/v1/persons/{id} | Retrieve person by personId|
| PUT | /api/v1/persons/{id} | Update Person|
|DELETE | /api/v1/persons/{id} |Delete Person|
| POST | /api/v1/persons/{personId}/hobby/{hobbyId} |Add hobby for Person |
| DELETE | /api/v1/persons/{personId}/hobby/{hobbyId} |Remove hobby for Person |
