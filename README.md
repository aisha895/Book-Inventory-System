# Book Inventory System

A simple Java-based inventory management system for books, built with a Swing-based GUI. This application allows users to add, view, filter, update, and delete books in an inventory system. It follows the MVC (Model-View-Controller) architecture and uses a DAO (Data Access Object) pattern for database operations.


## **Features:**

1- Add New Book: Input details like title, author, genre, publication date, and ISBN to add a book.

2- View All Books: Display all books in the inventory.

3- Filter Books: Search for books by title, author, genre, or ID.

4- Update Book: Modify details of an existing book.

5- Export CSV: Download all the books from the inventory.

6- User-Friendly Interface: Intuitive Swing-based GUI.


## **Technologies Used:**

Programming Language: Java

GUI Framework: Java Swing

Database: MySQL

IDE:  Eclipse 

Architecture: MVC (Model-View-Controller)

## **Design Decisions** 

### *- Layered Architecture:*

The system was designed with a three-layered architecture, separating:

Frontend (Presentation Layer): Handles the user interface using Java Swing.

Business Logic (Logic Layer): Implements CRUD methods and SQL operations.

Database (Data Layer): Stores book details persistently in a MySQL database.
Frontend Design:

### *- Business Logic:*

CRUD (Create, Read, Update, Delete) operations were implemented in the BookDAOImplementation class to handle all data manipulation tasks.
SQL operations were used to interact with the database efficiently.

### *- Database Design:*

A MySQL database with a Books table was designed to store book details such as Title, Author, Genre, Publication Date, and ISBN.

### *- Database Interaction:*

JDBC (Java Database Connectivity) was used for interacting with the MySQL database. This approach ensures robust and secure communication between the application and the database
