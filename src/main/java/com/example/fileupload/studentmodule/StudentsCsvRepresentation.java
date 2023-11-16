package com.example.fileupload.studentmodule;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.GeneratedValue;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentsCsvRepresentation {

    @CsvBindByName(column = "firstName")
    private String fName;
    @CsvBindByName(column = "lastName")
    private String lName;
    @CsvBindByName(column = "age")
    private int age;



}
