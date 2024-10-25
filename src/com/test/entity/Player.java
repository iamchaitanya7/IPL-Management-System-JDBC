package com.test.entity;

public class Player {
    private String name;
    private String role;
    private int age;

    public Player(String name, String role, int age) {
        this.name = name;
        this.role = role;
        this.age = age;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return String.format("Name: %s, Role: %s, Age: %d", name, role, age);
    }
}
