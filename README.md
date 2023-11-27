## API (Back-End)
*Description:*
- This project is a Backend API for a flying company's management system.
- It comes with a CRUD for users, tickets, cities and flights until the tag v1.0.2 and has 
more customizable elements under development.
- Up to the version of tag v1.0.2 the ticket's and flight's prices are calculated at a fixed rate.
More flexible price calculations are to become available in later versions.
---
- *Tecnologias*
    - Framework
        - Spring Boot (3.1.4)
    - Linguagem
        - Kotlin (1.8.22)
    - Database
        - MySql using DB4free.net () as a free MySql testing database service. (as of tag v1.0.2)
- *Installation*
  - GIT clone https://github.com/gcerbaro/SistemaVoosKotlin.git or 
  download one of the .zip files from any of the tags at https://github.com/gcerbaro/SistemaVoosKotlin/tags, it is recommended to download the latest version, at the top of the list. 
  - Run SistemadeVoosKotlin.kt
---
## EndPoints
*Note*
-All endpoints have free access, that means authentication nor login tokens neither admin role are
required to use any of the API's functionalities (until v1.0.2).
-URL
 -Localhost:8080
-PUBLIC
 -URL/login
  -POST:/

 -URL/register
  -POST:/

 -URL/city
  -POST:/
  -GET/
  -GET/{id}
  -PUT/{id}
  -DELETE/{id}

 -URL/usuarios
  -POST:/
  -GET/
  -GET/{id}
  -PUT/{id}
  -DELETE/{id}

 -URL/voos
  -POST:/
  -GET/
  -GET/{id}
  -PUT/{id}
  -DELETE/{id}

 -URL/ticket
  -POST:/
  -GET/
  -GET/{id}
  -PUT/{id}
  -DELETE/{id}




    






