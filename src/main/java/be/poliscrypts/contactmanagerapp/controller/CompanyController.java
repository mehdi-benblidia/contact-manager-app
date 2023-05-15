package be.poliscrypts.contactmanagerapp.controller;

import be.poliscrypts.contactmanagerapp.model.*;
import be.poliscrypts.contactmanagerapp.service.*;
import io.swagger.v3.oas.annotations.*;
import lombok.extern.apachecommons.*;
import org.springframework.http.*;
//import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/be/company")
@CommonsLog
public class CompanyController {
    private final CompanyService companyService;
    private final ContactService contactService;

    public CompanyController(CompanyService companyService, ContactService contactService) {
        this.companyService = companyService;
        this.contactService = contactService;
    }

    @GetMapping("/all")
    @Operation(summary = "Get all companies", description = "Find all companies in the application")
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companiesList = companyService.findAllCompanies();
        return new ResponseEntity<>(companiesList, HttpStatus.OK);
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable("id") Long id) {
        Company company = companyService.findCompanyById(id);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<Company> getCompanyByUuid(@PathVariable("uuid") UUID uuid) {
        Company company = companyService.findCompanyByUuid(uuid);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @PostMapping("/add")
    @Operation(summary = "Add anew company")
    public ResponseEntity<Company> addCompany(@RequestBody Company company) {
        Company newCompany = companyService.addCompany(company);
        return new ResponseEntity<>(newCompany, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @Operation(summary = "Update an company")
    public ResponseEntity<Company> updateCompany(@RequestBody Company company) {
        Company companyUpdated = companyService.updateCompany(company);
        return new ResponseEntity<>(companyUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{uuid}")
    @Operation(summary = "Delete a company by UUID")
    public ResponseEntity<Void> deleteCompany(@PathVariable UUID uuid) {
        try {
            companyService.deleteCompanyByUuid(uuid);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/{companyUuid}/contact")
    @Operation(summary = "Add a contact to a company")
    public ResponseEntity<Void> addContactToCompany(@PathVariable("companyUuid") UUID companyUuid,
                                                    @RequestBody Contact contactReqst) {
        Company company = companyService.findCompanyByUuid(companyUuid);
        Contact contact = contactService.findContactByUuid(contactReqst.getUuid());
        companyService.addContactToCompany(company, contact.getId());
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
