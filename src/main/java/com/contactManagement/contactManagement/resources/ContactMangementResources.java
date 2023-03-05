package com.contactManagement.contactManagement.resources;

import com.contactManagement.contactManagement.models.Contacts;
import com.contactManagement.contactManagement.repositories.ContactsRepository;
import com.contactManagement.contactManagement.services.ContactsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContactMangementResources {

    @Autowired
    ContactsRepository notesRepository;

    @Autowired
    ContactsServices services;

    @GetMapping("/contacts")
    public ResponseEntity<List<Contacts>> getNodes(){
        return new ResponseEntity<List<Contacts>>(notesRepository.findAll(),HttpStatus.OK);
    }

    @GetMapping("/search-contact/{name}")
    public ResponseEntity<List<Contacts>> getContactNames(@PathVariable String name){
       List<Contacts> contacts =  services.searchContacts(name);
        return new ResponseEntity<List<Contacts>>(contacts,HttpStatus.OK);
    }

    @PostMapping("/create-contact")
    public ResponseEntity createContacts(@RequestBody Contacts contacts){
        services.saveContacts(contacts);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update-contact/{id}")
    public ResponseEntity updateContacts(@PathVariable String id,@RequestBody Contacts contacts){
        try {
            services.updateContacts(id, contacts);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/delete-contact/{id}")
    public ResponseEntity deleteContacts(@PathVariable String id){
        try {
            notesRepository.delete(notesRepository.findById(Integer.valueOf(id)).get());
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }

    }


}
