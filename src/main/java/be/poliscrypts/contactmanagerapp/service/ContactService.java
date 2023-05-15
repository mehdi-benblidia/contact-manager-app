package be.poliscrypts.contactmanagerapp.service;

import be.poliscrypts.contactmanagerapp.model.*;

import java.util.*;

public interface ContactService {

    List<Contact> findAllContacts();

    Contact findContactById(Long id);

    Contact findContactByUuid(UUID uuid);

    Contact addContact(Contact contact);

    Contact updateContact(Contact contact);

    void deleteContactByUuid(UUID uuid);
}
