package com.oldboy.hdfs;

import org.apache.hadoop.io.IntWritable;
import org.junit.Test;

import java.io.*;

public class TestJavaSerial {
    @Test
    public void testSerial() throws Exception {
        Integer i = 100;
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:/JavaDemo/2.j"));
        oos.writeObject(i);
        oos.close();
    }

    @Test
    public void testDeserial() throws Exception{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:/JavaDemo/2.j"));
        Object o = ois.readObject();
        System.out.println((Integer)o);
                
    }

    @Test
    public void testPersonSerial() throws Exception {
        Person p = new Person("tom",10);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:/JavaDemo/person.j"));
        oos.writeObject(p);
        oos.close();
    }
    
    @Test
    public void testPersonDeserial() throws Exception {
        Person p = new Person();
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:/JavaDemo/person.j"));
        Object o = ois.readObject();
        System.out.println(o);
    }
}
