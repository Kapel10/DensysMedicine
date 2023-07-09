package densys.hospital.Repositories;

import densys.hospital.Models.Doctor;
import densys.hospital.Models.Patient;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByUserId(Long userId);
}
