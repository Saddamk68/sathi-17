package com.sathi.sim.dto;

import java.util.List;

public class AdmissionFormDTO {

    // Personal Details
    private String firstName;
    private String lastName;
    private String gender;
    private String dateOfBirth;
    
    // Contact Details
    private String phoneNumber;
    private String email;
    
    // Address Details
    private String street;
    private String city;
    private String state;
    private String postalCode;
    
    // Academic Details
    private String studentClass;
    private List<SubjectDTO> subjects;
    
    // Parent/Guardian Details
    private String guardianName;
    private String guardianPhone;
    private String guardianEmail;
    
    // Other Details (if needed)
    private String additionalNotes;

}
