package com.binary.giphy.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by duong on 9/20/2017.
 */
@Scope
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ActivityScope {
}
