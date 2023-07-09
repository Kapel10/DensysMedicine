package densys.hospital.Controllers;

import densys.hospital.Models.Doctor;
import densys.hospital.Models.Patient;
import densys.hospital.Models.User;
import densys.hospital.Services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class UserController {
    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // -- Get all
    @GetMapping("/user")
    public List<User> allUser(){
        return userService.serviceAllUsers();
    }
    @GetMapping("/doctor")
    public List<Doctor> allDoctors(){
        return userService.serviceAllDoctors();
    }

    @GetMapping("/patient")
    public List<Patient> allPatients(){
        return userService.serviceAllPatients();
    }
    // -- Add
    @PostMapping("/addUser")
    public void addUser(@RequestBody User user){
        userService.serviceAddUser(user);
    }
    @PostMapping("/addDoctor")
    public void addDoctor(@RequestBody Doctor doctor){
        userService.serviceAddDoctor(doctor);
    }
    @PostMapping("/addPatient")
    public void addPatient(@RequestBody Patient patient){
        userService.serviceAddPatient(patient);
    }

    // update
    @PutMapping("/update-user/{id}")
    public void updateUser(@PathVariable long id, @RequestBody User user ){
        userService.serviceUpdateUser(user,id);
    }
    @PutMapping("/update-doctor/{id}")
    public void updateDoctor(@PathVariable long id, @RequestBody Doctor doctor ){
        userService.serviceUpdateDoctor(doctor,id);
    }
    @PutMapping("/update-patient/{id}")
    public void updatePatient(@PathVariable long id, @RequestBody Patient patient ){
        userService.serviceUpdatePatient(patient,id);
    }


    // -- Deletion of users/doctors/patients
    @DeleteMapping("/delete-user/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.serviceDeleteUser(id);
    }
    @DeleteMapping("/delete-doctor/{id}")
    public void deleteDoctor(@PathVariable Long id){
        userService.serviceDeleteDoctor(id);
    }
    @DeleteMapping("/delete-patient/{id}")
    public void deletePatient(@PathVariable Long id){
        userService.serviceDeletePatient(id);
    }


}
