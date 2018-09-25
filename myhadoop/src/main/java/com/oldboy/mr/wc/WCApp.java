package com.oldboy.mr.wc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WCApp {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");
        //通过配置文件初始化job
        Job job = Job.getInstance(conf);
        //设置job名称
        job.setJobName("word count");
        //设置job入口函数类
        job.setJarByClass(WCApp.class);
        //设置mapper类
        job.setMapperClass(WCMapper.class);
        //设置reducer类
        job.setReducerClass(WCReducer.class);

        job.setNumReduceTasks(3);
        //设置map的输出k-v类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //设置reduce的输出k-v类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //设置输入输出路径
        FileInputFormat.addInputPath(job,new Path("D:/wc/1.txt"));
        FileOutputFormat.setOutputPath(job,new Path("D:/wc/out"));
        //执行job
        job.waitForCompletion(true);
    }
}
