package com.technokratos.models;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "course")
public class CourseEntity extends ExtendedAbstractEntity{

    @Column(nullable = false, columnDefinition = "VARCHAR(200)")
    private String name;

    private String description;

    @Column(name = "for_whom")
    private String forWhom;

    @Column(name = "access_code", nullable = true, columnDefinition = "VARCHAR(10)")
    private UUID accessCode;

    @Column(name = "is_finished")
    private Boolean isFinished;

    @ManyToOne
    @JoinColumn(name = "language_id", nullable = false)
    private LanguageEntity language;

    @ManyToOne
    @JoinColumn(name = "course_type_id", nullable = false)
    private CourseTypeEntity courseType;

    @OneToOne
    private FileInfoEntity photo;

    @OneToOne
    @JoinColumn(name = "certification_template_id", referencedColumnName = "id")
    private FileInfoEntity certificationTemplate;

    @OneToMany(mappedBy="course")
    private List<FeedbackEntity> feedbacks;

    @OneToMany(mappedBy="course", fetch = FetchType.EAGER)
    private Set<EducationEntity> students;

    @OneToMany(mappedBy="course")
    private Set<ThemeEntity> themes;
}

