package com.oldboy.mr.screw;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

import java.util.Random;

public class RandomPartition extends Partitioner<Text,IntWritable>{
    Random r = new Random();

    @Override
    public int getPartition(Text text, IntWritable intWritable, int numPartitions) {
        return r.nextInt(numPartitions);
    }
}
