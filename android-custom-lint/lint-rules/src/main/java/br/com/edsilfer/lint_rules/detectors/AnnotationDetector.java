package br.com.edsilfer.lint_rules.detectors;

import com.android.annotations.NonNull;
import com.android.tools.lint.detector.api.Context;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Speed;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import br.com.edsilfer.lint_rules.detectors.checkers.AnnotationChecker;
import lombok.ast.AstVisitor;
import lombok.ast.ConstructorInvocation;
import lombok.ast.MethodInvocation;
import lombok.ast.Node;

import static com.android.tools.lint.detector.api.Speed.FAST;

public class AnnotationDetector extends Detector implements Detector.JavaScanner {

    public static final String CAREFUL_ANNOTATION = "Careful";

    @Override
    public boolean appliesTo(@NonNull Context context, @NonNull File file) {
        return true;
    }

    @NonNull
    @Override
    public Speed getSpeed() {
        return FAST;
    }

    @Override
    public List<Class<? extends Node>> getApplicableNodeTypes() {
        return Arrays.<Class<? extends Node>>asList(
                MethodInvocation.class,
                ConstructorInvocation.class
        );
    }

    @Override
    public AstVisitor createJavaVisitor(@NonNull JavaContext context) {
        return new AnnotationChecker(context);
    }

}
