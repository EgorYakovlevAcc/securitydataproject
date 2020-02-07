package project.coder;

public interface HashFunction {
    String getId();

    String compute(byte[] message);
}
