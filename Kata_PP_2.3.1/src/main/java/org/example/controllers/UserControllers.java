package org.example.controllers;

import org.example.models.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
public class UserControllers {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "User/tableUser";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "User/oneUser";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("user", new User());
        return "User/newUser";
    }

    @PostMapping
    public String add(@ModelAttribute("user") @Valid User user,
                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "User/newUser";
        }
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String updatePerson(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "User/updateUser";
    }

    @PatchMapping("/{id}")
    public String createUpdateUser(@PathVariable("id") Long id,
                                   @ModelAttribute("user") @Valid User user,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "User/updateUser";
        }
        userService.updateUser(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @DeleteMapping("/deleteAll")
    public String deleteAllUser() {
        userService.deleteAllUser();
        return "redirect:/users";
    }
}
