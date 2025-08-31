package com.java.auth.annotations;

import com.java.auth.enums.AuthorType;
import com.java.auth.enums.CategoryType;

import java.lang.annotation.*;
import java.lang.annotation.Retention;

//This is an Custom Annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface FrameworkAnnotation {

    // This is not a method
    public AuthorType[] author();

    // public String[] category();
    public CategoryType[] category();
}
