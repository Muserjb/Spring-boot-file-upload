package com.example.fileupload.studentmodule;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class StudentService {


    private final StudentRepository studentRepository;


    public Integer uploadStudents(MultipartFile file) throws IOException {
        Set<Students> students = parseCsv(file);
        studentRepository.saveAll(students);
        return students.size();
    }

    private Set<Students> parseCsv(MultipartFile file) throws IOException {
        try(Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))){
            HeaderColumnNameMappingStrategy<StudentsCsvRepresentation> strategy =
                    new HeaderColumnNameMappingStrategy<StudentsCsvRepresentation>();
            strategy.setType(StudentsCsvRepresentation.class);
            CsvToBean<StudentsCsvRepresentation> csvToBean =
                    new CsvToBeanBuilder<StudentsCsvRepresentation>(reader)
                            .withMappingStrategy(strategy)
                            .withIgnoreEmptyLine(true)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();
           return  csvToBean.parse()
                    .stream()
                    .map(csvLine -> Students.builder()
                            .firstName(csvLine.getFName())
                            .lastName(csvLine.getLName())
                            .age(csvLine.getAge())
                            .build()
                    )
                    .collect(Collectors.toSet());

        }

    }
}
