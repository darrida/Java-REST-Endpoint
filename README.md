# Covid-19 Global Stats Updates (Sample Java REST Endpoint)

This is a simple, proof of concept, REST API Java application. For details on functionality see "How this works" further below. Use the the buttons for samples of response data from the endpoints.

### Endpoint Specification:
- **Latest Record from Database:** https://java-rest-endpoint-covid19.herokuapp.com/update
- **All Records from Database:** https://java-rest-endpoint-covid19.herokuapp.com/updats
- **Authentication:** None
- **Headers:** None

#### HTTP GET Request Usage Sample (using Python)
```python
import requests
from pprint import pprint
import json

result = requests.get('http://java-rest-endpoint-covid19.herokuapp.com/updates')
pprint(result.json())
```

### How this works
- **Functionality:**
  - **Step 1:** A GET request is made to https://corona.lmao.ninja/all to pull the current global status for cases, losses, recovered, and the current date.
  - **Step 2:** The JSON from the request is parsed into a class that transforms the date format into a human readable string.
  - **Step 3:** The class data is inserted into a SQLite database.
  - **Step 4:** The data in the database is made available via two REST endpoints ("/update" pulls the most recent record;   "/updates" pulls all records).
  - **Step 5:** When a request is made to one of the two endpoints the data is pulled from the database, transformed into a JSON string using a StringBuilder, then returned to the requester.
  - **NOTE:** Rather than create a scheduled task to routinely poll https://corona.lmao.ninja/all, an update is pulled each time a GET request is made to this application. The intention is to use it with an existing website that will pull from this application at regular intervals.
 
### Basic Functionality
- **Deployment:** Heroku using a GitHub => Heroku connection.
- **Web Framework:** Spring
- **Build Framework:** Maven
- **Foundation:** The Java application is based off of [Heroku's sample Java app]. Extensive modifications were made to the original foundation. No functionality originally existed related to making GET requests, JSON parsing and transformation, SQLite, or hosting REST endpoints.
- **Development Environment:** Intellij IDEA Community Edition (running on Ubuntu 18.04)

[Heroku's sample Java app]: https://github.com/heroku/java-getting-started
