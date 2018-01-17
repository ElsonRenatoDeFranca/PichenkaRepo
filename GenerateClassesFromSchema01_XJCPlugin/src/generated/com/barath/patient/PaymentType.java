//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.7 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2017.12.30 às 11:55:31 PM BRST 
//


package com.barath.patient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de PaymentType complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="PaymentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="cash" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="insurance" type="{http://www.barath.com/Patient}Insurance"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentType", propOrder = {
    "cash",
    "insurance"
})
public class PaymentType {

    protected Integer cash;
    protected Insurance insurance;

    /**
     * Obtém o valor da propriedade cash.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCash() {
        return cash;
    }

    /**
     * Define o valor da propriedade cash.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCash(Integer value) {
        this.cash = value;
    }

    /**
     * Obtém o valor da propriedade insurance.
     * 
     * @return
     *     possible object is
     *     {@link Insurance }
     *     
     */
    public Insurance getInsurance() {
        return insurance;
    }

    /**
     * Define o valor da propriedade insurance.
     * 
     * @param value
     *     allowed object is
     *     {@link Insurance }
     *     
     */
    public void setInsurance(Insurance value) {
        this.insurance = value;
    }

}
