package com.skt5cean.cheerup.auth.service;

import com.skt5cean.cheerup.auth.component.KakaoUserInfo;
import com.skt5cean.cheerup.auth.dto.KakaoUserInfoResponse;
import com.skt5cean.cheerup.user.domain.User;
import com.skt5cean.cheerup.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KakaoAuthService {
    private final KakaoUserInfo kakaoUserInfo;
    private final UserRepository userRepository;

    @Transactional
    public Long userLogin(String token) {
        KakaoUserInfoResponse userInfo = kakaoUserInfo.getUserInfo(token);
        Optional<User> user = userRepository.findByKakaoId(userInfo.getId());
        if(user.isPresent()) {
            return user.get().getId();
        }
        else {
            User newUser = User.createUser(userInfo.getKakao_account().getProfile().getNickname(), userInfo.getId());
            return userRepository.save(newUser).getId();
        }
    }
}
