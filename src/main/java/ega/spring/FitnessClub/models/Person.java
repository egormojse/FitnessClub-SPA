package ega.spring.FitnessClub.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Table(name="person")
@Getter
@Setter
public class Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 123456789L;


    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    @Size(min=2, max=50, message = "Имя должно быть больше 2 и меньше 50 символов")
    @NotEmpty(message = "Имя не должно быть пустым")
    private String first_name;

    @Column(name = "last_name")
    @Size(min=2, max=50, message = "Фамилия должна быть больше 2 и меньше 50 символов")
    @NotEmpty(message = "Фамилия не должна быть пустым")
    private String last_name;

    @Column(name = "bd_date")
    @NotNull(message = "Введите дату рождения")
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private Date bd_date;

    @Column(name = "email", unique = true)
    @Email(message = "Неверный формат электронной почты")
    private String email;

    @Column(name = "role")
    private String role;

    @Column(name = "password")
    private String password;

    @Column(name = "deleted")
    private boolean deleted;


    public Person() {}

    public Person(String first_name, String last_name, Date bd_date, String email,
                  String role, String password) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.bd_date = bd_date;
        this.email = email;
        this.role = role;
        this.password = password;
    }
}