package com.test.dao;

import com.test.entity.PlayerJDBC;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamDaoJDBC {
    private final String url = "jdbc:mysql://localhost:3306/ipl_management";
    private final String user = "root";
    private final String password = "Chaitanya@123";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public boolean addTeam(String teamName) {
        String query = "INSERT INTO teams (team_name) VALUES (?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, teamName);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addPlayer(String teamName, PlayerJDBC player) {
        String query = "INSERT INTO players (team_id, name, role, age) VALUES ((SELECT team_id FROM teams WHERE team_name = ?), ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, teamName);
            stmt.setString(2, player.getName());
            stmt.setString(3, player.getRole());
            stmt.setInt(4, player.getAge());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePlayer(String playerName) {
        String query = "DELETE FROM players WHERE name = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, playerName);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePlayer(String playerName, String newRole, int newAge) {
        String query = "UPDATE players SET role = ?, age = ? WHERE name = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newRole);
            stmt.setInt(2, newAge);
            stmt.setString(3, playerName);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<PlayerJDBC> getPlayersByTeam(String teamName) {
        List<PlayerJDBC> players = new ArrayList<>();
        String query = "SELECT p.name, p.role, p.age FROM players p JOIN teams t ON p.team_id = t.team_id WHERE t.team_name = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, teamName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                players.add(new PlayerJDBC(rs.getString("name"), rs.getString("role"), rs.getInt("age")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    public PlayerJDBC fetchPlayerByName(String playerName) {
        String query = "SELECT t.team_name, p.name, p.role, p.age " +
                "FROM players p " +
                "JOIN teams t ON p.team_id = t.team_id " +
                "WHERE p.name = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, playerName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new PlayerJDBC(
                        rs.getString("name"),
                        rs.getString("role"),
                        rs.getInt("age"),
                        rs.getString("team_name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, List<PlayerJDBC>> getAllTeamsAndPlayers() {
        Map<String, List<PlayerJDBC>> teams = new HashMap<>();
        String query = "SELECT t.team_name, p.name, p.role, p.age FROM teams t LEFT JOIN players p ON t.team_id = p.team_id";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String teamName = rs.getString("team_name");
                PlayerJDBC player = new PlayerJDBC(rs.getString("name"), rs.getString("role"), rs.getInt("age"));
                teams.computeIfAbsent(teamName, k -> new ArrayList<>()).add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teams;
    }
}
