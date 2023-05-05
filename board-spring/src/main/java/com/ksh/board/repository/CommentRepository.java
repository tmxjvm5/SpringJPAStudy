package com.ksh.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ksh.board.entity.CommentEntity;
@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

}
