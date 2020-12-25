package com.wxpay.api.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author doveylovey
 * @email 1135782208@qq.com
 * @date 2020-12-25
 */
public class XMLParser {
    public static <T> T getObjFromXML(String xmlString, Class<T> clazz) throws ParserConfigurationException, IOException, SAXException {
        JSONObject obj = getJSONObjectFromXML(xmlString);
        return obj.toJavaObject(clazz);
    }

    public static JSONObject getJSONObjectFromXML(String xmlString) throws ParserConfigurationException, IOException, SAXException {
        //这里用Dom的方式解析回包的最主要目的是防止API新增回包字段
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setExpandEntityReferences(false);
        factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream is = getStringStream(xmlString);
        Document document = builder.parse(is);
        //获取到document里面的全部结点
        NodeList allNodes = document.getFirstChild().getChildNodes();
        Node node;
        JSONObject jsonObject = new JSONObject();
        int i = 0;
        while (i < allNodes.getLength()) {
            node = allNodes.item(i);
            if (node instanceof Element) {
                jsonObject.put(node.getNodeName(), node.getTextContent());
            }
            i++;
        }
        return jsonObject;
    }

    public static String object2XML(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        for (Entry<String, Object> en : map.entrySet()) {
            String key = en.getKey();
            Object value = en.getValue();
            sb.append("<").append(key).append("><![CDATA[").append(value).append("]]></").append(key).append(">");
        }
        sb.append("</xml>");
        return sb.toString();
    }

    public static InputStream getStringStream(String sInputString) {
        ByteArrayInputStream tInputStringStream = null;
        if (sInputString != null && !sInputString.trim().equals("")) {
            try {
                tInputStringStream = new ByteArrayInputStream(sInputString.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return tInputStringStream;
    }

    @Deprecated
    public static Object getObjectFromXML(String xml, Class tClass) {
        return getObjectFromXml(xml, tClass, "xml");
    }

    @Deprecated
    public static Object getObjectFromXml(String xml, Class tClass, String alias) {
        //将从API返回的XML数据映射到Java对象
        XStream xStreamForResponseData = new XStream();
        xStreamForResponseData.alias(alias, tClass);
        xStreamForResponseData.ignoreUnknownElements();//暂时忽略掉一些新增的字段
        xStreamForResponseData.autodetectAnnotations(true);
        return xStreamForResponseData.fromXML(xml);
    }
}
