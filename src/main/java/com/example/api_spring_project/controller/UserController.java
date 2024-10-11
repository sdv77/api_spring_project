package com.example.api_spring_project.controller;

import com.example.api_spring_project.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private List<User> users = new ArrayList<>();

    // Получение всех пользователей
    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    // Получение пользователя по ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Создание нового пользователя
    @PostMapping
    public User createUser(@RequestBody User user) {
        users.add(user);
        return user;
    }

    // Обновление пользователя
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User user = users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (user != null) {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
        }

        return user;
    }

    // Удаление пользователя
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }
}
