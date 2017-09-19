package br.com.edsilfer.intellij.sample_plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

/**
 * Created by edgar on 18/09/17.
 */
public class SamplePlugin extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent event) {
        Project project = event.getData(PlatformDataKeys.PROJECT);

        String text = Messages.showInputDialog(
                project,
                "What is your name?",
                "Input your name",
                Messages.getQuestionIcon()
        );

        Messages.showMessageDialog(
                project,
                "Hello, " + text + "!\n I am glad to see you.",
                "Information",
                Messages.getInformationIcon()
        );
    }

}
