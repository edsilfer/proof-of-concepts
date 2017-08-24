package br.com.edsilfer.lint_rules.util;

import com.android.utils.SdkUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.CodeSource;

/**
 * Created by edgar on 23/08/17.
 */

public class FileUtil {

    private static final String ARG_TEST_RESOURCES_PATH = "resources/test/";

    public static InputStream getResource(String relativePath, CodeSource source) throws FileNotFoundException, MalformedURLException {
        String path = (ARG_TEST_RESOURCES_PATH + relativePath).replace('/', File.separatorChar);
        File file = new File(getTestDataRootDirectory(source), path);

        if (file.exists()) {
            return new BufferedInputStream(new FileInputStream(file));
        }

        return null;
    }

    private static File getTestDataRootDirectory(CodeSource source) throws MalformedURLException {
        if (source != null) {
            URL location = source.getLocation();
            File classesDir = SdkUtils.urlToFile(location);
            return classesDir.getParentFile().getAbsoluteFile().getParentFile().getParentFile();
        }
        return null;
    }

}
