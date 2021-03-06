package com.oldboy.mr.reducer;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class ReduceJoinReducer extends Reducer<CompKey, Text, Text, NullWritable> {

    @Override
    protected void reduce(CompKey key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        Iterator<Text> iter = values.iterator();

        //iter.hasNext();
        //用户行
        Text cusLine = iter.next();
        String[] cusArr = cusLine.toString().split("\t");

        while (iter.hasNext()) {

            //order行
            String orderLine = iter.next().toString();

            //拼串操作，并将其输出
            String[] orderArr = orderLine.split("\t");

            String uid = cusArr[0];
            String name = cusArr[1];
            String orderno = orderArr[1];
            String oprice = orderArr[2];

            String newLine = uid + "\t" + name + "\t" + orderno + "\t" + oprice;

            context.write(new Text(newLine),NullWritable.get());
        }
    }
}
