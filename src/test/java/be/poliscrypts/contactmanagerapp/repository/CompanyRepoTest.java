package be.poliscrypts.contactmanagerapp.repository;

import be.poliscrypts.contactmanagerapp.model.*;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.test.util.*;

@DataJpaTest
public class CompanyRepoTest {
    private CompanyRepo companyReposTest;

    public CompanyRepoTest(CompanyRepo companyReposTest) {
        this.companyReposTest = companyReposTest;
    }

    @Test
    void itFindCompanyById() {

        Company company = new Company().builder().adress("Chlef").tva("9%").build();
        companyReposTest.save(company);

        Company companyExp = companyReposTest.findCompanyById(company.getId());

        AssertionErrors.assertNotNull("", companyExp);
        Assertions.assertEquals(company, companyExp);
    }

}
