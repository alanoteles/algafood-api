package com.algaworks.algafoodapi;

import com.algaworks.algafoodapi.di.model.Client;
import com.algaworks.algafoodapi.di.service.ClientActivationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyFirstController {

  private ClientActivationService clientActivationService;

  public MyFirstController(ClientActivationService clientActivationService) {
    this.clientActivationService = clientActivationService;

    System.out.println("MyFirstController: " + clientActivationService);
  }

  @GetMapping("/hello")
  @ResponseBody
  public String hello(){
    Client john = new Client("John", "john@xyz.com", "9123456789");

    clientActivationService.activate(john);
    return "Hello again xxx!";
  }
}
