import com.smalllife.common.util.config.FileConfig;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Aaron on 01/04/2017.
 */
public class FileConfigTest {
    @Test
    public void test() throws IOException {
        System.out.println(new FileConfig("mongo-config").load().getString("mongo.servers",""));
    }
}
