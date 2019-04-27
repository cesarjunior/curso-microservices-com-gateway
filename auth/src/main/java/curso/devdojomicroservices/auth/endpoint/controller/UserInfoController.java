package curso.devdojomicroservices.auth.endpoint.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import curso.devdojomicroservices.core.model.UserApplication;

@RestController
@RequestMapping("user")
public class UserInfoController {

    @GetMapping(path = "info", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserApplication> getUserInfo(Principal principal) {
    	UserApplication userApplication = (UserApplication) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        return new ResponseEntity<>(userApplication, HttpStatus.OK);
    }
}