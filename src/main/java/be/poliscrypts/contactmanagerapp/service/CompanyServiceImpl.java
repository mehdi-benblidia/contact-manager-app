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

    public CompanyServiceImpl(CompanyRepo companyRepo) {
        this.companyRepo = companyRepo;
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
    public void deleteByUUID(UUID uuid) {
        companyRepo.deleteByUUID(uuid);
    }
}
