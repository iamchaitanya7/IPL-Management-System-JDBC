package com.test.service;

import com.test.dao.TeamDao;
import com.test.entity.Player;
import java.util.List;
import java.util.Map;

public class TeamService {
    private TeamDao dao = new TeamDao ();

    public String addTeam(String teamName) {
        return dao.addTeam(teamName) ? "Team added successfully." : "Team already exists!";
    }

    public String addPlayer(String teamName, String playerName, String role, int age) {
        if (!dao.getAllTeams().containsKey(teamName)) {
            return "Team does not exist! Please add the team first.";
        }
        Player newPlayer = new Player(playerName, role, age);
        dao.addPlayer(teamName, newPlayer);
        return "Player added successfully.";
    }

    public String deletePlayer(String teamName, String playerName) {
        return dao.deletePlayer(teamName, playerName) ? "Player removed successfully." : "Player not found in the team.";
    }

    public String updatePlayer(String teamName, String playerName, String newRole, int newAge) {
        Player player = dao.findPlayerByName(playerName);
        if (player != null && dao.getAllTeams().containsKey(teamName)) {
            player.setRole(newRole);
            player.setAge(newAge);
            return "Player details updated successfully.";
        }
        return "Player not found in the team.";
    }

    public List<Player> fetchPlayersByTeam(String teamName) {
        return dao.getPlayersByTeam(teamName);
    }

    public Player fetchPlayerByName(String playerName) {
        return dao.findPlayerByName(playerName);
    }

    public Map<String, List<Player>> getAllTeamsAndPlayers() {
        return dao.getAllTeams();
    }
}

