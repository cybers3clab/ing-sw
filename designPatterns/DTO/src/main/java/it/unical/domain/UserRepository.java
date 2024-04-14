package it.unical.domain;

import java.util.List;

public interface UserRepository {
    List<User> getAll();
    void save(User user);
    void deleteAll();
}
