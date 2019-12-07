package mipt.project;

import mipt.project.coder.MD5ModifiedCoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static mipt.project.coder.MD5Coder.*;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String[] testStrings = { "hello", "", "a", "abc", "message digest", "abcdefghijklmnopqrstuvwxyz", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789", "12345678901234567890123456789012345678901234567890123456789012345678901234567890" };
        for (String s : testStrings)
            System.out.println("0x" + toHexString(computeMD5(s.getBytes())) + " <== \"" + s + "\"");
        for (String s : testStrings)
            System.out.println("0x" + toHexString(MD5ModifiedCoder.computeMD5(s.getBytes())) + " <== \"" + s + "\"");



    }
}
