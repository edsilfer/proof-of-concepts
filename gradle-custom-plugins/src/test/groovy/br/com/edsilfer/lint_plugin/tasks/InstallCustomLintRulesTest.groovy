package br.com.edsilfer.lint_plugin.tasks

import org.gradle.testkit.runner.GradleRunner
import spock.lang.Specification

import static br.com.edsilfer.lint_plugin.util.FileUtil.*
import static org.gradle.testkit.runner.TaskOutcome.SUCCESS

/**
 * Created by edgar on 01/09/17.
 */
class InstallCustomLintRulesTest extends Specification {

    private static final String ARG_TASK = ":installLintRules"
    private static final String ARG_SAMPLE_LIBRARY_NAME = "sampleLibrary.jar"
    private static
    final String ARG_SOURCE_SAMPLE_FILE_PATH = getBuildDirectory().path + "/libs/" + ARG_SAMPLE_LIBRARY_NAME
    private static final String ARG_TARGET_SAMPLE_FILE_PATH = System.getProperty("user.home") + '/.android/lint'

    def setup() {
        deleteRulesFromLintDirectory()
        createSampleFiles()
    }

    private static def deleteRulesFromLintDirectory() {
        def library = new File(ARG_TARGET_SAMPLE_FILE_PATH + "/" + ARG_SAMPLE_LIBRARY_NAME)
        if (library.exists()) library.delete()
    }

    private static def createSampleFiles() {
        def sampleFile = new File(ARG_SOURCE_SAMPLE_FILE_PATH)
        if (!sampleFile.parentFile.exists()) {
            sampleFile.mkdirs()
            sampleFile.createNewFile()
        }
    }

    def "assert that files are correctly copied from source to target dirs when installCustomLintRules runs"() {
        when:
        def result = GradleRunner.create()
                .withProjectDir(getTestProjectDirectory())
                .withArguments(ARG_TASK)
                .build()

        then:
        result.task(ARG_TASK).outcome == SUCCESS
        new File(ARG_TARGET_SAMPLE_FILE_PATH + "/" + ARG_SAMPLE_LIBRARY_NAME).exists()
    }

    def cleanup() {
        deleteRulesFromLintDirectory()
        deleteBuildDirectory()
    }

}
