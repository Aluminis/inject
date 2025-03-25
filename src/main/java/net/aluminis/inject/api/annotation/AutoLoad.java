package net.aluminis.inject.api.annotation;

import net.aluminis.inject.api.constant.ProvidedIn;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AutoLoad {
    /**
     * TODO
     */
    ProvidedIn providedIn() default ProvidedIn.THIS;
}