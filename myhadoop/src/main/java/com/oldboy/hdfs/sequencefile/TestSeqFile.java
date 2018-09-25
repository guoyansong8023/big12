package com.oldboy.hdfs.sequencefile;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;


public class TestSeqFile {

    @Test
    public void testWriteSeq() throws Exception{
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");
        FileSystem fs = FileSystem.get(conf);
        Path p = new Path("D:/JavaDemo/block.seq");
        SequenceFile.Writer writer = SequenceFile.createWriter(fs, conf, p, IntWritable.class, Text.class, SequenceFile.CompressionType.BLOCK);
        for (int i = 1; i < 1000 ; i++) {
            IntWritable key = new IntWritable(i);
            Text value = new Text("hello" + i);
            writer.append(key,value);
        }
        writer.close();
    }

    @Test
    public void testReadSeq() throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");
        FileSystem fs = FileSystem.get(conf);
        Path p = new Path("D:/JavaDemo/block.seq");
        SequenceFile.Reader reader = new SequenceFile.Reader(fs,p,conf);
        IntWritable key = new IntWritable();
        Text value = new Text();
        
        while (reader.next(key,value)){
            System.out.println("key:" + key.get() + "," + "val:" + value.toString());
        }
        reader.close();
    }

    @Test
    public void testSeek() throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");
        FileSystem fs = FileSystem.get(conf);
        Path p = new Path("D:/seq/2.seq");
        SequenceFile.Reader reader = new SequenceFile.Reader(fs,p,conf);

        IntWritable key = new IntWritable();
        Text value = new Text();

        //reader.seek(150);
        reader.sync(0);
        reader.next(key, value);
        long position = reader.getPosition();
        System.out.println("key:" + key.get() + ",val:" + value.toString() + ",pos:" + position);
    }

    @Test
    public void testWrite2() throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");
        FileSystem fs = FileSystem.get(conf);
        Path p = new Path("D:/seq/2.seq");
        SequenceFile.Writer writer = SequenceFile.createWriter(fs, conf, p, IntWritable.class, Text.class);
        for (int i = 1; i < 1000; i++) {
            IntWritable key = new IntWritable(i);
            Text value = new Text("hello" + i);
            writer.append(key,value);
            writer.sync();
        }
        writer.close();
    }

    @Test
    public void testWriteRandom() throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");
        FileSystem fs = FileSystem.get(conf);
        Path p = new Path("D:/seq/random.seq");
        SequenceFile.Writer writer = SequenceFile.createWriter(fs, conf, p, IntWritable.class, Text.class, SequenceFile.CompressionType.BLOCK);

        Random r = new Random();

        for (int i = 1; i < 100000; i++) {
            int j = r.nextInt(100000);
            IntWritable key = new IntWritable(j);
            Text value = new Text("hello" + j);
            writer.append(key,value);
        }
        writer.close();
    }
}
