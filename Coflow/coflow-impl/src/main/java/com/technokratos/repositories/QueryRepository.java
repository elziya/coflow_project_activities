package com.technokratos.repositories;

import com.technokratos.models.QueryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QueryRepository extends MongoRepository<QueryEntity, String> {

    Optional<QueryEntity> findByAccountIdAndResourceId(UUID accountId, UUID resourceId);

    List<QueryEntity> findByAccountId(UUID accountId);
}

