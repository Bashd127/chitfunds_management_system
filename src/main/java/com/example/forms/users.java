package com.example.forms;

import jakarta.persistence.*;

@Entity
public class users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  

    @Column(unique = true)  // Ensure uniqueness
    private int memberid;

    private String name;
    private String phonenumber;
    private String mail;
    private String password;

    public users() {
    }

    public void setId(int id) {
        this.id = id;
        if (this.memberid == 0) {  // ✅ Set memberid ONLY if not already assigned
            this.memberid = id; 
        }
    }

    public int getId() {
        return id;
    }

    public int getMemberid() {
        return memberid;
    }

    public void setMemberid(int memberid) {  
        this.memberid = memberid;  
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
