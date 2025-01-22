package gr.hua.dit.ds.ds_lab_2024.service;


import gr.hua.dit.ds.ds_lab_2024.entities.Pet;
import gr.hua.dit.ds.ds_lab_2024.entities.Role;
import gr.hua.dit.ds.ds_lab_2024.entities.Shelter;
import gr.hua.dit.ds.ds_lab_2024.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ShelterService {

    private ShelterRepository shelterRepository;
    private RoleRepository roleRepository;
    private PetRepository petRepository;

    private BCryptPasswordEncoder passwordEncoder;

    public ShelterService(ShelterRepository shelterRepository, RoleRepository roleRepository, PetRepository petRepository, BCryptPasswordEncoder passwordEncoder) {
        this.shelterRepository = shelterRepository;
        this.roleRepository = roleRepository;
        this.petRepository = petRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public List<Shelter> getShelters() {

        return shelterRepository.findAll();
    }

    @Transactional
    public Shelter getShelter(Integer shelter_id) {

        return shelterRepository.findById(shelter_id).get();
    }

    @Transactional
    public Integer saveShelter(Shelter shelter) {
        String passwd= shelter.getPassword();
        String encodedPassword = passwordEncoder.encode(passwd);
        shelter.setPassword(encodedPassword);

        Role role = roleRepository.findByName("ROLE_SHELTER")
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        shelter.setRoles(roles);

        shelter = shelterRepository.save(shelter);
        return shelter.getId();
    }

    @Transactional
    public boolean deleteShelter(Integer shelter_id) {
        Optional<Shelter> doctor = shelterRepository.findById(shelter_id);

        if (doctor.isPresent()) {
            shelterRepository.deleteById(shelter_id);
            return true;
        }
        else {
            return false;
        }
    }


}
