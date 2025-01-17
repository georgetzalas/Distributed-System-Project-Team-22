//package gr.hua.dit.ds.ds_lab_2024.controllers;
//
//import gr.hua.dit.ds.ds_lab_2024.entities.AdoptionForm;
//import gr.hua.dit.ds.ds_lab_2024.entities.Shelter;
//import gr.hua.dit.ds.ds_lab_2024.service.ShelterService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.List;
//import java.util.Optional;
//
//@Controller
//@RequestMapping("shelter")
//public class ShelterController {
//    private ShelterService shelterService;
//
//    public ShelterController(ShelterService shelterService) {
//        this.shelterService = shelterService;
//    }
//
//
//    @GetMapping("")
//    public String getShelters(Model model) {
//        model.addAttribute("shelters", shelterService.getShelters());
//        return "shelter/list";
//
//    }
//
//    @GetMapping("/new")
//    public String newShelter(Model model){
//        Shelter shelter = new Shelter();
//        model.addAttribute("shelter", shelter);
//        return "shelter/new";
//    }
//
//    @PostMapping("/new")
//    public String createShelter(@ModelAttribute("shelter") Shelter shelter,Model model) {
//        shelterService.saveShelter(shelter);
//        model.addAttribute("shelter", shelter);
//        return "shelter/profile";
//    }
//
//    @GetMapping("/{shelter_id}")
//    public String getShelter(@PathVariable Integer shelter_id,Model model) {
//        try {
//            model.addAttribute("shelter", shelterService.getShelter(shelter_id));
//            return "shelter/profile";
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Shelter not found", e);
//        }
//
//    }

//    @DeleteMapping("/{shelter_id}")
//    public ResponseEntity<String>  deleteShelter(@PathVariable Integer shelter_id) {
//        boolean result = shelterService.deleteShelter(shelter_id);
//        if (result) {
//            return ResponseEntity.status(HttpStatus.OK).body("shelter deleted successfully");
//        }
//        else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("shelter not found");
//        }
//    }
//}
