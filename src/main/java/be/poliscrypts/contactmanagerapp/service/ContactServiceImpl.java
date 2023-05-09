package be.poliscrypts.contactmanagerapp.service;

import be.poliscrypts.contactmanagerapp.model.*;
import be.poliscrypts.contactmanagerapp.repository.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@Transactional
public abstract class ContactServiceImpl implements ContactService {

    private ContactRepo contactRepo;

    public ContactServiceImpl(ContactRepo contactRepo) {
        this.contactRepo = contactRepo;
    }

    @Override
    public List<Contact> findAllContacts() {
        List<Contact> contactList = contactRepo.findAll();
        return contactList;
    }

    @Override
    public Contact findContactByUuid(UUID uuid) {
        Contact contact = contactRepo.findContactByUuid(uuid);
        return contact;
    }

    @Override
    public Contact updateContact(Contact contact) {
        Contact updatedContact = contactRepo.updateContact(contact);
        return updatedContact;
    }

    @Override
    public void deleteByUUID(UUID uuid) {
        contactRepo.deleteByUUID(uuid);
    }

}
