package com.panel.admin.user.user;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RootRepository extends MongoRepository<Root, ObjectId> {


    List<Root> findAll();
    List<Root> findAllBy_id(String leadId);
    List<Root> findAllByCustomerId(String customerId);
    List<Root> findAllByProductType(String productType);

}