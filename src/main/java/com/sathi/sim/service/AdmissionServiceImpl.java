package com.sathi.sim.service;

import java.io.ByteArrayOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.sathi.sim.domain.Student;
import com.sathi.sim.repository.StudentRepository;

import reactor.core.publisher.Mono;

@Service
public class AdmissionServiceImpl implements AdmissionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdmissionServiceImpl.class);
	
	@Autowired
	private StudentRepository studentRepo;

	@Override
	public Mono<byte[]> generateAdmissionFormPDF(Long studentId) {
//	    byte[] pdfContent = admissionFormService.generateAdmissionForm(studentId);
//	    return ResponseEntity.ok()
//	            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=admission_form_" + studentId + ".pdf")
//	            .body(pdfContent);
		
	    return Mono.fromCallable(() -> {
	        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	        Document document = new Document();
	        PdfWriter.getInstance(document, byteArrayOutputStream);
	        document.open();
	        document.add(new Paragraph("Admission Form"));
	        document.add(new Paragraph("Name: DemoAdmissionForm"));
	        // Add other form fields
	        document.close();
	        return byteArrayOutputStream.toByteArray();
	    });
	}

	@Override
	public Mono<byte[]> printAdmissionForm(Long studentId) {
//	    return generateAdmissionFormPDF(studentId)
//	    		.map(pdf -> ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=DemoAdmissionForm.pdf")
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(pdf));
		return null;
	}
		
}
