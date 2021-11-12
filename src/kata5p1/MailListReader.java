package kata5p1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MailListReader {

    public List<String> read(String fileName){
        List<String> res = new ArrayList<String>();
        try{
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();
            String line;
            while((line=br.readLine()) != null){
                String[] emailParts = line.split("@");
                if(emailParts.length == 2 && !emailParts[1].equals("")){
                    res.add(line);
                }
            }
            fr.close();
        }
        catch(IOException e){}
        return res;
        }
}