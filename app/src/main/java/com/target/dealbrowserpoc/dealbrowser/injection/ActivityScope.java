package com.target.dealbrowserpoc.dealbrowser.injection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Activity Scoped
 */

@Retention(RetentionPolicy.RUNTIME)
@Scope
public @interface ActivityScope {
}
