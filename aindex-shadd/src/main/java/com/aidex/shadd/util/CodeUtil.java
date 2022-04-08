package com.aidex.shadd.util;

import com.aidex.common.utils.uuid.IdUtils;
import lombok.Synchronized;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author zhongliang
 * @version 1.0
 * @description: 编码码工具类
 * @date 2022/4/3 19:15
 */
public class CodeUtil {

    /** 订单类别头 */
    private static final String ORDER_CODE = "D-";

    /** 充值别头 */
    private static final String PAY_ORDER = "C-";

    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };
    
    /** 
     * 邀请码生成方法
     * @return  邀请码
     * @author zhongliang
     * @date: 2022/4/5 17:16
     */ 
    public static String generateShortUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = IdUtils.simpleUUID();
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }

    /**
     * 根据时间+前缀生成编号
     * @param: prefix
     * @author zhongliang
     * @date: 2022/4/5 17:41
     */
    @Synchronized
    public static String generateDateStrAddPrefix(String prefix) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");//设置日期格式
        return prefix + LocalDateTime.now().format(fmt);
    }


    /**
     * 生成充值记录编码
     * @return 充值记录编码
     * @author zhongliang
     * @date: 2022/4/5 17:41
     */
    public static String generatePayCode() {
       return generateDateStrAddPrefix(PAY_ORDER);
    }

    /**
     * 生成订单编号
     * @author zhongliang
     * @date: 2022/4/7 15:31
     */
    public static String generateOrderCode() {
        return generateDateStrAddPrefix(ORDER_CODE);
    }

}
