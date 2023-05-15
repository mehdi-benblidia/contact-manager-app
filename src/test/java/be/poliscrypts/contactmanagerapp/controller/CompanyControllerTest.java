package be.poliscrypts.contactmanagerapp.controller;

import be.poliscrypts.contactmanagerapp.model.*;
import be.poliscrypts.contactmanagerapp.service.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.result.*;
import org.springframework.test.web.servlet.setup.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.when;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.*;

class CompanyControllerTest {
    @Mock
    private CompanyServiceImpl companyService;

    @InjectMocks
    private CompanyController companyResource;



    @Test
    void shouldReturnTheExpectedList() {

        Company company1 = new Company().builder().address("Paris").tva("20%").build();
        Company company2 = new Company().builder().address("Rennes").tva("10%").build();
        List<Company> companyList = Arrays.asList(company1, company2);

        when(companyService.findAllCompanies()).thenReturn(companyList);

        List<Company> companyListReturn = companyResource.getAllCompanies().getBody();

        assertNotNull(companyListReturn);
        assertEquals(2, companyListReturn.size());
        assertEquals(company1, companyListReturn.get(0));
        assertEquals(company2, companyListReturn.get(1));
    }

    @Test
    void shouldAddCompany() {
        Company company = new Company().builder().address("Paris").tva("20%").build();

        when(companyService.addCompany(company)).thenReturn(company);

        Company company1 = companyResource.addCompany(company).getBody();

        assertNotNull(company1);
        assertEquals(company, company1);
    }

    @Test
    void shouldUpdateCompany() {

        Company company = new Company().builder().address("Paris").tva("20%").build();

        when(companyService.updateCompany(company)).thenReturn(company);

        Company company1 = company;
        company1.setAddress("Nantes");

        Company company2 = companyResource.updateCompany(company1).getBody();

        assertNotNull(company2);
        assertEquals(company, company2);
        assertEquals("Nantes", company2.getAddress());
    }

    @Test
    void shouldDeleteCompany() {

        Company company1 = new Company().builder().address("Paris").tva("20%").build();

        doNothing().when(companyService).deleteCompanyByUuid(company1.getUuid());

        companyResource.deleteCompany(company1.getUuid());

        verify(companyService, times(1)).deleteCompanyByUuid(company1.getUuid());
    }

    @Test
    void testDeleteCompany2() throws Exception {
        doNothing().when(this.companyService).deleteCompanyByUuid((UUID) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/be/company/delete/{uuid}", UUID.fromString("cc4514c0-298b-44d9-b208-d70da05f7c11"));
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.companyResource)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetAllCompanies() throws Exception {
        org.mockito.Mockito.when(this.companyService.findAllCompanies()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/be/company/all");
        MockMvcBuilders.standaloneSetup(this.companyResource)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
