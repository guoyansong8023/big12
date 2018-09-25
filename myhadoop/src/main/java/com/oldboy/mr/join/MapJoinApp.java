package com.oldboy.mr.join;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


import java.io.IOException;

public class MapJoinApp {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");
        conf.set("customer.path","D:/join/customers.txt");
        Job job = Job.getInstance(conf);
        FileSystem fs = FileSystem.get(conf);
        job.setJobName("Map join");
        job.setJarByClass(MapJoinApp.class);
        job.setMapperClass(MapJoinMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);
        Path pin = new Path("D:/join/orders.txt");
        Path pout = new Path("D:/join/out");
        FileInputFormat.addInputPath(job,pin);
        FileOutputFormat.setOutputPath(job,pout);
        if (fs.exists(pout)){
            fs.delete(pout,true);
        }
        job.setNumReduceTasks(1);
        job.waitForCompletion(true);
    }
}
