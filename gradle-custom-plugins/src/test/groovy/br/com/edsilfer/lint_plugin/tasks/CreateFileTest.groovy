package br.com.edsilfer.lint_plugin.tasks

import org.gradle.testkit.runner.GradleRunner
import spock.lang.Specification

import static br.com.edsilfer.lint_plugin.util.FileUtil.*
import static org.gradle.testkit.runner.TaskOutcome.SUCCESS

/**
 * Created by edgar on 01/09/17.
 */
class CreateFileTest extends Specification {

    private static final String ARG_TASK = ":createFile"
    private static final String ARG_SAMPLE_FILE = "/sample.txt"

    def setup() {
        deleteBuildDirectory()
    }

    def "assert that createFile task creates a sample.txt file inside build directory"() {
        when:
        def result = GradleRunner.create()
                .withProjectDir(getTestProjectDirectory())
                .withArguments(ARG_TASK)
                .build()

        then:
        result.task(ARG_TASK).outcome == SUCCESS
        new File(getBuildDirectory().path + ARG_SAMPLE_FILE).exists()
    }

    def cleanup() {
        deleteBuildDirectory()
    }

}
