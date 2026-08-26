package MultiThread.Practice_d_TCP_Client_Server.common;

import java.util.Random;

public class RandomMessageGenerator {
    // Tạo chuỗi ngẫu nhiên
    private final Random random = new Random();
    private final String charr = "QWERTYUIOPASDFGHJKLZXCVBNM1234567890";
    
    public Message generate(int id){
        StringBuilder content = new StringBuilder();//xây dựng và thay đổi chuỗi ký tự một cách hiệu quả.
        
        // Tạo 10 ký tự ngẫu nhiên
        for(int i = 0; i < 10; i++){
            int randomIndex = random.nextInt(charr.length());
            
            content.append(charr.charAt(randomIndex));
        }
        
        return new Message(id,content.toString());
    }
}
