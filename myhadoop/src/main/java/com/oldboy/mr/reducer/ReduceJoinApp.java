package com.oldboy.mr.reducer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class ReduceJoinApp {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");
        Job job = Job.getInstance(conf);
        FileSystem fs = FileSystem.get(conf);
        job.setJobName("Reduce Join");
        job.setJarByClass(ReduceJoinApp.class);
        job.setMapperClass(ReduceJoinMapper.class);
        job.setReducerClass(ReduceJoinReducer.class);
        job.setGroupingComparatorClass(GroupComparator.class);
        job.setMapOutputKeyClass(CompKey.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        Path pin = new Path("D:/join/");
        Path pout = new Path("D:/join/out");
        FileInputFormat.addInputPath(job,pin);
        FileOutputFormat.setOutputPath(job,pout);
        if (fs.exists(pout)){
            fs.delete(pout,true);
        }
        job.setNumReduceTasks(2);
        job.waitForCompletion(true);
    }
}
