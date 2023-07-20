package com.technokratos.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "feedback")
public class FeedbackEntity extends ExtendedAbstractEntity{

    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity author;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private CourseEntity course;

    @Column(nullable = false)
    private Integer estimation;
}
