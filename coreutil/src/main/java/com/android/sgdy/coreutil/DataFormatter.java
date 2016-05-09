package com.android.sgdy.coreutil;

import java.util.Date;

/**
 * Created by sgdy on 16/4/25.
 * 数字的格式化
 */
public class DataFormatter {

    /**
     * %[argument number][flags][width][.precision]type
     * argument number:如果要格式化的参数超过一个以上，可以在这里指定是哪一个。
     * flags:特定类型的特定选项，例如数字要加逗号或正负号。
     * width:最小的字符数，注意:这不是总数，输出可以超过此宽度，若不足则会主动补零。
     * precision:精确度，注意前面有个圆点符号。
     * type:一定要指定的类型标识。
     */

    /**
     * 返回一个整数
     * @param value 参数必须能够与int相容
     * @return
     */
    public static String d(int value) {
        return String.format("%,d", value);
    }

    /**
     * 返回精确位数的float
     * @param index
     * @param value 参数必须是浮点数类型
     * @return
     */
    public static String f(int index, float value) {
        return String.format("%." + index + "f", value);
    }

    /**
     * 返回16进制格式，参数必须是byte short int long BigInteger
     * @param value
     * @return
     */
    public static String x(int value) {
        return String.format("%x", value);
    }

    /**
     *  返回ASCLL上对应的字符  参数必须是byte short int long
     * @param value
     * @return
     */
    public static String c(int value) {
        return String.format("%c", value);
    }

    /**
     * 返回完整的日期和时间
     * @param date
     * @return
     */
    public static String tc(Date date) {
        return String.format("%tc", date);
    }

    /**
     * 返回的只有时间 没有日期
     * @param date
     * @return
     */
    public static String tr(Date date) {
        return String.format("%tr", date);
    }

    /**
     * 返回周 月 日 不用重复给参数 <: 表示重复利用之前用过的参数
     * @param date
     * @return
     */
    public static String taTbTd(Date date) {
        return String.format("%tA, %<tB %<td", date);
    }
}
