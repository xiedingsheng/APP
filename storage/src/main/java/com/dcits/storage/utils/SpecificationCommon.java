package com.dcits.storage.utils;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 公共查询条件类
 * @author xieds
 * @date 2019/4/10 9:29
 * @updater xieds
 * @updatedate 2019/4/10 9:29
 */
public class SpecificationCommon<T> implements Specification<T> {

    private PageInfo<T> pageInfo;

    private List fieldNameListLike;

    public SpecificationCommon(List fieldNameListLike, PageInfo<T> pageInfo) {
        this.pageInfo = pageInfo;
        this.fieldNameListLike = fieldNameListLike;
    }

    /**
     * 重写查询条件类
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if(pageInfo!=null){
            /*查询条件参数*/
            Object obj = pageInfo.getSelectOptions();
            if(obj != null){
                List<Field> fieldList = new ArrayList<>() ;
                Class tempClass = obj.getClass();
                while (tempClass != null) {
                    fieldList.addAll(Arrays.asList(tempClass .getDeclaredFields()));
                    tempClass = tempClass.getSuperclass();
                }

                if(fieldList!=null && fieldList.size()>0){
                    for (Field f : fieldList) {
                        f.setAccessible(true);
                        predicates = convertSelectOptionSpecification(obj, f, fieldNameListLike, predicates, root, cb);
                    }
                }
            }

            /*固定参数，若SQL中使用in，则字段前加@前缀*/
            Map params = pageInfo.getParams();
            if(params != null){
                Set<String> allKey = params.keySet();
                if(allKey!=null && allKey.size()>0){
                    for(String key : allKey){
                        if(StringUtils.isNotBlank(params, key)){
                            if(key.contains("@")){
                                /*固定参数，field in (value)，value形式为 值1,值2,值3...*/
                                CriteriaBuilder.In<Object> in = cb.in(root.get(key.substring(1,key.length())));
                                for(Object o : params.get(key).toString().split(",")){
                                    in.value(o);
                                }
                                predicates.add(in);
                            }
                            else{
                                /*固定参数，field = value*/
                                predicates.add(cb.equal(root.get(key), params.get(key).toString()));
                            }
                        }
                    }
                }
            }
        }
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    /**
     * 将查询条件转换成模糊查询或精准查询集合
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    public static List<Predicate> convertSelectOptionSpecification(Object obj, Field field, List fieldNameList, List<Predicate> predicates, Root root, CriteriaBuilder cb) {
        try {
            /*模糊查询*/
            if(fieldNameList.contains(field.getName())) {
                if(StringUtils.isNotBlank((String)field.get(obj))) {
                    predicates.add(cb.like(root.get(field.getName()), "%"+field.get(obj).toString()+"%"));
                }
            }
            /*精准查询，若有时间字段，起始时间在字段后加Start，结束时间在字段后加End*/
            else {
                if(field.getType() == String.class) {
                    if(StringUtils.isNotBlank((String)field.get(obj))) {
                        predicates.add(cb.equal(root.get(field.getName()), field.get(obj).toString()));
                    }
                }
                else {
                    if(field.get(obj)!=null) {
                        if(field.getType() == Date.class) {
                            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                            if(field.getName().contains("Start")) {
                                predicates.add(cb.greaterThanOrEqualTo(root.get(field.getName().replace("Start", "")), formatter.format(field.get(obj))));
                            }
                            if(field.getName().contains("End")) {
                                Calendar c = Calendar.getInstance();
                                c.setTime((Date)field.get(obj));
                                c.add(Calendar.DAY_OF_MONTH, 1);
                                predicates.add(cb.lessThanOrEqualTo(root.get(field.getName().replace("End", "")), formatter.format(c.getTime())));
                            }
                        }
                        else {
                            predicates.add(cb.equal(root.get(field.getName()), field.get(obj).toString()));
                        }
                    }
                }
            }
        }catch (Exception e){}
        return predicates;
    }
}
