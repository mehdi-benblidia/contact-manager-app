package be.poliscrypts.contactmanagerapp.service;

import be.poliscrypts.contactmanagerapp.exception.*;
import be.poliscrypts.contactmanagerapp.model.*;
import be.poliscrypts.contactmanagerapp.repository.*;

import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Transactional
@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepo companyRepo;
    private final ContactRepo contactRepo;

    public CompanyServiceImpl(CompanyRepo companyRepo, ContactRepo contactRepo) {
        this.companyRepo = companyRepo;
        this.contactRepo = contactRepo;
    }

    @Override
    public List<Company> findAllCompanies() {
        return companyRepo.findAll();
    }

    @Override
    public Company findCompanyById(Long id) {
        return companyRepo.findCompanyById(id);
    }

    @Override
    public Company findCompanyByUuid(UUID uuid) {
        return companyRepo.findCompanyByUuid(uuid);
    }

    @Override
    public Company addCompany(Company company) {
        company.setUuid(UUID.randomUUID());
        return companyRepo.save(company);
    }

    @Override
    public Company updateCompany(Company company) {
        Company companyToUpdate = companyRepo.findCompanyByUuid(company.getUuid());
        if (companyToUpdate == null) throw new CompanyNotFoundException("Company to be updated not found");
        company.setId(companyToUpdate.getId());
        return companyRepo.save(company);
    }

    @Override
    public void deleteCompanyByUuid(UUID uuid) {
        if (!companyRepo.existsCompanyByUuid(uuid)) {
            throw new CompanyNotFoundException("Company with uuid " + uuid + " does not exists");
        }

        companyRepo.deleteCompanyByUuid(uuid);
    }

    @Override
    public void addContactToCompany(Company company, Long contactId) {
        if (company == null) throw new CompanyNotFoundException("Company to add to the contact not founded");
        Contact contact = contactRepo.findContactById(contactId);
        if (contact != null) {
            company.getContactsList().add(contact);
            companyRepo.save(company);
        }
    }
}
