package com.technokratos.util.mapper;

import com.technokratos.dto.response.FileInfoResponse;
import com.technokratos.models.FileInfoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface FileInfoMapper {

    FileInfoResponse toResponse(FileInfoEntity fileInfoEntity);
}

