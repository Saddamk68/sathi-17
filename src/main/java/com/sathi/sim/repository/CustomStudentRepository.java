package com.sathi.sim.repository;

import com.sathi.sim.dto.AddressDTO;
import com.sathi.sim.dto.StudentDTO;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class CustomStudentRepository {

    @Autowired
    private DatabaseClient databaseClient;

    public Flux<StudentDTO> fetchStudentsWithAddressAndSubjects() {
        String query = """
        		SELECT s.id AS studentId, s.first_name, s.middle_name, s.last_name, s.father_name, s.mother_name, 
					s.dob, s.gender, s.school_name, s.class_name, s.contact_num, s.email, s.is_active, s.image_url,
					a.id, a.first_line, a.second_line, a.state, a.city, a.pin_code,
					GROUP_CONCAT(sub.sub_name) AS subjects
				FROM student_table s 
				LEFT JOIN address_table a ON s.address_id = a.id
				LEFT JOIN student_subject_table ss ON s.id = ss.student_id
				LEFT JOIN subject_table sub ON ss.sub_id = sub.id
				WHERE s.is_active = 1
				GROUP BY s.id
            """;

        return databaseClient.sql(query)
            .map((row, metadata) -> {
                StudentDTO studentDto = new StudentDTO();
                studentDto.setStudentId(row.get("studentId", Long.class));
                studentDto.setFirstName(row.get("first_name", String.class));
                studentDto.setMiddleName(row.get("middle_name", String.class));
                studentDto.setLastName(row.get("last_name", String.class));
                studentDto.setFatherName(row.get("father_name", String.class));
                studentDto.setMotherName(row.get("mother_name", String.class));
                studentDto.setDob(row.get("dob", LocalDate.class));
                studentDto.setGender(row.get("gender", String.class));
                studentDto.setSchoolName(row.get("school_name", String.class));
                studentDto.setClassName(row.get("class_name", Integer.class));
                studentDto.setContactNum(row.get("contact_num", String.class));
                studentDto.setEmail(row.get("email", String.class));
//                studentDto.setIsActive(row.get("is_active", Boolean.class));
                studentDto.setIsActive(row.get("is_active", Integer.class) != null 
                		&& row.get("is_active", Integer.class) == 1);
                studentDto.setImageUrl(row.get("image_url", String.class));

                // Mapping Address
                AddressDTO addressDto = new AddressDTO();
                addressDto.setId(row.get("id", Long.class));
                addressDto.setFirstLine(row.get("first_line", String.class));
                addressDto.setSecondLine(row.get("second_line", String.class));
                addressDto.setState(row.get("state", String.class));
                addressDto.setCity(row.get("city", String.class));
                addressDto.setPinCode(row.get("pin_code", String.class));
                studentDto.setAddress(addressDto);

                // Subjects (comma-separated string)
                String subjects = row.get("subjects", String.class);
                if (subjects != null) {
                    studentDto.setSubjects(Set.of(subjects.split(",")));
                }
                
                return studentDto;
            })
            .all();
    }
}
