package com.sathi.sim.service;

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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StudentServiceImplTest {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentRepository mockStudentRepo;

    @Mock
    private CustomStudentRepository mockCustomStudentRepo;

    @Mock
    private StudentMapper mockStudentMapper;

    private Student student;
    private StudentDTO studentDTO;
    private final Long studentId = 100001L;
    private final String firstName = "John";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        student = getStudentData();
        studentDTO = getStudentDTOData();
    }

    @Test
    void testInsertStudentDetail_Success() {
        when(mockStudentRepo.save(student)).thenReturn(Mono.just(student));
        when(mockStudentMapper.studentToStudentDTO(student)).thenReturn(studentDTO);

        Mono<StudentDTO> result = studentService.insertStudentDetail(student);

        assertNotNull(result);
        assertEquals(studentDTO.getStudentId(), result.block().getStudentId());
        verify(mockStudentRepo, times(1)).save(student);
    }

    @Test
    void testInsertStudentDetail_InvalidStudent() {
        student.setFirstName(null);

        Mono<StudentDTO> result = studentService.insertStudentDetail(student);

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof InvalidRequestException &&
                        throwable.getMessage().equals(ValidateStudentDetails.errMsg))
                .verify();
        verify(mockStudentRepo, never()).save(any());
    }

    @Test
    void testInsertStudentDetail_DatabaseError() {
        when(mockStudentRepo.save(student)).thenReturn(Mono.error(new RuntimeException("Database error")));

        Mono<StudentDTO> result = studentService.insertStudentDetail(student);

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                        throwable.getMessage().equals(Constants.ERR_SAVING_FEE_DET))
                .verify();

        verify(mockStudentRepo, times(1)).save(student);
    }

    @Test
    void testUpdateStudentDetails_Success() {
        when(mockStudentRepo.save(student)).thenReturn(Mono.just(student));
        when(mockStudentMapper.studentToStudentDTO(student)).thenReturn(studentDTO);

        Mono<StudentDTO> result = studentService.updateStudentDetails(student);

        assertNotNull(result);
        assertEquals(studentDTO.getStudentId(), result.block().getStudentId());
        verify(mockStudentRepo, times(1)).save(student);
    }

    @Test
    void testUpdateStudentDetails_InvalidStudent() {
        student.setFirstName(null); // Invalid data

        Mono<StudentDTO> result = studentService.updateStudentDetails(student);

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof InvalidRequestException &&
                        throwable.getMessage().equals(ValidateStudentDetails.errMsg))
                .verify();

        verify(mockStudentRepo, never()).save(any());
    }

    @Test
    void testUpdateStudentDetails_DatabaseError() {
        when(mockStudentRepo.save(student)).thenReturn(Mono.error(new RuntimeException("Database error")));

        Mono<StudentDTO> result = studentService.updateStudentDetails(student);

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                        throwable.getMessage().equals(Constants.ERR_UPDATE_STUDENT_DET))
                .verify();

        verify(mockStudentRepo, times(1)).save(student);
    }

    @Test
    void testRemoveStudentDetails_Success() {
        when(mockStudentRepo.deleteByStudentId(studentId)).thenReturn(Mono.empty());

        assertDoesNotThrow(() -> studentService.removeStudentDetails(studentId));
        verify(mockStudentRepo, times(1)).deleteByStudentId(studentId);
    }

    @Test
    void testRemoveStudentDetails_DatabaseError() {
        doThrow(new RuntimeException("Database error"))
                .when(mockStudentRepo).deleteByStudentId(studentId);

        assertThrows(RuntimeException.class,
                () -> studentService.removeStudentDetails(studentId),
                Constants.ERR_DELETE_STUDENT_DET);

        verify(mockStudentRepo, times(1)).deleteByStudentId(studentId);
    }

    @Test
    void testGetStudentDetailByStudentId_Success() {
        when(mockStudentRepo.findByStudentId(studentId)).thenReturn(Mono.just(student));
        when(mockStudentMapper.studentToStudentDTO(student)).thenReturn(studentDTO);

        Mono<StudentDTO> result = studentService.getStudentDetailByStudentId(studentId);

        assertNotNull(result);
        assertEquals(studentDTO.getStudentId(), result.block().getStudentId());
        verify(mockStudentRepo, times(1)).findByStudentId(studentId);
    }

    @Test
    void testGetStudentDetailByStudentId_NotFound() {
        when(mockStudentRepo.findByStudentId(studentId)).thenReturn(Mono.empty());

        Mono<StudentDTO> result = studentService.getStudentDetailByStudentId(studentId);

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof FeeDetailsNotFound &&
                        throwable.getMessage().equals(String.format(Constants.ERR_MSG_STUDENT_DET_NOT_FOUND_FOR_GIVEN_STUDENT_ID, 100001)))
                .verify();
        verify(mockStudentRepo, times(1)).findByStudentId(studentId);
    }

    @Test
    void testSearchStudentByFirstName_Success() {
        when(mockStudentRepo.findByFirstName(firstName)).thenReturn(Flux.just(student));
        when(mockStudentMapper.studentToStudentDTO(student)).thenReturn(studentDTO);

        Flux<StudentDTO> result = studentService.searchStudentByFirstName(firstName);

        assertNotNull(result);
        assertEquals(studentDTO.getStudentId(), result.blockFirst().getStudentId());
        verify(mockStudentRepo, times(1)).findByFirstName(firstName);
    }

    @Test
    void testSearchStudentByFirstName_NotFound() {
        when(mockStudentRepo.findByFirstName(firstName)).thenReturn(Flux.empty());

        Flux<StudentDTO> result = studentService.searchStudentByFirstName(firstName);

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof StudentDetailsNotFound &&
                        throwable.getMessage().equals(String.format(Constants.ERR_MSG_STUDENT_DET_NOT_FOUND_FOR_GIVEN_STUDENT_NAME, firstName)))
                .verify();
        verify(mockStudentRepo, times(1)).findByFirstName(firstName);
    }

    @Test
    void testGetAllStudent_Success() {
        when(mockStudentRepo.findAll()).thenReturn(Flux.just(student));
        when(mockStudentMapper.studentToStudentDTO(student)).thenReturn(studentDTO);

        Flux<StudentDTO> result = studentService.getAllStudent();

        StepVerifier.create(result)
                .expectNextMatches(dto -> dto.getStudentId().equals(studentDTO.getStudentId()))
                .verifyComplete();

        verify(mockStudentRepo, times(1)).findAll();
        verify(mockStudentMapper, times(1)).studentToStudentDTO(student);
    }

    @Test
    void testGetAllStudent_NotFound() {
        when(mockStudentRepo.findAll()).thenReturn(Flux.empty());

        Flux<StudentDTO> result = studentService.getAllStudent();

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof StudentDetailsNotFound &&
                        throwable.getMessage().equals(Constants.ERR_MSG_STUDENT_RECORD_NOT_FOUND))
                .verify();

        verify(mockStudentRepo, times(1)).findAll();
        verify(mockStudentMapper, never()).studentToStudentDTO(any());
    }

    @Test
    void testGetAllActiveStudent_Success() {
        when(mockCustomStudentRepo.fetchStudentsWithAddressAndSubjects()).thenReturn(Flux.just(studentDTO));

        Flux<StudentDTO> result = studentService.getAllActiveStudent();

        assertNotNull(result);
        assertEquals(studentDTO.getStudentId(), result.blockFirst().getStudentId());
        verify(mockCustomStudentRepo, times(1)).fetchStudentsWithAddressAndSubjects();
    }

    @Test
    void testGetAllActiveStudent_NotFound() {
        when(mockCustomStudentRepo.fetchStudentsWithAddressAndSubjects()).thenReturn(Flux.empty());

        Flux<StudentDTO> result = studentService.getAllActiveStudent();

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof StudentDetailsNotFound &&
                        throwable.getMessage().equals(Constants.ERR_MSG_NO_ACTIVE_STUDENT_RECORD_FOUND))
                .verify();
        verify(mockCustomStudentRepo, times(1)).fetchStudentsWithAddressAndSubjects();
    }

}
