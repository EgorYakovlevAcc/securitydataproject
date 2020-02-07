package project.coder;


import org.springframework.stereotype.Component;
import project.persistence.model.ExtensionType;

import java.util.List;

@Component
public class HashExtension {


    public String calculateExtensions(HashFunction hashFunction, String message, List<String> extensions) {
        String result = message;
        if (extensions.contains(ExtensionType.PREFIX.getType())) {
            result = calculatePrefixSuperposition(hashFunction, message, 100L);
        }
        if (extensions.contains(ExtensionType.TRANSPOSITION.getType())) {
            result = calculateTransposition(hashFunction, message, 10L);
        }
        if (result.equals(message)) {
            result = hashFunction.compute(message.getBytes());
        }
        return result;
    }

    private String calculatePrefixSuperposition(HashFunction hashFunction, String message, Long iterations) {
        if (iterations > 0) {
            return this.calculatePrefixSuperposition(hashFunction, hashFunction.compute(message.getBytes()) + message, iterations - 1);
        } else {
            return hashFunction.compute(message.getBytes());
        }
    }

    private String calculateTransposition(HashFunction hashFunction, String message, long l) {
        return message;
    }


}
