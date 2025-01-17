package gr.hua.dit.ds.ds_lab_2024.repositories;

import gr.hua.dit.ds.ds_lab_2024.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Integer> {
}
