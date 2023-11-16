package com.example.fileupload.studentmodule;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/vi/students")
@RequiredArgsConstructor
public class StudentController {

        private final StudentService service;

    @PostMapping(value = "upload", consumes = {"multipart/form-data"})
    public ResponseEntity<Integer> upload(@RequestPart("file")MultipartFile file) throws IOException {
        return ResponseEntity.ok(service.uploadStudents(file));
    }
}