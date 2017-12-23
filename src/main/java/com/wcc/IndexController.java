package com.wcc;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @RequestMapping(value = "/", produces = "application/json")
    public Object index() {
        return "wcc_api";
    }
}


