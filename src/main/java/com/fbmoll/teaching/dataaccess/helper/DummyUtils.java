package com.fbmoll.teaching.dataaccess.helper;

import com.fbmoll.teaching.dataaccess.data.Product;
import com.fbmoll.teaching.dataaccess.data.Student;
import org.jeasy.random.EasyRandom;

import java.util.ArrayList;
import java.util.List;

/**
 * com.fbmoll.teaching.dataaccess.helper
 * Class DummyUtils
 * 21/10/2020
 *
 * @author berto (alberto.soto@gmail.com)
 */
public class DummyUtils {

    public List<Student> genStudents(int size) {
        ArrayList<Student> arrData = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            EasyRandom generator = new EasyRandom();
            arrData.add(generator.nextObject(Student.class));
        }
        return arrData;
    }

    public List<Product> getProducts() {
        int numItems = (int) (Math.random() * 100);
        ArrayList<Product> aux = new ArrayList<>();
        EasyRandom gen = new EasyRandom();
        for (int i = 0; i < numItems; i++) {
            aux.add(gen.nextObject(Product.class));
        }
        return aux;
    }


}
