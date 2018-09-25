package com.oldboy.mr.userpicture;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class UserDataMapper extends Mapper<LongWritable,Text,Text,IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] arr = value.toString().split("\\|");
        String id = arr[0];
        String appid = arr[15];
        int duration = Integer.parseInt(arr[12]);

        context.write(new Text(id+appid),new IntWritable(duration));
    }
}
