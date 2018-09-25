package com.oldboy.mr.my;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class MyGroupComparator extends WritableComparator {
    public MyGroupComparator() {
        super(CompKey.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        CompKey ck1 = (CompKey)a;
        CompKey ck2 = (CompKey)b;
        return ck1.getYear().compareTo(ck2.getYear());
    }
}
