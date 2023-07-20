package com.technokratos.models;

import com.technokratos.dto.enums.CourseType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/** Course can be open, closed, free*/
@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "course_type")
public class CourseTypeEntity extends AbstractEntity{

    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    @Enumerated(value = EnumType.STRING)
    private CourseType name;
}
