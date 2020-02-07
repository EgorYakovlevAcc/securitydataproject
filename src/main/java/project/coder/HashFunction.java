package project.coder;

public interface HashFunction {
    String getId();

    byte[] compute(byte[] message);
}
