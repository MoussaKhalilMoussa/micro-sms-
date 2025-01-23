package moussed.edu.tr.feignClient;

import moussed.edu.tr.config.FeignClientConfig;
import moussed.edu.tr.model.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name ="person-identity-app", url ="http://person-identity-app:3000/api/user/", configuration = FeignClientConfig.class)
public interface PersonClient {

    @GetMapping("/getPersonById")
    public ResponseEntity<Person> getPersonById(@RequestParam Integer id);
    @GetMapping("/getPersonByUserName")
    public ResponseEntity<Person> getPersonByUserName(@RequestParam String userName) ;
    @GetMapping("/getAllPersons")
    public ResponseEntity<List<Person>> getPersons();
    @PostMapping("/addEnrollId")
    public ResponseEntity<String> addEnrollmentId(@RequestParam Integer personId, @RequestParam Long enrollId);
    @PostMapping("/removeEnrollId")
    public ResponseEntity<String> removeEnrollmentId(@RequestParam Integer personId, @RequestParam Long enrollId);
}
