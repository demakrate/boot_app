package d.p.haronatos.users_collection.db.service;

import d.p.haronatos.users_collection.db.models.User;

import java.util.List;

public interface Service {
    List<User> getAllUsers();

    User getUserByMail(String mail);

    Boolean addUser(User user);

    Boolean deleteUser(String mail);

    void changeUser(User user);
}
