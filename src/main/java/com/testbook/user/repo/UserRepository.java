package com.testbook.user.repo;

import com.testbook.base.repo.BaseRepository;
import com.testbook.user.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<UserEntity, String> {

}