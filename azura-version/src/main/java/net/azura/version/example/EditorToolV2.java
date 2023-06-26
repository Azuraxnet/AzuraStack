package net.azura.version.example;

public class EditorToolV2 implements EditorTool {
    @Override
    public void performAction() {
        System.out.println("Hello from inside EditorToolV2");
    }

    @Override
    public void isSupported() {

    }
}
