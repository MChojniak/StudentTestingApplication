package chojniak.marcin.testysb.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class DBUserDetailService implements UserDetailsService {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    private UserRepository userRepository;

    @Autowired
    public DBUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            this.logger.debug("Nie ma takiego użytkownika '{}'", username);

            throw new UsernameNotFoundException(
                    this.messages.getMessage("DBUserDetailService.notFound",
                            new Object[]{username}, "Nie znaleziono użytkownika {0}"));
        }

        return user;
    }
}
