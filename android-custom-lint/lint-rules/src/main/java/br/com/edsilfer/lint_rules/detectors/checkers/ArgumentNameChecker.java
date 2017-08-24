package br.com.edsilfer.lint_rules.detectors.checkers;

import com.android.tools.lint.detector.api.JavaContext;

import lombok.ast.ForwardingAstVisitor;
import lombok.ast.StrictListAccessor;
import lombok.ast.VariableDeclaration;
import lombok.ast.VariableDefinitionEntry;

import static br.com.edsilfer.lint_rules.resources.Issues.ISSUE_001;
import static br.com.edsilfer.lint_rules.resources.Strings.STR_ISSUE_001_DESCRIPTION;

/**
 * Created by edgar on 23/08/17.
 */

public class ArgumentNameChecker extends ForwardingAstVisitor {

    private final JavaContext context;

    public ArgumentNameChecker(JavaContext context) {
        this.context = context;
    }

    @Override
    public boolean visitVariableDeclaration(VariableDeclaration node) {
        StrictListAccessor<VariableDefinitionEntry, VariableDeclaration> varDefinitions =
                node.getVariableDefinitionEntries();

        for (VariableDefinitionEntry varDefinition : varDefinitions) {
            String name = varDefinition.astName().astValue();
            if (name.length() == 1) {
                context.report(
                        ISSUE_001,
                        context.getLocation(node),
                        STR_ISSUE_001_DESCRIPTION
                );
                return true;
            }
        }
        return false;
    }
}
