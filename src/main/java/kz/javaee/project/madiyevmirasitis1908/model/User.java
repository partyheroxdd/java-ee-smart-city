package kz.javaee.project.madiyevmirasitis1908.model;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "userr")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String login;

    private String password;

    private String role;

}