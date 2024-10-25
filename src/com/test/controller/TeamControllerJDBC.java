package com.test.controller;

import com.test.entity.PlayerJDBC;
import com.test.service.TeamServiceJDBC;

import java.util.List;
import java.util.Map;

public class TeamControllerJDBC {
    private final TeamServiceJDBC service = new TeamServiceJDBC();

    public String addTeam(String teamName) {
        return service.addTeam(teamName);
    }

    public String addPlayer(String teamName, String playerName, String role, int age) {
        return service.addPlayer(teamName, playerName, role, age);
    }

    public String deletePlayer(String playerName) {
        return service.deletePlayer(playerName);
    }

    public String updatePlayer(String playerName, String newRole, int newAge) {
        return service.updatePlayer(playerName, newRole, newAge);
    }

    public List<PlayerJDBC> fetchPlayersByTeam(String teamName) {
        return service.fetchPlayersByTeam(teamName);
    }

    public PlayerJDBC fetchPlayerByName(String playerName) {
        return service.fetchPlayerByName(playerName);
    }

    public Map<String, List<PlayerJDBC>> getAllTeamsAndPlayers() {
        return service.getAllTeamsAndPlayers();
    }
}
