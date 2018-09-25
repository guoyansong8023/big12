package tutorialspoint.com;

import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;

public class Test {
    public static void main(String[] args)  throws Exception{
        Emp emp = new Emp();
        emp.setId(1);
        emp.setName("tom");
        emp.setAge(20);
        emp.setSalary(20000);
        emp.setAddress("昌平");
        //
        DatumWriter<Emp> dw = new SpecificDatumWriter<Emp>(Emp.class);
        //入口点，实例化DataFileWriter
        DataFileWriter<Emp> dfw = new DataFileWriter<Emp>(dw);
        //开始序列化
        //参数2：输出文件路径
        dfw.create(emp.getSchema(), new File("E:/大数据安装包/avro/emp.avro"));
        //添加对象
        dfw.append(emp);
        dfw.close();
        System.out.println("data successfully serialized");
    }
}
