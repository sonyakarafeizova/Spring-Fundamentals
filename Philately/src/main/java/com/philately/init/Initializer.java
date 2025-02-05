package com.philately.init;

import com.philately.service.PaperService;
import com.philately.service.StampService;
import com.philately.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Initializer implements CommandLineRunner {
    private final UserService userService;
    private final PaperService paperService;

    private final StampService stampService;

    public Initializer(UserService userService, PaperService paperService, StampService stampService) {
        this.userService = userService;
        this.paperService = paperService;
        this.stampService = stampService;


    }

    @Override
    public void run(String... args) throws IOException {
        this.userService.initUsers();
        this.paperService.initPapers();
        this.stampService.initStamps();

    }
}
