package d.p.haronatos.users_collection.db.dao;

import d.p.haronatos.users_collection.db.models.User;

import java.util.List;

public interface dao {
    List<User> getAllUsers();

    User getUserByMail(String mail);

    Boolean addUser(User user);
    Boolean deleteUser(String mail);
    void changeUser(User user);
}
