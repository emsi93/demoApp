package com.example.demoApp.rest.controller;

import com.example.demoApp.rest.model.Greeting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/rest")
public class GrettingController {


        @RequestMapping(value="/greeting", method = RequestMethod.GET)
        public Greeting getGreeting(@RequestParam(value="name", defaultValue="World") String name){
            log.info("REST WESZLO");
            return new Greeting("Hello ", name );
        }
}
