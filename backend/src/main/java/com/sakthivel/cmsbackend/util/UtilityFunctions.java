package com.sakthivel.cmsbackend.util;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Set;

@Component
public class UtilityFunctions {

    public static void CopyAndReplaceFieldsBetweenObjects(Object source, Object target, Set<String> protectedFields)
            throws IllegalAccessException, NoSuchFieldException {


        Field[] fields = source.getClass().getDeclaredFields();
        for(Field field : fields){
            if(protectedFields.contains(field.getName())) continue;
            Field targetField = target.getClass().getDeclaredField(field.getName());

            field.setAccessible(true);
            targetField.setAccessible(true);

            Object value = field.get(source);
            targetField.set(target, value);

            field.setAccessible(false);
            targetField.setAccessible(false);
        }
    }
}