package com.fbmoll.teaching.dataaccess.data;

import java.util.Date;

/**
 * com.fbmoll.teaching.dataaccess.data
 * Class Student
 * 13/10/2020
 *
 * @author berto (alberto.soto@gmail.com)
 */
public class Student {

    private String name;
    private Integer mark;
    private Date bornDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }
}
