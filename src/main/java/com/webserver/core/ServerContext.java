package com.webserver.core;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * ��������������Ϣ
 *
 * @author ta
 */
public class ServerContext {
    /*
     * Servletӳ���ϵ
     * key:����·��
     * value:Servlet������
     */
    private static Map<String, String> servletMapping = new HashMap<String, String>();

    static {
        initServletMapping();
    }

    /**
     * ��ʼ��Servletӳ��
     */
    private static void initServletMapping() {
        /*
         * ����conf/servlets.xml�ļ���ʼ��
         */
        try {
            SAXReader reader = new SAXReader();
            Document doc = reader.read(new File("conf/servlets.xml"));
            Element root = doc.getRootElement();
            List<Element> list = root.elements();
            for (Element servletEle : list) {
                String url = servletEle.attributeValue("url");
                String className = servletEle.attributeValue("className");
                servletMapping.put(url, className);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * ��������·����ȡ��Ӧ��Servlet����
     *
     * @param url
     * @return
     */
    public static String getServletName(String url) {
        return servletMapping.get(url);
    }

    public static void main(String[] args) {
        System.out.println(
                getServletName("/myweb/reg")
        );
    }
}










