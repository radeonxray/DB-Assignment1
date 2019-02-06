import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;

public class HashMapping {

    HashMap<String, String> writeMap = new HashMap<String, String>();
    HashMap<String, String> readContentMap = new HashMap<String, String>();

    public static void main(String[] args) throws IOException {
        HashMapping main = new HashMapping();

        main.CheckBinFileExist();

    }

    //Function to check if the data.bin-file exist, if not, a new one will be created with default data
    public void CheckBinFileExist() throws IOException {
        Path binPath = Paths.get("data.bin");
        if(Files.notExists(binPath)){
            FillMap();
            WriteFile();
            Commands();

        } else if (Files.exists(binPath)) {
            //FillMap();
            //WriteFile();
            ReadFile();
            Commands();
        }
    }

    //Fills the HashMap with key and value
    //Because properties don't like to outstream Integers, the Key's have been "faked" to Strings
    public void FillMap() {

        writeMap.put("" + 45, "Trump");
        writeMap.put("" + 44, "Obama");
        writeMap.put("" + 43, "Bush");
        writeMap.put("" + 42, "Clinton");
        writeMap.put("" + 41, "Bush Sr.");
        writeMap.put("" + 40, "Reagan");
        writeMap.put("" + 39, "Carter");
        writeMap.put("" + 38, "Ford");
        writeMap.put("" + 37, "Nixon");
        writeMap.put("" + 36, "Johnson");
        writeMap.put("" + 35, "Kennedy");
        writeMap.put("" + 34, "Eisenhower");
        writeMap.put("" + 33, "Truman");
        writeMap.put("" + 32, "Roosevelt");
        writeMap.put("" + 31, "Hoover");
        writeMap.put("" + 30, "Coolidge");

    }

    //Function for writing the data.bin file, containing the information of the HashMap
    //The file is created in the project folder named "data.bin"
    //Because properties don't like to outstream Integers, the Key's have been "faked" to Strings
    public void WriteFile() throws IOException {


        Properties props = new Properties();
        props.putAll(writeMap);
        props.putAll(readContentMap);
        System.out.println("Size of DB: " + writeMap.size());
        //System.out.println(props);

        try {
            props.store(new FileOutputStream("data.bin"), null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        props.clear();


    }


    //Function for Reading the data.bin file
    public void ReadFile() {


        Properties props = new Properties();

        try {
            props.load(new FileInputStream("data.bin"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String key : props.stringPropertyNames()) {
            readContentMap.put(key, props.get(key).toString());
            System.out.println("Key: " + key + " / Value: " + props.get(key));
        }

        props.clear();
    }

    //Function for Adding a new entry to the HashMap!
    public void SetNewEntry(String key, String value) throws IOException {

        if (readContentMap.containsKey(key)) {
            System.out.println("---------");
            System.out.println("DB already contains an index with the key of " + key);
            System.out.println("---------");
        } else if(!readContentMap.containsKey(key)){
            writeMap.put(key, value);
            WriteFile();
            ReadFile();
        }



    }

    //Function to read/retrieve a entry from the HashMap
    public void GetEntry(String keyValue) {

        if (readContentMap.containsKey(keyValue)) {
            System.out.println(readContentMap.get(keyValue).toString());

        } else {
            System.out.println("Database does not contain any data with given key!");
        }

    }


    //User input Commands
    public void Commands() {

        Scanner sc = new Scanner(System.in);
        String input = "start";

        while (input != "") {
            System.out.println("Please enter your command or write help: ");


            input = sc.nextLine();

            String[] inputs = {};

            if (!input.isEmpty()) {
                inputs = input.split(" ");

            }

            if (input.startsWith("help")) {
                System.out.println("The DB contains a partial list of US Presidents");
                System.out.println("Add to the DB!");

                System.out.println("Command Examples:");
                System.out.println("---------");
                System.out.println("Add new President");
                System.out.println("set 29 Harding");
                System.out.println("set [#President] [President LastName]");
                System.out.println("---------");
                System.out.println("Retrieve a President, based on Key");
                System.out.println("get [#President]");
                System.out.println("get 44");
                System.out.println("Value: Obama");
                System.out.println("---------");
                System.out.println("Print current DB");
                System.out.println("db print");
                System.out.println("---------");
                System.out.println("Reset current DB to the default");
                System.out.println("db reset");
                System.out.println("---------");
            }

            if (input.startsWith("get")) {

                GetEntry(inputs[1].toString());
            }

            if (input.startsWith("set")) {

                try {
                    if (readContentMap.size() <= 0) {
                        FillMap();
                        SetNewEntry(inputs[1].toString(), inputs[2].toString());
                    } else {
                        SetNewEntry(inputs[1].toString(), inputs[2].toString());
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (input.contains("db print")) {
                System.out.println("--------------------");
                 if (writeMap.size() >= 16 || readContentMap.size() >= 16){
                    ReadFile();
                } else if (readContentMap.size() <= 0) {
                    System.out.println("DB is Empty! Please enter the command 'reset db' to initialize a new DB");
                }

                System.out.println("--------------------");
            }

            if (input.contains("db reset")) {
                readContentMap.clear();
                writeMap.clear();
                FillMap();
                try {
                    WriteFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
