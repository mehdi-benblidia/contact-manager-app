package be.poliscrypts.contactmanagerapp.service;

import be.poliscrypts.contactmanagerapp.model.*;
import be.poliscrypts.contactmanagerapp.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

public interface CompanyService {

    List<Company> findAllCompanies();

    Company findCompanyById(Long Id);

    Company findCompanyByUuid(UUID uuid);

    Company addCompany(Company company);

    Company updateCompany(Company company);

    void deleteByUUID(UUID uuid);

}
