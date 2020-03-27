# Covid-19 Global Stats Updates (Sample Java REST Endpoint)

This is a simple, proof of concept, REST API Java application.

### Functionality:
- **Step 1:** A GET request is made to https://corona.lmao.ninja/all to pull the current global status for cases, losses, recovered, and the current date.
- **Step 2:** The JSON from the request is parsed into a class that transforms the date format into a human readable string.
- **Step 3:** The class data is inserted into a SQLite database.
- **Step 4:** The data in the database is made available via two REST endpoints ("/update" pulls the most recent record; "/updates" pulls all records).
- **Step 5:** When a request is made to one of the two endpoints the data is pulled from the database, transformed into a JSON string using a StringBuilder, then returned to the requester.
- **NOTE:** Rather than create a scheduled task to routinely poll https://corona.lmao.ninja/all, an update is pulled each time a GET request is made to this application. The intention is to use it with an existing website that will pull from this application at regular intervals.

### Basic Information
- **Deployment:** Heroku using a GitHub => Heroku connection.
- **Web Framework:** Spring
- **Build Framework:** Maven
- **Foundation:** The Java application is based off of [Heroku's sample Java app]. Extensive modifications were made to the original foundation. The original sample application was a single Java class with a couple of website calls. No functionality existed related to making GET requests, JSON parsing and transformation, SQLite, or hosting REST endpoints.

[Heroku's sample Java app]: https://github.com/heroku/java-getting-started
