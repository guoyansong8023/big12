package com.oldboy.hdfs.codec;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.*;
import org.apache.hadoop.util.ReflectionUtils;
import org.junit.Test;

import java.io.*;

public class TestCodec {
    @Test
    public void testCompress() throws Exception {
        Configuration conf = new Configuration();
        CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(Lz4Codec.class, conf);
        CompressionOutputStream cos = codec.createOutputStream(new FileOutputStream("D:/codec/1.xml.lz4"));
        FileInputStream fis = new FileInputStream("D:/codec/1.xml");
        System.out.println("压缩前文件大小"+new File("D:/codec/1.xml").length());
        long start = System.currentTimeMillis();
        IOUtils.copyBytes(fis,cos,1024);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println("压缩后文件大小"+new File("D:/codec/1.xml.lz4").length());
        cos.close();
        fis.close();
    }
}
