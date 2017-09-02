package br.com.edsilfer.lint_plugin

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

/**
 * Created by edgar on 01/09/17.
 */
class LintPluginTest extends Specification {

    private Project project = ProjectBuilder.builder()
            .withName("test-lint-plugin")
            .build()

    def "assert that LintPlugin has installCustomLintRules task"() {
        when:
        project.pluginManager.apply LintPlugin

        then:
        project.tasks.installCustomLintRules.enabled
    }

    def "assert that LintPlugin has clean task"() {
        when:
        project.pluginManager.apply LintPlugin

        then:
        project.tasks.clean.enabled
    }

    def "assert that LintPlugin has createFile task"() {
        when:
        project.pluginManager.apply LintPlugin

        then:
        project.tasks.createFile.enabled
    }

}