package com.test.controller;

import com.test.entity.Player;
import com.test.service.TeamService;

import java.util.List;
import java.util.Map;

public class TeamController {
    private TeamService service = new TeamService ( );

    public String addTeam (String teamName) {
        return service.addTeam (teamName);
    }

    public String addPlayer (String teamName, String playerName, String role, int age) {
        return service.addPlayer (teamName, playerName, role, age);
    }

    public String deletePlayer (String teamName, String playerName) {
        return service.deletePlayer (teamName, playerName);
    }

    public String updatePlayer (String teamName, String playerName, String newRole, int newAge) {
        return service.updatePlayer (teamName, playerName, newRole, newAge);
    }

    public List<Player> fetchPlayersByTeam (String teamName) {
        return service.fetchPlayersByTeam (teamName);
    }

    public Player fetchPlayerByName (String playerName) {
        return service.fetchPlayerByName (playerName);
    }

    public Map<String, List<Player>> getAllTeamsAndPlayers () {
        return service.getAllTeamsAndPlayers ( );
    }
}
