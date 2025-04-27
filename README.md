# SpringBoot_Crud_RestAPI_Pagination
This project is a Spring Boot REST API that manages Products and Categories with a one-to-many relationship. It includes full CRUD operations for both entities, along with server-side pagination for efficient data handling. The project uses Spring Data JPA and Hibernate for database operations.
use following RestAPI:

1) Category CRUD operation.
Below are the APIs required to be developed :
1      http://localhost:8080/api/categories?page=3         GET all the categories
2      http://localhost:8080/api/categories                        POST - create a new category
3      http://localhost:8080/api/categories/{di}                GET category by Id
4      http://localhost:8080/api/categories/{di}                PUT - update category by id
5      http://localhost:8080/api/categories/{di}                DELETE - Delete category by id

2) Product CRUD operation.
 
Below are the APIs required to be developed :

 1     http://localhost:8080/api/products?page=2          GET all the products
 2     http://localhost:8080/api/products                         POST - create a new product
 3     http://localhost:8080/api/products/{di}                 GET product by Id
 4     http://localhost:8080/api/products/{di}                 PUT - update product by id
 5     http://localhost:8080/api/products/{di}                 DELETE - Delete product by id
 
