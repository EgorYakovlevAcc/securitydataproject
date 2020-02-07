package project.coder;

import java.util.List;

import static project.coder.CoderHelper.splitStringToSmallStringsBySize;
import static project.coder.CoderHelper.toHexString;

public class MD5_256 implements HashFunction {
    private static final int INIT_A = 0x67452301;
    private static final int INIT_B = (int) 0xEFCDAB89L;
    private static final int INIT_C = (int) 0x98BADCFEL;
    private static final int INIT_D = 0x10325476;
    private static final int INIT_E = (int) 0x51EE41BDL;
    private static final int INIT_F = (int) 0x99B34822L;
    private static final int INIT_G = (int) 0x2C2E59B3L;
    private static final int INIT_H = (int) 0xBFB5E5L;

    private static final int[] SHIFT_AMTS = {
            7, 12, 17, 22,
            5,  9, 14, 20,
            4, 11, 16, 23,
            6, 10, 15, 21
    };

    private static int funF(int x, int y, int z) {
        return ((x & y) | ((~x) & z));
    }

    private static int funG(int x, int y, int z) {
        return ((x & z) | ((~z) & y));
    }

    private static int funH(int x, int y, int z) {
        return (x ^ y ^ z);
    }

    private static int funI(int x, int y, int z) {
        return (y ^ ((~z) | x));
    }

    private static final int[] TABLE_T = new int[64];

    static {
        for (int i = 0; i < 64; i++)
            TABLE_T[i] = (int) (long) ((1L << 32) * Math.abs(Math.sin(i + 1)));
    }

    @Override
    public String getId() {
        return "md5_256";
    }

    @Override
    public String compute(byte[] input) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b: input) {
            stringBuilder.append(Integer.toString(b, 2));
        }
        StringBuilder inputStringBuilder = new StringBuilder();
        inputStringBuilder.append(stringBuilder.toString());
        inputStringBuilder.append("1");
        //step 2: L' = 512*k + 448
        while (inputStringBuilder.length() % 512 != 448) {
            inputStringBuilder.append("0");
            inputStringBuilder.append("1");
            inputStringBuilder.append("1");
        }
        List<String> splitedList = splitStringToSmallStringsBySize(16, inputStringBuilder.toString());
        int a = INIT_A;
        int b = INIT_B;
        int c = INIT_C;
        int d = INIT_D;
        int e = INIT_E;
        int f = INIT_F;
        int g = INIT_G;
        int h = INIT_H;
        int originalA = 0;
        int originalB = 0;
        int originalC = 0;
        int originalD = 0;
        int originalE = 0;
        int originalF = 0;
        int originalH = 0;
        int originalG = 0;
        int A = 0;
        int B = 0;
        int C = 0;
        int D = 0;
        int E = 0;
        int F = 0;
        int G = 0;
        int H = 0;
        for (String s : splitedList) {
            int i = 1;
            char[] char_arr = s.toCharArray();
            for (int k = 0; k < 16; k++) {
                if ((k!= 0) &&  (k % 4 == 0)){
                    a = originalA;
                    b = originalB;
                    c = originalC;
                    d = originalD;
                }
                if (k % 4 == 1) {
                    a = originalD;
                    b = originalA;
                    c = originalB;
                    d = originalC;
                } else if (k % 4 == 2) {
                    a = originalC;
                    b = originalD;
                    c = originalA;
                    d = originalB;

                } else if (k % 4 == 3) {
                    a = originalB;
                    b = originalC;
                    c = originalD;
                    d = originalA;
                }
//                System.out.println("Input values " + a + " " + b + " " + c +" " + d + " " + e + " " + f + " " + g+ " " + h +" " + k + " "+ SHIFT_AMTS[k%4] + " "+ i);
                a = b + ((a + funF(b, c, d) + Integer.parseInt(String.valueOf(char_arr[k])) + TABLE_T[i - 1]) << (SHIFT_AMTS[k % 4]));
                originalA = a;
                originalB = b;
                originalC = c;
                originalD = d;
                i++;
            }
            int cycle = 1;
            for (int k = 0; k < 16; k++) {
                if ((k!= 0) &&  (k % 4 == 0)){
                    a = originalA;
                    b = originalB;
                    c = originalC;
                    d = originalD;
                }
                if (k % 4 == 1) {
                    a = originalD;
                    b = originalA;
                    c = originalB;
                    d = originalC;
                } else if (k % 4 == 2) {
                    a = originalC;
                    b = originalD;
                    c = originalA;
                    d = originalB;

                } else if (k % 4 == 3) {
                    a = originalB;
                    b = originalC;
                    c = originalD;
                    d = originalA;
                }
//                System.out.println("Input values " + a + " " + b + " " + c +" " + d + " " + cycle + " "+ SHIFT_AMTS[k%4 + 4] + " "+ i);
                a = b + ((a + funG(b, c, d) + Integer.parseInt(String.valueOf(char_arr[cycle])) + TABLE_T[i - 1]) << SHIFT_AMTS[k%4 + 4]);
                originalA = a;
                originalB = b;
                originalC = c;
                originalD = d;
                i++;
                cycle = (cycle + 5) % 16;
            }
            cycle = 5;
            for (int k = 0; k < 16; k++) {
                if ((k!= 0) &&  (k % 4 == 0)) {
                    e = originalE;
                    f = originalF;
                    g = originalG;
                    h = originalH;
                }
                if (k % 4 == 1) {
                    e = originalH;
                    f = originalE;
                    g = originalF;
                    h = originalG;
                } else if (k % 4 == 2) {
                    e = originalC;
                    f = originalG;
                    g = originalE;
                    h = originalF;

                } else if (k % 4 == 3) {
                    e = originalF;
                    f = originalG;
                    g = originalH;
                    h = originalE;
                }
//                System.out.println("Input values " + a + " " + b + " " + c +" " + d + " " + cycle + " "+ SHIFT_AMTS[k%4 + 8] + " "+ i);
                e = f + ((e + funH(f, g, h) + Integer.parseInt(String.valueOf(char_arr[cycle])) + TABLE_T[i - 1]) << SHIFT_AMTS[k%4 + 8]);
                originalE = e;
                originalF = f;
                originalG = g;
                originalH = h;
                i++;
                cycle = (cycle + 3) % 16;
            }
            cycle = 0;
            for (int k = 0; k < 16; k++) {
                if ((k!= 0) &&  (k % 4 == 0)) {
                    e = originalE;
                    f = originalF;
                    g = originalG;
                    h = originalH;
                }
                if (k % 4 == 1) {
                    e = originalH;
                    f = originalE;
                    g = originalF;
                    h = originalG;
                } else if (k % 4 == 2) {
                    e = originalC;
                    f = originalG;
                    g = originalE;
                    h = originalF;

                } else if (k % 4 == 3) {
                    e = originalF;
                    f = originalG;
                    g = originalH;
                    h = originalE;
                }
//                System.out.println("Input values" + a + " " + b + " " + c +" " + d + " " + cycle + " "+ SHIFT_AMTS[k%4 + 12] + " "+ i);
                e = f + ((e + funI(f, g, h) + Integer.parseInt(String.valueOf(char_arr[cycle])) + TABLE_T[i - 1] << SHIFT_AMTS[k%4 + 12]));
                originalE = e;
                originalF = f;
                originalG = g;
                originalH = h;
                i++;
                cycle = (cycle + 7) % 16;
            }
            A = A + originalA;
            B = B + originalB;
            C = C + originalC;
            D = D + originalD;
            E = E + originalE;
            F = F + originalF;
            G = G + originalG;
            H = H + originalH;
        }
        byte[] md5 = new byte[32];
        int count = 0;
        for (int i = 0; i < 8; i++)
        {
            int n = 0;
            switch (i) {
                case 0:
                    n = A;
                    break;
                case 1:
                    n = B;
                    break;
                case 2:
                    n = C;
                    break;
                case 3:
                    n = D;
                    break;
                case 4:
                    n = E;
                    break;
                case 5:
                    n = F;
                    break;
                case 6:
                    n = G;
                    break;
                case 7:
                    n = E;
                    break;
            }
            for (int j = 0; j < 4; j++)
            {
                md5[count++] = (byte)n;
                n >>>= 8;
            }
        }
        return toHexString(md5);
    }

}
