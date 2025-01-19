package com.asbel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asbel.model.entity.Local;

@Repository
public interface LocalRepository extends JpaRepository<Local, Integer> {

}
