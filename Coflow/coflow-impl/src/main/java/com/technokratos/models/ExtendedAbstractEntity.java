package com.technokratos.models;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class ExtendedAbstractEntity extends AbstractEntity{

    /** Date of creation */
    @CreationTimestamp
    @Column(name = "create_date")
    private Instant createDate;
}
