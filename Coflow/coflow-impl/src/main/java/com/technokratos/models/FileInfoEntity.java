package com.technokratos.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/** File's information */
@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "file_info")
public class FileInfoEntity extends ExtendedAbstractEntity {
    /** Description */
    @Column(nullable = false, columnDefinition = "VARCHAR")
    private String description;

    /** Size */
    @Column(nullable = false)
    private Long size;

    /** Content type */
    @Column(name = "content_type", nullable = false, columnDefinition = "VARCHAR(200)")
    private String type;

    /** Original file name */
    @Column(name = "orig_name", nullable = false, columnDefinition = "VARCHAR(200)")
    private String origName;

    /** File's id in MongoDB */
    @Column(name = "storage_name", nullable = false, columnDefinition = "VARCHAR(24)")
    private String storageName;
}
