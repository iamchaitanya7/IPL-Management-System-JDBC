package com.test.service;

import com.test.dao.TeamDaoJDBC;
import com.test.entity.PlayerJDBC;
import java.util.List;
import java.util.Map;

public class TeamServiceJDBC {
    private final TeamDaoJDBC dao = new TeamDaoJDBC();

    public String addTeam(String teamName) {
        return dao.addTeam(teamName) ? "Team added successfully." : "Failed to add team.";
    }

    public String addPlayer(String teamName, String playerName, String role, int age) {
        PlayerJDBC player = new PlayerJDBC(playerName, role, age);
        return dao.addPlayer(teamName, player) ? "Player added successfully." : "Failed to add player.";
    }

    public String deletePlayer(String playerName) {
        return dao.deletePlayer(playerName) ? "Player deleted successfully." : "Player deletion failed.";
    }

    public String updatePlayer(String playerName, String newRole, int newAge) {
        return dao.updatePlayer(playerName, newRole, newAge) ? "Player updated successfully." : "Player update failed.";
    }

    public List<PlayerJDBC> fetchPlayersByTeam(String teamName) {
        return dao.getPlayersByTeam(teamName);
    }

    public PlayerJDBC fetchPlayerByName(String playerName) {
        return dao.fetchPlayerByName(playerName);
    }

    public Map<String, List<PlayerJDBC>> getAllTeamsAndPlayers() {
        return dao.getAllTeamsAndPlayers();
    }
}
