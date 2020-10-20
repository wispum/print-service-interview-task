package org.home.printservice.repository;

import org.home.printservice.model.Job;
import org.home.printservice.model.request.PrintFilterRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;

import java.util.List;


public class PrintFilterJobRepositoryImpl implements PrintFilterJobRepository {
    private static final String DEVICE_ID_FIELD = "deviceId";
    private static final String CREATED_AT_FIELD = "createdAt";
    private static final String TYPE_FIELD = "type";
    private static final String USER_ID_FIELD = "userId";
    private final MongoTemplate mongoTemplate;

    public PrintFilterJobRepositoryImpl(MongoTemplate template) {
        this.mongoTemplate = template;
    }

    @Override
    public List<Job> findAllByRequestFilter(PrintFilterRequest request) {
        Query query = new Query();
        if (!StringUtils.isEmpty(request.getDeviceId())) {
            query.addCriteria(Criteria.where(DEVICE_ID_FIELD).is(request.getDeviceId()));
        }
        if (!StringUtils.isEmpty(request.getUserId())) {
            query.addCriteria(Criteria.where(USER_ID_FIELD).is(request.getUserId()));
        }
        if (request.getType() != null) {
            query.addCriteria(Criteria.where(TYPE_FIELD).is(request.getType().name()));
        }
        if (request.getTimeFrom() != null) {
            query.addCriteria(Criteria.where(CREATED_AT_FIELD).gte(request.getTimeFrom()));
        }
        if (request.getTimeTo() != null) {
            query.addCriteria(Criteria.where(CREATED_AT_FIELD).lte(request.getTimeTo()));
        }
        query.with(Sort.by(Sort.Direction.ASC, CREATED_AT_FIELD));
        return mongoTemplate.find(query, Job.class);
    }
}
