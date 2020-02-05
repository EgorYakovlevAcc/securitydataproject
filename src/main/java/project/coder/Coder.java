package project.coder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public abstract class Coder {
    protected MessageDigest messageDigest = null;
    public String codedText(String text) {
            return codedText(text.getBytes(StandardCharsets.UTF_8));
    }
    protected abstract String codedText(byte[] byteText);

}
