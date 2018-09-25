package com.oldboy.hdfs;

import org.apache.hadoop.io.Writable;
import org.junit.Test;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class PersonWritable implements Writable {
    private Person p ;

    public PersonWritable(Person p) {
        this.p = p;
    }

    public PersonWritable() {
        p = new Person();
    }

    public void write(DataOutput out) throws IOException {
        out.writeUTF(p.getName());
        out.writeInt(p.getAge());

    }

    public void readFields(DataInput in) throws IOException {
        p.setName(in.readUTF());
        p.setAge(in.readInt());
        System.out.println(p);
    }

}
