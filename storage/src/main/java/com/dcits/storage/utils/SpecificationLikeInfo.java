package com.dcits.storage.utils;

import java.util.Arrays;
import java.util.List;

/**
 * 查询条件中需要模糊查询的字段集合
 * @author xieds
 * @date 2019/4/10 9:29
 * @updater xieds
 * @updatedate 2019/4/10 9:29
 */
public class SpecificationLikeInfo {

    /**入库单需模糊查询的字段名*/
    public static List TU_IN_STORE_FIELD_LIKE = Arrays.asList("inStoreNo","billNo","ownerName");

    /**出库单需模糊查询的字段名*/
    public static List TU_OUT_STORE_FIELD_LIKE = Arrays.asList("outStoreNo","carNo","boxNo");
}
