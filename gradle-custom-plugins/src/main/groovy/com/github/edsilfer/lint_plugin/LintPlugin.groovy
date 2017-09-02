package com.github.edsilfer.lint_plugin

import com.github.edsilfer.lint_plugin.tasks.InstallCustomLintRules
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by edgar on 01/09/17.
 */
class LintPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.task(InstallCustomLintRules.INFO_COMMAND, type: InstallCustomLintRules) {
            group = LintPlugin.name
            description = InstallCustomLintRules.INFO_DESCRIPTION
        }
    }
}
