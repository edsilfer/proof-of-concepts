package com.github.edsilfer.lint_plugin

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

    def "assert that LintPlugin has installLintRules task"() {
        when:
        project.pluginManager.apply LintPlugin

        then:
        project.tasks.installLintRules.enabled
    }

}