# GeoAPI - Postal Code to Town Information Service

## Project Description
GeoAPI is a RESTful web service built with Spring Boot 3 that allows users to retrieve information about towns based on
their postal codes. The service interacts with the French government's public API for administrative geographic data 
(https://geo.api.gouv.fr/decoupage-administratif/communes) to fetch the relevant data. This project provides a clean, 
simple interface to get the name and population of a town using its postal code.
```diff
- NB : Postal codes must contain only digits. 🤓
```
## Features
- **Retrieve Town Information**: Fetch the name and population of a town by providing its postal code.

- **Error Handling**: Implements comprehensive error handling to manage various potential issues, including invalid postal codes and network errors. Users receive meaningful error messages and appropriate HTTP status codes to facilitate easier debugging and error resolution.

- **Data Transformation**: Processes and transforms the raw data received from the external API into a consistent and user-friendly format. This ensures that the application always returns data in the expected structure, improving usability and integration with other systems.

- **Input Validation**: Validates that postal codes are numeric and non-blank before processing the request. This prevents invalid input from causing errors or unexpected behavior in the service.
## 🛠 visualize and test API
Run the project and visualize [swagger](https://swagger.io/tools/swaggerhub/features/swaggerhub-portal/) to test all existing API calls 👇

- [http://localhost:8080/swagger-ui/index.htm#/](http://localhost:8080/swagger-ui/index.htm#/)
<p align="center">
        <img src="screenshots/swagger.png" width="100%">
</p>
<br>

## 💻 POM reference resources:
To check used dependencies refer to [Pom](pom.xml). 