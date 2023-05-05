package com.ksh.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ksh.board.entity.HeartEntity;
@Repository
public interface HeartRepository extends JpaRepository<HeartEntity, Integer> {

}
