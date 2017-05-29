import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by shwetabarapatre on 29/05/17.
 */
public class Algorithm {
    public static ArrayList<Instance> testing;
    public static ArrayList<Instance> training;

    public static ArrayList<Boolean> classified = new ArrayList<Boolean>();

    public static Utils utils = new Utils();

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        File trainingFile = new File(args[0]);
        File testingFile = new File(args[1]);
        training = utils.readData(trainingFile, true);
        testing = utils.readData(testingFile, false);

        Classifier classifier = new Classifier(training);

        int count = 1;
        for(Instance i : testing) {
            System.out.println("Classifying email " +count+ "...");
            boolean spam = classifier.classify(i);
            classified.add(spam);
            count++;
            System.out.println("----------");
        }
        utils.makeOutputFile(testingFile, classified);




    }
}
