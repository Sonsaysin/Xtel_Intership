package MultiThread.Practice_d_TCP_Client_Server.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private final Properties properties = new Properties();
    public ConfigLoader(){
        // Tìm file -> mở file đó -> trả về InputStream
        try(InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")){
            if(input == null){
                throw new RuntimeException("Không tìm thấy file config.properties");
            }

            properties.load(input);
        }catch (IOException e){
            throw new RuntimeException("Không thể đọc file config.properties",e);
        }
    }

    public String getServerIp(){
        return properties.getProperty("server.ip");
    }

    public int getServerPort(){
        return  Integer.parseInt(properties.getProperty("server.port"));
    }

    public int getConnectionTimeout(){
        return Integer.parseInt(properties.getProperty("connection.timeout"));
    }

    public int getReceiveTimeout(){
        return Integer.parseInt(properties.getProperty("receive.timeout"));
    }

    public int getSendInterval(){
        return Integer.parseInt(properties.getProperty("send.interval"));
    }

    public int getReconnectInterval(){
        return Integer.parseInt(properties.getProperty("reconnect.interval"));
    }
}
