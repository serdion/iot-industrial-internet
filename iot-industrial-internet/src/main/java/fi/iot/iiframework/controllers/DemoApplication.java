package fi.iot.iiframework.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class DemoApplication {
    
    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "hello world!";
    }

}
