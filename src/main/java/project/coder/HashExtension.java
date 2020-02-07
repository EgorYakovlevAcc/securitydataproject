package project.coder;


import org.springframework.stereotype.Component;
import project.persistence.model.ExtensionType;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static project.coder.CoderHelper.toHexString;

@Component
public class HashExtension {


    public String calculateExtensions(HashFunction hashFunction, byte[] message, List<String> extensions) {
        byte[] result = new byte[message.length];
        System.arraycopy(message, 0, result, 0, message.length);
        if (extensions.contains(ExtensionType.PREFIX.getType())) {
            result = calculatePrefixSuperposition(hashFunction, message, 1000L);
        }
        if (extensions.contains(ExtensionType.TRANSPOSITION.getType())) {
            result = calculateTransposition(hashFunction, message, 7L);
        }
        if (Arrays.equals(result, message)) {
            result = hashFunction.compute(message);
        }
        return toHexString(result);
    }

    private byte[] calculatePrefixSuperposition(HashFunction hashFunction, byte[] a, Long iterations) {
        if (iterations > 0) {
            byte[] b = hashFunction.compute(a);
            return this.calculatePrefixSuperposition(hashFunction, concatenate(b, a), iterations - 1);
        } else {
            return hashFunction.compute(a);
        }
    }

    private byte[] calculateTransposition(HashFunction hashFunction, byte[] message, long seed) {
        Random random = new Random();
        random.setSeed(seed);
        final byte[][] result = {hashFunction.compute(message)};
        long bound = message.length/seed > 0 ? message.length/seed : 1;
        random.ints(bound, 0, message.length).sorted().sequential().reduce(0, (previousIndex, index) -> {
            result[0] = concatenate(result[0], hashFunction.compute(Arrays.copyOfRange(message, previousIndex, index)));
            return index;
        });
        return hashFunction.compute(result[0]);
    }

    private byte[] calculateCascadeTransposition(HashFunction hashFunction, byte[] message, long l) {
        return message;
    }

    private byte[] concatenate(byte[] a, byte[] b) {
        byte[] c = new byte[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }


}
