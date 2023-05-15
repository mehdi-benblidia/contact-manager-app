package be.poliscrypts.contactmanagerapp.repository;

import be.poliscrypts.contactmanagerapp.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface ContactRepo extends JpaRepository<Contact, Long> {
    Contact findContactById(Long id);

    Contact findContactByUuid(UUID uuid);

    Boolean existsContactByUuid(UUID uuid);

    void deleteContactByUuid(UUID uuid);
}
