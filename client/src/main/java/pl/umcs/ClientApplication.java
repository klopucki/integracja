package pl.umcs;

/**
 * Spring REST template client app
 */
public class ClientApplication {
    public static void main(String[] args) {
        UserProvider userProvider = new UserProvider();

        userProvider.addUser();
        userProvider.addUser();
        userProvider.addUser();
        userProvider.addUser();

        userProvider.printUserAsJson();
        userProvider.printUserAsObject();
        userProvider.fetchAllUsers();

        userProvider.updateUser();
        userProvider.fetchAllUsers();

        userProvider.deleteUser();
        userProvider.fetchAllUsers();
    }
}
