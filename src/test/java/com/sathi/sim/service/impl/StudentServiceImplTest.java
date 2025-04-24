package com.sathi.sim.service.impl;

import com.sathi.sim.domain.Student;
import com.sathi.sim.dto.StudentDTO;
import com.sathi.sim.exception.FeeDetailsNotFound;
import com.sathi.sim.exception.InvalidRequestException;
import com.sathi.sim.exception.StudentDetailsNotFound;
import com.sathi.sim.mapper.StudentMapper;
import com.sathi.sim.repository.CustomStudentRepository;
import com.sathi.sim.repository.StudentRepository;
import com.sathi.sim.util.Constants;
import com.sathi.sim.validat.ValidateStudentDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.sathi.sim.controller.StudentControllerTest.getStudentDTOData;
import static com.sathi.sim.controller.StudentControllerTest.getStudentData;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepo;

    @Mock
    private CustomStudentRepository customStudentRepository;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentServiceImpl studentService;

    private Student student;
    private StudentDTO studentDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        student = getStudentData();
        studentDTO = getStudentDTOData();
    }

    @Test
    void testInsertStudentDetail_Success() {
        when(studentRepo.save(student)).thenReturn(Mono.just(student));
        when(studentMapper.studentToStudentDTO(student)).thenReturn(studentDTO);

        Mono<StudentDTO> result = studentService.insertStudentDetail(student);

        assertNotNull(result);
        assertEquals("John", result.block().getFirstName());
        verify(studentRepo, times(1)).save(student);
    }

    @Test
    void testInsertStudentDetail_InvalidStudent() {
        student.setFirstName(null);

        Mono<StudentDTO> result = studentService.insertStudentDetail(student);

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof InvalidRequestException &&
                        throwable.getMessage().equals(ValidateStudentDetails.errMsg))
                .verify();
        verify(studentRepo, never()).save(any());
    }

    @Test
    void testUpdateStudentDetails_Success() {
        when(studentRepo.save(student)).thenReturn(Mono.just(student));
        when(studentMapper.studentToStudentDTO(student)).thenReturn(studentDTO);

        Mono<StudentDTO> result = studentService.updateStudentDetails(student);

        assertNotNull(result);
        assertEquals("John", result.block().getFirstName());
        verify(studentRepo, times(1)).save(student);
    }

    @Test
    void testRemoveStudentDetails() {
        when(studentRepo.deleteByStudentId(100001L)).thenReturn(Mono.empty());

        assertDoesNotThrow(() -> studentService.removeStudentDetails(100001L));
        verify(studentRepo, times(1)).deleteByStudentId(100001L);
    }

    @Test
    void testGetStudentDetailByStudentId_Found() {
        when(studentRepo.findByStudentId(100001L)).thenReturn(Mono.just(student));
        when(studentMapper.studentToStudentDTO(student)).thenReturn(studentDTO);

        Mono<StudentDTO> result = studentService.getStudentDetailByStudentId(100001L);

        assertNotNull(result);
        assertEquals("John", result.block().getFirstName());
        verify(studentRepo, times(1)).findByStudentId(100001L);
    }

    @Test
    void testGetStudentDetailByStudentId_NotFound() {
        when(studentRepo.findByStudentId(100001L)).thenReturn(Mono.empty());

        Mono<StudentDTO> result = studentService.getStudentDetailByStudentId(100001L);

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof FeeDetailsNotFound &&
                        throwable.getMessage().equals(String.format(Constants.ERR_MSG_STUDENT_DET_NOT_FOUND_FOR_GIVEN_STUDENT_ID, 100001)))
                .verify();
        verify(studentRepo, times(1)).findByStudentId(100001L);
    }

    @Test
    void testSearchStudentByFirstName_Found() {
        when(studentRepo.findByFirstName("John")).thenReturn(Flux.just(student));
        when(studentMapper.studentToStudentDTO(student)).thenReturn(studentDTO);

        Flux<StudentDTO> result = studentService.searchStudentByFirstName("John");

        assertNotNull(result);
        assertEquals("John", result.blockFirst().getFirstName());
        verify(studentRepo, times(1)).findByFirstName("John");
    }

    @Test
    void testSearchStudentByFirstName_NotFound() {
        when(studentRepo.findByFirstName("John")).thenReturn(Flux.empty());

        Flux<StudentDTO> result = studentService.searchStudentByFirstName("John");


        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof StudentDetailsNotFound &&
                        throwable.getMessage().equals(String.format(Constants.ERR_MSG_STUDENT_DET_NOT_FOUND_FOR_GIVEN_STUDENT_NAME, "John")))
                .verify();
        verify(studentRepo, times(1)).findByFirstName("John");
    }

    @Test
    void testGetAllActiveStudent_Found() {
        when(customStudentRepository.fetchStudentsWithAddressAndSubjects()).thenReturn(Flux.just(studentDTO));

        Flux<StudentDTO> result = studentService.getAllActiveStudent();

        assertNotNull(result);
        assertEquals("John", result.blockFirst().getFirstName());
        verify(customStudentRepository, times(1)).fetchStudentsWithAddressAndSubjects();
    }

    @Test
    void testGetAllActiveStudent_NotFound() {
        when(customStudentRepository.fetchStudentsWithAddressAndSubjects()).thenReturn(Flux.empty());

        Flux<StudentDTO> result = studentService.getAllActiveStudent();

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof StudentDetailsNotFound &&
                        throwable.getMessage().equals(Constants.ERR_MSG_NO_ACTIVE_STUDENT_RECORD_FOUND))
                .verify();
        verify(customStudentRepository, times(1)).fetchStudentsWithAddressAndSubjects();
    }

}
