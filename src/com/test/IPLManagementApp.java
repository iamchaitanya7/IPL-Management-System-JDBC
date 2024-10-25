package com.test;

import com.test.controller.TeamController;
import com.test.entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;

public class IPLManagementApp extends JFrame {
    private TeamController controller = new TeamController();

    public IPLManagementApp() {
        setTitle("IPL Team and Player Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(10, 1));

        JButton addTeamBtn = new JButton("Add a New Team");
        JButton addPlayerBtn = new JButton("Add a Player to a Team");
        JButton deletePlayerBtn = new JButton("Delete a Player from a Team");
        JButton updatePlayerBtn = new JButton("Update Player Details");
        JButton fetchPlayersBtn = new JButton("Fetch Players by Team Name");
        JButton fetchPlayerByNameBtn = new JButton("Fetch Player Details by Name");
        JButton displayAllBtn = new JButton("Display All Teams and Players");
        JButton exitBtn = new JButton("Exit");

        add(addTeamBtn);
        add(addPlayerBtn);
        add(deletePlayerBtn);
        add(updatePlayerBtn);
        add(fetchPlayersBtn);
        add(fetchPlayerByNameBtn);
        add(displayAllBtn);
        add(exitBtn);

        addTeamBtn.addActionListener(e -> addTeam());
        addPlayerBtn.addActionListener(e -> addPlayer());
        deletePlayerBtn.addActionListener(e -> deletePlayer());
        updatePlayerBtn.addActionListener(e -> updatePlayer());
        fetchPlayersBtn.addActionListener(e -> fetchPlayersByTeam());
        fetchPlayerByNameBtn.addActionListener(e -> fetchPlayerByName());
        displayAllBtn.addActionListener(e -> displayAllTeamsAndPlayers());
        exitBtn.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void addTeam() {
        String teamName = JOptionPane.showInputDialog("Enter Team Name:");
        JOptionPane.showMessageDialog(this, controller.addTeam(teamName));
    }

    private void addPlayer() {
        String teamName = JOptionPane.showInputDialog("Enter Team Name:");
        String playerName = JOptionPane.showInputDialog("Enter Player Name:");
        String role = JOptionPane.showInputDialog("Enter Player Role (Batsman, Bowler, All-rounder):");
        int age = Integer.parseInt(JOptionPane.showInputDialog("Enter Player Age:"));
        JOptionPane.showMessageDialog(this, controller.addPlayer(teamName, playerName, role, age));
    }

    private void deletePlayer() {
        String teamName = JOptionPane.showInputDialog("Enter Team Name:");
        String playerName = JOptionPane.showInputDialog("Enter Player Name:");
        JOptionPane.showMessageDialog(this, controller.deletePlayer(teamName, playerName));
    }

    private void updatePlayer() {
        String teamName = JOptionPane.showInputDialog("Enter Team Name:");
        String playerName = JOptionPane.showInputDialog("Enter Player Name:");
        String newRole = JOptionPane.showInputDialog("Enter New Role (Batsman, Bowler, All-rounder):");
        int newAge = Integer.parseInt(JOptionPane.showInputDialog("Enter New Age:"));
        JOptionPane.showMessageDialog(this, controller.updatePlayer(teamName, playerName, newRole, newAge));
    }

    private void fetchPlayersByTeam() {
        String teamName = JOptionPane.showInputDialog("Enter Team Name:");
        List<Player> players = controller.fetchPlayersByTeam(teamName);
        if (players == null || players.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No players found for this team.");
        } else {
            StringBuilder result = new StringBuilder("Players in " + teamName + ":\n");
            for (Player player : players) {
                result.append(player).append("\n");
            }
            JOptionPane.showMessageDialog(this, result.toString());
        }
    }

    private void fetchPlayerByName() {
        String playerName = JOptionPane.showInputDialog("Enter Player Name:");
        Player player = controller.fetchPlayerByName(playerName);
        if (player == null) {
            JOptionPane.showMessageDialog(this, "Player not found.");
        } else {
            JOptionPane.showMessageDialog(this, "Player Details: " + player);
        }
    }

    private void displayAllTeamsAndPlayers() {
        Map<String, List<Player>> teams = controller.getAllTeamsAndPlayers();
        if (teams.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No teams and players available.");
        } else {
            StringBuilder result = new StringBuilder("Teams and Players:\n");
            for (Map.Entry<String, List<Player>> entry : teams.entrySet()) {
                result.append("Team: ").append(entry.getKey()).append("\n");
                for (Player player : entry.getValue()) {
                    result.append("   - ").append(player).append("\n");
                }
            }
            JOptionPane.showMessageDialog(this, result.toString());
        }
    }

    public static void main(String[] args) {
        new IPLManagementApp();
    }
}

