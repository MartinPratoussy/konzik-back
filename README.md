# KONZIK

Service path:
- concert service: CONCERT-SERVICE/api/concert
  - GET requests:
    - get all concerts: /all
    - get one specific concert by ID: /all/find/{id}
  - DELETE request:
    - delete one specific concert by ID: /all/delete/{id}
  - POST requests:
    - add a new concert: /add
	```json
    {
      "date": "<date>",
      "artist": "<artist>",
      "genre": "<genre>",
      "location": "<location>",
      "city": "<city>",
      "country": "<country>"
    }
  	```
- auth service: AUTH-SERVICE/api/auth
  - POST requests:
    - register a new user: /signup
    ```json
    {
     "username" : "<username>",
      "email": "<email>",
      "role": ["<username>", "<role_name>"],
      "password": "<password"
    }
    ```
    - login with an existing user: /signin
    ```json
    {
      "username" : "<username>",
      "password": "<password>"
    }
    ```
