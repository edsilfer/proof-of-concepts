package br.com.edsilfer.lint_rules.detectors;

import com.android.tools.lint.detector.api.Detector;

import br.com.edsilfer.lint_rules.util.EnhancedLintDetectorClass;

import static br.com.edsilfer.lint_rules.resources.Strings.STR_ISSUE_002_DESCRIPTION;

/**
 * Created by edgar on 23/08/17.
 */

public class TestAnnotationDetector extends EnhancedLintDetectorClass {

    private static final String ARG_TEST_CASE_01 = "AnnotationTestCase1.java";
    private static final String ARG_TEST_CASE_02 = "AnnotationTestCase2.java";

    private static final String ARG_LOG_NO_WARNING = "No warnings.";

    @Override
    protected Detector getDetector() {
        return new AnnotationDetector();
    }

    public void test_all_methods_are_compliant() throws Exception {
        assertEquals(ARG_LOG_NO_WARNING, lintFiles(ARG_TEST_CASE_01));
    }

    public void test_methods_with_careful_annotation() throws Exception {
        assertTrue(lintFiles(ARG_TEST_CASE_02).contains(STR_ISSUE_002_DESCRIPTION));
    }

}
