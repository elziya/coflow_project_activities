package com.technokratos.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/** Comment on the lesson */

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comment")
public class CommentEntity extends ExtendedAbstractEntity {

    @Column(nullable = false)
    private String text;

    /** If this comment is a response to another comment, which is parent*/
    @OneToOne
    private CommentEntity parent;

    @ManyToOne
    @JoinColumn(name = "lesson_id", nullable = false)
    private LessonEntity lesson;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity author;
}
