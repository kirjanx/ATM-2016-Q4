package business_object.user;

public class UserBuilder {

    private User user;

    public UserBuilder() {
        user = UserFactory.createDefaultUser();
    }

    public UserBuilder login(String login) {
        user.setLogin(login);
        return this;
    }

    public UserBuilder password(String password) {
        user.setLogin(password);
        return this;
    }

    public User build() {
        return user;
    }
}
