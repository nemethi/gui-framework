package nemethi.demo;

import java.util.Arrays;

import nemethi.gui.BorderedPane;
import nemethi.gui.Button;
import nemethi.gui.ComboBox;
import nemethi.gui.DefaultDisplayEngine;
import nemethi.gui.DisplayEngine;
import nemethi.gui.Label;
import nemethi.gui.TabbedPane;
import nemethi.gui.TabbedPane.Tab;
import nemethi.gui.TextBox;


public class Demo {

    public static void main(String[] args) {
        TextBox textBox = new TextBox("Type here...");
        textBox.setSize(40);

        Button button = new Button("Click here!");
        ComboBox comboBox = new ComboBox(Arrays.asList("First item", "Second item", "Third item"));

        BorderedPane borderedPane = new BorderedPane();
        borderedPane.addNextToLast(textBox);
        borderedPane.addNextToLast(button);
        borderedPane.addUnderLast(comboBox);

        Tab firstTab = new Tab("First tab");
        firstTab.addNextToLast(borderedPane);
        firstTab.addUnderLast(new Label("Sample text"));

        Tab secondTab = new Tab("Second tab");
        secondTab.addNextToLast(new Label("Name:"));
        secondTab.addNextToLast(new TextBox());
        secondTab.addUnderLast(new Label("Sex:"));
        secondTab.addNextToLast(new ComboBox(Arrays.asList("Female", "Male")));

        TabbedPane tabbedPane = new TabbedPane();
        tabbedPane.addTab(firstTab);
        tabbedPane.addTab(secondTab);

        DisplayEngine displayEngine = new DefaultDisplayEngine();
        System.out.println(displayEngine.display(tabbedPane));
    }
}
