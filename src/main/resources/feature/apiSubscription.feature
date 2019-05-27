Feature: API Subscription

  Scenario: Subscribe a registered API

	Given John wants to subscribe for registered api as
	  | Client Application Name | Client Application Owner | Application Name To Register | Client Application Owner Email |
	  | restaurant_order        | PCFCOE                   | pcf_coe_api                  | amit.datta2@wipro.com          |
	Then Client application should be able to subscribe the api
	  | restaurant_order,PCFCOE,pcf_coe_api,amit.datta2@wipro.com |