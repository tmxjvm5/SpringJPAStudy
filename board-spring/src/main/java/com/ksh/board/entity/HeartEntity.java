package com.ksh.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="Heart")
@Table(name="Heart")
public class HeartEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int heartId;
	private int boardNumber;
	private String userEmail;
	private String likeUserProfile;
	private String likeUserNickname;
}
