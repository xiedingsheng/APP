package com.dcits.storage.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.security.MessageDigest;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 字符串工具类
 * @author xieds
 * @date 2019/3/5 9:29
 * @updater xieds
 * @updatedate 2019/3/5 9:29
 */
public class StringUtils {

    public StringUtils() {}

    /**
     * 字符串是否为空
     * @author xieds
     * @date 2019/3/5 9:29
     * @updater xieds
     * @updatedate 2019/3/5 9:29
     */
    public static boolean isBlank(String str) {
        return str == null || "".equals(str.trim());
    }

    /**
     * 字符串是否不为空
     * @author xieds
     * @date 2019/3/5 9:29
     * @updater xieds
     * @updatedate 2019/3/5 9:29
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * Map是否为空
     * @author xieds
     * @date 2019/3/5 9:29
     * @updater xieds
     * @updatedate 2019/3/5 9:29
     */
    public static boolean isBlank(Map<?, ?> map, Object key) {
        boolean flag = false;
        if (map.containsKey(key)) {
            Object o = map.get(key);
            if (o == null) {
                flag = true;
            } else if (o instanceof String && "".equals(((String)o).trim())) {
                flag = true;
            }
        } else {
            flag = true;
        }

        return flag;
    }

    /**
     * Map是否不为空
     * @author xieds
     * @date 2019/3/5 9:29
     * @updater xieds
     * @updatedate 2019/3/5 9:29
     */
    public static boolean isNotBlank(Map<?, ?> map, Object key) {
        return !isBlank(map, key);
    }

    /**
     * 转换成MD5
     * @author xieds
     * @date 2019/3/5 9:29
     * @updater xieds
     * @updatedate 2019/3/5 9:29
     */
    public static String toMd5(String str) throws Exception {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes());
        byte b[] = md.digest();

        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0) {
                i += 256;
            }
            if (i < 16) {
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }
        return buf.toString();
    }

    /**
     * UUID
     * @author xieds
     * @date 2019/3/5 9:29
     * @updater xieds
     * @updatedate 2019/3/5 9:29
     */
    public static String uuid() {

        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 将集合转换成JSON
     * @author xieds
     * @date 2019/3/5 9:29
     * @updater xieds
     * @updatedate 2019/3/5 9:29
     */
    public static String listToJson(List list) {
        JSONObject jsonObj = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(list);
        jsonObj.put("content", jsonArray);
        return jsonObj.toString();
    }
}
