package net.azura.version.example;

public class EditorToolV1 implements EditorTool {
    @Override
    public void performAction() {
        System.out.println("Hello from inside EditorToolV1");
    }

    @Override
    public String getVersion() {
        return "V1";
    }
}
