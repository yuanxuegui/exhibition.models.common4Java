package cn.ac.sec.exhibition.util;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JaxbXmlMapper {
	public static Marshaller createMarshaller(Class<?> clazz, String encoding) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);

			Marshaller marshaller = jaxbContext.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);

			if (encoding != null && !"".equals(encoding)) {
				marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
			}

			return marshaller;
		} catch (JAXBException e) {

		}
		return null;
	}

	/**
	 * 创建UnMarshaller. 线程不安全，需要每次创建或pooling。
	 */
	public static Unmarshaller createUnmarshaller(Class<?> clazz) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			return jaxbContext.createUnmarshaller();
		} catch (JAXBException e) {
			System.out.println(e);
		}
		return null;
	}

	public static String toXml(Object root, Class<?> clazz, String encoding) {
		try {
			StringWriter writer = new StringWriter();
			createMarshaller(clazz, encoding).marshal(root, writer);
			return writer.toString();
		} catch (JAXBException e) {
			System.out.println(e);
		}
		return null;
	}

	/**
	 * Xml->Java Object.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromXml(String xml, Class<T> clazz) {
		try {
			StringReader reader = new StringReader(xml);
			return (T) createUnmarshaller(clazz).unmarshal(reader);
		} catch (JAXBException e) {
			System.out.println(e);
		}
		return null;
	}

	/**
	 * Xml->Java Object.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromXml(File xml, Class<T> clazz) {
		try {
			return (T) createUnmarshaller(clazz).unmarshal(xml);
		} catch (JAXBException e) {
			System.out.println(e);
		}
		return null;
	}
}
