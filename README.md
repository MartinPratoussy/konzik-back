# KONZIK

Service path:
 - concert service: CONCERT-SERVICE/api/concert
	- all concerts: 	/all
	- find one: 		/find/{id}
	- display add form: 	/add
	- delete one: 		/delete/{id}
	- update one: 		/update{id}
 - auth service: AUTH-SERVICE/api/auth
	- sign-in:		/signin
```json
{
	"username" : "<username>",
	"email": "<email>",
	"role": ["<username>", "<role_name>"],
	"password": "<password"
}
```
	- sign-on:		/signup
```json
{
	"username" : "<username>",
	"password": "<password>"
}
```
	- all users: 		/all
	- find one: 		/find/{username}
	- update one: 		/update/{username}
	- delete one:		/find/{username}
	- verify: 		/verification-link/{userId}
	- reset password: 	/reset-password/{userId}