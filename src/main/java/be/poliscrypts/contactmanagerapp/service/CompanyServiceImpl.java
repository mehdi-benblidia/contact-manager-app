package be.poliscrypts.contactmanagerapp.service;

import be.poliscrypts.contactmanagerapp.model.*;
import be.poliscrypts.contactmanagerapp.repository.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@Transactional
public abstract class CompanyServiceImpl implements CompanyService {

    private CompanyRepo companyRepo;
    private final ContactRepo contactRepo;

    public CompanyServiceImpl(CompanyRepo companyRepo,
                              ContactRepo contactRepo) {
        this.companyRepo = companyRepo;
        this.contactRepo = contactRepo;
    }

    @Override
    public List<Company> findAllCompanies() {
        List<Company> companiesList = companyRepo.findAll();
        return companiesList;
    }

    @Override
    public Company findCompanyByUuid(UUID uuid) {
        Company company = companyRepo.findCompanyByUuid(uuid);
        return company;
    }

    @Override
    public Company addCompany(Company company) {
        Company newCompany = companyRepo.save(company);
        return newCompany;
    }

    @Override
    public Company updateCompany(Company company) {
        return null;
    }

    @Override
    public void deleteByUUID(UUID uuid) {
        companyRepo.deleteByUUID(uuid);
    }

    @Override
    public void addContactToCompany(Long companyId, Long contactId) {
        Optional<Company> optionalCompany = companyRepo.findCompanyById(companyId);
        Optional<Contact> optionalContact = contactRepo.findContactById(contactId);
        if (optionalCompany.isPresent() && optionalContact.isPresent()) {
            Company company = optionalCompany.get();
            Contact contact = optionalContact.get();
            company.getContactsList().add(contact);
            companyRepo.save(company);
        }
    }
}
