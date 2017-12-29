package com.cwy.loving.infrastructure.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cwy.loving.domain.model.entity.Hello;

public interface HelloDao extends JpaRepository <Hello, Integer> {

}