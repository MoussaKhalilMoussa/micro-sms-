package moussed.edu.tr.service.serviceImpl;


import moussed.edu.tr.model.Person;
import moussed.edu.tr.model.dto.PersonDto;
import moussed.edu.tr.repository.PersonRepository;
import moussed.edu.tr.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class personServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtEncoder jwtEncoder;

    @Override
    public String addPerson(PersonDto personDto) {
        Person person = new Person();
        Optional<Person> persondb = personRepository.findByUserName(personDto.getUserName());
        if (persondb.isPresent()) {
            return "Username already exists";
        }

        if (personDto.getEmail().contains("admin")) {
            person.setRole("ROLE_ADMIN");
        } else {
            person.setRole("ROLE_USER");
        }
        person.setUserName(personDto.getUserName());
        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());
        person.setPassword(passwordEncoder.encode(personDto.getPassword()));
        person.setEmail(personDto.getEmail());
        person.setDepartment(personDto.getDepartment());

        personRepository.save(person);
        return "Person registered successfully";
    }

    @Override
    public Map<String, Object> login(String userName, String password) {
        Authentication authentication = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(userName, password) );
        Optional<Person> person = personRepository.findByUserName(userName);
        Map<String, Object> response = new HashMap<>();
        if (person.isEmpty()) {
            response.put("message", "Invalid username or password");
            return response;
        }
        String accessToken = generateToken(person.get(),authentication, 3600);
        response.put("access_token", accessToken);
        response.put("expires_in", 3600);
        return response;
    }

    private String generateToken(Person person, Authentication authentication, long expiryDuration) {
        Instant now = Instant.now();
        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("Moussa")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiryDuration))
                .subject(authentication.getName())
                .claim("role", authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toSet()))
                .claim("firstName", person.getFirstName())
                .claim("lastName", person.getLastName())
                .claim("userId", person.getId())
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }


    @Override
    public Person getPersonById(Integer id) {
        Optional<Person> person = personRepository.findById(id);
        return person.orElse(null);
    }

    @Override
    public Person getPersonByUserName(String userName) {
        Optional<Person> person = personRepository.findByUserName(userName);
        return person.orElse(null);
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public String addEnrollmentId(Integer personId, Long enrollmentId) {
        Optional<Person> person = personRepository.findById(personId);
        person.get().getEnrollmentIds().add(enrollmentId);
        personRepository.save(person.get());
        return "enrollment with id="+enrollmentId+" added successfully to student with id="+personId;
    }

    @Override
    public String removeEnrollmentId(Integer personId, Long enrollId) {
        Optional<Person> person = personRepository.findById(personId);
        person.get().getEnrollmentIds().remove(enrollId);
        personRepository.save(person.get());
        return "enrollment with id="+enrollId+" removed successfully from student with id="+personId;
    }

    @Override
    public String updatePerson(Person person, Integer personId) {
        Person personDb = personRepository.findById(personId).get();

        if (ObjectUtils.isEmpty(personDb)) {
            return "Person with " + personId + "not found";
        } else {
            personDb.setUserName(person.getUserName());
            personDb.setPassword(person.getPassword());
            personDb.setEmail(person.getEmail());
            personDb.setDepartment(person.getDepartment());
            personDb.setRole(person.getRole());
            personRepository.save(personDb);
            return "Person updated successfully";
        }
    }

    @Override
    public String deletePerson(Integer id) {
        personRepository.deleteById(id);
        return "Person deleted successfully";
    }

}
