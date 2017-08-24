package br.com.edsilfer.lint_rules.resources;

import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;

import br.com.edsilfer.lint_rules.detectors.AnnotationDetector;
import br.com.edsilfer.lint_rules.detectors.ArgumentNameDetector;

import static br.com.edsilfer.lint_rules.resources.Strings.STR_ISSUE_001_DESCRIPTION;
import static br.com.edsilfer.lint_rules.resources.Strings.STR_ISSUE_001_EXPLANATION;
import static br.com.edsilfer.lint_rules.resources.Strings.STR_ISSUE_001_ID;
import static br.com.edsilfer.lint_rules.resources.Strings.STR_ISSUE_002_DESCRIPTION;
import static br.com.edsilfer.lint_rules.resources.Strings.STR_ISSUE_002_EXPLANATION;
import static br.com.edsilfer.lint_rules.resources.Strings.STR_ISSUE_002_ID;
import static com.android.tools.lint.detector.api.Category.TYPOGRAPHY;
import static com.android.tools.lint.detector.api.Category.USABILITY;
import static com.android.tools.lint.detector.api.Scope.JAVA_FILE_SCOPE;
import static com.android.tools.lint.detector.api.Severity.WARNING;

public class Issues {

    public static final
    Issue ISSUE_001 = Issue.create(
            STR_ISSUE_001_ID,
            STR_ISSUE_001_DESCRIPTION,
            STR_ISSUE_001_EXPLANATION,
            TYPOGRAPHY,
            // Priority ranging from 0 to 10 in severeness
            6,
            WARNING,
            new Implementation(ArgumentNameDetector.class, JAVA_FILE_SCOPE)
    );

    public static final Issue ISSUE_002 = Issue.create(
            STR_ISSUE_002_ID,
            STR_ISSUE_002_DESCRIPTION,
            STR_ISSUE_002_EXPLANATION,
            USABILITY,
            6,
            WARNING,
            new Implementation(AnnotationDetector.class, JAVA_FILE_SCOPE)
    );

}
