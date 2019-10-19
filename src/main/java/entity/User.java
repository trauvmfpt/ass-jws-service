package entity;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    private int gender;
    private String address;
    private int role;
    private String password;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String token;
    private String salt;
}
