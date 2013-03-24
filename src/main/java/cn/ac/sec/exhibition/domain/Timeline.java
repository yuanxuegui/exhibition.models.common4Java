//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.03.20 at 09:36:46 上午 CST 
//


package cn.ac.sec.exhibition.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for timeline complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="timeline">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice maxOccurs="unbounded">
 *           &lt;element name="property" type="{http://www.sec.ac.cn/exhibition-1.2}property"/>
 *         &lt;/choice>
 *         &lt;choice maxOccurs="unbounded">
 *           &lt;element name="timePoint" type="{http://www.sec.ac.cn/exhibition-1.2}timePoint"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "timeline", propOrder = {
    "property",
    "timePoint"
})
public class Timeline implements IPropertyFinder, IItemFinder<TimePoint, Integer> {

    protected List<Property> property;
    protected List<TimePoint> timePoint;

    /**
     * Gets the value of the property property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the property property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProperty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Property }
     * 
     * 
     */
    public List<Property> getProperty() {
        if (property == null) {
            property = new ArrayList<Property>();
        }
        return this.property;
    }

    /**
     * Gets the value of the timePoint property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the timePoint property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTimePoint().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TimePoint }
     * 
     * 
     */
    public List<TimePoint> getTimePoint() {
        if (timePoint == null) {
            timePoint = new ArrayList<TimePoint>();
        }
        return this.timePoint;
    }

	@Override
	public TimePoint getItemByKey(Integer key) {
		if (key == null)
			return null;
		for (TimePoint timePoint : this.timePoint) {
			if (timePoint.getTick().equals(key)) {
				return timePoint;
			}
		}
		return null;
	}

	@Override
	public String getPropertyValue(String name) {
		if (name == null || "".equals(name))
			return null;
		for (Property prop : this.property) {
			if (prop.getName().equals(name)) {
				return prop.getValue();
			}
		}
		return null;
	}

}