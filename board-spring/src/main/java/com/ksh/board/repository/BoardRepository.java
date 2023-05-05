package com.ksh.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ksh.board.entity.BoardEntity;
@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {

}
