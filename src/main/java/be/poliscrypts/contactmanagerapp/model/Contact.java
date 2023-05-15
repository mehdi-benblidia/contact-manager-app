package be.poliscrypts.contactmanagerapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "uuid", updatable = false, unique = true)
    private UUID uuid;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "tva")
    private String tva;

    @Enumerated(EnumType.STRING)
    @Column(name = "statu")
    private Statu statu;

    @ManyToMany(mappedBy = "contactsList")
    private Set<Company> companiesList = new HashSet<>();

    public void removeCompany(Company company) {
        this.companiesList.remove(company);
        company.getContactsList().remove(this);
    }
}
