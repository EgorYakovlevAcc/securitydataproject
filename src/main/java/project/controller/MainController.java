package project.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class MainController {

    @GetMapping("/")
    public String getIndexPage(){
        return "index";
    }

//    @PostMapping(value = "/registration")
//    public String registerUserAccount(@Valid final UserDto accountDto, final HttpServletRequest request) {
//        log.debug("Registering user account with information: {}", accountDto);
//
//        final User registered = userService.registerNewUserAccount(accountDto);
//        return "Success";
//    }
}
