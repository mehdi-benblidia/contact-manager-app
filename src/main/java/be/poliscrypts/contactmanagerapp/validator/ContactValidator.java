package be.poliscrypts.contactmanagerapp.validator;

import be.poliscrypts.contactmanagerapp.exception.*;
import be.poliscrypts.contactmanagerapp.model.*;
import org.springframework.stereotype.*;

@Component
public class ContactValidator {
    public void validate(Contact contact) {
        if (isFreelanceWithNoTVA(contact)) {
            throw new FreelanceWithNoTVAException("Freelance contact must have TVA number");
        }
    }
    private boolean isFreelanceWithNoTVA(Contact contact) {
        return Statu.FREELANCE.equals(contact.getStatu()) && (contact.getTva() == null || contact.getTva().isEmpty());
    }
}
