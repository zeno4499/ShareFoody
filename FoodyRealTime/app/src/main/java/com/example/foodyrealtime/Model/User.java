package com.example.foodyrealtime.Model;

public class User {
    String name = "pham hong son";
    String age = "21";

    public User() {
    }

    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getInforUser() {
        return name + " - " + age;
    }

}
