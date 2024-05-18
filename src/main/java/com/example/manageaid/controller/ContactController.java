package com.example.manageaid.controller;

import com.example.manageaid.exception.EntityNotFoundException;
import com.example.manageaid.model.Contact;
import com.example.manageaid.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/contacts")
    List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @GetMapping("/contacts/{id}")
    Contact getContactById(@PathVariable Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    @PostMapping("/contacts")
    Contact addContact(@RequestBody Contact newContact) {
        return contactRepository.save(newContact);
    }


    @PutMapping("/contacts/{id}")
    Contact updateContact(@RequestBody Contact newContact, @PathVariable Long id) {
        return contactRepository.findById(id)
                .map(entity -> {
                    entity.setName(newContact.getName());
                    entity.setEmail(newContact.getEmail());
                    entity.setPhoneNumber(newContact.getPhoneNumber());
                    entity.setAddress(newContact.getAddress());
                    entity.setCompany(newContact.getCompany());
                    return contactRepository.save(entity);
                }).orElseThrow(()-> new EntityNotFoundException(id));
    }

    @DeleteMapping("/contacts/{id}")
    String deleteContact(@PathVariable Long id) {
        if (!contactRepository.existsById(id)) {
            throw new EntityNotFoundException(id);
        }
        contactRepository.deleteById(id);
        return "Entity with id " + id + " has been successfully deleted.";
    }

}
