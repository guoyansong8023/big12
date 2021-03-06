package com.oldboy.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class TestScan {

    @Test
    public void getData() throws Exception {

        Configuration conf = HBaseConfiguration.create();

        Connection conn = ConnectionFactory.createConnection(conf);

        Table table = conn.getTable(TableName.valueOf("test:t1"));

        Get get = new Get(Bytes.toBytes("row1"));

        Result rs = table.get(get);

        List<Cell> cells = rs.listCells();

        for (Cell cell : cells) {
            String row = Bytes.toString(CellUtil.cloneRow(cell));
            String cf = Bytes.toString(CellUtil.cloneFamily(cell));
            String col = Bytes.toString(CellUtil.cloneQualifier(cell));
            String value = Bytes.toString(CellUtil.cloneValue(cell));
            System.out.println(row + "/" + cf + "/" + col + "/" + value);

        }


    }

    @Test
    public void scanData() throws Exception {

        Configuration conf = HBaseConfiguration.create();

        Connection conn = ConnectionFactory.createConnection(conf);

        Table table = conn.getTable(TableName.valueOf("test:t1"));

        Scan scan = new Scan();

        ResultScanner scanner = table.getScanner(scan);

        for (Result rs : scanner) {
            List<Cell> cells = rs.listCells();

            for (Cell cell : cells) {
                String row = Bytes.toString(CellUtil.cloneRow(cell));
                String cf = Bytes.toString(CellUtil.cloneFamily(cell));
                String col = Bytes.toString(CellUtil.cloneQualifier(cell));
                String value = Bytes.toString(CellUtil.cloneValue(cell));
                System.out.println(row + "/" + cf + "/" + col + "/" + value);

            }
        }
    }

    /**
     * 设定扫描的起始key和结束key
     * @throws Exception
     */
    @Test
    public void scanData2() throws Exception {



        Configuration conf = HBaseConfiguration.create();

        Connection conn = ConnectionFactory.createConnection(conf);

        HTable table = (HTable) conn.getTable(TableName.valueOf("test:t1"));

        Scan scan = new Scan();
        scan.setCaching(10);

        scan.setBatch(100);

        System.out.println(scan.getCaching());

//        scan.setStartRow(Bytes.toBytes("row015"));
//        scan.setStopRow(Bytes.toBytes("row025"));

        ResultScanner scanner = table.getScanner(scan);
        Iterator<Result> it = scanner.iterator();

        long start = System.currentTimeMillis();
        while (it.hasNext()){

            List<Cell> cells = it.next().listCells();

            for (Cell cell : cells) {
                String row = Bytes.toString(CellUtil.cloneRow(cell));
                String cf = Bytes.toString(CellUtil.cloneFamily(cell));
                String col = Bytes.toString(CellUtil.cloneQualifier(cell));
                String value = Bytes.toString(CellUtil.cloneValue(cell));
                System.out.println(row + "/" + cf + "/" + col + "/" + value);

            }

            System.out.println("=========================================");
        }

        System.out.println(System.currentTimeMillis() - start);
    }

}
