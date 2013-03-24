package cn.ac.sec.exhibition.parser.impl;

import java.io.File;

import cn.ac.sec.exhibition.domain.Exhibition;
import cn.ac.sec.exhibition.parser.IExhibitionParser;
import cn.ac.sec.exhibition.util.JaxbXmlMapper;

public class JaxbXmlExhibitionParser implements IExhibitionParser {

	@Override
	public Exhibition parse(String xmlFile) {
			File file = new File(xmlFile);
			if(file.exists())
				return JaxbXmlMapper.fromXml(file, Exhibition.class);
		return null;
	}
}
