package com.megatap.themovies.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Jackie Nguyen <nguyenngoc100@gmail.com> on 10/15/15.
 */
@Retention(RetentionPolicy.RUNTIME)
@Scope
public @interface ActivityScope {
}
