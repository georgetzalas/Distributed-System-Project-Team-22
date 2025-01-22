package gr.hua.dit.ds.ds_lab_2024.controllers;



import gr.hua.dit.ds.ds_lab_2024.entities.*;
import gr.hua.dit.ds.ds_lab_2024.service.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
@RequestMapping("pet")
public class PetController {
    private final ShelterService shelterService;
    private final HealthFormService healthFormService;
    private final UserService userService;
    private final DoctorService doctorService;
    private final ClientService clientService;
    private final AdoptionFormService adoptionFormService;
    private final PetService petService;

    public PetController(PetService petService, ShelterService shelterService, HealthFormService healthFormService, UserService userService, DoctorService doctorService,ClientService clientService, AdoptionFormService adoptionFormService) {
        this.petService = petService;
        this.shelterService = shelterService;
        this.healthFormService = healthFormService;
        this.userService = userService;
        this.doctorService = doctorService;
        this.clientService = clientService;
        this.adoptionFormService = adoptionFormService;
    }


    @GetMapping("")
    public String getPets(Model model) {
        model.addAttribute("pets",petService.getPets());
        return "pet/list";
    }

    @Secured({"ROLE_ADMIN","ROLE_SHELTER"})
    @GetMapping("/new")
    public String newPet(Model model){
        Pet pet = new Pet();
        model.addAttribute("pet", pet);
        model.addAttribute("shelters", shelterService.getShelters());
        return "pet/new";
    }

    @Secured({"ROLE_ADMIN","ROLE_SHELTER"})
    @PostMapping("/new")
    public String createPet(@Valid @ModelAttribute("pet") Pet pet, BindingResult theBindingResult, Model model) {
           if (theBindingResult.hasErrors()) {
            System.out.println("error");
            theBindingResult.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
            return "pet/new";
        } else {
            petService.savePet(pet);
            model.addAttribute("pet", pet);
            return "pet/profile";
        }
    }

    @GetMapping("/{pet_id}")
    public String getPet(@PathVariable Integer pet_id,Model model) {
        try {
            model.addAttribute("pet", petService.getPet(pet_id));
            return "pet/profile";
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet not found", e);
        }
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/{pet_id}/delete")
    public String  deletePet(@PathVariable Integer pet_id) {
        boolean result = petService.deletePet(pet_id);
        if (result) {
            return "pet/deleted";
        } else {
            return "error/error";
        }
    }


    @Secured({"ROLE_ADMIN","ROLE_DOCTOR"})
    @GetMapping("/{pet_id}/health")
    public String approvePet(@PathVariable Integer pet_id, @AuthenticationPrincipal UserDetails userDetails, Model model) {

        Pet pet = petService.getPet(pet_id);

        if (userDetails.getAuthorities().toString().equals("[ROLE_DOCTOR]")) {

            User user = userService.findByEmail(userDetails.getUsername()).get();
            if (doctorService.getDoctor(user.getId())!=null){
                HealthForm form = pet.getHealthform();
                form.setApproved(true);

                form.setDoctor(doctorService.getDoctor(user.getId()));
                healthFormService.saveHealthForm(form);
                model.addAttribute("pet", pet);
                return "pet/profile";
            }

        }
        return "error/error-403";


    }


    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/{pet_id}/adopt")
    public String adoptForm(@PathVariable Integer pet_id, @AuthenticationPrincipal UserDetails userDetails, Model model) {

        Pet pet = petService.getPet(pet_id);

        if (userDetails.getAuthorities().toString().equals("[ROLE_USER]")) {
            System.out.println(userDetails.getAuthorities().toString().equals("[ROLE_USER]"));
            User user = userService.findByEmail(userDetails.getUsername()).get();
            if (clientService.getClient(user.getId())!=null){
                AdoptionForm form = new AdoptionForm();
                model.addAttribute("form", form);
                model.addAttribute("pet", pet);
                return "pet/adopt";
            }

        }
        return "error/error-403";


    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @PostMapping("/{pet_id}/adopt")
    public String saveAdoptForm(@PathVariable Integer pet_id,@Valid @ModelAttribute("form") AdoptionForm form, @AuthenticationPrincipal UserDetails userDetails, Model model) {

        Pet pet = petService.getPet(pet_id);
        if (userDetails.getAuthorities().toString().equals("[ROLE_USER]")) {
            System.out.println(userDetails.getAuthorities().toString().equals("[ROLE_USER]"));
            User user = userService.findByEmail(userDetails.getUsername()).get();
            if (clientService.getClient(user.getId())!=null) {
                form.setApproved(false);
                form.setDate(new Date());
                form.setPet(pet);
                form.setClient(clientService.getClient(user.getId()));
                adoptionFormService.saveAdoptionForm(form);
                model.addAttribute("pet", pet);
                return "pet/profile";
            }

        }
        return "error/error-403";
    }
}
