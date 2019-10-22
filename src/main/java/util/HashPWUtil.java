package util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class HashPWUtil {
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltByte = new byte[8];
        random.nextBytes(saltByte);
        String str = Base64.getEncoder().encodeToString(saltByte);
        System.out.println(str);
        return str;
    }
    public static String hashPW(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] saltBytes = salt.getBytes();
        KeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        String strPw = Base64.getEncoder().encodeToString(hash);
        System.out.println(strPw);
        return strPw;
    };
}
