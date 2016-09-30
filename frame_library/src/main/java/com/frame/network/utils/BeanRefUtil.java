/*
 * Copyright (C) 14-6-6 下午3:42 Guangzhou Visions Tech Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.frame.network.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Use:
 * Created by yinglovezhuzhu@gmail.com on 2014-06-06.
 */
public class BeanRefUtil {

    private BeanRefUtil() {}

    /**
     * Get all fields' value and put them into a Map.
     *
     * @param bean
     * @return Map
     */
    public static Map<String, Object> getFieldValueMap(Object bean) {
        Map<String, Object> valueMap = new HashMap<String, Object>();
        if(bean == null) {
            return valueMap;
        }
        Class<?> cls = bean.getClass();
        Map<String, Field> fieldMap = getFields(cls);
        Iterator<Entry<String, Field>> iterator = fieldMap.entrySet().iterator();
        while(iterator.hasNext()) {
            try {
                Field field = iterator.next().getValue();
                field.setAccessible(true);
                Object value = field.get(bean);
                valueMap.put(field.getName(), value);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return valueMap;
    }

    /**
     * Get all fields, contain superclass' fields.
     * @param cls
     * @return
     */
    public static Map<String, Field> getFields(Class<?> cls) {
        Map<String, Field> fieldMap = new HashMap<String, Field>();
        Class<?> superClass = cls.getSuperclass();
        if(superClass != null) {
            fieldMap.putAll(getFields(superClass));
        }
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            fieldMap.put(field.getName(), field);
        }
        return fieldMap;
    }

    /**
     * Set all fields' value.
     *
     * @param bean
     * @param valMap
     */
    public static void setFieldValues(Object bean, Map<String, Object> valMap) {
        Class<?> cls = bean.getClass();
        Map<String, Field> fieldMap = getFields(cls);
        Iterator<Entry<String, Object>> iterator = valMap.entrySet().iterator();
        while(iterator.hasNext()) {
            try {
                Field field = fieldMap.get(iterator.next().getKey());
                if(field == null) {
                    continue;
                }
                field.setAccessible(true);
                field.set(bean, valMap.get(field.getName()));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
