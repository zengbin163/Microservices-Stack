package com.famiao.search.database.sql;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.famiao.search.database.sql.template.DynamicSqlTemplate;

/**
 * 动态sql处理模板，根据用户配置的sql语句查询数据
 * 
 * @author zengbin
 *
 */
@Configuration
public class DynamicSqlResolver {

	@Value("${dynamic.sql.path}")
	private String dynamicSqlFile;

	/**
	 * 解析动态sql
	 * 
	 * @return
	 * @throws DocumentException
	 */
	public List<DynamicSqlTemplate> resolve() throws DocumentException {
		// 遍历所有的元素节点
		return this.resolveNodes(this.getRootElement());
	}
	
	private Element getRootElement() throws DocumentException  {
        // 创建saxReader对象
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(dynamicSqlFile));
        // 获取根节点元素对象
        Element node = document.getRootElement();
        return node;
	}

	/**
	 * 遍历当前节点元素下面的所有(元素的)子节点
	 * 
	 * @param node
	 */
	private List<DynamicSqlTemplate> resolveNodes(Element node) {
		if (null == node) {
			throw new IllegalArgumentException("node参数值为空");
		}
		List<DynamicSqlTemplate> tempList = new ArrayList<DynamicSqlTemplate>();
		// 当前节点下面子节点迭代器
		Iterator<Element> it = node.elementIterator();
		// 遍历
        List<Integer> ids = new ArrayList<>();
		while (it.hasNext()) {
			DynamicSqlTemplate temp = new DynamicSqlTemplate();
			// 获取某个子节点对象
			Element e = it.next();
			if ("nodeMapping".equals(e.getName())) {
			    if(null==e.element("id") || StringUtils.isBlank(e.element("id").getStringValue())) {
			        throw new IllegalArgumentException("DynamicSqlTemplate \'id\' is null");
                } else {
                    Integer id = Integer.valueOf(this.replaceX(e.element("id").getStringValue()));
                    if(ids.contains(id)) {
                        throw new IllegalArgumentException("DynamicSqlTemplate \'id\' = " + id + " is repeated");
                    }
                    ids.add(id);
                    temp.setId(id);
                }
			    
			    if (null == e.element("mapping") || StringUtils.isBlank(e.element("mapping").getStringValue())) {
			        throw new IllegalArgumentException("DynamicSqlTemplate \'sql\' is null");
			    } else {
			        temp.setMapping(this.replaceX(e.element("mapping").getStringValue()));
			    }
			    
                if (null == e.element("sql") || StringUtils.isBlank(e.element("sql").getStringValue())) {
                    throw new IllegalArgumentException("DynamicSqlTemplate \'sql\' is null");
                } else {
                    temp.setSql(this.replaceX(e.element("sql").getStringValue()));
                }
                
                if (null == e.element("field") || StringUtils.isBlank(e.element("field").getStringValue())) {
                	throw new IllegalArgumentException("DynamicSqlTemplate \'field\' is null");
                } else {
                	temp.setField(this.replaceX(e.element("field").getStringValue()));
                }
                
                if (null == e.element("index") || StringUtils.isBlank(e.element("index").getStringValue())) {
                    throw new IllegalArgumentException("DynamicSqlTemplate \'index\' is null");
                } else {
                    temp.setIndex(this.replaceX(e.element("index").getStringValue()));
                }

				tempList.add(temp);
			}
			// 对子节点进行遍历
			this.resolveNodes(e);
		}
		return tempList;
	}
	
	public DynamicSqlTemplate resolveNodeById(Integer sqlId) throws DocumentException {
	    Element rootNode = this.getRootElement();
        if (null == rootNode) {
            throw new IllegalArgumentException("rootNode参数值为空");
        }
        if (null == sqlId) {
            throw new IllegalArgumentException("sqlId参数值为空");
        }
        // 当前节点下面子节点迭代器
        Iterator<Element> it = rootNode.elementIterator();
        while (it.hasNext()) {
            DynamicSqlTemplate temp = new DynamicSqlTemplate();
            // 获取某个子节点对象
            Element e = it.next();
            if ("nodeMapping".equals(e.getName()) && sqlId.equals(Integer.valueOf(this.replaceX(e.element("id").getStringValue())))) {
                temp.setId(Integer.valueOf(this.replaceX(e.element("id").getStringValue())));
                
                if (null == e.element("mapping") || StringUtils.isBlank(e.element("mapping").getStringValue())) {
                    throw new IllegalArgumentException("DynamicSqlTemplate \'sql\' is null");
                } else {
                    temp.setMapping(this.replaceX(e.element("mapping").getStringValue()));
                }
                
                if (null == e.element("sql") || StringUtils.isBlank(e.element("sql").getStringValue())) {
                    throw new IllegalArgumentException("DynamicSqlTemplate \'sql\' is null");
                } else {
                    temp.setSql(this.replaceX(e.element("sql").getStringValue()));
                }
                
                if (null == e.element("field") || StringUtils.isBlank(e.element("field").getStringValue())) {
                    throw new IllegalArgumentException("DynamicSqlTemplate \'field\' is null");
                } else {
                    temp.setField(this.replaceX(e.element("field").getStringValue()));
                }
                
                if (null == e.element("index") || StringUtils.isBlank(e.element("index").getStringValue())) {
                    throw new IllegalArgumentException("DynamicSqlTemplate \'index\' is null");
                } else {
                    temp.setIndex(this.replaceX(e.element("index").getStringValue()));
                }

                return temp;
            }
        }
        return null;
	}
	
	/**
	 * 替换非法字符
	 * @param element
	 * @return
	 */
	private String replaceX(String element) {
	    if(StringUtils.isBlank(element)) {
	        return element;
	    }
	    return element.replaceAll("(\r\n|\r|\n|\n\r|\t)", " ").trim();
	}
}
