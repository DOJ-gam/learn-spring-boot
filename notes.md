# Learning Spring Boot

- The quickest way to start a project is to go to *spring initializer* website and start a new project, the download and
  unzip the folder.
- Open it with IntelliJ and wait for dependencies to download, then run the app

# IntelliJ Shortcuts

- Shortcut to create constructor(__ctrl + n__, shows all shortcuts, __ctrl + shift + a__ shows actions tab where you
  find constructor)
- To Clean-up unused imports (__ctrl + shift + o__)

# Making a Simple API with Spring Boot

- Go to the main Java File(DemoApplication.java):
    - Above the class name add __@RestController__ to tell springBoot that this is a REST controller(Makes the class to
      serve Rest endpoints )
    - Then we need to add our restfull endpoints.
        - To add a restfull endpoint, you create a method and on top of the method you will add an annotation to either
          GET, PUT, DELETE, POST, etc.. eg __GetMapping__
- __NOTE__ that all annotations have to be imported

```java
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping
    public String hello() {
        return "hello world";
    }
}
```

- To return a json instead of a string like above, you can use __Lists__, and the data will be in JSON format

```java
import java.util.List;

@GetMapping
public List<String> hello(){
        return List.of("hello","world");
        }
```

# Structuring Project

- We can structure our project by creating packages for different functionalities.
- Lets learn it by building a simple Student Management System(CRUD)

# Student CRUD

- Create a student package in the main project package(com.example.demo)
- Then we create a class for our model(Student.java)
- We can use the shortcuts to generate constructors, getters and setters, toString methods, etc...
- Then we need to use it in our main Java file(DemoApplication.java)

```java
// Student.java
package com.example.demo.student;

import java.time.LocalDate;

public class Student {
    private Long id;
    private String name;
    private String email;
    private LocalDate dob;
    private Integer age;

    public Student() {

    }

    public Student(Long id, String name, String email, LocalDate dob, Integer age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.age = age;
    }

    public Student(String name, String email, LocalDate dob, Integer age) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}

```

```java
// DemoApplication.java
package com.example.demo;

import com.example.demo.student.Student; //studentModel
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping
    public List<Student> hello() {
        return List.of(
                new Student(
                        1L, //because it is Long
                        "Omar Jeng",
                        "doj@gmail.com",
                        LocalDate.of(2020, Month.MARCH, 22),
                        13
                )
                      );
    }
}

```

## API Layer

- Now we can structure our API Properly using Models, Route and Controllers, Business Logics, etc
- The structure should be like: *From the client to the Api Layer.*
    - The API Layer should talk to the __Service Layer__ => Hold the Business Logic of our API,
    - and the service layer should talk to the Data Access Layer,
    - then Data Access Layer will reach out to the Database.
- We first create a StudentController class,
- We then Create a StudentService class,
    - If we create a student Service, We should make use of dependency injection to avoid direction invoking a class or
      object

# Dependency Injection

- We have to add the annotation __@Autowired__ on top of the method(constructor) you want to instantiate the service
  from
- The Service class should also have the annotation __@Component__ to tell spring that this class can be instantiated
  without using the _new_ keyword.
    - To be more specific, you can use the __@Service__ annotation instead, which is more specific.

# Connecting to Database

- The database connection should be created in the _src/main/resources/application.properties_
- Then you add the database dependencies in the __pom.xml__=> It's where the applications dependencies are saved
    - After that you will need to __reload the project__=> right-click on the pom.xml, click maven, then click reload
      project

# Accessing Database

- By convention and following the convention of _spring-data-jpa_, we create a Repository file as an interface
- the interface will extend the JpaRepository, which is generic, and takes in two arguments:
    - The Type(Model) that we want the repository to work upon,
    - The type(data type) of the id of the Model we want to work on(eg: Long)
- Then we also add the annotation __@Repository__ to the Interface.
- Then we can use it in our _ServiceFile.java_ by adding __@Autowired__ to the constructor
- By setting that up we can have access to lots of methods provided by spring-data-jpa, like: __findAll()__, etc

# Adding Data to Database

- We can start by creating a __StudentConfig.java_ class
- Annotate it with __@Configuration__

# Adding Virtual Columns(@Transient)

- To add a virtual column(Column that will not be stored in the database), we use the _@Transient_ annotation on top of
  the column

# POST Request (@PostMapping)

- We will create a void method to create our new post,
- The method will in the Student as a Parameter,
- But inorder for us to access the data the user is sending, we will have to map the request body to the student
  parameter using _@RequestBody_ annotation.

# Sending Errors Along with response

- To allow error message to be sent along with request, we can set it up in _application.properties_ file.

# Updating the Database(@DeleteMapping(path="studentId}))

- To delete we use the _@DeleteMapping_ annotation, which will accept a dynamic variable in its path called __
  studentId__,
- You can access the __studentId__ from the delete method by mapping the studentId(Long) with an annotation called __
  PathVariable("studentId)
- Then call your delete method created in the services, and pass in the ID as a parameter