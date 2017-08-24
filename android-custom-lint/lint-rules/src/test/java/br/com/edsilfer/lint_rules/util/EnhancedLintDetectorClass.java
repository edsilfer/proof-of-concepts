package br.com.edsilfer.lint_rules.util;

import com.android.tools.lint.checks.infrastructure.LintDetectorTest;
import com.android.tools.lint.detector.api.Issue;

import java.io.InputStream;
import java.util.List;

import br.com.edsilfer.lint_rules.resources.IssuesCatalog;

/**
 * Created by edgar on 23/08/17.
 */

public abstract class EnhancedLintDetectorClass extends LintDetectorTest {

    @Override
    protected List<Issue> getIssues() {
        return new IssuesCatalog().getIssues();
    }

    @Override
    protected InputStream getTestResource(String relativePath, boolean expectExists) {
        try {
            return FileUtil.getResource(relativePath, getClass().getProtectionDomain().getCodeSource());
        } catch (Exception e) {
            fail(e.getMessage());
        }
        return null;
    }

}
