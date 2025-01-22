package gr.hua.dit.ds.ds_lab_2024.controllers;

import gr.hua.dit.ds.ds_lab_2024.entities.Role;
import gr.hua.dit.ds.ds_lab_2024.repositories.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {


    RoleRepository roleRepository;

    public AuthController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void setup() {
        Role role_user = new Role("ROLE_USER");
        Role role_admin = new Role("ROLE_ADMIN");
        Role role_doctor = new Role("ROLE_DOCTOR");
        Role role_shelter = new Role("ROLE_SHELTER");

        System.out.println(roleRepository.updateOrInsert(role_user).getName());
        System.out.println(roleRepository.updateOrInsert(role_admin).getName());
        System.out.println(roleRepository.updateOrInsert(role_doctor).getName());
        System.out.println(roleRepository.updateOrInsert(role_shelter).getName());
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }
}