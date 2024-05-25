package com.naeshu.naeshubackend.user.service;

import com.naeshu.naeshubackend.common.ConflictException;
import com.naeshu.naeshubackend.common.UnAuthorizedException;
import com.naeshu.naeshubackend.user.User;
import com.naeshu.naeshubackend.user.dto.request.UserLoginRequest;
import com.naeshu.naeshubackend.user.dto.request.UserSignUpRequest;
import com.naeshu.naeshubackend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void SignUp(UserSignUpRequest signUpRequest){
        if(userRepository.findByUserId(signUpRequest.userId()).isPresent()){
            throw new ConflictException("해당 아이디로 가입한 유저 아이디가 있습니다.");
        }
        User user = new User(
                signUpRequest.userId(),
                signUpRequest.userPassword(),
                signUpRequest.userName(),
                0L,
                signUpRequest.userBirth()
        );
        userRepository.save(user);
    }

    public Long Login(UserLoginRequest userLoginRequest){
        User user = userRepository.findByUserId(userLoginRequest.userId()).orElseThrow(
                () -> new UnAuthorizedException("존재하지 않는 아이디입니다.")
        );
        user.login(userLoginRequest.userPassword());
        return user.getId();
    }

    public String findNameById(Long userId){
        return userRepository.findById(userId).get().getUserName();
    }

}
