package mipt.project.coder;

public class MD5ModifiedCoder {
    private static final int INIT_A = 0x67452301;
    private static final int INIT_B = (int)0xEFCDAB89L;
    private static final int INIT_C = (int)0x98BADCFEL;
    private static final int INIT_D = 0x10325476;
    private static final int INIT_E = (int)0x51EE41BDL;
    private static final int INIT_F = (int)0x99B34822L;
    private static final int INIT_G = (int)0x2C2E59B3L;
    private static final int INIT_H = (int)0xBFB5E5L;

    private static final int[] SHIFT_AMTS = {
            7, 12, 17, 22,
            5,  9, 14, 20,
            4, 11, 16, 23,
            6, 10, 15, 21
    };

    private static final int[] TABLE_T = new int[64];
    static
    {
        for (int i = 0; i < 64; i++) {
            TABLE_T[i] = (int) (long) ((1L << 32) * Math.abs(Math.sin(i + 1)));
        }
    }

    public static byte[] computeMD5(byte[] message)
    {
        int messageLenBytes = message.length;
        int numBlocks = ((messageLenBytes + 8) >>> 6) + 1;
        int totalLen = numBlocks << 7;
        byte[] paddingBytes = new byte[totalLen - messageLenBytes];
        paddingBytes[0] = (byte)0x80;

        long messageLenBits = (long)messageLenBytes << 3;
        for (int i = 0; i < 8; i++)
        {
            paddingBytes[paddingBytes.length - 8 + i] = (byte)messageLenBits;
            messageLenBits >>>= 8;
        }

        int a = INIT_A;
        int b = INIT_B;
        int c = INIT_C;
        int d = INIT_D;
        int e = INIT_E;
        int f = INIT_F;
        int g = INIT_G;
        int h = INIT_H;
        int[] buffer = new int[32];
        for (int i = 0; i < numBlocks; i ++)
        {
            int index = i << 6;
            for (int j = 0; j < 128; j++, index++)
                buffer[j >>> 2] = ((int)((index < messageLenBytes)
                        ? message[index]
                        : paddingBytes[index - messageLenBytes]) << 24) | (buffer[j >>> 2] >>> 8);
            int originalA = a;
            int originalB = b;
            int originalC = c;
            int originalD = d;
            int originalE = e;
            int originalF = f;
            int originalG = g;
            int originalH = h;
            for (int j = 0; j < 128; j++)
            {
                int div16 = j >>> 4;
                int res = 0;
                int temp = 0;
                int bufferIndex = j;
                switch (div16)
                {
                    case 0:
                        res = (b & c) | (~b & d);
                        temp = b + Integer.rotateLeft(a + res + buffer[bufferIndex] + TABLE_T[j], SHIFT_AMTS[(div16 << 2) | (j & 3)]);
                        break;

                    case 1:
                        res = (b & d) | (c & ~d);
                        bufferIndex = (bufferIndex * 5 + 1) & 0x0F;
                        temp = b + Integer.rotateLeft(a + res + buffer[bufferIndex] + TABLE_T[j], SHIFT_AMTS[(div16 << 2) | (j & 3)]);
                        break;

                    case 2:
                        res = f ^ g ^ h;
                        bufferIndex = (bufferIndex * 3 + 5) & 0x0F;
                        temp = f + Integer.rotateLeft( e + res + buffer[bufferIndex] + TABLE_T[j], SHIFT_AMTS[(div16 << 2) | (j & 3)]);
                        break;

                    case 3:
                        res = g ^ (f | ~h);
                        bufferIndex = (bufferIndex * 7) & 0x0F;
                        temp = f + Integer.rotateLeft( e + res + buffer[bufferIndex] + TABLE_T[j], SHIFT_AMTS[(div16 << 2) | (j & 3)]);
                        break;
                }
                a = d;
                d = c;
                c = b;
                b = temp;
            }

            a += originalA;
            b += originalB;
            c += originalC;
            d += originalD;
            e += originalE;
            f += originalF;
            g += originalG;
            h += originalH;
        }

        byte[] md5 = new byte[32];
        int count = 0;
        for (int i = 0; i < 4; i++)
        {
            int n = (i == 0) ? a : ((i == 1) ? b : ((i == 2) ? c : d));
            for (int j = 0; j < 4; j++)
            {
                md5[count++] = (byte)n;
                n >>>= 8;
            }
        }
        return md5;
    }

    public static String toHexString(byte[] b)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; i++)
        {
            sb.append(String.format("%02X", b[i] & 0xFF));
        }
        return sb.toString();
    }
    public static String to2String(byte[] b)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; i++)
        {
            sb.append(Integer.toString(b[i], 2));
        }
        return sb.toString();
    }


}
