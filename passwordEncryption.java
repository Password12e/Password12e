import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class passwordEncryption 
{
    public static String get_SHA_1_SecurePassword(String passwordToHash, byte[] salt) 
    {
        String generatedPassword = null;
        try 
        {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder str = new StringBuilder();
            for (byte aByte : bytes) 
            {
                str.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = str.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
    
    //SALT INCREASES THE SECURITY OF THE PASSWORD

    public static byte[] getSalt() throws NoSuchAlgorithmException 
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
}
