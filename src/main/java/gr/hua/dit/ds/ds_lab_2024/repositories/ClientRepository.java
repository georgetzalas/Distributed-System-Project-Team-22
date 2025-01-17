package gr.hua.dit.ds.ds_lab_2024.repositories;

import gr.hua.dit.ds.ds_lab_2024.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
