package br.com.edsilfer.lint_rules.detectors;

import com.android.tools.lint.detector.api.Detector;

import br.com.edsilfer.lint_rules.util.EnhancedLintDetectorClass;

import static br.com.edsilfer.lint_rules.resources.Strings.STR_ISSUE_001_DESCRIPTION;

public class TestArgumentNameDetector extends EnhancedLintDetectorClass {

    private static final String ARG_TEST_CASE_01 = "ArgumentNameTestCase1.java";
    private static final String ARG_TEST_CASE_02 = "ArgumentNameTestCase2.java";

    private static final String ARG_LOG_NO_WARNING = "No warnings.";

    @Override
    protected Detector getDetector() {
        return new ArgumentNameDetector();
    }

    public void test_all_arguments_are_compliant() throws Exception {
        assertEquals(ARG_LOG_NO_WARNING, lintFiles(ARG_TEST_CASE_01));
    }

    public void test_argument_name_not_compliant() throws Exception {
        assertTrue(lintFiles(ARG_TEST_CASE_02).contains(STR_ISSUE_001_DESCRIPTION));
    }
}
