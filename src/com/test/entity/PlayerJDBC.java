package com.test.entity;

public class PlayerJDBC {
    private String name;
    private String role;
    private int age;
    private String teamName;

    public PlayerJDBC(String name, String role, int age) {
        this.name = name;
        this.role = role;
        this.age = age;
    }

    public PlayerJDBC(String name, String role, int age, String teamName) {
        this.name = name;
        this.role = role;
        this.age = age;
        this.teamName = teamName;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }

    @Override
    public String toString() {
        return "Player: [" +
                "Name : '" + name + '\'' +
                ", Role : '" + role + '\'' +
                ", Age : " + age +
                ", TeamName : '" + teamName + '\'' +
                ']';
    }
}
