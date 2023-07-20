package com.technokratos.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "teacher")
public class TeacherEntity extends ExtendedAbstractEntity {
    /** Information about teacher*/
    private String info;

    @OneToOne
    private AccountEntity account;
}
