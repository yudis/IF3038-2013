package id.ac.itb.todolist.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Properties;

public class ProxyUtil {

    private static Proxy proxy = null;
    public static Proxy getProxy() {
        try {
            Properties prop = new Properties();
            InputStream inputStream = ProxyUtil.class.getClassLoader().getResourceAsStream("/proxy.properties");
            prop.load(inputStream);
            
            String type = prop.getProperty("type");
            if ("DIRECT".equalsIgnoreCase(type)) {
                proxy = Proxy.NO_PROXY;
            } else {
                String hostname = prop.getProperty("hostname");
                int port = Integer.parseInt(prop.getProperty("port"));
                
                if ("HTTP".equalsIgnoreCase(type)) {
                    proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(hostname, port));
                } else if ("SOCKS".equalsIgnoreCase(type)) {
                    proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(hostname, port));                    
                } else {
                    proxy = Proxy.NO_PROXY;
                    throw new IllegalArgumentException("Type doesn't supported, using direct proxy.");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return proxy;
    }
}
