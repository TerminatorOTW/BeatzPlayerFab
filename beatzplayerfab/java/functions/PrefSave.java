
// Developed by the TerminatorOTW

package beatzplayerfab.java.functions;

import beatzplayerfab.java.api.File_rw;
import beatzplayerfab.java.api.INI_rw;
import java.io.File;
import java.io.IOException;

public class PrefSave {
    
    private final File mainFolder = new File(System.getProperty("user.home") + File.separator + ".beatzPlayer");
    private final File menuDataFile = new File(mainFolder.getPath() + File.separator + "menuData.ini");
    private final File sceneDataFile = new File(mainFolder.getPath() + File.separator + "sceneData.ini");
    private final File docsDataFile = new File(mainFolder.getPath() + File.separator + "docsData.ini");
    private final File savedFileData = new File(mainFolder.getPath() + File.separator + "savedFileData.ini");
    
    /**
     * Main methods for getting and setting,
     * preference values
     * @param fileName
     * @param header
     * @param key
     * @return value
     */
    public String getSavedValue(String fileName, String header, String key) {
        
        String value = "";
        
        if (checkSaveData()) {
            switch(fileName) {
                case "menuData":
                    value = INI_rw.getKeyValue(menuDataFile, header, key);
                    break;
                case "sceneData":
                    value = INI_rw.getKeyValue(sceneDataFile, header, key);
                    break;
                case "docsData":
                    value = INI_rw.getKeyValue(docsDataFile, header, key);
                    break;
                case "savedFileData":
                    value = INI_rw.getKeyValue(savedFileData, header, key);
                default:
                    break;
            }
        }
        
        return value;
    }
    public void setSavedValue(String fileName, String header, String key, String value) {
        
        if (checkSaveData()) {
            switch(fileName) {
                case "menuData":
                    INI_rw.setKeyValue(menuDataFile, header, key, value);
                    break;
                case "sceneData":
                    INI_rw.setKeyValue(sceneDataFile, header, key, value);
                    break;
                case "docsData":
                    INI_rw.setKeyValue(docsDataFile, header, key, value);
                    break;
                case "savedFileData":
                    INI_rw.setKeyValue(savedFileData, header, key, value);
                default:
                    break;
            }
        }
    }
    /* end */
    
    private boolean checkSaveData() {
        
        if (!mainFolder.isDirectory()) {
            mainFolder.mkdir();
        }
        
        if (mainFolder.isDirectory()) {
            if (!menuDataFile.isFile()) {
                createNewFile(menuDataFile);
                defaultSaveData("menuData");
            }

            if (!sceneDataFile.isFile()) {
                createNewFile(sceneDataFile);
                defaultSaveData("sceneData");
            }

            if (!docsDataFile.isFile()) {
                createNewFile(docsDataFile);
                defaultSaveData("docsData");
            }
            
            if (!savedFileData.isFile()) {
                createNewFile(savedFileData);
                defaultSaveData("savedFileData");
            }
        } else {}
        
        if (menuDataFile.isFile() && sceneDataFile.isFile() && docsDataFile.isFile() && savedFileData.isFile())
            return true;
        
        return false;
    }
    
    private void defaultSaveData(String fileName) {
        
        String data = "";
        File file = null;
        
        switch(fileName) {
            case "menuData":
                data = "" +
                    "\n[View]\n" +
                    "aot=false"
                    ;
                file = menuDataFile;
                break;
            case "sceneData":
                data = "" +
                    ""
                    ;
                file = sceneDataFile;
                break;
            case "docsData":
                data = "" +
                    "\n[File]\n" +
                    "lastDir=" + System.getProperty("user.home")
                    ;
                file = docsDataFile;
                break;
            case "savedFileData":
                data = "" +
                    "";
                file = savedFileData;
            default:
                break;
        }
        
        if (file != null && !data.isEmpty() && file.isFile()) {
            new File_rw().setText(data, file, System.lineSeparator());
        }
        
    }
    
    private void createNewFile(File file) {
        try {
            file.createNewFile();
        } catch (IOException ex) {}
    }
    
}