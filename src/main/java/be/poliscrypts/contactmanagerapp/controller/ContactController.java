package be.poliscrypts.contactmanagerapp.controller;

import be.poliscrypts.contactmanagerapp.model.*;
import be.poliscrypts.contactmanagerapp.service.*;
import be.poliscrypts.contactmanagerapp.validator.*;
import io.swagger.v3.oas.annotations.*;
import lombok.extern.apachecommons.*;
import org.springframework.http.*;
//import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/be/contact")
@CommonsLog
public class ContactController {

    private final ContactService contactService;
    private final ContactValidator contactValidator;

    public ContactController(ContactService contactService, ContactValidator contactValidator) {
        this.contactService = contactService;
        this.contactValidator = contactValidator;
    }

    @GetMapping("/all")
    @Operation(summary = "Get all contacts", description = "Find all contacts")
    public ResponseEntity<List<Contact>> getAllContacts() {
        List<Contact> contactList = contactService.findAllContacts();
        return new ResponseEntity<>(contactList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable("id") Long id) {
        Contact contact = contactService.findContactById(id);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }


    @GetMapping("/{uuid}")
    public ResponseEntity<Contact> getContactById(@PathVariable("uuid") UUID uuid) {
        Contact contact = contactService.findContactByUuid(uuid);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }


    @PostMapping("/add")
    @Operation(summary = "Add a new contact")
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        contactValidator.validate(contact);
        Contact newContact = contactService.addContact(contact);
        return new ResponseEntity<>(newContact, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @Operation(summary = "Update an contact")
    public ResponseEntity<Contact> updateContact(@RequestBody Contact contact) {
        contactValidator.validate(contact);
        Contact contactUpdated = contactService.updateContact(contact);
        return new ResponseEntity<>(contactUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{uuid}")
    @Operation(summary = "Delete a contact by UUID")
    public ResponseEntity<HttpStatus> deleteByUuid(@PathVariable UUID uuid) {
        try {
            contactService.deleteContactByUuid(uuid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

