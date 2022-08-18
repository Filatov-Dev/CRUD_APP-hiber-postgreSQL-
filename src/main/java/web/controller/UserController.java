package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.showAllUsers());
        return "users/all_users";
    }

    @GetMapping("/{id}")
    public String getUserInfo(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.showUserInfo(id));
        return "users/user_info";
    }

    @GetMapping("/new")
    public String createNewUser(Model model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping()
    public String addNewUser(@ModelAttribute("user") User newUser) {
        userService.saveUser(newUser);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") int id){
        model.addAttribute("user", userService.showUserInfo(id));
        return "users/edit_user";
    }

    @PatchMapping("/{id}")
    public String updateUserInfo(@ModelAttribute("user") User user,@PathVariable("id") int id , Model model) {
        userService.updateUserInfo(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
