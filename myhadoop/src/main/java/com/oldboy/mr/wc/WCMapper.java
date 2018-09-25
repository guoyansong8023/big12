package com.oldboy.mr.wc;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WCMapper extends Mapper<LongWritable,Text,Text,IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //将value变成String格式
        String line = value.toString();
        //将一行文本进行截串
        String[] arr = line.split(" ");
        for (String word : arr) {
            context.write(new Text(word),new IntWritable(1));
        }
    }
}
