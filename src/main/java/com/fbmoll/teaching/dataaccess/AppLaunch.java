package com.fbmoll.teaching.dataaccess;

import org.apache.commons.lang3.StringUtils;

/**
 * com.fbmoll.teaching.dataaccess
 * Class AppLaunch
 * 09/11/2020
 *
 * @author berto (alberto.soto@gmail.com)
 */
public class AppLaunch {

    private String myInnerMethod(String dummy1, Integer loQueQuieras, String... otherThings){
        return null;
    }

    public static void main(String... args) {
        try {
            AppLaunch aux = new AppLaunch();
            aux.myInnerMethod("yuhu",123,"1","2","3");
            System.out.println("Aloha " + StringUtils.defaultIfEmpty(args[0],"Pepito"));
        }catch (Exception e){
            System.out.println("PUM");
        }
    }
}
