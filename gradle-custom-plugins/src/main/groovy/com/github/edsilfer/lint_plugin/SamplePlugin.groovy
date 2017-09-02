package com.github.edsilfer.lint_plugin

import com.github.edsilfer.lint_plugin.tasks.CleanBuildDirectory
import com.github.edsilfer.lint_plugin.tasks.CreateFile
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by edgar on 02/09/17.
 */
class SamplePlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
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