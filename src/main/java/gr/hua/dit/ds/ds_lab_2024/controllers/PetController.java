//package gr.hua.dit.ds.ds_lab_2024.controllers;
//
//
//import gr.hua.dit.ds.ds_lab_2024.entities.Doctor;
//import gr.hua.dit.ds.ds_lab_2024.entities.Pet;
//import gr.hua.dit.ds.ds_lab_2024.service.PetService;
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
//@RequestMapping("pet")
//public class PetController {
//    private PetService petService;
//
//    public PetController(PetService petService) {
//        this.petService = petService;
//    }
//
//    @GetMapping("")
//    public String getPets(Model model) {
//        model.addAttribute("pets",petService.getPets());
//        return "pet/list";
//    }
//
//    @GetMapping("/new")
//    public String newPet(Model model){
//        Pet pet = new Pet();
//        model.addAttribute("pet", pet);
//        return "pet/new";
//    }
//
//    @PostMapping("/new")
//    public String createPet(@ModelAttribute("pet") Pet pet, Model model) {
//        petService.savePet(pet);
//        model.addAttribute("pet", pet);
//        return "pet/profile";
//    }
//
//    @GetMapping("/{pet_id}")
//    public String getPet(@PathVariable Integer pet_id,Model model) {
//        try {
//            model.addAttribute("pet", petService.getPet(pet_id));
//            return "pet/profile";
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet not found", e);
//        }
//    }
//
////    @DeleteMapping("/{pet_id}")
////    public ResponseEntity<String>  deletePet(@PathVariable Integer pet_id) {
////        boolean result = petService.deletePet(pet_id);
////        if (result) {
////            return ResponseEntity.status(HttpStatus.OK).body("shelter deleted successfully");
////        }
////        else {
////            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("shelter not found");
////        }
////    }
//
//}