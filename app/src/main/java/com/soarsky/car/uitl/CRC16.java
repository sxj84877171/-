package com.soarsky.car.uitl;

import android.util.Log;

/**
 * Andriod_Car_App<br>
 * 作者： Elvis<br>
 * 时间： 2017/3/31<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：sunxiangjin@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 为和智能终端嵌入式能够兼容的CRC16算法。<br>
 * 该算法和市面上算法不一样。<br>
 * 具体算法原理，请参考c语言算法<br>
 * 采用1021多项式(CRC-ITU)实现<br>
 * <br>
 * 具体c语言实现：<br>
 * Function Name  : CRC16<br>
 * Description     : CRC16计算函数, 采用1021多项式(CRC-ITU)实现<br>
 * Input          : ptr = 待验证数据地址<br>
 * len = 待验证数据长度<br>
 * Output         : None<br>
 * Return         : None<br>
 * unsigned short CRC16(unsigned char* src, unsigned short len)
 * {
 * union {
 * unsigned char  c[2];
 * unsigned short x;
 * } crc;
 * <p>
 * unsigned char t = 0;
 * unsigned short i = len;
 * unsigned char* ptr = src;
 * <p>
 * // CRC余式表
 * static unsigned char crch[16] = { 0x00,0x10,0x20,0x30,0x40,0x50,0x60,0x70,
 * 0x81,0x91,0xa1,0xb1,0xc1,0xd1,0xe1,0xf1};
 * <p>
 * static unsigned char crcl[16] = { 0x00,0x21,0x42,0x63,0x84,0xa5,0xc6,0xe7,
 * 0x08,0x29,0x4a,0x6b,0x8c,0xad,0xce,0xef};
 * <p>
 * // 检查指针
 * if( 0 == ptr || 0 == len ) return 0;
 * <p>
 * crc.x = 0;
 * <p>
 * while( i > 0 ) {
 * t = (crc.c[0] >> 4) ^ (*ptr >>4);
 * crc.x <<= 4;
 * crc.c[0] ^= crch[t];
 * crc.c[1] ^= crcl[t];
 * <p>
 * t = (crc.c[0] >> 4) ^ (*ptr & 0x0F);
 * crc.x <<= 4;
 * crc.c[0] ^= crch[t];
 * crc.c[1] ^= crcl[t];
 * <p>
 * ptr++;
 * i--;
 * }
 * <p>
 * return crc.x;
 * }
 */

public class CRC16 {

    static {
        System.loadLibrary("native-lib");
    }

    /**
     *
     */
    public static short[] CRCH = {0x00, 0x10, 0x20, 0x30, 0x40, 0x50, 0x60,
            0x70, 0x81, 0x91, 0xa1, 0xb1, 0xc1, 0xd1, 0xe1, 0xf1};


    public static short[] CRCL = {0x00, 0x21, 0x42, 0x63, 0x84, 0xa5, 0xc6,
            0xe7, 0x08, 0x29, 0x4a, 0x6b, 0x8c, 0xad, 0xce, 0xef};

    /**
     * 这里采用的是低字节序
     *
     * @param bytes
     * @return
     */
    public static char bytesToChar(byte[] bytes) {
        int val = 0;
        int t = bytes[1] & 0xFF;
        val = val | t << 8;
        t = bytes[0] & 0xFF;
        val = val | t;
        return (char) val;
    }

    /**
     * crc 编码原字节流后的结果（包括源）
     * @param src 输入源
     * @return 输出结果
     */
    public static byte[] crcEncode(byte[] src) {
        byte[] bytes = crcEncodeToBytes(src);
        byte[] ret = new byte[src.length + bytes.length];
        System.arraycopy(src, 0, ret, 0, src.length);
        System.arraycopy(bytes, 0, ret, src.length, bytes.length);
        return ret;
    }

//    public native static byte[] crcNativeEncodeToBytes(byte[] src);

    /**
     * 指定字节流转换的CRCcode返回字符。
     *
     * @param src crc编码源
     * @return crc编码后的结果，以字节数组返回
     */
    public static byte[] crcEncodeToBytes(byte[] src) {

        byte[] bytes = new byte[]{0x0, 0x0};
        int t = 0;
        int index = src.length;
        while (index > 0) {
            // t = (crc.c[0] >> 4) ^ (*ptr >>4);
            t = ((((bytes[0] >> 4) & 0xF) ^ ((src[src.length - index] >> 4) & 0xF)) & 0xF);

            // crc.x <<= 4;
            bytes[1] <<= 4;
            byte temp = (byte) (((bytes[0] & 0xF0) >> 4) & 0xF);
            bytes[1] |= temp;
            bytes[0] <<= 4;

            // crc.c[0] ^= crch[t];
            bytes[0] ^= CRCH[t];
            // crc.c[1] ^= crcl[t];
            bytes[1] ^= CRCL[t];

            // t = (crc.c[0] >> 4) ^ (*ptr & 0x0F);
            t = (((bytes[0] >> 4) & 0xF) ^ ((src[src.length - index] & 0x0F)) & 0xF);

            // crc.x <<= 4;
            bytes[1] <<= 4;
            temp = (byte) (((bytes[0] & 0xF0) >> 4) & 0xF);
            bytes[1] |= temp;
            bytes[0] <<= 4;

            // crc.c[0] ^= crch[t];
            bytes[0] ^= CRCH[t];
            // crc.c[1] ^= crcl[t];
            bytes[1] ^= CRCL[t];

            index--;
        }

        return bytes;

    }

    /**
     * 指定字节流转换的CRCcode返回字符。
     *
     * @param src crc编码源
     * @return crc编码后的结果，以字符返回
     */
    public static char crcEncodeToChar(byte[] src) {

        return bytesToChar(crcEncodeToBytes(src));
    }

    public native short crEncodeToBytes(byte[] bytes);

    public static boolean checkCRC(byte[] src,int length){
        byte[] source = new byte[length-2];
        byte[] crc = new byte[2];
        System.arraycopy(src, 0, source, 0, length-2);
        System.arraycopy(src, length-2, crc,0, 2);
//        byte[] ret = crcEncodeToBytes(source);
        byte[] ret = ByteUtil.short2Byte(new CRC16().crEncodeToBytes(source));
        LogUtil.i(ret);
        LogUtil.i(crc);
        Log.d("TAG","ret="+ByteUtil.bytearrayToHexString(ret,ret.length)+"crc=="+ByteUtil.bytearrayToHexString(crc,crc.length));
        return ret[0] == crc[0] && ret[1] == crc[1];
    }

}
