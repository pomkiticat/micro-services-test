package com.example.userservice.controller;

import com.example.userservice.model.UserView;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment env;

    @RequestMapping("/")
    public String home() {
        return "Hello from Userview Service running at port: " + env.getProperty("local.server.port");
    }

    @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping("/{id}")
    public UserView getDetails(@PathVariable int id){
       System.out.println(" kadkjsa");
      //  @SuppressWarnings("unchecked")
        UserView users = restTemplate.getForObject("http://userDetails-service/api/v1/fetchAnyUser/"+id, UserView.class);
    //    gallery.setImages(images);
        return users;
    }

   // @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping("/all")
    public List<UserView> getAll(){
     //   @SuppressWarnings("unchecked")
        List<UserView> userViewList= restTemplate.getForObject("http://userDetails-service/api/v1/fetchUserAll", List.class);

        return userViewList;
    }

    @RequestMapping("/admin")
    public String homeAdmin() {
        return "This is the admin area of Userview service running at port: " + env.getProperty("local.server.port");
    }
    public UserView fallback(int id, Throwable hystrixCommand) {
        return new UserView(id);
    }


}
