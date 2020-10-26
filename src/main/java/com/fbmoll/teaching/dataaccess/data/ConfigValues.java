package com.fbmoll.teaching.dataaccess.data;

import org.apache.commons.lang3.StringUtils;

import javax.security.auth.login.Configuration;

/**
 * com.fbmoll.teaching.dataaccess.data
 * Class PropertyWrapper
 * 21/10/2020
 *
 * @author berto (alberto.soto@gmail.com)
 */
public class ConfigValues {
    private String name;
    private String password;
    private String server;
    private String port;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass().isAssignableFrom(this.getClass())) {
            ConfigValues otherValue = (ConfigValues) obj;
            return StringUtils.equals(otherValue.getName(), this.getName())
                    && StringUtils.equals(otherValue.getPassword(), this.getPassword())
                    && StringUtils.equals(otherValue.getPort(), this.getPort())
                    && StringUtils.equals(otherValue.getServer(), this.getServer());
        }
        return super.equals(obj);
    }
}
