package com.technokratos.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "File info response")
public class FileInfoResponse {

    @ApiModelProperty(value = "File info id")
    private UUID id;

    @ApiModelProperty(value = "Description")
    private String description;

    @ApiModelProperty(value = "File size")
    private Long size;

    @ApiModelProperty(value = "Content type", example = "image/png")
    private String type;

    @ApiModelProperty(value = "Original file name")
    private String origName;

    @ApiModelProperty(value = "Storage file name")
    private String storageName;
}

