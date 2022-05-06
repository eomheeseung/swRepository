package hello.se.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class HomeController {

    @GetMapping()
    public String homeView() {
        log.info("first page");
        return "SW-Project-main/index";
    }

    @GetMapping("/index")
    public String homeView2() {
        log.info("click menu to index page");
        return "SW-Project-main/index";
    }

    @GetMapping("/menu")
    public String menuView() {
        return "SW-Project-main/menu";
    }

    @GetMapping("/book")
    public String bookView() {
        return "SW-Project-main/book";
    }

    @GetMapping("/about")
    public String aboutView() {
        return "SW-Project-main/about";
    }
}
