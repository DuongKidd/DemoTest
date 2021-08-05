package io.github.tubean.myspringcrud.controller;

import io.github.tubean.myspringcrud.entity.APIResponse;
import io.github.tubean.myspringcrud.entity.User;
import io.github.tubean.myspringcrud.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public APIResponse<List<User>> getAll() {
        List<User> users = userService.getAllUser();
        APIResponse<List<User>> responseData = new APIResponse<>();
        responseData.setStatus(HttpStatus.OK.value());
        responseData.setMessage("Find all customer successful");
        responseData.setData(users);
        return responseData;
    }

    @GetMapping("/{id}")
    public APIResponse<User> GetById(@PathVariable Long id) {
        Optional<User> users = userService.findUserById(id);
        APIResponse<User> responseData = new APIResponse<>();
        responseData.setStatus(HttpStatus.OK.value());
        responseData.setMessage("Find by id customer successful");
        responseData.setData(users.get());
        return responseData;
    }

    @PostMapping("/create")
    public APIResponse<User> CreateUser(@RequestBody User user) {
        User userSaved = userService.saveUser(user);
        APIResponse<User> responseData = new APIResponse<>();
        responseData.setStatus(HttpStatus.OK.value());
        responseData.setMessage("Create customer successful");
        responseData.setData(userSaved);
        return responseData;
    }

    @GetMapping("/delete/{id}")
    public APIResponse<Boolean> DeleteById(@PathVariable Long id) {
        APIResponse<Boolean> responseData = new APIResponse<>();
        if (userService.deleteUser(id)) {
            responseData.setStatus(HttpStatus.OK.value());
            responseData.setMessage("Delete by id customer successful");
            responseData.setData(true);
            return responseData;
        } else {
            responseData.setStatus(HttpStatus.OK.value());
            responseData.setMessage("Delete fail");
            responseData.setData(false);
            return responseData;
        }
    }
}
