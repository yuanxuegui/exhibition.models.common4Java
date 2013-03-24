package cn.ac.sec.exhibition.parser;

import cn.ac.sec.exhibition.domain.Exhibition;

public interface IExhibitionParser {
	Exhibition parse(String file);
}
