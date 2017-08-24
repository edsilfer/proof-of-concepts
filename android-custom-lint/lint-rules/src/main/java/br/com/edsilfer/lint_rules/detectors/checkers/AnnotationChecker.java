package br.com.edsilfer.lint_rules.detectors.checkers;

import com.android.annotations.NonNull;
import com.android.tools.lint.client.api.JavaParser;
import com.android.tools.lint.detector.api.JavaContext;

import java.util.ArrayList;
import java.util.List;

import lombok.ast.ConstructorInvocation;
import lombok.ast.ForwardingAstVisitor;
import lombok.ast.MethodInvocation;
import lombok.ast.Node;

import static br.com.edsilfer.lint_rules.detectors.AnnotationDetector.CAREFUL_ANNOTATION;
import static br.com.edsilfer.lint_rules.resources.Issues.ISSUE_002;
import static br.com.edsilfer.lint_rules.resources.Strings.STR_ISSUE_002_DESCRIPTION;
import static br.com.edsilfer.lint_rules.resources.Strings.STR_ISSUE_002_EXPLANATION;

/**
 * Created by edgar on 23/08/17.
 */

public class AnnotationChecker extends ForwardingAstVisitor {

    private final JavaContext context;

    public AnnotationChecker(JavaContext context) {
        this.context = context;
    }

    @Override
    public boolean visitMethodInvocation(@NonNull MethodInvocation call) {
        JavaParser.ResolvedNode resolved = context.resolve(call);

        if (resolved instanceof JavaParser.ResolvedMethod) {
            JavaParser.ResolvedMethod method = (JavaParser.ResolvedMethod) resolved;
            checkCall(call, method);
        }

        return false;
    }

    @Override
    public boolean visitConstructorInvocation(@NonNull ConstructorInvocation call) {
        JavaParser.ResolvedNode resolved = context.resolve(call);

        if (resolved instanceof JavaParser.ResolvedMethod) {
            JavaParser.ResolvedMethod method = (JavaParser.ResolvedMethod) resolved;
            checkCall(call, method);
        }

        return false;
    }

    private void checkCall(@NonNull Node call, JavaParser.ResolvedMethod method) {
        Iterable<JavaParser.ResolvedAnnotation> annotations = method.getAnnotations();
        annotations = filterRelevantAnnotations(annotations);

        for (JavaParser.ResolvedAnnotation annotation : annotations) {
            checkMethodAnnotation(context, call, annotation);
        }
    }

    private Iterable<JavaParser.ResolvedAnnotation> filterRelevantAnnotations(Iterable<JavaParser.ResolvedAnnotation> resolvedAnnotationsIn) {
        List<JavaParser.ResolvedAnnotation> resolvedAnnotationsOut = new ArrayList<>();

        for (JavaParser.ResolvedAnnotation resolvedAnnotation : resolvedAnnotationsIn) {
            if (resolvedAnnotation.getName().contains(CAREFUL_ANNOTATION)) {
                resolvedAnnotationsOut.add(resolvedAnnotation);
            }
        }

        return resolvedAnnotationsOut;
    }

    private static void checkMethodAnnotation(
            @NonNull JavaContext context,
            @NonNull Node node,
            @NonNull JavaParser.ResolvedAnnotation annotation) {
        String signature = annotation.getSignature();
        if (signature.contains(CAREFUL_ANNOTATION)) {
            context.report(
                    ISSUE_002,
                    node,
                    context.getLocation(node),
                    STR_ISSUE_002_DESCRIPTION
            );
        }
    }

}
