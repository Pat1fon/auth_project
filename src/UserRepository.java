import java.sql.*;

public class UserRepository {
    private final String SQL_URL;

    public UserRepository(String url) {
        this.SQL_URL = url;
    }

    public boolean checkIfExists(String login) {
        String sqlExists = "SELECT EXISTS (SELECT 1 FROM users WHERE login = ?)";

        try (Connection connection = DriverManager.getConnection(SQL_URL)) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlExists)) {
                preparedStatement.setString(1, login);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getBoolean(1);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return false;
    }

    public void insertUser(Profile userProfile) {

        try (Connection connection = DriverManager.getConnection(SQL_URL)) {
            String sqlInsert = "INSERT INTO users (name, login, password) VALUES (?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert)) {
                preparedStatement.setString(1, userProfile.getName());
                preparedStatement.setString(2, userProfile.getLogin());
                preparedStatement.setString(3, userProfile.getPassword());

                preparedStatement.executeUpdate();

                System.out.println("Добавлен пользователь" + userProfile);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Profile loginUser(String login, String password)
    {
        String sqlSelect = "SELECT id, name, login, password FROM users WHERE login = ? AND password = ?";

        try (Connection connection = DriverManager.getConnection(SQL_URL))
        {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect))
            {
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery())
                {
                    if (resultSet.next())
                    {
                        int id = resultSet.getInt(1);
                        String name = resultSet.getString(2);
                        Profile profile = new Profile(name, login, password);
                        profile.setId(id);
                        return profile;
                    }
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }
}

