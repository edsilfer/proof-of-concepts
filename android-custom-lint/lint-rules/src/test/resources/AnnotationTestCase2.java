package br.com.edsilfer.lint_rules;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class AnnotationTestCase2 {

    private String sampleVariable;

    @Careful
    public void beCareful() {

    }

    public void sampleMethod() {
        beCareful();
    }

}

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD})
@interface Careful {

}
