package io.github.tubean.myspringcrud.service;

import io.github.tubean.myspringcrud.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
  List<User> getAllUser();

  User saveUser(User user);

  boolean deleteUser(Long id);

  Optional<User> findUserById(Long id);
}
