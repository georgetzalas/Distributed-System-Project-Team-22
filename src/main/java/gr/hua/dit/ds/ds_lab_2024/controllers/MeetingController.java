package gr.hua.dit.ds.ds_lab_2024.controllers;


import gr.hua.dit.ds.ds_lab_2024.entities.*;
import gr.hua.dit.ds.ds_lab_2024.service.*;
import gr.hua.dit.ds.ds_lab_2024.entities.User;
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

import java.util.Date;

@Controller
@RequestMapping("meeting")
public class MeetingController {

    private final ShelterService shelterService;
    private final UserService userService;
    private final ClientService clientService;
    private final AdoptionFormService adoptionFormService;
    private final PetService petService;
    private final MeetingFormService meetingFormService;

    public MeetingController(PetService petService, ShelterService shelterService, UserService userService, ClientService clientService, AdoptionFormService adoptionFormService, MeetingFormService meetingFormService) {
        this.petService = petService;
        this.shelterService = shelterService;
        this.userService = userService;
        this.clientService = clientService;
        this.adoptionFormService = adoptionFormService;
        this.meetingFormService = meetingFormService;
    }



    @Secured({"ROLE_ADMIN","ROLE_USER","ROLE_SHELTER"})
    @GetMapping("")
    public String getMeetings(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        if (userDetails.getAuthorities().toString().equals("[ROLE_USER]")) {

            User user = userService.findByEmail(userDetails.getUsername()).get();
            if (clientService.getClient(user.getId())!=null) {

                model.addAttribute("meetings",clientService.getClient(user.getId()).getMeetingforms());
                return "meeting/list";
            }

        } else if (userDetails.getAuthorities().toString().equals("[ROLE_SHELTER]")) {

            User user = userService.findByEmail(userDetails.getUsername()).get();
            if (shelterService.getShelter(user.getId())!=null) {
                model.addAttribute("meetings",shelterService.getShelter(user.getId()).getMeetingforms());
                return "meeting/list";
            }
        }
        return "error/error-403";
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("new")
    public String newMeeting( Model model) {
        MeetingForm form = new MeetingForm();
        model.addAttribute("form", form);
        model.addAttribute("shelters", shelterService.getShelters());
        return "meeting/new";
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @PostMapping("/new")
    public String createMeeting(@Valid @ModelAttribute("form") MeetingForm form,@AuthenticationPrincipal UserDetails userDetails, BindingResult theBindingResult, Model model) {
        if (theBindingResult.hasErrors()) {
            System.out.println("error");
            theBindingResult.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
            return "index";
        } else {
            form.setClient(clientService.getClient(userService.findByEmail(userDetails.getUsername()).get().getId()));
            form.setApproved(false);
            meetingFormService.saveMeetingForm(form);
            return "index";
        }

    }
    @Secured({"ROLE_ADMIN","ROLE_SHELTER"})
    @GetMapping("/{meeting_id}/approve")
    public String approveMeeting(@PathVariable Integer meeting_id,Model model) {
        MeetingForm form = meetingFormService.getMeetingForm(meeting_id);
        form.setApproved(true);
        meetingFormService.saveMeetingForm(form);
        model.addAttribute("form", meetingFormService.getMeetingForms());

        return "meeting/list";
    }
}
