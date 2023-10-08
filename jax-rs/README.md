Simple REST application that shows a list of users and an option to signup or login

- unauthenticated users can see the users created
- unautenticated users can create a new user
- uathenticated users can edit/delete themselfs
- admin's can edit/delete everybody

The following resources exists

METHOD | URL               | SECURITY ROLES | FUNCTION    | RESPONSE                                 |
------------------------------------------------------------------------------------------------------
GET    | /users/{username} | NONE           | Show user   | 200(OK), 404(Not Found)      
PUT    | /users/{username} | USER, ADMIN    | Update user | 200(OK), 409(Conflict)
DELETE | /users/{username} | USER, ADMIN    | Delete user | 200(OK), 401(Unauthorized), 404(Not Found)
POST   | /users            | NONE           | Create user | 201(Created), 409(Conflict) 
GET    | /users            | ADMIN          | List users  | 200(OK)

Also demo's how to run integration tests against the REST service

# generate JAXB XML beans by running: "mvn generate-sources"