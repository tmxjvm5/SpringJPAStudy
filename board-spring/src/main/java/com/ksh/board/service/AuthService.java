package com.ksh.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ksh.board.dto.ResponseDto;
import com.ksh.board.dto.SignInDto;
import com.ksh.board.dto.SignInResponseDto;
import com.ksh.board.dto.SignUpDto;
import com.ksh.board.entity.UserEntity;
import com.ksh.board.repository.UserRepository;
import com.ksh.board.security.TokenProvider;

@Service
public class AuthService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TokenProvider tokenProvider;
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public ResponseDto<?> signUp(SignUpDto dto) {
		String userEmail = dto.getUserEmail();
		String userPassword = dto.getUserAddress();
		String userPasswordCheck = dto.getUserPasswordCheck();

		// Email 중복 확인
		try {
			if (userRepository.existsById(userEmail))
				return ResponseDto.setFailed("Existed Email!");
		} catch (Exception e) {
			return ResponseDto.setFailed("Data Base Error!");
		}

		// 비밀번호가 서로 다르면 failed response 반환!
		if (!userPassword.equals(userPasswordCheck))
			return ResponseDto.setFailed("Password does not matched!");

		// UserEntity 생성
		UserEntity userEntity = new UserEntity(dto);
		
		// 비밀번호 암호화
		String encodedPassword = passwordEncoder.encode(userPassword);
		userEntity.setUserPassword(encodedPassword);

		// UserRepository를 이용해서 데이터베이스에 Entity 저장!!
		try {
			userRepository.save(userEntity);
		} catch (Exception e) {
			return ResponseDto.setFailed("Data Base Error!");
		}
		// 성공시 success reponse 반환
		return ResponseDto.setSuccess("SignUp Success!", null);
	}

	public ResponseDto<SignInResponseDto> signIn(SignInDto dto) {
		String userEmail = dto.getUserEmail();
		String userPassword = dto.getUserPassword();
		
		UserEntity userEntity = null;
		try {
			userEntity = userRepository.findByUserEmail(userEmail);
			// 잘못된 이메일
			if(userEntity == null) return ResponseDto.setFailed("Sign In Failed");
			// 잘못된 패스워드
			if(passwordEncoder.matches(userPassword, userEntity.getUserPassword()))
				return ResponseDto.setFailed("Sign In Failed");
		} catch (Exception error) {
			return ResponseDto.setFailed("Database Error");
		}

		userEntity.setUserPassword("");

		String token = tokenProvider.create(userEmail);
		int exprTime = 3600000;

		SignInResponseDto signInResponseDto = new SignInResponseDto(token, exprTime, userEntity);
		return ResponseDto.setSuccess("Sign In Success", signInResponseDto);

	}

}
