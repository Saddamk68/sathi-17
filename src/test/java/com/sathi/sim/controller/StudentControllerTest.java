package com.sathi.sim.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sathi.sim.domain.Student;
import com.sathi.sim.dto.StudentDTO;
import com.sathi.sim.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StudentControllerTest {

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentService studentService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
    }

    public static Student getStudentData() {
        Student student = new Student();
        student.setStudentId(100001L);
        student.setFirstName("John");
        student.setMiddleName("Michael");
        student.setLastName("Doe");
        student.setGender("Male");
        student.setDob(LocalDate.of(2000, 1, 1));
        student.setSchoolName("Springfield High");
        student.setClassName(10);
        student.setContactNum("1234567890");
        student.setEmail("john.doe@example.com");
        student.setIsActive(true);
        student.setCreatedAt(LocalDateTime.now());
        student.setCreatedBy("Admin");
        student.setUpdatedAt(LocalDateTime.now());
        student.setUpdatedBy("Admin");
        return student;
    }

    public static StudentDTO getStudentDTOData() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(100001L);
        studentDTO.setFirstName("John");
        studentDTO.setMiddleName("Michael");
        studentDTO.setLastName("Doe");
        studentDTO.setGender("Male");
        studentDTO.setDob(LocalDate.of(2000, 1, 1));
        studentDTO.setSchoolName("Springfield High");
        studentDTO.setClassName(10);
        studentDTO.setContactNum("1234567890");
        studentDTO.setEmail("john.doe@example.com");
        studentDTO.setIsActive(true);
        return studentDTO;
    }

    @Test
    void testInsertStudentDetail() throws Exception {
        Student student = getStudentData();
        StudentDTO studentDTO = getStudentDTOData();

        when(studentService.insertStudentDetail(any(Student.class))).thenReturn(Mono.just(studentDTO));

        mockMvc.perform(post("/api/v1/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isCreated());
//                .andExpect(jsonPath("$.firstName").value("John"));

        verify(studentService, times(1)).insertStudentDetail(any(Student.class));
    }

    @Test
    void testUpdateStudentDetails() throws Exception {
        Student student = getStudentData();
        StudentDTO studentDTO = getStudentDTOData();

        when(studentService.updateStudentDetails(any(Student.class))).thenReturn(Mono.just(studentDTO));

        mockMvc.perform(put("/api/v1/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.firstName").value("John"));

        verify(studentService, times(1)).updateStudentDetails(any(Student.class));
    }

    @Test
    void testRemoveStudentDetails() throws Exception {
        doNothing().when(studentService).removeStudentDetails(anyLong());

        mockMvc.perform(delete("/api/v1/student/{studentId}", 1L))
                .andExpect(status().isOk());

        verify(studentService, times(1)).removeStudentDetails(1L);
    }

    @Test
    void testGetStudentDetailByStudentId() throws Exception {
        StudentDTO studentDTO = getStudentDTOData();

        when(studentService.getStudentDetailByStudentId(anyLong())).thenReturn(Mono.just(studentDTO));

        mockMvc.perform(get("/api/v1/student/{id}", 1L))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.firstName").value("John"));

        verify(studentService, times(1)).getStudentDetailByStudentId(1L);
    }

    @Test
    void testSearchStudentByFirstName() throws Exception {
        StudentDTO studentDTO = getStudentDTOData();

        when(studentService.searchStudentByFirstName(anyString())).thenReturn(Flux.just(studentDTO));

        mockMvc.perform(get("/api/v1/student/getStudentByName")
                        .param("name", "John"))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$[0].firstName").value("John"));

        verify(studentService, times(1)).searchStudentByFirstName("John");
    }

    @Test
    void testGetAllActiveStudent() throws Exception {
        StudentDTO studentDTO = getStudentDTOData();

        when(studentService.getAllActiveStudent()).thenReturn(Flux.just(studentDTO));

        mockMvc.perform(get("/api/v1/student"))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$[0].firstName").value("John"));

        verify(studentService, times(1)).getAllActiveStudent();
    }

}
