package gr.hua.dit.ds.ds_lab_2024.controllers;

import gr.hua.dit.ds.ds_lab_2024.entities.AdoptionForm;
import gr.hua.dit.ds.ds_lab_2024.entities.Pet;
import gr.hua.dit.ds.ds_lab_2024.entities.Shelter;
import gr.hua.dit.ds.ds_lab_2024.entities.User;
import gr.hua.dit.ds.ds_lab_2024.service.AdoptionFormService;
import gr.hua.dit.ds.ds_lab_2024.service.PetService;
import gr.hua.dit.ds.ds_lab_2024.service.ShelterService;
import gr.hua.dit.ds.ds_lab_2024.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("shelter")
public class ShelterController {
    private final PetService petService;
    private final UserService userService;
    private final AdoptionFormService adoptionFormService;
    private ShelterService shelterService;

    public ShelterController(ShelterService shelterService, PetService petService, UserService userService, AdoptionFormService adoptionFormService) {
        this.shelterService = shelterService;
        this.petService = petService;
        this.userService = userService;
        this.adoptionFormService = adoptionFormService;
    }


    @GetMapping("")
    public String getShelters(Model model) {
        model.addAttribute("shelters", shelterService.getShelters());
        return "shelter/list";

    }

    @GetMapping("/new")
    public String newShelter(Model model){
        Shelter shelter = new Shelter();
        model.addAttribute("shelter", shelter);
        return "shelter/new";
    }

    @PostMapping("/new")
    public String createShelter(@ModelAttribute("shelter") Shelter shelter, BindingResult theBindingResult,Model model) {
        if (theBindingResult.hasErrors()) {
            System.out.println("error");
            theBindingResult.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
            return "shelter/new";
        } else {
            shelterService.saveShelter(shelter);
            model.addAttribute("shelter", shelter);
            return "shelter/profile";
        }
    }

    @GetMapping("/{shelter_id}")
    public String getShelter(@PathVariable Integer shelter_id,Model model) {
        try {
            model.addAttribute("shelter", shelterService.getShelter(shelter_id));
            return "shelter/profile";
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Shelter not found", e);
        }

    }
    @Secured("ROLE_ADMIN")
    @GetMapping("/{shelter_id}/delete")
    public String  deleteShelter(@PathVariable Integer shelter_id) {
        boolean result = shelterService.deleteShelter(shelter_id);
        if (result) {
            return "shelter/deleted";
        } else {
            return "error/error";
        }
    }

    @Secured({"ROLE_ADMIN","ROLE_SHELTER"})
    @GetMapping("adopt")
    public String getAdoptionForms(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        if (userDetails.getAuthorities().toString().equals("[ROLE_SHELTER]")) {

            User user = userService.findByEmail(userDetails.getUsername()).get();
            if (shelterService.getShelter(user.getId())!=null) {
                List<AdoptionForm> forms=new ArrayList<>();
                for (Pet pet : shelterService.getShelter(user.getId()).getPets()) {
                    for (AdoptionForm form : pet.getAdoptionforms()) {
                        if (form != null) {
                            forms.add(form);
                        }
                    }
                }
                System.out.println(forms);
                model.addAttribute("forms",forms);
                return "adopt/list";
            }
        }
        return "error/error-403";
    }

    @Secured({"ROLE_ADMIN","ROLE_SHELTER"})
    @PostMapping("/adopt")
    public String approveAdoptForm(@Valid @ModelAttribute("id") Integer id, @AuthenticationPrincipal UserDetails userDetails, Model model) {

        AdoptionForm f = adoptionFormService.getAdoptionForm(id);
        f.setApproved(true);
        f.setDate(new Date());
//        System.out.println(form);
        adoptionFormService.saveAdoptionForm(f);
        return "index";
    }


}
