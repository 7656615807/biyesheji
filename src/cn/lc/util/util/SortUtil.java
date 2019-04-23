package cn.lc.util.util;

import cn.lc.pojo.Groups;

import java.util.Comparator;

/**
 * @description:
 * @author: lc
 * @date: 2019-03-26 22:57
 **/

public class SortUtil {
    public static Comparator<Groups> compar_groups = new Comparator<Groups>() {
        @Override
        public int compare(Groups o1, Groups o2) {
            if (o1.getGid() < o2.getGid()){
                return 1;
            }else if (o1.getGid() == o2.getGid()){
                return 0;
            }else {
                return -1;
            }
        }
    };
}
