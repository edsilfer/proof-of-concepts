package br.com.edsilfer.lint_rules;

import com.android.tools.lint.detector.api.Issue;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import br.com.edsilfer.lint_rules.resources.IssuesCatalog;

import static br.com.edsilfer.lint_rules.resources.Issues.ISSUE_001;
import static br.com.edsilfer.lint_rules.resources.Issues.ISSUE_002;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by edgar on 23/08/17.
 */

public class TestIssuesCatalog {

    private IssuesCatalog catalog;

    @Before
    public void setUp() throws Exception {
        catalog = new IssuesCatalog();
    }

    @Test
    public void testNumberOfIssues() throws Exception {
        int size = catalog.getIssues().size();
        assertThat(size).isEqualTo(2);
    }

    @Test
    public void testGetIssues() throws Exception {
        List<Issue> actual = catalog.getIssues();
        assertThat(actual).contains(ISSUE_001);
        assertThat(actual).contains(ISSUE_002);
    }

}
