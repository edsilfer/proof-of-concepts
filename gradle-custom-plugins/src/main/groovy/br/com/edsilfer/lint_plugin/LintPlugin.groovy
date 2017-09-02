package br.com.edsilfer.lint_plugin

import br.com.edsilfer.lint_plugin.tasks.CleanBuildDirectory
import br.com.edsilfer.lint_plugin.tasks.CreateFile
import br.com.edsilfer.lint_plugin.tasks.InstallCustomLintRules
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

        project.task(CleanBuildDirectory.INFO_COMMAND, type: CleanBuildDirectory) {
            group = LintPlugin.name
            description = CleanBuildDirectory.INFO_DESCRIPTION
        }

        project.task(CreateFile.INFO_COMMAND, type: CreateFile) {
            group = LintPlugin.name
            description = CreateFile.INFO_DESCRIPTION
        }
    }
}
