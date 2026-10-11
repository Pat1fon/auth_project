void main()
{
    final String SQL_URL = "jdbc:sqlite:Test_SQL.db?busy_timeout=5000";
    UserRepository userRepository = new UserRepository(SQL_URL);
    UserService userService = new UserService(userRepository);

    Scanner scanner = new Scanner(System.in);
    String userName = scanner.nextLine();
    String userLogin = scanner.nextLine();
    String userPassword = scanner.nextLine();

    Profile userProfile = new Profile(userName, userLogin, userPassword);

    if (userService.initiateRegister(userProfile))
    {
        System.out.println("Все четко" + userProfile);
    }
    else
    {
        System.out.println("Не прошло");
    }

    /*try (Connection connection = DriverManager.getConnection(SQL_URL)) {
        Statement statement = connection.createStatement();

        Scanner scanner = new Scanner(System.in);
        String userName = scanner.nextLine();
        String userLogin = scanner.nextLine();
        String userPassword = scanner.nextLine();

        Profile userProfile = new Profile(userName, userLogin, userPassword);

        String sqlExists = "SELECT EXISTS (SELECT 1 FROM users WHERE login = ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlExists))
        {
            //preparedStatement.setString(1, userName);
            preparedStatement.setString(1, userLogin);
            //preparedStatement.setString(3, userPassword);

            try (ResultSet resultSet = preparedStatement.executeQuery())
            {
                if (resultSet.next())
                {
                    boolean exists = resultSet.getBoolean(1);
                    if (!exists)
                    {
                        String sqlInsert = "INSERT INTO users (name, login, password) VALUES (?, ?, ?)";

                        try (PreparedStatement preparedStatement1 = connection.prepareStatement(sqlInsert))
                        {
                            preparedStatement1.setString(1, userName);
                            preparedStatement1.setString(2, userLogin);
                            preparedStatement1.setString(3, userPassword);

                            preparedStatement1.executeUpdate();

                            System.out.println("Добавлен пользователь" + userProfile);
                        }
                    }
                    else
                    {
                        System.out.println("Пользователь существует");
                    }
                }
            }
        }

    } catch (SQLException e) {
        System.out.println(e.getMessage());
        throw new RuntimeException(e);
    }

     */

    /*
    Profile user = new Profile();
    user.setName("Ilya");
    user.setLogin("Patifon");
    user.setPassword("Navi");

    System.out.println(user);

     */
}

