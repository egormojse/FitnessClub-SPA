package ega.spring.FitnessClub.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "spa_employees")
@Getter
@Setter
public class SpaEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    @Size(min=2, max=50, message = "Имя должно быть больше 2 и меньше 50 символов")
    @NotEmpty(message = "Имя не должно быть пустым")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    @Email(message = "Неверный формат электронной почты")
    private String email;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "experience")
    private int experience;

    @Column(name = "bio")
    private String bio;

    @Column(name = "role")
    private String role;

}
