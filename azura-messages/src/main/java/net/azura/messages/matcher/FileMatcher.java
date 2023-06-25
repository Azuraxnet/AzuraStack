package net.azura.messages.matcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class FileMatcher {
    private char startChar;
    private char endChar;
    private Pattern pattern;

    public FileMatcher(char startChars, char endChars){
        this.startChar = startChars;
        this.endChar = endChars;
        this.pattern = Pattern.compile( this.startChar + "[^" + this.startChar + this.endChar + "]+" + this.endChar);
    }

    public HashMap<String, String> match(String string, String matched){
        java.util.regex.Matcher matcher = this.pattern.matcher(matched);
        List<String> names = new ArrayList<>();
        while(matcher.find()){
            names.add(matcher.group());
        }
        List<String> objNames = new ArrayList<>();
        for(String name : names){
            objNames.add(name.replace(String.valueOf(this.startChar), "").replace(String.valueOf(this.endChar), ""));
        }
        HashMap<String, String> matchObj = new HashMap<>();
        String pat = matched;

        for(int i = 0; i<objNames.size(); i++){
            matchObj.put(objNames.get(i), "");
            pat = pat.replace(names.get(i), "§Q§");
        }
        pat = pat.replaceAll("[-\\[\\]{}()*+?.,\\\\^$|#\\s]", "\\\\$0");
        pat = pat.replace("§Q§", "(.+)");
        Pattern newPattern = Pattern.compile(pat);
        java.util.regex.Matcher newMatcher = newPattern.matcher(string);
        if(newMatcher.find()){
            for(int i=0; i<objNames.size(); i++){
                matchObj.put(objNames.get(i), newMatcher.group(i + 1));
            }
        }else{
            for (String objName : objNames) {
                matchObj.put(objName, null);
            }
        }
        return matchObj;
    }
}
