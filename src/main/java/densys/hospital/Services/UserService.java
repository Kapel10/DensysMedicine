package densys.hospital.Services;

import densys.hospital.Models.Doctor;
import densys.hospital.Models.Patient;
import densys.hospital.Models.User;
import densys.hospital.Repositories.DoctorRepository;
import densys.hospital.Repositories.PatientRepository;
import densys.hospital.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    public final DoctorRepository doctorRepository;
    public final PatientRepository patientRepository;
    public final UserRepository userRepository;


    public UserService(DoctorRepository doctorRepository, PatientRepository patientRepository, UserRepository userRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    // -- Get all
    public List<User> serviceAllUsers(){
        return userRepository.findAll();
    }
    public List<Doctor> serviceAllDoctors(){
        return doctorRepository.findAll();
    }
    public List<Patient> serviceAllPatients(){
        return patientRepository.findAll();
    }
    // -- Add user/doctor/patient
    public void serviceAddUser(User user){
        userRepository.save(user);
    }
    public void serviceAddDoctor(Doctor doctor){
        User user = new User();
        user.setUsername(doctor.getUser().getUsername());
        user.setPassword(doctor.getUser().getPassword());
        user.setIs_admin(doctor.getUser().getIs_admin());
        serviceAddUser(user);
        doctor.setUser(user);
        doctorRepository.save(doctor);
    }
    public void serviceAddPatient(Patient patient){
        User user = new User();
        user.setUsername(patient.getUser().getUsername());
        user.setPassword(patient.getUser().getPassword());
        user.setIs_admin(patient.getUser().getIs_admin());
        serviceAddUser(user);
        patient.setUser(user);
        patientRepository.save(patient);
    }


    // --Update user/doctor/patient

    public void serviceUpdateUser(User user,Long id){
        Optional<User> new_user = userRepository.findById(id);
        if(new_user.isPresent()){
            new_user.get().setUsername(user.getUsername());
            new_user.get().setPassword(user.getPassword());
            new_user.get().setIs_admin(user.getIs_admin());

            userRepository.save(new_user.get());
        }

    }
    public void serviceUpdateDoctor(Doctor doctor,Long id){
        Optional<Doctor> new_doctor = doctorRepository.findById(id);

        if(new_doctor.isPresent()){
            new_doctor.get().getUser().setUsername(doctor.getUser().getUsername());
            new_doctor.get().getUser().setPassword(doctor.getUser().getPassword());
            new_doctor.get().getUser().setIs_admin(doctor.getUser().getIs_admin());
            new_doctor.get().setName(doctor.getName());
            new_doctor.get().setExperience_years(doctor.getExperience_years());
            doctorRepository.save(new_doctor.get());
        }

    }
    public void serviceUpdatePatient(Patient patient,Long id){
        Optional<Patient> new_patient = patientRepository.findById(id);

        if(new_patient.isPresent()){
            new_patient.get().getUser().setUsername(patient.getUser().getUsername());
            new_patient.get().getUser().setPassword(patient.getUser().getPassword());
            new_patient.get().getUser().setIs_admin(patient.getUser().getIs_admin());
            new_patient.get().setName(patient.getName());
            new_patient.get().setContact_number(patient.getContact_number());
            patientRepository.save(new_patient.get());
        }
    }



    // -- Delete user/doctor/patient

    public void serviceDeleteUser(Long id){
        // id = 3 , find patient where patient.get.user.id = 3

        Doctor new_doctor = doctorRepository.findByUserId(id);
        Patient new_patient = patientRepository.findByUserId(id);

        if(new_doctor != null){
            serviceDeleteDoctor(new_doctor.getId());
        }
        if(new_patient != null){
            serviceDeletePatient(new_patient.getId());
        }

        userRepository.deleteById(id);

    }


    public void serviceDeleteDoctor(Long id){

        doctorRepository.deleteById(id);
    }

    public void serviceDeletePatient(Long id){
        patientRepository.deleteById(id);
    }


}
