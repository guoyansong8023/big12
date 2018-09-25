package com.oldboy.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.io.IOException;

public class TestHbase {


    /**
     * 创建名字空间
     */
    @Test
    public void createNS() throws Exception {

        //初始化hbase 的conf
        Configuration conf = HBaseConfiguration.create();

        //通过连接工厂创建连接
        Connection conn = ConnectionFactory.createConnection(conf);

        //获取hbase管理员
        Admin admin = conn.getAdmin();

        //通过构建器模式，创建namespace描述符
        NamespaceDescriptor nsd = NamespaceDescriptor.create("test").build();

        admin.createNamespace(nsd);

        admin.close();

        conn.close();
    }

    /**
     * 创建表
     */
    @Test
    public void createTable() throws Exception {

        //初始化hbase 的conf
        Configuration conf = HBaseConfiguration.create();

        //通过连接工厂创建连接
        Connection conn = ConnectionFactory.createConnection(conf);

        //获取hbase管理员
        Admin admin = conn.getAdmin();

        TableName table = TableName.valueOf("test:t1");

        HTableDescriptor htd = new HTableDescriptor(table);

        htd.addFamily(new HColumnDescriptor("f1"));
        htd.addFamily(new HColumnDescriptor("f2"));

        admin.createTable(htd);

        admin.close();

        conn.close();
    }

    /**
     * 插入数据
     */
    @Test
    public void putData() throws Exception {

        //初始化hbase 的conf
        Configuration conf = HBaseConfiguration.create();

        //通过连接工厂创建连接
        Connection conn = ConnectionFactory.createConnection(conf);

        Table table = conn.getTable(TableName.valueOf("test:t1"));

        //Bytes.toBytes(）可以将任意类型转换成字节数组
        Put put = new Put(Bytes.toBytes("row1"));
        put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("name"), Bytes.toBytes("tom"));


        table.put(put);

        table.close();

        conn.close();
    }

}
