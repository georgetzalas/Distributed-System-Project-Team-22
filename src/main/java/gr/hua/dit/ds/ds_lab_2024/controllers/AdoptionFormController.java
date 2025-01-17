//package gr.hua.dit.ds.ds_lab_2024.controllers;
//
//import gr.hua.dit.ds.ds_lab_2024.entities.AdoptionForm;
//import gr.hua.dit.ds.ds_lab_2024.service.AdoptionFormService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;
//import java.util.List;
//
//@Controller
//@RequestMapping("adoption-form")
//public class AdoptionFormController {
//
//    private AdoptionFormService adoptionFormService;
//
//    public AdoptionFormController(AdoptionFormService adoptionFormService) {
//        this.adoptionFormService = adoptionFormService;
//    }
//
//    @GetMapping("")
//    public String getAdoptionForms(Model model) {
//        model.addAttribute("adoptionforms", adoptionFormService.getAdoptionForms());
//        return "adoption-form/list";
//    }
//
//    @GetMapping("/new")
//    public String newAdoptionForm(Model model){
//        AdoptionForm adoptionForm = new AdoptionForm();
//        model.addAttribute("adoptionform", adoptionForm);
//        return "adoption-form/new";
//    }
//
//
//    @PostMapping("/new")
//    public String createAdoptionForm(@ModelAttribute("adoptionform") AdoptionForm adoptionForm, Model model) {
//        adoptionFormService.saveAdoptionForm(adoptionForm);
//        model.addAttribute("adoptionform", adoptionForm);
//        return "adoption-form/profile";
//    }
//
//    @GetMapping("/{adoptionform_id}")
//    public String getAdoptionForm(@PathVariable Integer adoptionform_id,Model model) {
//        try {
//            model.addAttribute("adoptionform", adoptionFormService.getAdoptionForm(adoptionform_id));
//            return "adoption-form/adoption-form";
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Form not found", e);
//        }
//    }
//
//
//
////    @DeleteMapping("/{adoptionform_id}")
////    public ResponseEntity<String>  deleteAdoptionForm(@PathVariable Integer adoptionform_id) {
////        boolean result = adoptionFormService.deleteAdoptionForm(adoptionform_id);
////        if (result) {
////            return ResponseEntity.status(HttpStatus.OK).body("AF deleted successfully");
////        }
////        else {
////            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("AF not found");
////        }
////    }
//}
