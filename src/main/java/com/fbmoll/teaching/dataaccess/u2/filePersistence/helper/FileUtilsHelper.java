package com.fbmoll.teaching.dataaccess.u2.filePersistence.helper;

import com.fbmoll.teaching.dataaccess.u2.filePersistence.data.ConfigValues;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * com.fbmoll.teaching.dataaccess.helper
 * Class FileUtils
 * 19/10/2020
 *
 * @author berto (alberto.soto@gmail.com)
 */
public class FileUtilsHelper {
    private static Logger log = LoggerFactory.getLogger(FileUtilsHelper.class);
    private static final String APP_PATH = ".fbmoll";//TODO 2020: Change to project property
    private static final String SERVER_PARAM = "server";
    public static File genUserFile(String fileName, String content) {
        try {
            String pathSeparator = System.getProperty("path.separator");
            String realFileName = StringUtils.substringAfterLast(fileName, pathSeparator);
            String fullFileName = String.format("%s\\%s\\%s", System.getProperty("user.home"), APP_PATH, realFileName);
            return generateFile(fullFileName, content);
        } catch (Exception e) {
            log.error("gen user file failed!", e);
            return null;
        }
    }

    public static File generateFile(String path, String content) {
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;
        File file = null;
        try {
            file = new File(path);
            file.getParentFile().mkdirs();
            fileWriter = new FileWriter(path);
            printWriter = new PrintWriter(fileWriter);
            printWriter.println(content);
        } catch (IOException e) {
            log.error("i/o ERROR ", e);
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    log.error("i/o ERROR ", e);
                }
            }
            return file;
        }
    }

    public static List<String> readFileLines(String filePath) {
        ArrayList<String> rtn = new ArrayList<>();
        BufferedReader buffer = null;
        try {
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            buffer = new BufferedReader(fileReader);
            String fileLine = null;
            while ((fileLine = buffer.readLine()) != null) {
                rtn.add(fileLine);
            }
        } catch (Exception e) {
            log.error("Can't access to file", e);
        } finally {
            if (buffer != null)
                try {
                    buffer.close();
                } catch (Exception e) {
                    log.error("Can't close file", e);
                }
        }
        return rtn;
    }

    /**
     * @param path
     * @return
     */
    public ConfigValues loadProperties(String path) {
        try {
            ConfigValues configValues = new ConfigValues();
            Properties properties = new Properties();
            properties.load(new FileInputStream(path));
            configValues.setName(properties.getProperty(ConfigurationProperties.Name.toString()));
            configValues.setPassword(properties.getProperty(ConfigurationProperties.Password.toString()));
            configValues.setPort(properties.getProperty("port"));
            configValues.setServer(properties.getProperty("server"));
            return configValues;
        } catch (Exception e) {
            FileUtilsHelper.log.error("loading props", e);
            return null;
        }
    }

    public void saveProperties(ConfigValues data, String path) {
        try {
            Properties properties = new Properties();
            properties.setProperty(ConfigurationProperties.Name.toString(), data.getName());
            properties.setProperty(ConfigurationProperties.Password.toString(), data.getPassword());
            properties.setProperty(SERVER_PARAM, data.getServer());
            //TODO 26/10/2020 asoto: Esto es muerte. Quitar ASAP.
            properties.setProperty("port", data.getPort());
            properties.store(new FileOutputStream(path),
                    "Fichero de configuración molón");
        } catch (Exception e) {
            FileUtilsHelper.log.error("saving props", e);
        }
    }
}
