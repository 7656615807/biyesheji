package cn.lc.test;

import org.springframework.util.DigestUtils;

/**
 * @description:
 * @author: lc
 * @date: 2019-03-03 02:38
 **/

public class Test01 {
    public static void main(String[] args) {
        System.out.println(DigestUtils.md5DigestAsHex("123456".getBytes()));
    }
}
