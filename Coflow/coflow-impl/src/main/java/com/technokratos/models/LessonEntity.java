package com.technokratos.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/** Lesson of the theme */
@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lesson")
public class LessonEntity extends ExtendedAbstractEntity {

    @Column(nullable = false, columnDefinition = "VARCHAR(200)")
    private String name;

    private String description;

    /** Educational content of the lesson*/
    @Column(name = "main_content")
    private String mainContent;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private ThemeEntity theme;

    @OneToMany(mappedBy="lesson")
    private List<CommentEntity> comments;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "lesson_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "material_id", referencedColumnName = "id"),
            name = "lesson_material")
    private Set<FileInfoEntity> materials;
}

