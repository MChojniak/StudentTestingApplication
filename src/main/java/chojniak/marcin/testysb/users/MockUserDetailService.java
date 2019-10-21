package chojniak.marcin.testysb.users;

import chojniak.marcin.testysb.users.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import java.util.HashMap;
import java.util.Map;

public class MockUserDetailService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    private Map<String, UserDetails> users = new HashMap<>();

    {
        users.put("user", User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
        users.put("admin", User.withDefaultPasswordEncoder().username("admin").password("password").roles("USER").build());
        logger.info("password: {}", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("password"));
        logger.info("password: {}", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("password"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = users.get(username);
        if (userDetails == null)
            throw new UsernameNotFoundException("Nie ma takiego użytkownika");
        return userDetails;
    }
}
