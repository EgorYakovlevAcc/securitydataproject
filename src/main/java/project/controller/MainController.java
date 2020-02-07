package project.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import project.persistence.model.CalculateHashBody;
import project.service.BackendServiceImpl;

import java.util.List;

@Slf4j
@Controller
public class MainController {

    @Autowired
    private BackendServiceImpl backendService;

    @GetMapping("/")
    public String getIndexPage(){
        return "index";
    }

    @GetMapping("/api/hashFunctions")
    @ResponseBody
    public List<String> getFunctions(){
        return backendService.getFunctions();
    }

    @GetMapping("/api/extensions")
    @ResponseBody
    public List<String> getExtensions(){
        return backendService.getExtensions();
    }

    @PostMapping("/calculate")
    @ResponseBody
    public String calculateHash(@RequestBody CalculateHashBody requestBody){
        return backendService.calculateHash(requestBody);
    }


//    @PostMapping(value = "/registration")
//    public String registerUserAccount(@Valid final UserDto accountDto, final HttpServletRequest request) {
//        log.debug("Registering user account with information: {}", accountDto);
//
//        final HashResult registered = userService.registerNewUserAccount(accountDto);
//        return "Success";
//    }
}
