package com.oldboy.mr.top10;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.TreeSet;

public class TopReducer2 extends Reducer<Text,IntWritable,CompKey,NullWritable> {
    int top;
    TreeSet<CompKey> ts;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        top = Integer.parseInt(context.getConfiguration().get("num.top"));
        ts = new TreeSet<CompKey>();
    }

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        for (IntWritable value : values) {
            String pass = key.toString();
            int sum = value.get();
            CompKey ck = new CompKey(pass,sum);
            ts.add(ck);

            if (ts.size() > top){
                ts.remove(ts.last());
            }
        }
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        for (CompKey ck : ts) {
            context.write(ck,NullWritable.get());
        }
    }
}

