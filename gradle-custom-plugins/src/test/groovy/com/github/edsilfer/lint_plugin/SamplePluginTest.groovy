package com.github.edsilfer.lint_plugin

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

/**
 * Created by edgar on 02/09/17.
 */
class SamplePluginTest extends Specification {

    private Project project = ProjectBuilder.builder()
            .withName("test-sample-plugin")
            .build()

    def "assert that SamplePlugin has clean task"() {
        when:
        project.pluginManager.apply SamplePlugin

        then:
        project.tasks.clean.enabled
    }

    def "assert that SamplePlugin has createFile task"() {
        when:
        project.pluginManager.apply SamplePlugin

        then:
        project.tasks.createFile.enabled
    }

}