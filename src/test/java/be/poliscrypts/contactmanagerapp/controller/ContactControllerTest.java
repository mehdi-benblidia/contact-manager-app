package be.poliscrypts.contactmanagerapp.controller;

import be.poliscrypts.contactmanagerapp.model.*;
import be.poliscrypts.contactmanagerapp.service.*;
import org.junit.jupiter.api.*;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.*;

class ContactControllerTest {
    @Mock
    ContactServiceImpl contactService;

    @InjectMocks
    ContactController contactResource;

    @BeforeEach
    void init() {
        initMocks(this);
    }

    @Test
    void shouldReturnTheExpectedList() {

        Contact contact1 = new Contact().builder().firstName("Ghilas").lastName("Ouarez").address("PARIS").statu(Statu.FREELANCE).tva("10%").build();
        Contact contact2 = new Contact().builder().firstName("Mehdi").lastName("BENBLIDIA").address("PARIS").statu(Statu.EMPLOYEE).build();
        List<Contact> contactList = Arrays.asList(contact1, contact2);

        when(contactService.findAllContacts()).thenReturn(contactList);

        List<Contact> contactListReturn = contactResource.getAllContacts().getBody();

        assertNotNull(contactListReturn);
        assertEquals(2, contactListReturn.size());
        assertEquals(contact1, contactListReturn.get(0));
        assertEquals(contact2, contactListReturn.get(1));

    }

    @Test
    void shouldDeleteContact() {

        Contact contact1 = new Contact().builder().address("PARIS").tva("10%").build();

        doNothing().when(contactService).deleteContactByUuid(contact1.getUuid());

        contactResource.deleteByUuid(contact1.getUuid());

        verify(contactService, times(1)).deleteContactByUuid(contact1.getUuid());
    }
}
