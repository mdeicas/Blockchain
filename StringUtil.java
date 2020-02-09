import java.util.ArrayList;
import java.security.MessageDigest;
public class StringUtil{
    
    //returns String hash of a String input
    public static String applysha256(String input){
        try{
            //MessageDigest object that hashes with sha256 is created 
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            
            //convers input to Bytes using UTF-8 then hashes it 
            byte[] hash = digest.digest(input.getBytes("UTF-8"));

            //concerts hash to part converts the hash to hexadecimal.
            // @Michael Myers on StackOverflow
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1){
                    hexString.append('0');
                }
                hexString.append(hex);
            }
    
            return hexString.toString();
       }catch(Exception exception){
           return "Error";
       } 
    }
}
