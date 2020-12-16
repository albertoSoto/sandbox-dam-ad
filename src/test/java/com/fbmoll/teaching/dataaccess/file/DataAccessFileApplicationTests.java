package com.fbmoll.teaching.dataaccess.file;

import com.fbmoll.teaching.dataaccess.u2.filePersistence.data.Catalog;
import com.fbmoll.teaching.dataaccess.u2.filePersistence.data.Product;
import com.fbmoll.teaching.dataaccess.u2.filePersistence.helper.DummyUtils;
import com.fbmoll.teaching.dataaccess.u2.filePersistence.helper.FileDataSingleton;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.w3c.dom.Document;

import java.io.File;
import java.util.List;

/**
 * com.fbmoll.teaching.dataaccess
 * Class DataAccessFileApplicationTests
 * 26/10/2020
 *
 * @author berto (alberto.soto@gmail.com)
 */
@SpringBootTest
public class DataAccessFileApplicationTests {

    @Test
    void generateXMLDocument() {
        FileDataSingleton fileDataSingleton = FileDataSingleton.getInstance();
        Document document = fileDataSingleton.generateRandomProductXML();
        String fileName = String.format("%s\\.fbmoll\\classic.xml", System.getProperty("user.home"));
        File file = fileDataSingleton.saveDocumentAsFile(fileName, document, true);
        Assert.notNull(file, "El fichero no puede ser nulo, animal!");
    }

    @Test
    void trySerializer() {
        DummyUtils dummyUtils = new DummyUtils();
        String fileName = String.format("%s\\.fbmoll\\serial.data", System.getProperty("user.home"));
        List<Product> products = dummyUtils.getProducts();
        FileDataSingleton.getInstance().serializeContent(fileName, products);
        Assert.notEmpty(products, "Sin productos");
        List<Product> returnProducts = FileDataSingleton.getInstance().deserialize(fileName);
        Assert.notEmpty(returnProducts, "Sin productos");
        boolean isSameFirstName = StringUtils.equals(products.get(0).getName()
                , returnProducts.get(0).getName());
        Assert.isTrue(isSameFirstName, "No son iguales");
    }

    @Test
    void tryMarshallingAndUnmarshalling() {
        String fileName = String.format("%s\\.fbmoll\\jackson-v2.xml", System.getProperty("user.home"));
        FileDataSingleton fileDataSingleton = FileDataSingleton.getInstance();
        Catalog catalog = new Catalog();
        DummyUtils dummyUtils = new DummyUtils();
        catalog.setProducts(dummyUtils.getProducts());
        catalog.setName("catalog serialization test");
        File file = fileDataSingleton.marshallContent(fileName, catalog);
        Assert.notNull(file, "Example rules");
        Catalog loadedCatalog = fileDataSingleton.unmarshallContent(fileName, Catalog.class);
        Assert.notNull(loadedCatalog, "Example rules");
    }

    @Test
    void tryJSONMarshallingAndUnmarshalling(){
        FileDataSingleton fileDataSingleton = FileDataSingleton.getInstance();
        Catalog catalog = new Catalog();
        DummyUtils dummyUtils = new DummyUtils();
        catalog.setProducts(dummyUtils.getProducts());
        catalog.setName("catalog serialization test");
        String jsonContent = fileDataSingleton.marshall2JSON(catalog);
        Catalog unmarshallCatalog = fileDataSingleton.unmarshallJSON(jsonContent,Catalog.class);
        Assert.notNull(unmarshallCatalog, "Example rules");
    }
}
