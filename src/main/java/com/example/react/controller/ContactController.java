package com.example.react.controller;

import com.example.react.exception.ContactException;
import com.example.react.model.Contact;
import com.example.react.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class ContactController {
    private ContactRepository contactRepository;

    ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/contacts")
    public ResponseEntity<List<Contact>> getContacts() {
        List<Contact> contacts = contactRepository.findAll();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @PostMapping("/contacts")
    public ResponseEntity createContact(@Valid @RequestBody Contact contact) {
        contactRepository.save(contact);
        log.info("String the new data {}", contact.getName());
        return new ResponseEntity(contact, HttpStatus.CREATED);
    }

    @GetMapping("/contacts/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable("id") String id)
    throws ContactException {
        Optional<Contact> contact = contactRepository.findById(id);
        if (!contact.isPresent())
            throw new ContactException("Unable to find the contact");

        return new ResponseEntity(contact, HttpStatus.OK);
    }

    @PutMapping("/contacts/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable("id") String id,
                                                 @Valid @RequestBody Contact contact)
        throws ContactException {
        Optional<Contact> optionalContact = contactRepository
                .findById(id);

        if (!optionalContact.isPresent())
            throw new ContactException("Unable to find the contact");


        Contact newContact = optionalContact.get();
        newContact.setAddress(contact.getAddress());
        newContact.setCity(contact.getCity());
        newContact.setEmail(contact.getEmail());
        newContact.setName(contact.getName());
        newContact.setPhone(contact.getPhone());
        log.info("Updating the contact name {}", newContact.getName());
        contactRepository.save(newContact);

        return new ResponseEntity<>(newContact, HttpStatus.OK);
    }

    @DeleteMapping("/contacts/{id}")
    public String deleteContact(@PathVariable("id") String id)
        throws ContactException {
        Optional<Contact> optionalContact = contactRepository
                .findById(id);

        if (!optionalContact.isPresent())
            throw new ContactException("Unable to find the contact");

        contactRepository.delete(optionalContact.get());
        return "Contact deleted successfully";
    }
}
