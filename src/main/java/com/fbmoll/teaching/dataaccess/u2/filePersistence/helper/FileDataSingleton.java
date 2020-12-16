package com.fbmoll.teaching.dataaccess.u2.filePersistence.helper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import com.fbmoll.teaching.dataaccess.u2.filePersistence.data.Product;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.List;

/**
 * com.fbmoll.teaching.dataaccess.helper
 * Class FileDataSingleton
 * 26/10/2020
 * <p>
 * Implements Singleton pattern:
 * - private constructor
 * - getInstance Method
 * - defines an unique instance
 *
 * @author berto (alberto.soto@gmail.com)
 */
public class FileDataSingleton {
    private Logger log = LoggerFactory.getLogger(FileDataSingleton.class);
    private static FileDataSingleton instance = null;

    public static FileDataSingleton getInstance() {
        if (instance == null) {
            instance = new FileDataSingleton();
        }
        return instance;
    }

    private FileDataSingleton() {
    }

    public void serializeContent(String path, Object o) {
        ObjectOutputStream serializer = null;
        try {
            if (o != null) {
                serializer = new ObjectOutputStream(new FileOutputStream(path));
                serializer.writeObject(o);
            }
        } catch (Exception e) {
            log.error("Can't serialize", e);
        } finally {
            if (serializer != null) {
                try {
                    serializer.close();
                } catch (IOException e) {
                    log.error("Can't serialize", e);
                }
            }
        }
    }

    /**
     * @param path file path location
     * @param <T>  collection or data to receive
     * @return collection stored in serialization
     */
    public <T extends Serializable> T deserialize(String path) {
        T o = null;
        ObjectInputStream deserializer = null;
        try {
            deserializer = new ObjectInputStream(new FileInputStream(path));
            o = (T) deserializer.readObject();
        } catch (Exception e) {
            log.error("deserializing file", e);
        } finally {
            if (deserializer != null) {
                try {
                    deserializer.close();
                } catch (Exception e) {
                    log.error("deserializing file", e);
                }
            }
        }
        return o;
    }

    /**
     * Marshalling and saving XML to the file.
     *
     * @param path
     * @param catalog
     * @param <T>
     * @return
     */
    public <T extends Serializable> File marshallContent(String path, T catalog) {
        File file = new File(path);
        try {
            JAXBContext context = JAXBContext.newInstance(catalog.getClass());
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(catalog, file);
        } catch (Exception e) {
            log.error("Marshalling file", e);
        }
        return file;
    }


    /**
     * @param path              File location
     * @param typeArgumentClass class of expected object. Should have empty constructor!
     * @param <T>
     * @return unmarshalled file content
     */
    public <T extends Serializable> T unmarshallContent(String path, Class<T> typeArgumentClass) {
        File file = new File(path);
        try {
            T catalog = typeArgumentClass.newInstance();
            JAXBContext context = JAXBContext.newInstance(catalog.getClass());
            Unmarshaller um = context.createUnmarshaller();
            if (file.exists() && !file.isDirectory()) {
                catalog = (T) um.unmarshal(file);
            }
            return catalog;
        } catch (Exception e) {
            log.error("Marshalling file", e);
        }
        return null;
    }

    private ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME);
        AnnotationIntrospector introspector = new JaxbAnnotationIntrospector(mapper.getTypeFactory());
        mapper.setAnnotationIntrospector(introspector);
        // Don't close the output stream
        mapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
        // Don't include NULL properties.
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper;
    }

    public <T extends Serializable> String marshall2JSON(T item) {
        try {
            return getMapper().writeValueAsString(item);
        } catch (Exception e) {
            log.error("Marshalling to string failure", e);
            return StringUtils.EMPTY;
        }
    }

    public <T extends Serializable> T unmarshallJSON(String content, Class<T> typeArgumentClass) {
        try {
            return getMapper().readValue(content, typeArgumentClass);
        } catch (Exception e) {
            log.error("Unmarshalling string failure", e);
            return null;
        }
    }

    /*
     * XML Methods
     */
    public Document generateRandomProductXML() {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            // root element
            Element rootElement = doc.createElement("products");
            DummyUtils dummyUtils = new DummyUtils();
            List<Product> products = dummyUtils.getProducts();
            for (Product item : products) {
                Element aux = doc.createElement("product");
                aux.setAttribute("value", item.getValue().toString());
                aux.setTextContent(item.getName());
                rootElement.appendChild(aux);
            }
            doc.appendChild(rootElement);
            return doc;
        } catch (Exception e) {
            log.error("Creating XML", e);
            return null;
        }
    }

    /**
     * @param path
     * @param document
     * @param showOuput
     * @return
     */
    public File saveDocumentAsFile(String path, Document document, boolean showOuput) {
        try {
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            File file = new File(path);
            StreamResult result = new StreamResult(new File(path));
            transformer.transform(source, result);
            if (showOuput) {
                // Output to console for testing
                StreamResult consoleResult = new StreamResult(System.out);
                transformer.transform(source, consoleResult);
            }
            return file;
        } catch (Exception e) {
            log.error("Saving xml file", e);
            return null;
        }
    }


}
