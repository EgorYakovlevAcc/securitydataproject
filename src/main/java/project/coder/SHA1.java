package project.coder;


import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import project.persistence.model.HashFunctionType;

@Component
public class SHA1 implements HashFunction {

    @Override
    public String getId() {
        return HashFunctionType.SHA1.getType();
    }

    @Override
    public String compute(byte[] message) {
        return DigestUtils.sha1Hex(message);
    }
}
