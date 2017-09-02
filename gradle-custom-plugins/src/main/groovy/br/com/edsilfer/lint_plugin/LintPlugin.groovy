package br.com.edsilfer.lint_plugin

import br.com.edsilfer.lint_plugin.tasks.CleanBuildDirectory
import br.com.edsilfer.lint_plugin.tasks.CreateFile
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by edgar on 01/09/17.
 */
class LintPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.task("clean", type: CleanBuildDirectory) {
            group = LintPlugin.name
            description = CleanBuildDirectory.INFO_DESCRIPTION
        }

        project.task("createFile", type: CreateFile) {
            group = LintPlugin.name
            description = CreateFile.INFO_DESCRIPTION
        }
    }
}
