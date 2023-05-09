package be.poliscrypts.contactmanagerapp.repository;

import be.poliscrypts.contactmanagerapp.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Long> {

    Company findCompanyById(Long id);
    Company findCompanyByUuid(UUID uuid);
    void deleteByUUID(UUID uuid);

}
