package business_object.user;

import static business_object.user.UserCredentials.SENDER_LOGIN;
import static business_object.user.UserCredentials.SENDER_PASSWORD;

public class UserFactory {

    public static User createDefaultUser() {
        User user = new User();
        user.setLogin(SENDER_LOGIN);
        user.setPassword(SENDER_PASSWORD);
        return user;
    }

    public static User createUserWithLogin(String login) {
        User user = createDefaultUser();
        user.setLogin(login);
        return user;
    }

    public static User createUserWithPassword(String password) {
        User user = createDefaultUser();
        user.setPassword(password);
        return user;
    }

    public static User createUserWithLoginAndPassword(String login, String password) {
        User user = createDefaultUser();
        user.setLogin(login);
        user.setPassword(password);
        return user;
    }
}