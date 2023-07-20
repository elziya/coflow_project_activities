package com.technokratos.services.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.technokratos.models.QueryEntity;
import com.technokratos.repositories.QueryRepository;
import com.technokratos.services.QueryService;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MongoQueryServiceImpl implements QueryService {

    private final QueryRepository queryRepository;

    @Override
    public void saveQueryInfo(QueryEntity query) {
        if (!checkResourceQueryExists(query.getAccountId(), query.getResourceId())) {
            queryRepository.save(query);
        }
    }

    @Override
    public boolean checkResourceQueryExists(UUID accountId, UUID resourceId) {
        return queryRepository.findByAccountIdAndResourceId(accountId, resourceId).isPresent();
    }

    @Override
    public List<QueryEntity> getAllAccountsResourcesQueries(UUID accountId) {
        return queryRepository.findByAccountId(accountId);
    }
}

