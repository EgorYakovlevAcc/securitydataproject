package mipt.project;

import mipt.project.coder.MD5_256Out;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import static mipt.project.coder.MD5Coder.computeMD5;
import static mipt.project.coder.MD5Coder.toHexString;
import static mipt.project.coder.MD5_256Out.calculate;

public class Main {

    public static void main(String[] args) {
        String[] testStrings = { "hello", "iello" };
        for (String s : testStrings)
            System.out.println("0x" + toHexString(computeMD5(s.getBytes())) + " <== \"" + s + "\"");
        for (String s : testStrings) {
            System.out.println(s);
            System.out.println(MD5_256Out.calculate(s.getBytes()));
        }
    }
}
