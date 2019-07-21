package com.app.android.june.alcmeetup20.java;

public class user {
    private String Name;
    private String Phone;
    private String Email;

    public user () {
    }

    public user(String name, String phone, String email) {
        Name = name;
        Phone = phone;
        Email = email;
    }



    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }


}
