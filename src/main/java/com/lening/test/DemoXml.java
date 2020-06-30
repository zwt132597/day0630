package com.lening.test;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;

public class DemoXml {
    public static void main(String[] args) throws Exception {
//        读取文件
        File file = new File("D:\\idea_work _space\\day0408\\src\\main\\resources\\jiexi.xml");
        SAXReader reader = new SAXReader();
        Document doc =  null;
        try {
            doc = reader.read(file);
        } catch (Exception e){
            e.printStackTrace();
        }
        Element root = doc.getRootElement();
        Iterator it = root.elementIterator();
        while (it.hasNext()){
            Element person = (Element) it.next();
            System.out.println("节点:"+person.elementText("CARDNO"));
        }
    }
}
