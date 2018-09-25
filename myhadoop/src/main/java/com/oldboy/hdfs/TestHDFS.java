package com.oldboy.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class TestHDFS {
    @Test
    public void testRead() throws Exception {
        //初始化配置文件
        Configuration conf = new Configuration();
        //初始化文件系统
        FileSystem fs = FileSystem.get(conf);
        //初始化路径
        Path p = new Path("/case.sh");
        //通过文件系统获取输入流
        FSDataInputStream fis = fs.open(p);

        int len = 0;
        byte[] buf = new byte[1024];
        while ((len = fis.read(buf)) != -1) {
            System.out.print(new String(buf, 0, len));
        }
        fis.close();
    }

    @Test
    public void testWrite() throws IOException {
        System.setProperty("HADOOP_USER_NAME", "centos");
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        FSDataOutputStream fos = fs.create(new Path("/1.txt"));
        FileInputStream fis = new FileInputStream("D:\\JavaDemo\\safasd.txt");
        IOUtils.copyBytes(fis, fos, 1024);
        fis.close();
        fos.close();
    }

    @Test
    public void testList() throws Exception {

        System.setProperty("HADOOP_USER_NAME","centos");
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        Path p = new Path("/");
        test(fs, p);


    }

    public void test(FileSystem fs, Path p) throws Exception {

        FileStatus[] statuses = fs.listStatus(p);
        for (FileStatus fis : statuses) {
            if (fis.isFile()) {
                System.out.println(fis.getPath().getName() + " is a file");
            } else if (fis.isDirectory()) {
                System.out.println(fis.getPath().getName() + " is a directory");
                test(fs, fis.getPath());
            }
        }

    }

}