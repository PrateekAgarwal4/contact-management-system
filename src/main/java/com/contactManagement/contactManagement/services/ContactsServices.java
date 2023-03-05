package com.contactManagement.contactManagement.services;

import com.contactManagement.contactManagement.models.Contacts;
import com.contactManagement.contactManagement.repositories.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class ContactsServices {

    @Autowired
    ContactsRepository contactsRepository;
    public void saveContacts(Object contacts){
        contactsRepository.save((Contacts) contacts);
    }

    public void updateContacts(String id, Contacts contacts) {
        try {
            Contacts userContact = contactsRepository.findById(Integer.valueOf(id)).get();

            if (contacts.getEmail() != null) {
                userContact.setEmail(contacts.getEmail());
            }
            if (contacts.getFirstName() != null) {
                userContact.setFirstName(contacts.getFirstName());
            }
            if (contacts.getLastName() != null) {
                userContact.setLastName(contacts.getFirstName());
            }
            if (contacts.getPhoneNumber() != null) {
                userContact.setPhoneNumber(contacts.getPhoneNumber());
            }
            contactsRepository.save(userContact);
        }catch (Exception e){
            throw e;
        };
    }

    public List<Contacts> searchContacts(String name) {
        List<Contacts> contactsByFirstName = contactsRepository.findByFirstName(name);
        List<Contacts> contactsByLastName = contactsRepository.findByLastName(name);
        List<Contacts> contactsByEmail = contactsRepository.findByEmail(name);

        Set<Contacts> set = new LinkedHashSet<>();
        set.addAll(contactsByFirstName);
        set.addAll(contactsByLastName);
        set.addAll(contactsByEmail);

        return new ArrayList<>(set);
    }
}
