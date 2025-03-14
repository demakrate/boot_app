package d.p.haronatos.users_collection.web.controller;


import d.p.haronatos.users_collection.db.Models.User;
import d.p.haronatos.users_collection.db.Service.Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;


@Controller
public class HelloController {

    Service service;

    public HelloController(Service service) {
        this.service = service;
    }

    @GetMapping(value = "/")
    public String printWelcome(ModelMap model) {
        return "index";
    }


    @GetMapping(value = "/getAllUsers")
    public String getAll(Model model) {
        if (!service.getAllUsers().equals(new ArrayList<User>())) {
            model.addAttribute("users", service.getAllUsers());
            return ("allUsers");
        } else {
            model.addAttribute("message", "Записи отсутствуют");
            return ("message");
        }


    }

    @GetMapping(value = "/getUserByMail")
    public String getUser(@RequestParam(value = "mail") String mail, Model model) {
        User user = service.getUserByMail(mail);
        if (user == null) {
            model.addAttribute("message", "Пользователь не найден");
        } else {
            model.addAttribute("message", user);
        }
        return ("message");
    }

    @PostMapping(value = "/addUser")
    public String addUser(@RequestParam String name, @RequestParam int age, @RequestParam String mail, Model model) {
        User user = new User(name, age, mail);
        if (service.addUser(user)) {
            model.addAttribute("message", "Пользователь " + name + " успешно добавлен");
        } else {
            model.addAttribute("message", "Пользователь с такой почтой уже существует");
        }
        return ("message");
    }

    @GetMapping(value = "/deleteUser")
    public String deleteUser(@RequestParam(value = "mail") String mail, Model model) {
        if (service.deleteUser(mail)) {
            model.addAttribute("message", "Пользователь удален");
        } else {
            model.addAttribute("message", "Пользователь не удален (или его не существует)");
        }
        return ("message");
    }

    @PostMapping(value = "/changeUser")
    public String changeUser(@RequestParam String name, @RequestParam int age, @RequestParam String mail, Model model) {
        service.changeUser(new User(name, age, mail));
        model.addAttribute("message", "Пользователь " + name + " успешно заменён");
        return ("message");
    }
}