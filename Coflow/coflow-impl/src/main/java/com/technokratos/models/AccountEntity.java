package com.technokratos.models;

import com.technokratos.dto.enums.State;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.CascadeType.*;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account", uniqueConstraints = { @UniqueConstraint(columnNames = "email")})
public class AccountEntity extends ExtendedAbstractEntity {

    @Column(nullable = false, columnDefinition = "VARCHAR")
    private String email;

    @Column(name = "password_hash", columnDefinition = "VARCHAR")
    private String passwordHash;

    @OneToMany(mappedBy="account")
    private Set<AccountRoleEntity> roles;

    @Column(name = "first_name", columnDefinition = "VARCHAR(150)")
    private String firstName;

    @Column(name = "last_name", columnDefinition = "VARCHAR(150)")
    private String lastName;

    @OneToOne
    private FileInfoEntity photo;

    @OneToMany(mappedBy="account")
    private Set<EducationEntity> courses;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @OneToMany(mappedBy="account", cascade = {PERSIST, REFRESH})
    private Set<ConfirmationCodeEntity> codes;
}
