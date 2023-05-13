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

    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "adress")
    private String adress;

    @Column(name = "tva")
    private String tva;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private String statut;

    @ManyToMany(mappedBy = "contactsList")
    private Set<Company> companiesList = new HashSet<>();
}
