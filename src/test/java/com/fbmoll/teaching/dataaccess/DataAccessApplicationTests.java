package com.fbmoll.teaching.dataaccess;

import com.fbmoll.teaching.dataaccess.data.ConfigValues;
import com.fbmoll.teaching.dataaccess.data.Student;
import com.fbmoll.teaching.dataaccess.helper.DummyUtils;
import com.fbmoll.teaching.dataaccess.helper.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.io.File;
import java.util.List;

@SpringBootTest
class DataAccessApplicationTests {

    @Test
    void tryCreateFile() {
        String fileName = String.format("%s\\.fbmoll\\test.txt", System.getProperty("user.home"));
        File file = FileUtils.generateFile(fileName, "dummy Content");
        Assert.notNull(file, "Fichero nulo!");
    }

    @Test
    void tryCreateAndReadFile() {
        String fileName = String.format("%s\\.fbmoll\\test.data", System.getProperty("user.home"));
        File file = FileUtils.generateFile(fileName, "Hola\nDonPimpon");
        List<String> fileContent = FileUtils.readFileLines(fileName);
        Assert.notEmpty(fileContent, "Tu fichero esta vacío!");
        Assert.isTrue(StringUtils.equals(fileContent.get(0), "Hola"), "Primera linea chunga");
        Assert.isTrue(StringUtils.equals(fileContent.get(1), "DonPimpon"), "Segunda linea chunga");
    }

    @Test
    void validateProperties() {
        String fileName = String.format("%s\\.fbmoll\\myprops.properties", System.getProperty("user.home"));
        FileUtils fileUtils = new FileUtils();
        ConfigValues data = new ConfigValues();
        data.setServer("localhost");
        data.setPort("3306");
        data.setName("myUser");
        data.setPassword("myPassword");

        fileUtils.saveProperties(data, fileName);
        ConfigValues properties = fileUtils.loadProperties(fileName);
        Assert.isTrue(data.equals(properties), "Props are different");
    }

    @Test
    void tryDummyGenerator(){
        DummyUtils dummyUtils = new DummyUtils();
        List<Student> data = dummyUtils.genStudents(50);
        System.out.println("yuhu");
    }

//TDD Test Driven Development
}
