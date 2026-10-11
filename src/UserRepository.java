import java.sql.*;

public class UserRepository
{
    private final String SQL_URL;

    public UserRepository(String url)
    {
        this.SQL_URL = url;
    }

    public boolean checkIfExists(String login)
    {
        String sqlExists = "SELECT EXISTS (SELECT 1 FROM users WHERE login = ?)";

        try (Connection connection = DriverManager.getConnection(SQL_URL))
        {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlExists))
            {
                preparedStatement.setString(1, login);
                try (ResultSet resultSet = preparedStatement.executeQuery())
                {
                    if (resultSet.next()) {
                        return resultSet.getBoolean(1);
                    }
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return false;
    }

    public void insertUser(Profile userProfile)
    {

        try (Connection connection = DriverManager.getConnection(SQL_URL))
        {
            String sqlInsert = "INSERT INTO users (name, login, password) VALUES (?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert))
            {
                preparedStatement.setString(1, userProfile.getName());
                preparedStatement.setString(2, userProfile.getLogin());
                preparedStatement.setString(3, userProfile.getPassword());

                preparedStatement.executeUpdate();

                System.out.println("Добавлен пользователь" + userProfile);
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

