package com.oldboy.mr.userdraw;

import com.oldboy.mr.userdraw.util.ConfUtil;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class UserDrawReducer2 extends Reducer<Text,Text,Text,Text> {
    ConfUtil confUtil;
    double[] arr2;
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        confUtil = new ConfUtil();
        arr2 = new double[]{0,0,0,0,0,0,0};
    }

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        for (Text value : values) {
            String line = value.toString();
            String[] arr = line.split(confUtil.separator);
            arr2[0] += Double.parseDouble(arr[0])*Double.parseDouble(arr[3]);
            arr2[1] += Double.parseDouble(arr[0])*Double.parseDouble(arr[4]);
            arr2[2] += Double.parseDouble(arr[0])*Double.parseDouble(arr[5]);
            arr2[3] += Double.parseDouble(arr[0])*Double.parseDouble(arr[6]);
            arr2[4] += Double.parseDouble(arr[0])*Double.parseDouble(arr[7]);
            arr2[5] += Double.parseDouble(arr[0])*Double.parseDouble(arr[8]);
            arr2[6] += Double.parseDouble(arr[0])*Double.parseDouble(arr[9]);
        }

        double fm = arr2[0]/(arr2[0]+arr2[1]);
        double fw = arr2[1]/(arr2[0]+arr2[1]);
        double f1 = arr2[2]/(arr2[2]+arr2[3]+arr2[4]+arr2[5]+arr2[6]);
        double f2 = arr2[3]/(arr2[2]+arr2[3]+arr2[4]+arr2[5]+arr2[6]);
        double f3 = arr2[4]/(arr2[2]+arr2[3]+arr2[4]+arr2[5]+arr2[6]);
        double f4 = arr2[5]/(arr2[2]+arr2[3]+arr2[4]+arr2[5]+arr2[6]);
        double f5 = arr2[6]/(arr2[2]+arr2[3]+arr2[4]+arr2[5]+arr2[6]);

        context.write(key,new Text(fm+"|"+fw+"|"+f1+"|"+f2+"|"+f3+"|"+f4+"|"+f5));
    }
}
