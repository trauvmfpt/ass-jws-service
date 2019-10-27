package dto;

import entity.*;
import util.ObjectUtil;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserDTO {
    private int id;
    private String name;
    private int age;
    private int gender;
    private String address;
    private String password;
    private String email;
    private String username;
    private String token;
    private List<String> role;
    private String salt;
    private int status;

    public UserDTO(User user) {
        ObjectUtil.cloneObject(this,user);
        for (Role r:user.getRoleSet()
             ) {
            if (this.role == null){
                this.role = new ArrayList<>();
            }
            this.role.add(r.getName());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
