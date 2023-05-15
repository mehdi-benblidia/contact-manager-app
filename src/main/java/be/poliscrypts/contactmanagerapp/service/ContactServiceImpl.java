package be.poliscrypts.contactmanagerapp.service;

import be.poliscrypts.contactmanagerapp.exception.*;
import be.poliscrypts.contactmanagerapp.model.*;
import be.poliscrypts.contactmanagerapp.repository.*;
import be.poliscrypts.contactmanagerapp.validator.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {

    private final ContactRepo contactRepo;
    private final ContactValidator contactValidator;

    public ContactServiceImpl(ContactRepo contactRepo, ContactValidator contactValidator) {
        this.contactRepo = contactRepo;
        this.contactValidator = contactValidator;
    }

    @Override
    public List<Contact> findAllContacts() {
        return contactRepo.findAll();
    }

    @Override
    public Contact findContactById(Long id) {
        return contactRepo.findContactById(id);
    }

    @Override
    public Contact findContactByUuid(UUID uuid) {
        return contactRepo.findContactByUuid(uuid);
    }

    @Override
    public Contact addContact(Contact contact) {
        contactValidator.validate(contact);
        contact.setUuid(UUID.randomUUID());
        return contactRepo.save(contact);
    }

    @Override
    public Contact updateContact(Contact contact) {
        Contact contactToUpdate = contactRepo.findContactByUuid(contact.getUuid());
        if (contactToUpdate == null) throw new ContactNotFoundException("Contact to be updated not found");
        contact.setId(contactToUpdate.getId());
        return contactRepo.save(contact);
    }

    @Override
    public void deleteContactByUuid(UUID uuid) {
        Boolean existingContact = contactRepo.existsContactByUuid(uuid);
        if (existingContact) {
            Contact contact = this.contactRepo.findContactByUuid(uuid);
            if (contact.getCompaniesList() != null && !contact.getCompaniesList().isEmpty()) {
                for (Company company : contact.getCompaniesList()) {
                    contact.removeCompany(company);
                }
            }
        } else {
            throw new ContactNotFoundException(
                    "Contact with uuid " + uuid + " does not exists");
        }

        contactRepo.deleteContactByUuid(uuid);
    }

}
