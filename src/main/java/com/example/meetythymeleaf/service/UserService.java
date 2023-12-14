package com.example.meetythymeleaf.service;


import com.example.meetythymeleaf.model.User;
import com.example.meetythymeleaf.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    UserRepository userRepository;


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> getLimitedUsers() {
        return userRepository.findAll().stream().limit(10).toList();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void updateUser(Long id, User updatedUser) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setFullName(updatedUser.getFullName());
            user.setEmail(updatedUser.getEmail());
            userRepository.save(user);
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
        System.out.println("User deleted successfully.");
    }


    public List<User> searchUsers(String fullName) {
        Optional<List<User>> res = userRepository.findUsersByFullNameContaining(fullName);
        if (res.isPresent())
            return res.get();
        else
            return null;
    }

    public List<User> getUsersByInterest(String interest) {
        return userRepository.findUsersByInterests_Name(interest);
    }

    public List<User> getByIds(List<Long> ids) {
        return userRepository.findAllById(ids);

    }
}
