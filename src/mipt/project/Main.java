package mipt.project;

import mipt.project.coder.MD5128;

import java.security.NoSuchAlgorithmException;

import static mipt.project.coder.MD5Coder.*;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String[] testStrings = { "hello", "iello" };
        for (String s : testStrings)
            System.out.println("0x" + toHexString(computeMD5(s.getBytes())) + " <== \"" + s + "\"");
        for (String s : testStrings) {
            System.out.println(s);
            System.out.println(MD5128.calculate(s.getBytes()));
        }



    }
}
