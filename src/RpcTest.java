import com.zhu.exporter.RpcExporter;
import com.zhu.importer.RpcImporter;
import com.zhu.service.EchoService;
import com.zhu.service.impl.EchoServiceImpl;

import java.net.InetSocketAddress;

public class RpcTest {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    RpcExporter.exporter("localhost",8080);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        RpcImporter<EchoService> importer = new RpcImporter<EchoService>();
        EchoService echo = importer.importer(EchoServiceImpl.class,new InetSocketAddress("localhost",8080));
        System.out.println(echo.echo("Are you ok ?"));
    }
}
