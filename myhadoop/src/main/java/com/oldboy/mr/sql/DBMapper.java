package com.oldboy.mr.sql;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class DBMapper extends Mapper<LongWritable,MyWritable,Text,IntWritable> {
    @Override
    protected void map(LongWritable key, MyWritable value, Context context) throws IOException, InterruptedException {
        String line = value.getLine();
        String[] arr = line.split(" ");
        for (String word : arr) {
            context.write(new Text(word),new IntWritable(1));
        }
    }
}
