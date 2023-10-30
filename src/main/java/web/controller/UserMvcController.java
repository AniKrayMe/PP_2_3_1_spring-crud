package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.model.User;
import web.service.UserService;
import web.service.UserServiceImpl;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/")
public class UserMvcController {

    @Autowired
    private UserService service;


    @GetMapping("/")
    public String getAllEmployees(Model model) {
        List<User> list = service.getAllUsers();

        model.addAttribute("users", list);
        return "list_users";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editUserById(Model model, @PathVariable("id") Optional<Long> id) throws RuntimeException {
        if (id.isPresent()) {
            User user = service.getUserById(id.get());
            model.addAttribute("user", user);
        } else {
            model.addAttribute("user", new User());
        }
        return "add-edit-user";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteUserById(Model model, @PathVariable("id") Long id) throws RuntimeException {
        service.deleteUserById(id);
        return "redirect:/";
    }

    @RequestMapping(path = "/createUser", method = RequestMethod.POST)
    public String createOrUpdateEmployee(User user) {
        service.createOrUpdateUser(user);
        return "redirect:/";
    }
}