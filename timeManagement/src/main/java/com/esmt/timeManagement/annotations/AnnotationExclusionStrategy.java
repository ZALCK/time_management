package com.esmt.timeManagement.annotations;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
/*
 * Check the Exclude Annotation Class in the same package
 */
public class AnnotationExclusionStrategy implements ExclusionStrategy{

	@Override
    public boolean shouldSkipField(FieldAttributes f) {
        return f.getAnnotation(Exclude.class) != null;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }

}
