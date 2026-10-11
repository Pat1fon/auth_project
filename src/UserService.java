public class UserService
{
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository     = userRepository;
    }

    public boolean initiateRegister(Profile userProfile)
    {
        if (!(userRepository.checkIfExists(userProfile.getLogin())))
        {
            userRepository.insertUser(userProfile);
            return true;
        }
        System.out.println("Пользователь существует");
        return false;
    }

    public Profile initiateLogin(Profile userProfile)
    {
        Profile result = userRepository.loginUser(userProfile.getLogin(), userProfile.getPassword());
        if (result != null)
        {
            return result;
        }
        return null;
    }
}
