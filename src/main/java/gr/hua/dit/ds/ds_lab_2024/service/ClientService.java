package gr.hua.dit.ds.ds_lab_2024.service;


import gr.hua.dit.ds.ds_lab_2024.entities.*;
import gr.hua.dit.ds.ds_lab_2024.repositories.AdoptionFormRepository;
import gr.hua.dit.ds.ds_lab_2024.repositories.ClientRepository;
import gr.hua.dit.ds.ds_lab_2024.repositories.MeetingFormRepository;
import gr.hua.dit.ds.ds_lab_2024.repositories.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ClientService {

    private final RoleRepository roleRepository;
    private ClientRepository clientRepository;
    private AdoptionFormRepository adoptionFormRepository;
    private MeetingFormRepository meetingFormRepository;
    private BCryptPasswordEncoder passwordEncoder;


    public ClientService(BCryptPasswordEncoder passwordEncoder, ClientRepository clientRepository, AdoptionFormRepository adoptionFormRepository, MeetingFormRepository meetingFormRepository, RoleRepository roleRepository) {
        this.clientRepository = clientRepository;
        this.adoptionFormRepository = adoptionFormRepository;
        this.meetingFormRepository = meetingFormRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public List<Client> getClients() {

        return clientRepository.findAll();
    }

    @Transactional
    public Client getClient(Integer id) {

        return clientRepository.findById(id).get();
    }

    @Transactional
    public Integer saveClient(Client client) {
        String passwd= client.getPassword();
        String encodedPassword = passwordEncoder.encode(passwd);
        client.setPassword(encodedPassword);

        Role role = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        client.setRoles(roles);

        client = clientRepository.save(client);
        return client.getId();
    }

    @Transactional
    public boolean deleteDoctor(Integer doctor_id) {
        Optional<Client> doctor = clientRepository.findById(doctor_id);

        if (doctor.isPresent()) {
            clientRepository.deleteById(doctor_id);
            return true;
        }
        else {
            return false;
        }
    }

    @Transactional
    public void assignAdoptionFormToClient(Integer adoptionform_id, Client client) {
        AdoptionForm form = adoptionFormRepository.findById(adoptionform_id).get();
        form.setClient(client);
        adoptionFormRepository.save(form);
    }

    @Transactional
    public void assignMeetingFormToClient(Integer meetingform_id, Client client) {
        MeetingForm form = meetingFormRepository.findById(meetingform_id).get();
        form.setClient(client);
        meetingFormRepository.save(form);
    }
}
