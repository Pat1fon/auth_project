import java.sql.*;

void main()
{
    final String SQL_URL = "jdbc:sqlite:Test_SQL.db";
    String sqlInsert = "";

    ArrayList<Profile> listOfUsers = new ArrayList<>();

    try (Connection connection = DriverManager.getConnection(SQL_URL)) {
        Statement statement = connection.createStatement();

        String sqlSelect = """
                SELECT id, name, login, password
                FROM users;
                """;
        try (ResultSet resultSet = statement.executeQuery(sqlSelect)) {
            while (resultSet.next()) {
                Profile user = new Profile();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                listOfUsers.add(user);

            }
        }
        Scanner scanner = new Scanner(System.in);
        String userName = scanner.nextLine();
        String userLogin = scanner.nextLine();
        String userPassword = scanner.nextLine();

        Profile userProfile = new Profile(userName, userLogin, userPassword);
        boolean flag = true;
        for (Profile existingUser : listOfUsers)
        {
            if ((Objects.equals(existingUser.getLogin(), userProfile.getLogin())) & (Objects.equals(existingUser.getName(), userProfile.getName())) & (Objects.equals(existingUser.getPassword(), userProfile.getPassword()))) {
                System.out.println("Вы вошли в аккаунт)))");
                flag = false;
                break;
            }
        }
        if (flag)
        {
            System.out.println("Неверное имя пользователя или пароль или логин");
        }

    } catch (SQLException e) {
        System.out.println(e.getMessage());
        throw new RuntimeException(e);
    }

    /*
    Profile user = new Profile();
    user.setName("Ilya");
    user.setLogin("Patifon");
    user.setPassword("Navi");

    System.out.println(user);

     */
}

