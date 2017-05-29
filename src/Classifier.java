import java.util.ArrayList;

/**
 * Created by shwetabarapatre on 29/05/17.
 */

/*
	 * Naives Bayes -> P(Class|Data) = P(Data|Class) X P(C)
	 * With the data -> P(C|f1,f2,f3,...,f12) = P(f1|C) X P(f2|C) X P(f3|C) X ... X P(f4|C) X P(C)
	 * f1 to f12 -> features of the instance from the data file (1 or 0)
	 * C -> The class -> spam or not spam -> 1 or 0
	 * Don't have a denominator because it is always the same
	 *
	 * Check the probability that the instance is spam
	 * Check the probability that the instance is not spam
	 * Which ever number is bigger is the result
	 */

public class Classifier {
    ArrayList<Instance> instances;
    final int TOTAL_NUM_INSTANCES = 200;

    public Classifier(ArrayList<Instance> instances) {
        this.instances = instances;
    }

    public boolean classify(Instance testInstance) {
        double trueProbability = 1.0;
        double falseProbability = 1.0;

        ArrayList<Instance> trueList = new ArrayList<Instance>();
        ArrayList<Instance> falseList = new ArrayList<Instance>();

        for(Instance trainingInstance : instances){
            if(trainingInstance.get(12) == 1){
                trueList.add(trainingInstance);
            }
            else {
                falseList.add(trainingInstance);
            }
        }
        trueProbability = (double) trueList.size() / TOTAL_NUM_INSTANCES;
        falseProbability = 1-trueProbability;

        ArrayList <Double> totalProbabilities = calculateProbabilities(instances);
        ArrayList <Double> trueProbabilities = calculateProbabilities(trueList);
        ArrayList <Double> falseProbabilities = calculateProbabilities(falseList);

        double naiveBayesDenominator = 1.0;

        for (int i = 0; i < testInstance.features.size(); i++){
            if(testInstance.get(i) == 1) {
                System.out.println(testInstance.get(i)+"test instance feature in true if");
                trueProbability = trueProbability * trueProbabilities.get(i);
                falseProbability = falseProbability * falseProbabilities.get(i);
                naiveBayesDenominator = naiveBayesDenominator * totalProbabilities.get(i);
            }
            else {
                System.out.println(testInstance.get(i)+"test instance feature in false if");
                trueProbability = trueProbability * (1.0 - trueProbabilities.get(i));
                falseProbability = falseProbability * (1.0 - falseProbabilities.get(i));
                naiveBayesDenominator = naiveBayesDenominator * (1.0 - totalProbabilities.get(i));
            }

        }
        System.out.println(trueProbability + "true prob");
        System.out.println(trueProbability + "false prob");


        trueProbability = trueProbability/naiveBayesDenominator;
        falseProbability = falseProbability/naiveBayesDenominator;
        System.out.println(trueProbability + "true prob");
        System.out.println(trueProbability + "false prob");

        return trueProbability > falseProbability;
    }

    private ArrayList<Double> calculateProbabilities(ArrayList<Instance> listOfInstances){
        ArrayList<Double> tempProbability = new ArrayList<Double>();
        int[] countOfFeature = new int[12];

        for(Instance i : listOfInstances){
            for(int j = 0 ;j < 12; j++ ){
                countOfFeature[j] += i.get(j);
            }
        }

        for(int i = 0; i < countOfFeature.length; i++){
            double value = (double)countOfFeature[i] / (double) listOfInstances.size();

            tempProbability.add(value);

        }

        return tempProbability;
    }
}
