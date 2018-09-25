package tutorialspoint.com;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.junit.Test;

import java.io.File;

public class AvroSeq {
    @Test
    public void testSerial() throws Exception{
        //
        DatumWriter<Emp> dw = new SpecificDatumWriter<Emp>(Emp.class);

        //入口点，实例化DataFileWriter
        DataFileWriter<Emp> dfw = new DataFileWriter<Emp>(dw);

        Emp[] emp = new Emp[1000000];
        //开始序列化
        //参数2：输出文件路径
        dfw.create(Emp.getClassSchema(), new File("E:/大数据安装包/avro/emp.avro"));
        long start = System.currentTimeMillis();
        for (int i=0;i<1000000;i++){
            emp[i] = new Emp();
            emp[i].setId(i);
            emp[i].setName("tom"+i);
            emp[i].setAge(i);
            emp[i].setSalary(i);
            emp[i].setAddress("昌平"+i);
            dfw.append(emp[i]);
        }
        System.out.println(System.currentTimeMillis() - start);
        dfw.close();
        System.out.println("data successfully serialized");
    }

    @Test
    public  void testDeSerail()  throws Exception{

        DatumReader<Emp> dr = new SpecificDatumReader<Emp>();
        DataFileReader<Emp> dfr = new DataFileReader<Emp>(new File("E:/大数据安装包/avro/emp.avro"),dr);
        long start = System.currentTimeMillis();
        while(dfr.hasNext()){
            Emp emp = dfr.next();
        }
        System.out.println(System.currentTimeMillis() - start);
        dfr.close();
    }
}
