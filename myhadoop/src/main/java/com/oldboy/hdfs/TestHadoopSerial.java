package com.oldboy.hdfs;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;
import org.junit.Test;

import java.io.*;

public class TestHadoopSerial {
    @Test
    public void testSerial() throws Exception {
        IntWritable iw = new IntWritable(100);
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("D:/JavaDemo/1.h"));
        iw.write(dos);
        dos.close();
    }

    @Test
    public void testDeserial() throws Exception {
        DataInputStream dis = new DataInputStream(new FileInputStream("D:/JavaDemo/1.h"));
        IntWritable iw = new IntWritable();
        iw.readFields(dis);
        dis.close();
        int i = iw.get();
        System.out.println(i);
    }

    @Test
    public void testPersonSerial() throws Exception {
        Person p = new Person("tom",10);
        PersonWritable pw = new PersonWritable(p);
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("D:/JavaDemo/2.h"));
        pw.write(dos);
        dos.close();
    }

    @Test
    public void testPersonDeserial() throws Exception {
        DataInputStream dis = new DataInputStream(new FileInputStream("D:/JavaDemo/2.h"));
        PersonWritable pw = new PersonWritable();
        pw.readFields(dis);
        dis.close();
    }
}
