package org.clkg.breezes.controller;

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
        return Response.success();
    }
}
