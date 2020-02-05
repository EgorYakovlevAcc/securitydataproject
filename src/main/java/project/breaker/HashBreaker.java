package project.breaker;

import java.util.function.Function;

public interface HashBreaker {

    public void breakHash(Function<String, String> hashFunction);
}
