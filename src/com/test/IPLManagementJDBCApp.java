package com.test;

import com.test.controller.TeamControllerJDBC;
import com.test.entity.PlayerJDBC;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class IPLManagementJDBCApp extends JFrame {
    private TeamControllerJDBC controller = new TeamControllerJDBC();

    //Color palette
    private final Color primaryColor = new Color(0x2C3E50);
    private final Color secondaryColor = new Color(0xECF0F1);
    private final Color accentColor = new Color(0xFFA654);

    public IPLManagementJDBCApp() {
        setTitle("IPL Team and Player Management System");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //Main panel setup
        JPanel panel = new JPanel();
        panel.setBackground(secondaryColor);
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // UI Buttons setup
        JButton addTeamBtn = createButton("Add a New Team");
        JButton addPlayerBtn = createButton("Add a Player to a Team");
        JButton deletePlayerBtn = createButton("Delete a Player from a Team");
        JButton updatePlayerBtn = createButton("Update Player Details");
        JButton fetchPlayersBtn = createButton("Fetch Players by Team Name");
        JButton fetchPlayerByNameBtn = createButton("Fetch Player by Player Name");
        JButton displayAllBtn = createButton("Display All Teams and Players");
        JButton exitBtn = createButton("Exit");

        //Event Listeners
        addTeamBtn.addActionListener(e -> addTeam());
        addPlayerBtn.addActionListener(e -> addPlayer());
        deletePlayerBtn.addActionListener(e -> deletePlayer());
        updatePlayerBtn.addActionListener(e -> updatePlayer());
        fetchPlayersBtn.addActionListener(e -> fetchPlayersByTeam());
        fetchPlayerByNameBtn.addActionListener(e -> fetchPlayerByName());
        displayAllBtn.addActionListener(e -> displayAllTeamsAndPlayers());
        exitBtn.addActionListener(e -> System.exit(0));

        //Add buttons to the panel
        panel.add(addTeamBtn);
        panel.add(addPlayerBtn);
        panel.add(deletePlayerBtn);
        panel.add(updatePlayerBtn);
        panel.add(fetchPlayersBtn);
        panel.add(fetchPlayerByNameBtn);
        panel.add(displayAllBtn);
        panel.add(exitBtn);

        //Adding the panel to the frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        add(panel, gbc);
        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN , 16));
        button.setBackground(accentColor);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(200, 40));

        //Adding hover effect.
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.YELLOW);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(accentColor);
            }
        });

        return button;
    }

    private void addTeam() {
        String teamName = JOptionPane.showInputDialog("Enter Team Name:");
        JOptionPane.showMessageDialog(this, controller.addTeam(teamName));
    }

    private void addPlayer() {
        String teamName = JOptionPane.showInputDialog("Enter Team Name:");
        String playerName = JOptionPane.showInputDialog("Enter Player Name:");
        String role = JOptionPane.showInputDialog("Enter Player Role:");
        int age = Integer.parseInt(JOptionPane.showInputDialog("Enter Player Age:"));
        JOptionPane.showMessageDialog(this, controller.addPlayer(teamName, playerName, role, age));
    }

    private void deletePlayer() {
        String playerName = JOptionPane.showInputDialog("Enter Player Name:");
        JOptionPane.showMessageDialog(this, controller.deletePlayer(playerName));
    }

    private void updatePlayer() {
        String playerName = JOptionPane.showInputDialog("Enter Player Name:");
        String newRole = JOptionPane.showInputDialog("Enter New Role:");
        int newAge = Integer.parseInt(JOptionPane.showInputDialog("Enter New Age:"));
        JOptionPane.showMessageDialog(this, controller.updatePlayer(playerName, newRole, newAge));
    }

    private void fetchPlayerByName() {
        String playerName = JOptionPane.showInputDialog("Enter Player Name:");
        PlayerJDBC player = controller.fetchPlayerByName(playerName);
        if (player == null) {
            JOptionPane.showMessageDialog(this, "Player not found.");
        } else {
            JOptionPane.showMessageDialog(this, "Player Details:\n" + player);
        }
    }

    private void fetchPlayersByTeam() {
        String teamName = JOptionPane.showInputDialog("Enter Team Name:");
        List<PlayerJDBC> players = controller.fetchPlayersByTeam(teamName);
        if (players == null || players.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No players found for this team.");
        } else {
            StringBuilder result = new StringBuilder("Players in " + teamName + ":\n");
            for (PlayerJDBC player : players) {
                result.append(player).append("\n");
            }
            JOptionPane.showMessageDialog(this, result.toString());
        }
    }

    private void displayAllTeamsAndPlayers() {
        Map<String, List<PlayerJDBC>> teams = controller.getAllTeamsAndPlayers();
        if (teams.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No teams and players available.");
        } else {
            StringBuilder result = new StringBuilder("Teams and Players:\n");
            for (Map.Entry<String, List<PlayerJDBC>> entry : teams.entrySet()) {
                result.append("Team: ").append(entry.getKey()).append("\n");
                for (PlayerJDBC player : entry.getValue()) {
                    result.append("   - ").append(player).append("\n");
                }
            }
            JOptionPane.showMessageDialog(this, result.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new IPLManagementJDBCApp());
    }
}
