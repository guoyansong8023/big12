import com.oldboy.qq.util.PropertiesUtil;
import org.junit.Test;

/**
 * Created by Administrator on 2018/9/10.
 */
public class TestProperties {
	@Test
	public void testPropRead(){
		System.out.println(PropertiesUtil.getStringValue("qq.server.channel.blocking.mode"));
	}
}
