import org.apache.catalina.startup.Tomcat;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.net.URL;

/**
 * Created by Administrator on 2017/6/5.
 * http://www.cnblogs.com/huangyin/p/5888821.html
 */
public class TomcatStarter {


    private int port = 9999;

    private String contextPath = "/";

    public void start() throws Exception {
        Tomcat tomcat = new Tomcat();
        URL url = getClass().getClassLoader().getResource("logback-test.xml");
        String pwd = StringUtils.substringBefore(url.getPath(), "/target/classes");
        tomcat.setBaseDir(pwd);
        tomcat.setPort(port);

        StringBuilder webAppBuilder = new StringBuilder();
        webAppBuilder.append(pwd).append(File.separator).append("..").append(File.separator).append("taotao-manage-web/src/main/webapp");
        String webapp = webAppBuilder.toString();


        tomcat.addWebapp(contextPath, webapp);
        tomcat.enableNaming();
        tomcat.start();
        tomcat.getServer().await();
    }

    public static void main(String[] args) throws Exception {
        TomcatStarter starter = new TomcatStarter();
        starter.start();
    }

}
