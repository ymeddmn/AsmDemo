package com.asm.mage.plugin.log;

/**
 * author  :mayong
 * function:
 * date    :2020-03-28
 **/
public class CLog {
    public static boolean log = true;

    /**
     * 可以控制打印的方法
     *
     * @param title
     * @param parsms
     */
    public static void log(String title, Object... parsms) {
        if (log) {
            String content = title+",";
            for (Object p : parsms) {
                if (p == null) {
                    content += "null";
                } else {
                    content += p.toString();
                }
                content += ",";
            }
            System.out.println(content);
        }
    }

    /**
     * 大多数情况下都要打印的方法
     *
     * @param title
     * @param parsms
     */
    public static void logAways(String title, Object... parsms) {
        String content = title;
        for (Object p : parsms) {
            if (p == null) {
                content += "null";
            } else {
                content += p.toString();
            }
            content += ",";
        }
        System.out.println(content);
    }

    public static void log(String title) {

        System.out.println(title);
    }
}
