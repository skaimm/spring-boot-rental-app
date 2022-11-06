# spring-boot-rental-app
# Spring Boot Rental App
Its a JSON API that returns a list of vehicles for rental that can be filtered, sorted, and paginated.

## Respond to the Followin URLs:
- `rvs`
- `rvs?price[min]=9000&price[max]=75000`
- `rvs?page[limit]=3&page[offset]=6`
- `rvs?ids=2000,51155,54318`
- `rvs?near=33.64,-117.93` // within 100 miles
- `rvs?sort=price`
- `rvs/RV_ID`

## Use this Heroku url for example responds 
- https://spring-boot-rental-app.herokuapp.com/rvs
