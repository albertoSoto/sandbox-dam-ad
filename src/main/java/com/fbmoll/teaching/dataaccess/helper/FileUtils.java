package com.fbmoll.teaching.dataaccess.helper;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * com.fbmoll.teaching.dataaccess.helper
 * Class FileUtils
 * 19/10/2020
 *
 * @author berto (alberto.soto@gmail.com)
 */
public class FileUtils {
    private static Logger log = LoggerFactory.getLogger(FileUtils.class);
    private static final String APP_PATH = ".fbmoll";//TODO 2020: Change to project property

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
            while ((fileLine = buffer.readLine()) != null){
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
}
