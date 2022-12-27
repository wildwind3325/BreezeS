package org.clkg.breezes.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.clkg.breezes.util.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/common")
public class CommonController {

    @GetMapping(value = "/status")
    public Response status(HttpSession session) {
        Map data = new HashMap<>();
        data.put("name", "Lucky");
        data.put("date", Calendar.getInstance());
        return Response.success(data);
    }
}
