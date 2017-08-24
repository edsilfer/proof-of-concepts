package br.com.edsilfer.lint_rules.resources;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;

import java.util.ArrayList;
import java.util.List;

import static br.com.edsilfer.lint_rules.resources.Issues.ISSUE_001;
import static br.com.edsilfer.lint_rules.resources.Issues.ISSUE_002;

/**
 * Created by edgar on 23/08/17.
 */

public class IssuesCatalog extends IssueRegistry {

    @Override
    public List<Issue> getIssues() {
        return new ArrayList<Issue>() {
            {
                add(ISSUE_001);
                add(ISSUE_002);
            }
        };
    }

}
