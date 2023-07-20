package com.technokratos.models;

import com.technokratos.dto.enums.EducationCategory;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/** Education category can be process, desired, finished*/

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "course_account_category")
public class EducationCategoryEntity extends AbstractEntity{

    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    @Enumerated(value = EnumType.STRING)
    private EducationCategory name;
}

