package com.example.meetythymeleaf.controller.requests;

import com.example.meetythymeleaf.ApplicationContextProvider;
import com.example.meetythymeleaf.model.Interest;
import com.example.meetythymeleaf.model.User;
import com.example.meetythymeleaf.service.InterestService;
import com.example.meetythymeleaf.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequest {

    private static UserService userService;
    private Long id;

    private String fullName;


    private String email;
    private String password;

    private List<String> interests;
    private List<String> friends;

    private String friendsRes;

    private String description;

    public User toUser() {
        User u = new User();
        u.setEmail(email);
        u.setFullName(fullName);
        u.setPassword(password);
        u.setDescription(description);
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        UserService userService = context.getBean(UserService.class);
        InterestService interestService = context.getBean(InterestService.class);
        List<String> friendsStrs= Arrays.stream(friendsRes.split(",")).toList();
        List<Long> friendsIds=new ArrayList<>();
        for(String i:friendsStrs){
            try {
                friendsIds.add(Long.valueOf(i));
            }catch (Exception ignored){}
        }
        List<User> friendsList=userService.getByIds(friendsIds);
        u.setFriends(friendsList);

        List<Long> interestsIds=interests.stream().map(Long::valueOf).collect(Collectors.toList());
        List<Interest> interestsList=interestService.getByIds(interestsIds);
        u.setInterests(interestsList);
        return u;
    }

    public static AddUserRequest toRequest(User user) {
        AddUserRequest u = new AddUserRequest();
        u.setEmail(user.getEmail());
        u.setFullName(user.getFullName());
        u.setPassword(user.getPassword());
        u.setDescription(user.getDescription());
        return u;
    }

    public static List<AddUserRequest> toRequest(List<User> users) {
        return users.stream().map(AddUserRequest::toRequest).toList();
    }

}
