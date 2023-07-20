package com.technokratos.repositories;

import com.technokratos.models.FileInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FileInfoRepository extends JpaRepository<FileInfoEntity, UUID> {
}
