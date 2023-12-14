package com.example.meetythymeleaf.controller;


import com.example.meetythymeleaf.controller.requests.AddUserRequest;
import com.example.meetythymeleaf.model.Interest;
import com.example.meetythymeleaf.model.User;
import com.example.meetythymeleaf.service.InterestService;
import com.example.meetythymeleaf.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Qualifier("getApplicationContext")
    ApplicationContext applicationContext;

    private final InterestService interestService;




    @GetMapping
    public String getUsers(Model model,@RequestParam(required = false)  String fullName, @RequestParam(required = false) String selectedInterest) {
        List<User> users;

        if (fullName!=null&&!fullName.isEmpty()) {
            model.addAttribute("fullName", fullName);
            users = userService.searchUsers(fullName);
            if(selectedInterest!=null&&!selectedInterest.isEmpty()&&!selectedInterest.equals("all"))
                users=users.stream().filter(x->x.getInterests().contains(new Interest(selectedInterest))).toList();

        } else if(selectedInterest!=null&&!selectedInterest.isEmpty()&&!selectedInterest.equals("all")){
            users = userService.getUsersByInterest(selectedInterest);
        }else
            users=userService.getLimitedUsers();

        model.addAttribute("users", users);

        List<String> interests = interestService.findAll().stream().map(Interest::getName).toList();

        model.addAttribute("interests", interests);

        return "users/user-list";
    }

    @GetMapping("/{userId}")
    public String getUser(@PathVariable Long userId, Model model) {
        User user = userService.getUserById(userId);

        model.addAttribute("user", user);

        return "users/user-details";
    }
    @PostMapping("/byName/{name}")
    public ResponseEntity<List<User>> getUser(@PathVariable String name) {
        return ResponseEntity.ok(userService.searchUsers(name));


    }

    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user",new AddUserRequest());
        model.addAttribute("interests", interestService.findAll());
        return "users/user-add";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") AddUserRequest user) {
        User u=user.toUser();
        userService.saveUser(u);
        return "redirect:/user";
    }

    @GetMapping("/edit/{userId}")
    public String showEditUserForm(@PathVariable Long userId, Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        model.addAttribute("interests", interestService.findAll().stream().map(Interest::getName).toList());
        return "users/user-edit";
    }

    @PostMapping("/edit/{userId}")
    public String editUser(@PathVariable Long userId, @ModelAttribute User user) {
        userService.updateUser(userId, user);
        return "redirect:/users";
    }



    @GetMapping("/delete/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return "redirect:/user";
    }

}