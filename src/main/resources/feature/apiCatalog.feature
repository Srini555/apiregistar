Feature: API catalog

  Background: xTron is migrating to microservice and wants to create own api catalog

  Scenario: Register new api

	Given xTron restaurant team given the information to register their api as
	  | Application Name   | Application Owner | Application Owner Email | Application Api Url                   |
	  | restaurant_account | PCFCOE            | amit.datta2@wipro.com   | http://localhost:8002/swagger-ui.html |
	  | restaurant_order   | PCFCOE            | amit.datta2@wipro.com   | http://localhost:8003/swagger-ui.html |

	Then Api should be registered as
	  | restaurant_account,PCFCOE,amit.datta2@wipro.com,http://localhost:8002/swagger-ui.html,Application registered successfully |
	  | restaurant_order,PCFCOE,amit.datta2@wipro.com,http://localhost:8003/swagger-ui.html,Application registered successfully   |

