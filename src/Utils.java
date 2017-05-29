import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by shwetabarapatre on 29/05/17.
 */
public class Utils {

    public ArrayList<Instance> readData(File file, boolean training) throws FileNotFoundException {
        ArrayList<Instance> list = new ArrayList<Instance>();
        if (training){
            try {
                Scanner scData = new Scanner(file);

                while (scData.hasNextLine()) {
                    ArrayList<Integer> currentLine = new ArrayList<Integer>();
                    for (int i = 0; i < 13; i++) {
                        currentLine.add(scData.nextInt());
                    }
                    scData.nextLine();
                    list.add(new Instance(currentLine));
                }
                scData.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("Training Data LOADED");
        }
        else{
            try {
                Scanner scData = new Scanner(file);

                while(scData.hasNextLine()) {
                    ArrayList<Integer> currLine = new ArrayList<Integer>();
                    for(int i=0; i < 12; i++) {
                        currLine.add(scData.nextInt());
                    }
                    scData.nextLine();
                    list.add(new Instance(currLine));
                }
                scData.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("Testing Data LOADED\n");

        }
        return list;
    }

    public static void makeOutputFile(File file, ArrayList<Boolean> classified) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("sampleoutput.txt", "UTF-8");
        Scanner scan = new Scanner(file);
        int count = 0;
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
                    if(classified.get(count)==true){
                        line = line + ("     "+1);
                    }
                    else
                        line = line + ("     "+0);
            writer.println(line);

            count++;
        }
        writer.flush();
        writer.close();
        scan.close();
        System.out.println("Output file created");
    }
}
