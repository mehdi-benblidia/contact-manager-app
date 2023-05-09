package be.poliscrypts.contactmanagerapp.repository;

import be.poliscrypts.contactmanagerapp.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface ContactRepo extends JpaRepository<Contact, Long> {

    //Contact findContactById(Long id);

    Contact findContactByUuid(UUID uuid);

    Contact updateContact(Contact contact);
    void deleteByUUID(UUID uuid);
}
