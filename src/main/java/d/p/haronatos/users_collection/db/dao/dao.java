package d.p.haronatos.users_collection.db.dao;

import d.p.haronatos.users_collection.db.Models.User;

import java.util.List;

public interface dao {
    public List<User> getAllUsers();

    public User getUserByMail(String mail);

    public Boolean addUser(User user);
    public Boolean deleteUser(String mail);
    public void changeUser(User user);
}
