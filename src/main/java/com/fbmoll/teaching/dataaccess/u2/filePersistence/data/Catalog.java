package com.fbmoll.teaching.dataaccess.u2.filePersistence.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * com.fbmoll.teaching.dataaccess.data
 * Class Catalog
 * 28/10/2020
 *
 * @author berto (alberto.soto@gmail.com)
 */
//TODO make LOMBOK work at test/runtime
@Getter
@Setter
@NoArgsConstructor // <--- THIS is it
@XmlRootElement(name = "catalog")
@XmlAccessorType(XmlAccessType.FIELD)
public class Catalog extends  MarshallingWrapper{
    @XmlElement(name = "name")
    private String name;

    @XmlElementWrapper(name = "products")
    @XmlElement(name = "product")
    private List<Product> products;

    @XmlElement(name = "value")
    private Double value;


    private void setValue(Double value) {
        this.value = value;
    }

    public Double getValue() {
        double total = 0;
        if (products != null) {
            for (Product item : products) {
                if (item.getValue()!=null){
                    total += item.getValue();
                }
            }
        }
        setValue(total);
        return value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
