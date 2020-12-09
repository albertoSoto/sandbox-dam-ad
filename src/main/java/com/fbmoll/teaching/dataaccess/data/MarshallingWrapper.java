package com.fbmoll.teaching.dataaccess.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * com.fbmoll.teaching.dataaccess.data
 * Class MarshallingWrapper
 * 28/10/2020
 *
 * @author berto (alberto.soto@gmail.com)
 */
@XmlRootElement(name = "dataContainer")
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class MarshallingWrapper implements Serializable {

}
