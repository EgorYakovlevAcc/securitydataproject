package project.coder;

import java.util.ArrayList;
import java.util.List;

public class CoderHelper {

    public static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (byte value : b) {
            sb.append(String.format("%02X", value & 0xFF));
        }
        return sb.toString();
    }

    public static String to2String(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (byte value : b) {
            sb.append(Integer.toString(value, 2));
        }
        return sb.toString();
    }

    public static List<String> splitStringToSmallStringsBySize(int size, String string) {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < string.length(); i++) {
            if ((i % size == 0) && (i != 0)) {
                strings.add(string.substring(i - size, i));
            }
        }
        return strings;
    }
}
