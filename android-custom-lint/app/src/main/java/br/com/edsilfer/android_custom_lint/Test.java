package br.com.edsilfer.android_custom_lint;

import br.com.edsilfer.android_custom_lint.annotations.Careful;

public class Test {

    public String a;

    @Careful
    public static void doComplexWork() {

    }

    public void test() {
        doComplexWork();
    }

}
