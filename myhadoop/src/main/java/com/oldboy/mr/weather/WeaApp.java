package com.oldboy.mr.weather;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WeaApp {
    public static void main(String[] args) throws Exception {


        Configuration conf = new Configuration();

        //通过配置文件初始化job
        Job job = Job.getInstance(conf);

        //设置job名称
        job.setJobName("Weather");

        //job入口函数类
        job.setJarByClass(WeaApp.class);

        //设置mapper类
        job.setMapperClass(WeaMapper.class);

        //设置reducer类
        job.setReducerClass(WeaReducer.class);


        //设置map的输出k-v类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //设置reduce的输出k-v类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);

        //设置输入路径
        FileInputFormat.addInputPath(job,new Path(args[0]));

        //设置输出路径
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        //执行job
        job.waitForCompletion(true);






    }


}