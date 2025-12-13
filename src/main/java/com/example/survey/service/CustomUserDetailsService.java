package com.example.survey.service;

import com.example.survey.entity.CustomUserDetails;
import com.example.survey.entity.User;
import com.example.survey.repository.UserRepository;
import com.example.survey.util.FetchUtil;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = FetchUtil.orThrow(userRepository.findByEmail(email), User.class);
        return new CustomUserDetails(
               user.getId(),
               user.getEmail(),
               user.getPassword(),
               AuthorityUtils.createAuthorityList("ROLE_" + user.getRole().name())
       );
    }
}
