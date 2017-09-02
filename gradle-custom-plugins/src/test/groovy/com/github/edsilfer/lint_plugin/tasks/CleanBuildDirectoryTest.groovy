package com.github.edsilfer.lint_plugin.tasks

import org.gradle.testkit.runner.GradleRunner
import spock.lang.Specification

import static com.github.edsilfer.lint_plugin.util.FileUtil.*
import static org.gradle.testkit.runner.TaskOutcome.SUCCESS

/**
 * Created by edgar on 01/09/17.
 */
class CleanBuildDirectoryTest extends Specification {

    private static final String ARG_TASK = ":clean"

    def setup() {
        createBuildDirectory()
    }

    def "assert that clean task deletes build directory"() {
        when:
        def result = GradleRunner.create()
                .withProjectDir(getTestProjectDirectory())
                .withArguments(ARG_TASK)
                .build()

        then:
        result.task(ARG_TASK).outcome == SUCCESS
        !getBuildDirectory().exists()
    }

    def cleanup() {
        deleteBuildDirectory()
    }

}
