package com.github.edsilfer.lint_plugin.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Created by edgar on 01/09/17.
 */
class InstallCustomLintRules extends DefaultTask {

    static String INFO_COMMAND = "installLintRules"
    static String INFO_DESCRIPTION = "Copies lint JAR file into the {user.home}/.android/lint directory"

    @TaskAction
    def action() {
        String ARG_SOURCE_DIRECTORY = "build/libs/"
        String ARG_TARGET_DIRECTORY = System.getProperty("user.home") + '/.android/lint'
        String ARG_SOURCE_TYPE = "*.jar"

        /*
        ignore lint error that from/into/include wasn't found
         */
        project.copy {
            from(ARG_SOURCE_DIRECTORY)
            into(ARG_TARGET_DIRECTORY)
            include(ARG_SOURCE_TYPE)
        }
    }

}
