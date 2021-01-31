
// Developed by the TerminatorOTW

package beatzplayerfab.java.api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class INI_rw {
    
    private static INI_rw ini_rw = new INI_rw();
    
    /**
     * Main methods of this class
     */
    public static boolean setKeyValue(File iniFile, String header, String key, String value) {
        if (iniFile == null || header.isEmpty() || key.isEmpty())
            return false;
        
        String text = ini_rw.getText(iniFile);
        int headerEndPos = ini_rw.getHeaderEndPos(text, header);
        
        if (headerEndPos < 0) {
            System.out.println("Your specified header doesn't exist :(");
        } else {
            int keyEndPos = ini_rw.getKeyEndPos(text, key, headerEndPos);
            
            if (keyEndPos < 0) {
                System.out.println("Your specified key doesn't exist in the [" + header + "] header");
            } else {
                ini_rw.setValue(text, value, keyEndPos, iniFile);
                return true;
            }
        }
        
        return false;
    }
    public static String getKeyValue(File iniFile, String header, String key) {
        
        if (iniFile == null || (!iniFile.isFile()) || header.isEmpty() || key.isEmpty())
            return "";
        
        String value = "";
        String text = ini_rw.getText(iniFile);
        int headerEndPos = ini_rw.getHeaderEndPos(text, header);
        
        if (headerEndPos < 0) {
            System.out.println("Your specified header doesn't exist :(");
        } else {
            int keyEndPos = ini_rw.getKeyEndPos(text, key, headerEndPos);
            
            if (keyEndPos < 0) {
                System.out.println("Your specified key doesn't exist in the [" + header + "] header");
            } else {
                value = ini_rw.getValue(text, keyEndPos);
            }
        }
        
        return value;
    }
    /* end */
    
    private int getHeaderEndPos(String text, String header) {
        
        final int length = text.length();
        int headerEndPos = -1;
        
        header = "[" + header + "]";
        final int headerLength = header.length();
        
        for (int i = 0; i < length; i++) {
            if (text.charAt(i) == '[') {
                int matched = 1;
                for (int j = 1; j < headerLength; j++) {
                    if (text.charAt(i + j) != header.charAt(j))
                        break;
                    matched++;
                }
                
                if (matched == headerLength) {
                    headerEndPos = i + headerLength;
                    break;
                } else {
                    i += headerLength;
                }
            }
        }
        
        return headerEndPos;
    }
    
    private int getKeyEndPos(String text, String key, int headerEndPos) {
        
        final int length = text.length();
        int keyEndPos = -1;
        
        key = key + "=";
        final int keyLength = key.length();
        
        char firstChar = key.charAt(0);
        
        for (int i = headerEndPos; i < length; i++) {
            
            char character = text.charAt(i);
            
            if (character == '[')
                break;
            
            if (character == '\n')
                continue;
            
            if (character == firstChar) {
                int matched = 1;
                for (int j = 1; j < keyLength; j++) {
                    if (text.charAt(i + j) != key.charAt(j))
                        break;
                    matched++;
                }
                
                if (matched == keyLength) {
                    keyEndPos = i + keyLength;
                    break;
                } else {}
                
                while (i < length && text.charAt(i) != '\n') {i++;}
            }
        }
        
        return keyEndPos;
    }
    
    private String getValue(String text, int keyEndPos) {
        
        String key = "";
        
        final int length = text.length();
        
        for (int i = keyEndPos; i < length; i++) {
            char character = text.charAt(i);
            if (character == '\n')
                break;
            key += character;
        }
        
        return key;
    }
    
    private void setValue(String text, String value, int keyEndPos, File file) {
        
        final int length = text.length();
        int valueEndPos = 0 + keyEndPos;
        
        for (; valueEndPos < length; valueEndPos++) {
            if (text.charAt(valueEndPos) == '\n')
                break;
        }
        
        text = replaceStringAtPos(keyEndPos, valueEndPos, text, value);
        setText(text, file);
    }
    
    private String getText(File file) {
        
        if (file == null)
            return "";
        
        String text = "";
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                text += line + '\n';
            }
            
            if (!text.isEmpty()) {
                if (text.charAt(text.length() - 1) == '\n') {
                    text = text.substring(0, text.length() - 1);
                }
            }
        } catch (Exception e) {}
        
        return text;
        
    }
    
    private boolean setText(String text, File file) {
        
        if (file == null)
            return false;
        else
            text = text.replace("\n", System.lineSeparator());
        
        try (BufferedWriter br = new BufferedWriter(new FileWriter(file))) {
            br.write(text);
            br.flush();
            br.close();
            return true;
        } catch (Exception e) {}
        
        return false;
    }
    
    private String replaceStringAtPos(int start, int end, String text, String value) {
        
        if (start < 0 || end > text.length() || start > end)
            return text;
        
        String text1 = text.substring(0, start);
        String text2 = text.substring(end, text.length());
        
        return text1 + value + text2;
    }
    
}
