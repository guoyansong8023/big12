package tutorialspoint.com;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;

public class JavaSeq {
    @Test
    public void testSerial() throws Exception{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("E:/大数据安装包/avro/emp.java"));
        long start = System.currentTimeMillis();
        for (int i=0;i<1000000;i++){
            Emp emp = new Emp();
            emp = new Emp();
            emp.setId(i);
            emp.setName("tom"+i);
            emp.setAge(i);
            emp.setSalary(i);
            emp.setAddress("昌平"+i);
            oos.writeObject(emp);
        }
        System.out.println(System.currentTimeMillis() - start);
        oos.close();

    }

    @Test
    public void testDeSerial() throws Exception{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("E:/大数据安装包/avro/emp.java"));
        long start = System.currentTimeMillis();
        Emp emp = (Emp)ois.readObject();
        Emp emp2 = (Emp)ois.readObject();
        System.out.println(emp);
        System.out.println(emp2);
        System.out.println(System.currentTimeMillis() - start);
        ois.close();
    }
}
