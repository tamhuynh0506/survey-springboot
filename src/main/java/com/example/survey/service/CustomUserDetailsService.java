package com.example.survey.service;

import com.example.survey.entity.CustomUserDetails;
import com.example.survey.entity.User;
import com.example.survey.exception.NotFoundException;
import com.example.survey.repository.UserRepository;
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
        User user = userRepository.findByEmail(email)
                .orElseThrow(NotFoundException::new);

        return new CustomUserDetails(
               user.getId(),
               user.getEmail(),
               user.getPassword(),
               AuthorityUtils.createAuthorityList("ROLE_" + user.getRole().name())
       );
    }
}
