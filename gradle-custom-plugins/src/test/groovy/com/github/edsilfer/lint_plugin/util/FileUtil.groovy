package com.github.edsilfer.lint_plugin.util

/**
 * Created by edgar on 01/09/17.
 */
class FileUtil {

    private static final String ARG_A_SAMPLE_FILE = "/afile.txt"

    private static URL url = Thread.currentThread()
            .getContextClassLoader()
            .getResource("build.gradle")
    private static def testProjectDir = new File(url.getPath()).parentFile
    private static def buildDirectory = new File(testProjectDir.path + "/build")

    static void createBuildDirectory() {
        if (!buildDirectory.exists()) {
            buildDirectory.mkdirs()
            def file = new File(buildDirectory.path + ARG_A_SAMPLE_FILE)
            file.createNewFile()
            file.text = "Sample text file"
        }
    }

    static void deleteBuildDirectory() {
        if (buildDirectory.exists()) buildDirectory.deleteDir()
    }

    static File getTestProjectDirectory() { return testProjectDir }

    static File getBuildDirectory() { return buildDirectory }

}
