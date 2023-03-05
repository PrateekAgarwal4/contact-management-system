package com.contactManagement.contactManagement.repositories;

import com.contactManagement.contactManagement.models.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactsRepository extends JpaRepository<Contacts, Integer> {
    List<Contacts> findByFirstName(String firstName);

    List<Contacts> findByLastName(String lastName);

    List<Contacts> findByEmail(String email);
}
