package com.fbmoll.teaching.dataaccess.data;

import java.io.Serializable;

/**
 * com.fbmoll.teaching.dataaccess.data
 * Class Product
 * 26/10/2020
 *
 * @author berto (alberto.soto@gmail.com)
 */
public class Product implements Serializable {

    private String name;
    private Double value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
