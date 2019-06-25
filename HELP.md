Account Transaction Analyser - An Application that analyses financial transaction Records

Rest Service to derive relative account balance of an Account in a given timeframe.

This Restful webservice provide a rest end point

A rest endpoint to view the account balance of an Account in a given timeframe - Rest URL : http://localhost:8080/checkBalance

To test this service, it doesn't need any db setup as it is using H2 Embedded DB with entity - ACCOUNTS TRANSACTIONS. The data is loaded from CSV file on start of the application.

Alternatively open the H2 DB console using http://localhost:8080/h2-console/ where we can insert more data.

How to Execute:

Run this Service as a Spring Boot Application with maven build. Once the application is up and running, open a SOAPUI or POSTMAN.

To get the relative balance and no of transactions included for given timeframe for an Account: Use the URL - http://localhost:8080/checkBalance to send the Get Request with
 1. Key as "accountId" and the value as one of the available account number in the above list.
 2. Key as "fromDate" and value as given from Date
 3. Key as "toDate" and value as given to Date

In case there is no data or exception, it will return error message

Note: Make sure to update the path of the CSV file in Application.properties of the resources folder when this application is dowloaded and run in local.