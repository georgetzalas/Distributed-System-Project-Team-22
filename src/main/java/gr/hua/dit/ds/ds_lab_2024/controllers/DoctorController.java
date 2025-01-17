package gr.hua.dit.ds.ds_lab_2024.controllers;


import gr.hua.dit.ds.ds_lab_2024.entities.AdoptionForm;
import gr.hua.dit.ds.ds_lab_2024.entities.Doctor;
import gr.hua.dit.ds.ds_lab_2024.repositories.DoctorRepository;
import gr.hua.dit.ds.ds_lab_2024.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("doctor")
public class DoctorController {
    private final DoctorService doctorService;


    public DoctorController(DoctorRepository doctorRepository, DoctorService doctorService) {

        this.doctorService = doctorService;
    }

    @GetMapping("")
    public String getDoctors(Model model) {
        model.addAttribute("doctors",doctorService.getDoctors());
        return "doctor/list";
    }

    @GetMapping("/new")
    public String newDoctor(Model model){
        Doctor doctor = new Doctor();
        model.addAttribute("doctor", doctor);
        return "doctor/new";
    }

    @PostMapping("/new")
    public String createShelter(@ModelAttribute("doctor") Doctor doctor,Model model) {
        doctorService.saveDoctor(doctor);
        model.addAttribute("doctor", doctor);
        return "doctor/profile";
    }

    @GetMapping("/{doctor_id}")
    public String getDoctor(@PathVariable Integer doctor_id,Model model) {
        try {
            model.addAttribute("doctor", doctorService.getDoctor(doctor_id));
            return "doctor/profile";
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found", e);
        }
    }

    @DeleteMapping("/{doctor_id}")
    public ResponseEntity<String>  deleteDoctor(@PathVariable Integer doctor_id) {
        boolean result = doctorService.deleteDoctor(doctor_id);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body("shelter deleted successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("shelter not found");
        }
    }
}
