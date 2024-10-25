package com.test.dao;

import com.test.entity.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamDao {
    private Map <String, List<Player>> teams = new HashMap<> ();

    public boolean addTeam(String teamName) {
        if (teams.containsKey(teamName)) {
            return false;
        }
        teams.put(teamName, new ArrayList<> ());
        return true;
    }

    public boolean addPlayer(String teamName, Player player) {
        List<Player> players = teams.get(teamName);
        if (players == null) {
            return false;
        }
        players.add(player);
        return true;
    }

    public boolean deletePlayer(String teamName, String playerName) {
        List<Player> players = teams.get(teamName);
        if (players == null) return false;

        return players.removeIf(player -> player.getName().equalsIgnoreCase(playerName));
    }

    public Player findPlayerByName(String playerName) {
        for (List<Player> players : teams.values()) {
            for (Player player : players) {
                if (player.getName().equalsIgnoreCase(playerName)) {
                    return player;
                }
            }
        }
        return null;
    }

    public List<Player> getPlayersByTeam(String teamName) {
        return teams.getOrDefault(teamName, null);
    }

    public Map<String, List<Player>> getAllTeams() {
        return teams;
    }
}
