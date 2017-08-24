package br.com.edsilfer.lint_rules.detectors;

import com.android.annotations.NonNull;
import com.android.tools.lint.detector.api.Context;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Speed;

import java.io.File;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import br.com.edsilfer.lint_rules.detectors.checkers.ArgumentNameChecker;
import lombok.ast.AstVisitor;

import static com.android.SdkConstants.TAG_ATTR;
import static com.android.tools.lint.detector.api.Scope.JAVA_FILE_SCOPE;
import static com.android.tools.lint.detector.api.Speed.FAST;

/**
 * Created by edgar on 23/08/17.
 */

public class ArgumentNameDetector extends Detector implements Detector.JavaScanner {

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
    public List<String> getApplicableElements() {
        return Arrays.asList(TAG_ATTR);
    }

    @Override
    public EnumSet<Scope> getApplicableFiles() {
        return JAVA_FILE_SCOPE;
    }

    @Override
    public AstVisitor createJavaVisitor(@NonNull JavaContext context) {
        return new ArgumentNameChecker(context);
    }

}
