package com.example.springboot;

import com.example.springboot.models.User;
import com.example.springboot.service.RoleService;
import com.example.springboot.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Component
public class DBFiller {

    private final UserService userService;
    private final RoleService roleService;

    public DBFiller(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void generateUsers() {
        for (int i = 1; i <= 5; i++) {
            userService.saveUser(new User("Kekw"+i, 15+i, "15@14.12+i", "user"+i, "user"+i,
                    new HashSet<>(Collections.singletonList(
                            roleService.getOrCreateRole("ROLE_USER")
                    ))
            ));
        }

        userService.saveUser(new User(
                "admin",
                15,
                "admin@mail.com",
                "admin",
                "admin",
                new HashSet<>(Arrays.asList(
                        roleService.getOrCreateRole("ROLE_USER"),
                        roleService.getOrCreateRole("ROLE_ADMIN")
                ))
        ));
    }
}
