import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DatabaseConnector {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/XOGAME";
    private static final String DB_USERNAME = "van";
    private static final String DB_PASSWORD = "theeramat";

    private Connection connection;

    public DatabaseConnector() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
           // System.out.println("เชื่อมต่อกับฐานข้อมูลสำเร็จ");
        } catch (SQLException e) {
           // System.out.println("ไม่สามารถเชื่อมต่อกับฐานข้อมูล: " + e.getMessage());
        }
    }

public int insertData(String winner) {
    int gameId = -1;
    try {
        String query = "INSERT INTO game_result (winner) VALUES (?)";
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, winner);
        statement.executeUpdate();

        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            gameId = generatedKeys.getInt(1);
        }

      //  System.out.println("บันทึกข้อมูลลงในฐานข้อมูลสำเร็จ");
    } catch (SQLException e) {
      //  System.out.println("ไม่สามารถบันทึกข้อมูลลงในฐานข้อมูล: " + e.getMessage());
    }
    return gameId;
}

public void insertDataGameReplay(List<String> positionsList, List<String> playersList, int boardSize, String winner) {
    try {
        int gameId = this.insertData(winner);

        for (int i = 0; i < positionsList.size(); i++) {
            String query = "INSERT INTO game_move (id_game, position, player, size) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            String position = positionsList.get(i);
            String player = playersList.get(i);
            int size = boardSize;

            statement.setInt(1, gameId);
            statement.setString(2, position);
            statement.setString(3, player);
            statement.setInt(4, size);
            statement.executeUpdate();
        }
    } catch (SQLException e) {
        //System.out.println("ไม่สามารถบันทึกข้อมูลลงในฐานข้อมูล: " + e.getMessage());
    }
}
}


