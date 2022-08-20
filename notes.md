# Learning Spring Boot
- The quickest way to start a project is to go to *spring initializer* website and start a new project, the download and unzip the folder.
- Open it with IntelliJ and wait for dependencies to download, then run the app

# Making a Simple API with Spring Boot
- Go to the main Java File(DemoApplication.java):
  - Above the class name add __@RestController__ to tell springBoot that this is a REST controller(Makes the class to serve Rest endpoints )
  - Then we need to add our restfull endpoints.
    - To add a restfull endpoint, you create a method and on top of the method you will add an annotation to either GET, PUT, DELETE, POST, etc.. eg __GetMapping__
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
	public String hello(){
		return "hello world";
	}
}
```
- To return a json instead of a string like above, you can use __Lists__, and the data will be in JSON format
```java
import java.util.List;
@GetMapping
public List<String> hello(){
    return List.of("hello", "world");
}
```
# Structuring Project
- We can structure our project by creating packages for different functionalities.
- Lets learn it by building a simple Student Management System(CRUD)

# Student CRUD
- Create a student package in the main project package(com.example.demo)
- Then we create a class for our model(Student.java)
  - Shortcut to create constructor(__ctrl + n__, shows all shortcuts, __ctrl + shift + a__ shows actions tab where you find constructor)
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
  public List<Student> hello(){
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

