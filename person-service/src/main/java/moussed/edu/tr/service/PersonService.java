package moussed.edu.tr.service;


import moussed.edu.tr.model.Person;
import moussed.edu.tr.model.dto.PersonDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface PersonService {
    public String addPerson(PersonDto personDto);
    public String updatePerson(Person person, Integer personId);
    public String deletePerson(Integer id);
    public Person getPersonById(Integer id);
    public Person getPersonByUserName(String userName);
    public List<Person> getAllPersons();
    public String addEnrollmentId(Integer personId, Long enrollmentId);
    public Map<String, Object> login(String userName, String password);
    public String removeEnrollmentId(Integer personId, Long enrollId);

}
