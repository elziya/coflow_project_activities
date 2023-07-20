package com.technokratos.models;

import com.technokratos.dto.enums.Role;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "role")
public class RoleEntity extends ExtendedAbstractEntity {

    @Enumerated(EnumType.STRING)
    @Column(length = 64)
    private Role role;
}
