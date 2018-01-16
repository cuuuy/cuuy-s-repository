package com.cwy.loving.infrastructure.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cwy.loving.domain.mode.entity.Member;

public interface MemberDao extends JpaRepository <Member, Integer> {

}