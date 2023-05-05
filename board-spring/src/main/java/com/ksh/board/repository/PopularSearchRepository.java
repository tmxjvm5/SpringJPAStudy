package com.ksh.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ksh.board.entity.PopularSearchEntity;
@Repository
public interface PopularSearchRepository extends JpaRepository<PopularSearchEntity, String> {

}
