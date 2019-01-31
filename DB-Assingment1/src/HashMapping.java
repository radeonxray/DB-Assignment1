import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class HashMapping {

    HashMap<String, String > theMap = new HashMap<String, String>();

    public static void main(String[] args) throws IOException {
        HashMapping main = new HashMapping();

        main.FillMap();
        main.WriteFile();
        main.ReadFile();

    }

    //Fills the HashMap with key and value
    //Because properties don't like to outstream Integers, the Key's have been "faked" to Strings
    public void FillMap(){

        theMap.put(""+45, "Trump");
        theMap.put(""+44, "Obama");
        theMap.put(""+43, "Bush");
        theMap.put(""+42, "Clinton");
        theMap.put(""+41, "Bush Sr.");
        theMap.put(""+40, "Reagan");
        theMap.put(""+39, "Carter");
        theMap.put(""+38, "Ford");
        theMap.put(""+37, "Nixon");
        theMap.put(""+36, "Johnson");
        theMap.put(""+35, "Kennedy");
        theMap.put(""+34, "Eisenhower");
        theMap.put(""+33, "Truman");
        theMap.put(""+32, "Roosevelt");
        theMap.put(""+31, "Hoover");
        theMap.put(""+30, "Coolidge");

    }

    //Function for writing the data.bin file, containing the information of the HashMap
    //The file is created in the project folder named "data.bin"
    //Because properties don't like to outstream Integers, the Key's have been "faked" to Strings
    public void WriteFile() throws IOException{

      Properties props = new Properties();
      props.putAll(theMap);
      //System.out.println(props);

        try{
            props.store(new FileOutputStream("data.bin"),null);
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    //Function for Reading the data.bin file
    public void ReadFile(){

        HashMap<String, String> mapContent = new HashMap<String,String>();

        Properties props = new Properties();

        try{
            props.load(new FileInputStream("data.bin"));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String key: props.stringPropertyNames()){
            mapContent.put(key, props.get(key).toString());
            System.out.println("Key: " + key + " / Value: " + props.get(key));
        }
    }


}
