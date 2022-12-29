package org.clkg.breezes.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.clkg.breezes.dao.CommonDao;
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
    CommonDao dao;

    @Autowired
    UserMapper mapper;

    @GetMapping(value = "/list")
    public Response list(HttpSession session) {
        List<Map<String, Object>> users = dao.find("select * from user");
        return Response.success(users);
    }

    @GetMapping(value = "/add")
    public Response add(@RequestParam String name, @RequestParam int age, HttpSession session) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setCreatedAt(new Date());
        mapper.insert(user);
        return Response.success(user);
    }
}
