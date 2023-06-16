package net.azura.version;

import net.azura.version.example.EditorTool;
import net.azura.version.example.EditorToolV1;
import net.azura.version.example.EditorToolV2;

public class Main {
    public static void main(String... args){

        OldVersion<EditorTool> v1 = new OldVersion(new EditorToolV1(), EditorTool.class);
        OldVersion<EditorTool> v2 = new OldVersion(new EditorToolV2(), EditorTool.class);

        System.out.println("Expected: V1");
        EditorTool tool = v1.getElement();
        tool.performAction();

        System.out.println();

        System.out.println("Expected: V1 -> V2");
        v1 = v1.convert("V2");
        tool = v1.getElement();
        tool.performAction();

        System.out.println();

        System.out.println("Expected: V2");
        tool = v2.getElement();
        tool.performAction();

        System.out.println();

        System.out.println("Expected: V2 -> V1");
        v2 = v2.convert("V1");
        tool = v2.getElement();
        tool.performAction();

        System.out.println();

        System.out.println("Creating new Editor Tool");
        OldVersion<EditorTool> editorToolOldVersion = new OldVersion<>("V1", EditorTool.class);
        System.out.println("Expected: V1");
        editorToolOldVersion.getElement().performAction();


        System.out.println("Expected: Exception");
        v1.convert("V3");





        //Creating New versions
        EditorToolV1 editorToolV1 = new EditorToolV1();
        EditorToolV2 editorToolV2 = new EditorToolV2();

        //ClassRegister them in your onEnable or something like that
        OldVersion.registerVersion(editorToolV1, EditorTool.class);
        OldVersion.registerVersion(editorToolV2, EditorTool.class);

        //Grab current server version
        EditorTool v2Tool = OldVersion.get(EditorTool.class, getCurrentServerVersion());
        v2Tool.performAction();

        //Grab a previous version
        EditorTool v1Tool = OldVersion.get(EditorTool.class, "V1");
        v1Tool.performAction();
    }

    public static String getCurrentServerVersion(){
        return "V2";
    }
}
