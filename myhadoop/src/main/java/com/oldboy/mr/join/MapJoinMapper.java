package com.oldboy.mr.join;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MapJoinMapper extends Mapper<LongWritable,Text,Text,NullWritable> {
    Map<String,String> map;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        String path = context.getConfiguration().get("customer.path");
        BufferedReader br = new BufferedReader(new FileReader(path));
        map = new HashMap<String, String>();
        String line = null;
        while ((line = br.readLine())!=null){
            String id = line.split("\t")[0];
            map.put(id,line);
        }
        br.close();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] orderArr = line.split("\t");
        String id = orderArr[3];
        String cusLine = map.get(id);
        String[] cusArr = cusLine.split("\t");
        String name = cusArr[1];
        String orderNo = orderArr[1];
        String oPrice = orderArr[2];
        String newLine = id+"\t"+name+"\t"+orderNo+"\t"+oPrice;
        context.write(new Text(newLine),NullWritable.get());
    }
}
