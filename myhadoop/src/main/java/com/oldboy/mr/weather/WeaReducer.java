package com.oldboy.mr.weather;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WeaReducer extends Reducer<Text,IntWritable,Text,DoubleWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int max = Integer.MIN_VALUE;
        for (IntWritable c:values) {
            max = Math.max(max,c.get());
        }
        context.write(key,new DoubleWritable(max/10.0));
    }
}
