package be.poliscrypts.contactmanagerapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
import java.util.Set;
import java.util.HashSet;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "uuid", updatable = false)
    private UUID uuid;

    @Column(name = "address")
    private String address;

    @Column(name = "tva")
    private String tva;

    @ManyToMany
    @JoinTable(name = "company_contact",
            joinColumns = @JoinColumn(name = "company_id")
            , inverseJoinColumns = @JoinColumn(name = "contact_id"))
    private Set<Contact> contactsList = new HashSet<>();
}
