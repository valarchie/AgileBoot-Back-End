package com.agileboot.infrastructure.cache.map;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import com.agileboot.orm.common.annotations.Dictionary;
import com.agileboot.orm.common.enums.*;
import com.agileboot.orm.common.interfaces.DictionaryEnum;
import com.agileboot.orm.common.result.DictionaryData;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 本地一级缓存  使用Map
 *
 * @author valarchie
 */
public class MapCache {

    private static final Map<String, List<DictionaryData>> DICTIONARY_CACHE = MapUtil.newHashMap(128);

    private MapCache() {
    }

    static {
        initDictionaryCache();
    }

    private static void initDictionaryCache() {

        loadInCache(BusinessTypeEnum.values());
        loadInCache(YesOrNoEnum.values());
        loadInCache(StatusEnum.values());
        loadInCache(GenderEnum.values());
        loadInCache(NoticeStatusEnum.values());
        loadInCache(NoticeTypeEnum.values());
        loadInCache(OperationStatusEnum.values());
        loadInCache(LoginStatusEnum.values());
        loadInCache(VisibleStatusEnum.values());

    }


    public static Map<String, List<DictionaryData>> dictionaryCache() {
        return DICTIONARY_CACHE;
    }

    private static void loadInCache(DictionaryEnum[] dictionaryEnums) {
        DICTIONARY_CACHE.put(getDictionaryName(dictionaryEnums[0].getClass()), arrayToList(dictionaryEnums));
    }


    private static String getDictionaryName(Class<?> clazz) {
        Objects.requireNonNull(clazz);
        Dictionary annotation = clazz.getAnnotation(Dictionary.class);

        Objects.requireNonNull(annotation);
        return annotation.name();
    }

    @SuppressWarnings("rawtypes")
    private static List<DictionaryData> arrayToList(DictionaryEnum[] dictionaryEnums) {
        if(ArrayUtil.isEmpty(dictionaryEnums)) {
            return ListUtil.empty();
        }
        return Arrays.stream(dictionaryEnums).map(DictionaryData::new).collect(Collectors.toList());
    }


}
