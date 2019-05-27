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

  Scenario: Registering an already registered  Api

	Given xTron restaurant team already registered restaurant_account and trying to register again
	  | Application Name | Application Owner | Application Owner Email | Application Api Url                   |
	  | duplicate_api    | PCFCOE            | amit.datta2@wipro.com   | http://localhost:8002/swagger-ui.html |
	  | duplicate_api    | PCFCOE            | amit.datta2@wipro.com   | http://localhost:8002/swagger-ui.html |

	Then Api should not be registered
	  | Application registered successfully |
	  | Application already registered      |


  Scenario: Search for registered api

	Given John wants to use xTron registered api and searching for registered api
	Then John should get registered Api as
	  | restaurant_account,PCFCOE,amit.datta2@wipro.com,http://localhost:8002/swagger-ui.html |
	  | restaurant_order,PCFCOE,amit.datta2@wipro.com,http://localhost:8003/swagger-ui.html   |
	  | duplicate_api,PCFCOE,amit.datta2@wipro.com,http://localhost:8002/swagger-ui.html      |

  Scenario: Search for registered api by API name

	Given John is searching for api as
	  | restaurant_account |
	Then John should get registered Api as
	  | restaurant_account,PCFCOE,amit.datta2@wipro.com,http://localhost:8002/swagger-ui.html |


  Scenario: Update already registered API

	Given xTron team wants to update api as
	  | Application Name   | Application Owner | Application Owner Email | Application Api Url                   |
	  | restaurant_account | PCFCOE_LAB        | amit.datta2@wipro.com   | http://localhost:8002/swagger-ui.html |
	Then API should be updated as
	  | restaurant_account,PCFCOE_LAB,amit.datta2@wipro.com,http://localhost:8002/swagger-ui.html,Application has been updated successfully |