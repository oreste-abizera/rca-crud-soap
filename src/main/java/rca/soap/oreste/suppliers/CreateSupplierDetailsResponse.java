//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.06.25 at 02:43:30 PM SAST 
//


package rca.soap.oreste.suppliers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="supplierDetails" type="{http://soap.rca/oreste/suppliers}SupplierDetails"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "supplierDetails",
    "message"
})
@XmlRootElement(name = "CreateSupplierDetailsResponse")
public class CreateSupplierDetailsResponse {

    @XmlElement(required = true)
    protected SupplierDetails supplierDetails;
    @XmlElement(required = true)
    protected String message;

    /**
     * Gets the value of the supplierDetails property.
     * 
     * @return
     *     possible object is
     *     {@link SupplierDetails }
     *     
     */
    public SupplierDetails getSupplierDetails() {
        return supplierDetails;
    }

    /**
     * Sets the value of the supplierDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link SupplierDetails }
     *     
     */
    public void setSupplierDetails(SupplierDetails value) {
        this.supplierDetails = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

}