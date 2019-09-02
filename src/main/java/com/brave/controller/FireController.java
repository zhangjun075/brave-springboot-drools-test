package com.brave.controller;

import com.brave.service.FireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FireController {
    @Autowired FireService fireService;

    @GetMapping("/file")
    public void fileinit() {
        fireService.turnOnSprinkler();
    }
}
