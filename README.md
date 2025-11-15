Company Registration Web Application 
A simple and fully functional Java Servlet–based web application that allows users to submit 
their registration details, which are stored securely in a MySQL database using JDBC. 
The project is deployed on Apache Tomcat and follows the MVC architecture. 
Features 
 User registration form 
 Password & Confirm Password validation 
 Duplicate email check 
 Secure database insertion using PreparedStatement 
 Centralized DB connection handling using DBUtil 
 Clean MVC architecture 
 Fully compatible with Tomcat 9/10 (Jakarta Servlet API) 
Project Structure 
companydb/ 
│ 
├── src/main/java/ 
│   ├── com/example/servlet/ 
│   │   └── SubmitServlet.java 
│   └── com/example/util/ 
│       └── DBUtil.java 
│ 
├── src/main/webapp/ 
│   ├── index.html 
│   └── WEB-INF/ 
│       └── web.xml 
│ 
└── pom.xml (if using Maven) 
Technologies Used 
 Java Servlets (Jakarta EE) 
 HTML5, CSS3, JavaScript 
 MySQL Database 
 JDBC 
 Apache Tomcat 9/10 
 MVC Architecture 
How It Works 
1. User fills out the form on index.html. 
2. Form submits a POST request to /SubmitServlet. 
3. The servlet: 
o Validates passwords 
o Checks if the email already exists 
o Inserts the user data into the MySQL database 
4. User gets success or error message. 
Database Setup 
Run the following SQL commands: 
CREATE DATABASE companydb; 
USE companydb; 
 
CREATE TABLE users ( 
  id INT AUTO_INCREMENT PRIMARY KEY, 
  name VARCHAR(100) NOT NULL, 
  email VARCHAR(150) NOT NULL UNIQUE, 
  password VARCHAR(255) NOT NULL, 
  dob DATE, 
  mobile VARCHAR(20), 
  country VARCHAR(100), 
  gender VARCHAR(10), 
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP 
); 
 
 Configuration 
 DBUtil.java 
Set your MySQL username & password: 
private static final String URL = 
"jdbc:mysql://localhost:3306/companydb?useSSL=false&serverTimezone=UTC"; 
private static final String USER = "root"; 
private static final String PASS = "root"; 
 Web.xml Mapping 
<servlet> 
    <servlet-name>SubmitServlet</servlet-name> 
    <servlet-class>com.example.servlet.SubmitServlet</servlet-class> 
</servlet> 
 
<servlet-mapping> 
    <servlet-name>SubmitServlet</servlet-name> 
    <url-pattern>/SubmitServlet</url-pattern> 
</servlet-mapping> 
 
 How to Run the Project 
1. Install Tomcat (9.x or 10.x) 
2. Import project into Eclipse/IntelliJ 
3. Add MySQL Connector JAR to WEB-INF/lib 
4. Start Tomcat server 
5. Open browser and visit: 
http://localhost:8080/companydb/ 
 
 Screenshots 
(Add screenshots here if you want) 
Example: 
![Registration Form Screenshot](screenshots/form.png) 
 
 Security 
 Uses PreparedStatement to prevent SQL Injection 
 SHA-256 password hashing method included (can be enabled) 
 Server-side and client-side validation 
 
 Future Enhancements 
 Add Login & Authentication 
 Add JSP pages for success/error 
 Admin dashboard to view users 
 Email OTP Verification 
 Password encryption on database insert 
 
 Contributing 
Pull requests are welcome. 
For major changes, open an issue first to discuss what you would like to improve. 
 
License 
This project is open-source and free to use.
