package com.oldboy.mr.my;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class CompKey implements WritableComparable<CompKey> {
    private String year;
    private int temp;

    public CompKey() {
    }

    public CompKey(String year, int temp) {
        this.year = year;
        this.temp = temp;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int compareTo(CompKey o) {
        String oyear = o.getYear();
        String tyear = this.getYear();
        int otemp = o.getTemp();
        int ttemp = this.getTemp();

        if (tyear.equals(oyear)){
            return otemp - ttemp;
        }
        return oyear.compareTo(tyear);
    }

    public void write(DataOutput out) throws IOException {
        out.writeUTF(year);
        out.writeInt(temp);
    }

    public void readFields(DataInput in) throws IOException {
        this.setYear(in.readUTF());
        this.setTemp(in.readInt());
    }
}
