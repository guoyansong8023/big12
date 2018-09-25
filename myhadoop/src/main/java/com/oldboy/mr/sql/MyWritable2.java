package com.oldboy.mr.sql;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyWritable2 implements Writable,DBWritable {
    private String word;
    private int count;

    public void write(DataOutput out) throws IOException {
        out.writeUTF(word);
        out.writeInt(count);
    }

    public void readFields(DataInput in) throws IOException {
        word = in.readUTF();
        count = in.readInt();
    }

    public void write(PreparedStatement statement) throws SQLException {
        statement.setString(1,word);
        statement.setInt(2,count);
    }

    public void readFields(ResultSet resultSet) throws SQLException {
        word = resultSet.getString(1);
        count = resultSet.getInt(2);
    }

    public MyWritable2(String word, int count) {
        this.word = word;
        this.count = count;
    }
}
