package org.clkg.breezes.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.clkg.breezes.model.User;
import org.clkg.breezes.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/common")
public class CommonController {

    @Autowired
    ObjectMapper mapper;

    @GetMapping(value = "/status")
    public Response status(HttpSession session) throws JsonProcessingException {
        Map data = new HashMap<>();
        data.put("name", "Lucky");
        data.put("date", Calendar.getInstance());
        String str = mapper.writeValueAsString(data);
        User user = mapper.readValue(str, User.class);
        System.out.println(user);
        return Response.success(str);
    }
}
