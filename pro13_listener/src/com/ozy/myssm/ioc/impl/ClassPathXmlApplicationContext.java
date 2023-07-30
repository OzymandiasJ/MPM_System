package com.ozy.myssm.ioc.impl;

import com.ozy.myssm.ioc.BeanFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 加载xml配置文件
 */
public class ClassPathXmlApplicationContext implements BeanFactory {
    private Map<String,Object> beanMap =new HashMap<>();
    public ClassPathXmlApplicationContext(String path) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        //        读取applicationContext.xml配置文件
        InputStream inputStream=getClass().getClassLoader().getResourceAsStream(path);
        //1,创建DocumentBuilderFactory对象
        DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newDefaultInstance();
        //2，创建DocumentBuilder对象
        DocumentBuilder documentBuilder= null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        //3,创建
        Document document= null;//请注意，要选择导org.w3c.com,导错了不识别的
        try {
            document = documentBuilder.parse(inputStream);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //4,获取所有的bean节点
        NodeList beanNodeList=document.getElementsByTagName("bean");
        for (int i = 0; i < beanNodeList.getLength(); i++) {
            Node beanNode =beanNodeList.item(i);
            if(beanNode.getNodeType()==Node.ELEMENT_NODE){//如果当前beanNode节点的类型是：元素节点
                Element beanElement=(Element) beanNode;//那么就强转成元素节点类型Element
                String beanId =beanElement.getAttribute("id");
                String className=beanElement.getAttribute("class");
                Class beanClass=Class.forName(className);
//                创建bean实例
                Object beanObject = beanClass.newInstance();
//                将bean实例对象保存到map容器中
                beanMap.put(beanId,beanObject);
//                到目前为止，此必需要注意的是，bean和bean之间的依赖关采还没有设置
            }
        }
        //5，组装bean之间的依赖关系
        for (int i = 0; i < beanNodeList.getLength(); i++) {
            Node beanNode = beanNodeList.item(i);
            if (beanNode.getNodeType() == Node.ELEMENT_NODE) {//如果当前beanNode节点的类型是：元素节点
                Element beanElement = (Element) beanNode;//那么就强转成元素节点类型Element
                String beanId = beanElement.getAttribute("id");
                NodeList beanChildList=beanElement.getChildNodes();
                System.out.println(beanChildList.getLength());
                for (int j = 0; j < beanChildList.getLength(); j++) {
                    Node beanChildNode=beanChildList.item(j);
                    if(beanChildNode.getNodeType()==Node.ELEMENT_NODE&&"property".equals(beanChildNode.getNodeName())){
                        Element propertyElement=(Element) beanChildNode;
                        String propertyName=propertyElement.getAttribute("name");
                        String propertyRef=propertyElement.getAttribute("ref");
//                        1,找到propertyRef对应的实例refObj
                        Object refObj=beanMap.get(propertyRef);
//                        2,将refObj设置到当前bean对应的实例的property属性上去
                        Object beanObj=beanMap.get(beanId);
                        Class beanClazz =beanObj.getClass();
                        Field propertyField=beanClazz.getDeclaredField(propertyName);
                        propertyField.setAccessible(true);
                        propertyField.set(beanObj,refObj);
                    }
                }
            }
        }
    }
    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }
}
