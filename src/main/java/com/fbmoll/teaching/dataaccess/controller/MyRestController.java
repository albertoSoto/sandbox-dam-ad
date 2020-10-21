package com.fbmoll.teaching.dataaccess.controller;

import com.fbmoll.teaching.dataaccess.data.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * com.fbmoll.teaching.dataaccess.controller
 * Class MyRestController
 * 13/10/2020
 *
 * @author berto (alberto.soto@gmail.com)
 */
@RestController
public class MyRestController {

    @GetMapping("/getStudent")
    public Student getStudent(@RequestParam(value = "name", defaultValue = "Pepito") String name) {
        Student aux = new Student();
        aux.setName(name);
        aux.setMark(8);
        return aux;
    }
    @GetMapping("/createStudents")
    public List<Student> createStudents(@RequestParam(value="q",defaultValue = "1")
                                    Integer numStudents){
        ArrayList<Student> arrData = new ArrayList<>();
        for (int i = 0; i < numStudents; i++) {
            Student aux = new Student();
            aux.setName(String.format("Students %s",i));
            arrData.add(aux);
        }
        return arrData;
    }

}
