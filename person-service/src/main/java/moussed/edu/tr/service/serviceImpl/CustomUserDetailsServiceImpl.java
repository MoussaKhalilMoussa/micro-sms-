package moussed.edu.tr.service.serviceImpl;

import moussed.edu.tr.model.CustomUserDetails;
import moussed.edu.tr.model.Person;
import moussed.edu.tr.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private PersonRepository personRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person personEntity = personRepository.findByUserName(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found"));

        return new CustomUserDetails(personEntity);
    }
}
