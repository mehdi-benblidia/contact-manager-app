package be.poliscrypts.contactmanagerapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;


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

    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "adress")
    private String adress;

    @Column(name = "tva")
    private String tva;

    @ManyToMany
    @JoinTable(name = "company_contact",
            joinColumns = @JoinColumn(name = "company_id")
            , inverseJoinColumns = @JoinColumn(name = "contact_id"))
    private Set<Contact> contactsList = new HashSet<>();
}
