package com.esmt.timeManagement.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/*
 * Exclude Annotation allow to exclude a field of an entity to Gson Serializer
 * 
 * The AnnotationExclusionStrategy.class is include in the definition of this annotation 
 * 
 * For more explanations, check the page below
 * https://stackoverflow.com/questions/4802887/gson-how-to-exclude-specific-fields-from-serialization-without-annotations/27986860#27986860
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Exclude {
}