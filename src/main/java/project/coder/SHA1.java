package project.coder;


import org.apache.commons.codec.digest.DigestUtils;

public class SHA1 implements HashFunction {

    @Override
    public String getId() {
        return "sha1";
    }

    @Override
    public String compute(byte[] message) {
        return DigestUtils.sha1Hex(message);
    }
}
