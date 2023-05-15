package be.poliscrypts.contactmanagerapp.service;

import be.poliscrypts.contactmanagerapp.model.*;

import java.util.*;

public interface CompanyService {

    List<Company> findAllCompanies();

    Company findCompanyById(Long id);

    Company findCompanyByUuid(UUID uuid);

    Company addCompany(Company company);

    Company updateCompany(Company company);

    void deleteCompanyByUuid(UUID uuid);

    void addContactToCompany(Company company, Long contactId);

}
