package org.clkg.breezes.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.clkg.breezes.mapper.UserMapper;
import org.clkg.breezes.model.User;
import org.clkg.breezes.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping(value = "/list")
    public Response list(HttpSession session) {
        List<User> users = userMapper.selectList(null);
        return Response.success(users);
    }

    @GetMapping(value = "/add")
    public Response add(@RequestParam String name, @RequestParam int age, HttpSession session) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        userMapper.insert(user);
        return Response.success(user);
    }
}
