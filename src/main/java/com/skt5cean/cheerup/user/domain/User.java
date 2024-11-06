package com.skt5cean.cheerup.user.domain;

import com.skt5cean.cheerup.company.domain.Company;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
@Getter
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private Long kakaoId; //로그인 식별키

    private boolean isUsed = false;

    private String nickname;

    @Setter
    private String goal;

    private String keyword1;

    private String keyword2;

    private String keyword3;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    List<Company> companies = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    List<TotalDocument> totalDocuments = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    List<TotalInterview> totalInterviews = new ArrayList<>();

    public static User createUser(String nickname, Long kakaoId) {
        User user = new User();
        user.nickname = nickname;
        user.kakaoId = kakaoId;
        return user;
    }

    public void updateKeywords(String keyword1, String keyword2, String keyword3){
        this.keyword1 = keyword1;
        this.keyword2 = keyword2;
        this.keyword3 = keyword3;
    }

    //Jwt 설정을 위한 UserDetails 메소드
    @ElementCollection(fetch = FetchType.EAGER) //roles 컬렉션
    private List<String> roles = new ArrayList<>();
    @Override   //사용자의 권한 목록 리턴
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return kakaoId.toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    //Jwt 설정 종료
}
