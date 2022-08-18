package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    public List<User> showAllUsers();
    public User showUserInfo(int id);
    public void saveUser(User user);
    public void deleteUser(int id);
    public void updateUserInfo(int id, User user);
}
