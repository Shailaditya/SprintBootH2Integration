package com.shail.h2integration.repository;

import org.springframework.data.repository.CrudRepository;

import com.shail.h2integration.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
