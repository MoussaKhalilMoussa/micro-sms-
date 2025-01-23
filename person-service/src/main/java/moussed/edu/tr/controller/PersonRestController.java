package moussed.edu.tr.controller;

import moussed.edu.tr.model.Person;
import moussed.edu.tr.model.dto.PersonDto;
import moussed.edu.tr.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class PersonRestController {
    @Autowired
    private PersonService personService;

//    @Autowired
//    private ServiceImpl service;

    @PostMapping("/register")
    public ResponseEntity<String> registerPerson(@RequestBody PersonDto personDto) {
        String s = personService.addPerson(personDto);
        return ResponseEntity.ok(s);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> loginUser(@RequestParam String userName, @RequestParam String password){
        return ResponseEntity.ok(personService.login(userName, password));
    }

    @GetMapping("/getPersonById")
    public ResponseEntity<Person> getPersonById(@RequestParam Integer id) {
        Person person = personService.getPersonById(id);
        if (person != null) {
            return ResponseEntity.ok(person);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/getPersonByUserName")
    public ResponseEntity<Person> getPersonByUserName(@RequestParam String userName) {
        Person person = personService.getPersonByUserName(userName);
        if (person != null) {
            return ResponseEntity.ok(person);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/getAllPersons")
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personService.getAllPersons();
        return persons.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.ok(persons);
    }

    @PutMapping("/updatePerson/{personId}")
    public ResponseEntity<String> updatePerson(@RequestBody Person person,@PathVariable Integer personId) {
        String updatedPerson = personService.updatePerson(person,personId);
        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("/deletePerson/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Integer id) {
        String deletedPerson = personService.deletePerson(id);
        return ResponseEntity.ok(deletedPerson);
    }

    @PostMapping("/addEnrollId")
    public ResponseEntity<String> addEnrollmentId(@RequestParam Integer personId, @RequestParam Long enrollId) {
        String s = personService.addEnrollmentId(personId, enrollId);
        return ResponseEntity.ok(s);
    }
    @PostMapping("/removeEnrollId")
    public ResponseEntity<String> removeEnrollmentId(@RequestParam Integer personId, @RequestParam Long enrollId) {
        String s = personService.removeEnrollmentId(personId, enrollId);
        return ResponseEntity.ok(s);
    }
}
