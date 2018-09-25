package com.oldboy.mr.userdraw;

import com.oldboy.mr.userdraw.util.ConfUtil;
import com.oldboy.mr.userdraw.util.ReadAppTab;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserDrawMapper2 extends Mapper<LongWritable,Text,Text,Text> {

    /**
     *
     * +/rmMLtMV+s+gXTDoOaoxQ==|10005|824
     * +/rmMLtMV+s+gXTDoOaoxQ==|220499|98
     * +/rmMLtMV+s+gXTDoOaoxQ==|70093|75610
     *
     * AppTab
     * 10005|微信|0.001|0.001|0|0.2|0.3|0.2|0.3
     * 220499|搜狐浏览器|0.001|0.001|0.001|0.002|0.002|0.002|0.003
     * 70093|豌豆荚|0.001|0.001|0.001|0.002|0.002|0.002|0.003
     */
    ConfUtil confUtil;
    Map<String,String> map;
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        map = new HashMap<String,String>();
        confUtil = new ConfUtil();
        map = ReadAppTab.readFile();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] arr = line.split(confUtil.separator);
        String id = arr[0];
        String appid = arr[1];
        String duration = arr[2];
        if (map.containsKey(appid)){
            String appLine = map.get(appid);
            context.write(new Text(id),new Text(duration+"|"+appLine));
        }
    }

}
