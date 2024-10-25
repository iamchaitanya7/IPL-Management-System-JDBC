


# IPL Team and Player Management System

## Overview

This project is a Menu/GUI-driven IPL Team and Player Management System built using Java, MVC Architecture, JDBC API, MySQL Database, and Swing for UI. The system allows users to manage multiple IPL teams and their players, performing operations like adding, deleting, updating, and fetching players by team or player name.

## Features
1. **Team Management**:
    * Add new teams with unique names. 
    * Display all existing teams.
2. **Player Management**:
   * Add players to specific teams with their name, role (batsman, bowler, all-rounder), and age. 
   * Delete players from teams. 
   * Update player details (role and age). 
   * Fetch players by team name. 
   * Search for players across all teams by name.
3. **Data Persistence**:
   * All team and player data is stored in a MySQL database using JDBC, ensuring data is saved between sessions.
4. **User Interface**:
   * Intuitive and easy-to-use graphical user interface built with Swing. 
   * Clear menu-driven navigation for all functionalities.
5. **Error Handling**:
   * Handles various edge cases and provides informative error messages to the user. 
   * Prevents duplicate player entries within a team. 
   * Gracefully handles operations on non-existent teams or players.

## Menu

1. Add a new team
2. Add a player to a team
3. Delete a player from a team
4. Update player details
5. Fetch all players by team name
6. Fetch player details by player name
7. Display all teams and their players
8. Exit


## Technologies Used
   * **Java**: Core programming language. 
   * **MVC Architecture**: For organized code structure and separation of concerns. 
   * **JDBC API**: For database interaction. 
   * **MySQL Database**: For persistent data storage. 
   * **Swing**: For building the graphical user interface.

## How to Run
1. **Prerequisites**:
    * Ensure you have Java Development Kit (JDK) installed. 
    * Install a MySQL database server and create a database for the application. 
    * Include the necessary JDBC driver JAR file in your project's classpath.
2. **Database Configuration**:
    * Update the database connection details in the TeamServiceJDBC class to match your MySQL server configuration.
3. **Compile and Run**:
    * Compile the Java source code. 
    * Run the IPLManagementJDBCApp class to launch the application.

## Future Enhancements

   * Implement user authentication and authorization for secure access.
   * Add more sophisticated search filters for players (e.g., by age range, role).
   * Introduce a reporting module to generate statistics and insights from the data.
   * Explore using an ORM framework like Hibernate for more streamlined database operations.

## Contributing

Contributions are welcome! Please submit a pull request with your changes.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.