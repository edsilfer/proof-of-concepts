package br.com.edsilfer.lint_plugin.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Created by edgar on 01/09/17.
 */
class CreateFile extends DefaultTask {

    static String INFO_COMMAND = "createFile"
    static String INFO_DESCRIPTION = "Creates a sample file with extension .txt inside build directory"

    private static final String ARG_SAMPLE_FILE_NAME = "sample.txt"

    @TaskAction
    def action() {
        def file = new File(project.buildDir, ARG_SAMPLE_FILE_NAME)
        file.parentFile.mkdirs()
        file.createNewFile()
        file.text = "This is a sample file created by a sample task"
        println String.format("Output file can be found at: %s", file.path)
    }

}
