package com.technokratos.services;

import com.technokratos.models.QueryEntity;

import java.util.List;
import java.util.UUID;

public interface QueryService {

    void saveQueryInfo(QueryEntity query);

    boolean checkResourceQueryExists(UUID accountId, UUID resourceId);

    List<QueryEntity> getAllAccountsResourcesQueries(UUID accountId);
}

