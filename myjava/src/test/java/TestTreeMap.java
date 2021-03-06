
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Administrator on 2018/9/6.
 */
public class TestTreeMap {

	/**
	 *
	 */


	@Test
	public void testNewTree2() throws Exception {
		TreeMap<Integer, String> map = new TreeMap<Integer, String>();
		map.put(1 , "tom1");
		map.put(2 , "tom1");
		map.put(3 , "tom1");
		map.put(4 , "tom1");
		map.put(5 , "tom1");

		preOrderTravel(getRoot(map));


	}

	public static Map.Entry getRoot(TreeMap map) throws Exception {
		Field f = TreeMap.class.getDeclaredField("root") ;
		f.setAccessible(true);
		Object o = f.get(map) ;
		return (Map.Entry) o;
	}

	//得到e节点的k值
	public static Object getKey(Map.Entry e) throws Exception {
		Field f = e.getClass().getDeclaredField("key") ;
		f.setAccessible(true);
		Object o = f.get(e) ;
		return o;
	}

	public static Map.Entry getLeft(Map.Entry e) throws Exception {
		Field f = e.getClass().getDeclaredField("left") ;
		f.setAccessible(true);
		Object o = f.get(e) ;
		return (Map.Entry) o;
	}

	public static Object getLeftKey(Map.Entry e) throws Exception {
		Field f = e.getClass().getDeclaredField("left") ;
		f.setAccessible(true);
		Object o = f.get(e) ;
		if(o != null){
			Field k = o.getClass().getDeclaredField("key") ;
			k.setAccessible(true);
			return k.get(o) ;
		}
		return null;
	}

	public static Map.Entry getRight(Map.Entry e) throws Exception {
		Field f = e.getClass().getDeclaredField("right") ;
		f.setAccessible(true);
		Object o = f.get(e) ;
		return (Map.Entry) o;
	}

	public static Object getRightKey(Map.Entry e) throws Exception {
		Field f = e.getClass().getDeclaredField("right");
		f.setAccessible(true);
		Object o = f.get(e);
		if (o != null) {
			Field k = o.getClass().getDeclaredField("key");
			k.setAccessible(true);
			return k.get(o);
		}
		return null;
	}

	public static void preOrderTravel(Map.Entry e) throws Exception {
		if(e != null){
			System.out.println(getKey(e));
			preOrderTravel(getLeft(e)) ;
			preOrderTravel(getRight(e)) ;

		}
	}

}
