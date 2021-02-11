package br.com.alura.forum;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloWorld {
    @RequestMapping("/")
    @ResponseBody
    public String hello() {
        return "Hello world!!!";
    }
}
