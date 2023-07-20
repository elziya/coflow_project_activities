package com.technokratos.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/** Language in which the course was created */
@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "language")
public class LanguageEntity extends AbstractEntity {

    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    private String name;
}
