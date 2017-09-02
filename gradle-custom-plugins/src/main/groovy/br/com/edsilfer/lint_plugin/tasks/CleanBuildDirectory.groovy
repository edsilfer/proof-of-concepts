package br.com.edsilfer.lint_plugin.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Created by edgar on 01/09/17.
 */
class CleanBuildDirectory extends DefaultTask {

    static String INFO_DESCRIPTION = "Deletes build directory and its contents"

    @TaskAction
    def action() {
        project.buildDir.deleteDir()
        println(String.format("Removed %s", project.buildDir.path))
    }

}
