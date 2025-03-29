package com.testbook.base.repo;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.testbook.base.entity.BaseEntity;
@NoRepositoryBean
public interface BaseRepository<REQ extends BaseEntity<?>, ID> extends MongoRepository<REQ, ID> {
}