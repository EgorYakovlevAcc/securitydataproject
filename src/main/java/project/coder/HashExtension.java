package project.coder;


import org.springframework.stereotype.Component;
import project.persistence.model.ExtensionType;

import java.util.Arrays;
import java.util.List;

import static project.coder.CoderHelper.toHexString;

@Component
public class HashExtension {


    public String calculateExtensions(HashFunction hashFunction, byte[] message, List<String> extensions) {
        byte[] result = new byte[message.length];
        System.arraycopy(message, 0, result, 0, message.length);;
        if (extensions.contains(ExtensionType.PREFIX.getType())) {
            result = calculatePrefixSuperposition(hashFunction, message, 1000L);
        }
        if (extensions.contains(ExtensionType.TRANSPOSITION.getType())) {
            result = calculateTransposition(hashFunction, message, 10L);
        }
        if (Arrays.equals(result, message)) {
            result = hashFunction.compute(message);
        }
        return toHexString(result);
    }

    private byte[] calculatePrefixSuperposition(HashFunction hashFunction, byte[] a, Long iterations) {
        if (iterations > 0) {
            byte[] b = hashFunction.compute(a);
            byte[] c = new byte[a.length + b.length];
            System.arraycopy(a, 0, c, 0, a.length);
            System.arraycopy(b, 0, c, a.length, b.length);
            return this.calculatePrefixSuperposition(hashFunction, c, iterations - 1);
        } else {
            return hashFunction.compute(a);
        }
    }

    private byte[] calculateTransposition(HashFunction hashFunction, byte[] message, long l) {
        return message;
    }

    private byte[] calculateCascadeTransposition(HashFunction hashFunction, byte[] message, long l){
        return message;
    }


}
