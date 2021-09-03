# library
* [Project Description](#project-description)
* [Project Features](#project-features)
* [DB Scheme](#db-scheme)
* [Roles](#roles)
* [Guest's scope](#guests-scope)
* [Reader's scope](#readers-scope)
* [Librarian's scope](#librarians-scope)
* [Admin's scope](#admins-scope)
* [Authorized user's scope](#authorized-users-scope)
* [Additional Info](#additional-info)

# Project Description
Library Management System (Java EE, Bootstrap, Tomcat, Maven, JUnit, javadoc)

# Project Features
* Design patterns: MVC, Layered architecture, DAO, Factory, Command, Singleton, Proxy
* Localization: en_EN, ru_RU
* 4 user's roles
* XSS protection with custom tag
* Custom connection pool and proxy connections
* Double data validation

# DB Scheme
![bd](https://user-images.githubusercontent.com/62715846/131978786-30217d47-9bb1-4fb7-9f56-ff8b47563cc2.png)

# Roles
* Guest
* Reader
* Librarian
* Admin

# Guest's scope
* Register
* Login
* Change language
* View books

# Reader's scope
* Create book request to reading room/for subscription
* View reader own requests
* View books pdf files
* Create report (contact administration)

# Librarian's scope
* Manage books (CRUD)
* Manage book requests
* View user book requests
* View user profiles
* Create report (contact administration)

# Admin's scope
* View all users
* View user profiles
* Manage users (activate/deactivate, change role)
* Manage user reports (create response)

# Authorized user's scope
* Update profile information
* Change password
* Change avatar
* Deactivate account
* Send report(communication with admin via forms and email)

# Additional Info
* Default users password: `password`
* Don't forget to enter email address and password in the properties file for correct email sending
 ```java
mail.user.name=yourEmail
mail.user.password=yourPassword
