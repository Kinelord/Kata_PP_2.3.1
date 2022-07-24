package org.example.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;
    @NotEmpty(message = "Last Name should not be empty")
    @Size(min = 2, max = 30, message = "Last Name should be between 2 and 30 characters")
    private String lastName;
    @Min(value = 0, message = "The age must be greater than 0")
    @Max(value = 120, message = "The age must be less than 0")
    private int age;
    @NotEmpty(message = "E-mail should not be empty")
    @Email
    private String email;

    public User(String name, String lastName, int age, String email) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }
}
