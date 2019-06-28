package com.mrbook.util;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ParseJSON {
    public static <T> T parseObject(ServletInputStream inputStream, Class<T> clazz) {
        String s = "";
        while (!inputStream.isFinished()) {
            byte[] bytes = new byte[1024];
            int m = 0;
            try {
                m = inputStream.read(bytes, 0, 1024);
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] bytes1 = Arrays.copyOf(bytes, m);
            s += new String(bytes1, StandardCharsets.UTF_8);
        }
        return JSON.parseObject(s, clazz);
    }
}
