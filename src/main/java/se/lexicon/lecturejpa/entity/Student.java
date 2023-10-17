package se.lexicon.lecturejpa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

@Entity
public class Student {

    @Id //jakarta
//    @GeneratedValue(strategy = GenerationType.IDENTITY) //IDENTITY means auto increment??
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")

    private String id; //needed to have UUID id

    @Column(nullable = false, length = 100)
    @Setter
    private String firstName;

    @Column(nullable = false, length = 100)
    @Setter
    private String lastName;

    @Column(nullable = false, unique = true)
    @Setter
    private String email;

    private boolean status;
    private LocalDateTime createDate;

    @Setter
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.status = true;
        this.createDate = LocalDateTime.now();
    }
}
