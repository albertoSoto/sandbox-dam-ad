package com.fbmoll.teaching.dataaccess;

/**
 * com.fbmoll.teaching.dataaccess
 * Class MyAppException
 * 14/12/2020
 *
 * @author berto (alberto.soto@gmail.com)
 */
public class MyAppException extends Exception {
    @Override
    public String toString() {
        String aux =super.toString();
        aux += "\n Toma Jeroma \n";
        return aux;
    }

}
