package com.technokratos.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

/** Theme of the course */
@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "theme")
public class ThemeEntity extends ExtendedAbstractEntity {

    @Column(nullable = false, columnDefinition = "VARCHAR(200)")
    private String name;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private CourseEntity course;

    @OneToMany(mappedBy="theme")
    private List<LessonEntity> lessons;
}

