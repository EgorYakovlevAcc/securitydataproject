package mipt.project;

import mipt.project.coder.MD5_256Out;

import static mipt.project.coder.MD5Coder.computeMD5;
import static mipt.project.coder.MD5Coder.toHexString;

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
