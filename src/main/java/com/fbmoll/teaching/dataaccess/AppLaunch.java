package com.fbmoll.teaching.dataaccess;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * com.fbmoll.teaching.dataaccess
 * Class AppLaunch
 * 09/11/2020
 *
 * @author berto (alberto.soto@gmail.com)
 */
public class AppLaunch {
    private static final Logger log = LoggerFactory.getLogger(AppLaunch.class);

    private String myInnerMethod(String dummy1, Integer loQueQuieras, String... otherThings){
        return null;
    }

    public static void main(String... args) {
        try {
            AppLaunch aux = new AppLaunch();
            aux.myInnerMethod("yuhu",123,"1","2","3");
            log.info("Aloha " + StringUtils.defaultIfEmpty(args[0],"Pepito"));
            throw new MyAppException();
        }catch (Exception e){
            log.error("PUM",e);
        }
    }
}
