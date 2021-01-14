package com.fbmoll.teaching.dataaccess.controller;

import com.fbmoll.teaching.dataaccess.u2.filePersistence.data.Student;
import com.fbmoll.teaching.dataaccess.u4.persistantData.data.Customer;
import com.fbmoll.teaching.dataaccess.u4.persistantData.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * com.fbmoll.teaching.dataaccess.controller
 * Class MyRestController
 * 13/10/2020
 *
 * @author berto (alberto.soto@gmail.com)
 */
@RestController("/api/rest/v1/")
public class MyRestController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final CustomerService customerService;

    public MyRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("getCustomer")
    public Iterable<Customer> getCustomer() {
        customerService.initDB();
        return customerService.getRepository().findAll();
    }

    @GetMapping("getStudent")
    public Student getStudent(@RequestParam(value = "name", defaultValue = "Pepito") String name) {
        Student aux = new Student();
        aux.setName(name);
        aux.setMark(name.length());
        return aux;
    }

    @GetMapping("createStudents")
    public List<Student> createStudents(@RequestParam(value = "q", defaultValue = "1")
                                                Integer numStudents) {
        ArrayList<Student> arrData = new ArrayList<>();
        for (int i = 0; i < numStudents; i++) {
            Student aux = new Student();
            aux.setName(String.format("Students %s", i));
            arrData.add(aux);
        }
        return arrData;
    }

    @RequestMapping(value = "base"
            , method = {RequestMethod.GET, RequestMethod.POST}
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map> getData(@RequestParam(value = "q", defaultValue = "1")
                                               Integer numParams, HttpServletRequest rq) {
        try {
            Map<String, Object> map = new HashMap<>();
            for (int i = 0; i < numParams; i++) {
                map.put("key" + i, "pepito" + i);
            }
            String[] arr = {"asd", "123"};
            List items = CollectionUtils.arrayToList(arr);
            items.forEach(o -> {
                //to explain tomorrow!
            });
            map.put("arrData", items);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            log.error("register:get/", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
