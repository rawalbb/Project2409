import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Project2 {
    public static void main(String[] args) throws IOException {
        List<Person> allData = readData();
        double alpha = 0.3;

        hardActivation(0.75, alpha, allData);
//        softActivation(0.75, alpha, allData);
//        hardActivation(0.25, alpha, allData);
//        softActivation(0.25, alpha, allData);
    }

    private static List<Person> readData() throws IOException {
        ArrayList<Person> allDataPoints = new ArrayList<>();
        File dataFile = new File("data.csv");
        Scanner scanFile = new Scanner(dataFile);

        while (scanFile.hasNextLine()) {
            String dataPoint = scanFile.nextLine();
            String[] data = dataPoint.split(",");
            allDataPoints.add(new Person(Double.parseDouble(data[0]), Double.parseDouble(data[1]),
                    Integer.parseInt(data[2].trim())));
        }
        scanFile.close();
        return allDataPoints;
    }

    private static void hardActivation(double trainingData, double learning, List<Person> list) {

        double net;
        double weightMultiplier;
        double[] weights = {1,2,1};
        double[] weightChange = new double[3];
        double error = 10;

        int count = 0;//todo delete


        while (error > 0.00005 | count!=10) {
            for (int i = 0; i < (list.size() * trainingData); i++) {
                int out;
                net = ((list.get(i).getHeight() * weights[0]) + (list.get(i).getHeight() * weights[1]) + (list.get(i).getGender() * weights[2]));

                if (net > 0) {
                    out = 1;
                } else {
                    out = 0;
                }

                weightMultiplier = learning * (list.get(i).getGender() - out);
                weightChange[0] = list.get(i).getHeight() * weightMultiplier;
                weightChange[1] = list.get(i).getWeight() * weightMultiplier;
                weightChange[2] = list.get(i).getGender() * weightMultiplier;
                error =  errorCalculation(list, trainingData, weights);

                for (int j = 0; j < weights.length; j++) {
                    weights[j] = weights[j] - weightChange[j];
                }
            }
            count++;
        }

    }

    public static void softActivation(double trainingData, double learning, List<Person> list) {

        double net = 0.0;
        double weightMultiplier;
        double[] weights = {1,2,0};
        double[] weightChange = new double[3];
        double error = 0;

        while (error > 0.00005) {
            for (int i = 0; i < (list.size() * trainingData); i++) {
                int out;
                net = ((list.get(i).getHeight() * weights[0]) + (list.get(i).getHeight() * weights[1])
                        + (list.get(i).getGender() * weights[2]));
                
                out = (int) (1 / ( 1 + Math.exp(net * -1)));
                weightMultiplier = learning * (list.get(i).getGender() - out);
                weightChange[0] = list.get(i).getHeight() * weightMultiplier;
                weightChange[1] = list.get(i).getWeight() * weightMultiplier;
                weightChange[2] = list.get(i).getGender() * weightMultiplier;


                for (int j = 0; j < weights.length; j++) {
                    weights[j] = weights[j] - weightChange[j];
                }

            }
        }
    }

    private static double errorCalculation(List<Person> list, double dataSize, double[] weights){
        double error;
        int out;
        double correctCount = 0;
        double incorrectCount = 0;

//        for(int i = 10; i<20;     //list.size();
//            i++ ) {
//
//            double net = ((list.get(i).getHeight()  * weights[0]) + (list.get(i).getHeight() * weights[1])
//                    + (list.get(i).getGender() * weights[2]));
//
//            if (net > 0) {
//                out = 1;
//            } else {
//                out = 0;
//            }
//
//            //if (list.get(i).getGender() == Math.round(out)) {
//            //numMenCorrect++;
//                error += Math.pow(list.get(i).getGender() - out, 2);
//           // }
//        }



        for (int i = (int) (list.size()*dataSize); i<list.size(); i++){

            double net = ((list.get(i).getHeight()  * weights[0]) + (list.get(i).getHeight() * weights[1])
                    + (list.get(i).getGender() * weights[2]));

            if (net > 0) {
                out = 1;
            } else {
                out = 0;
            }

            if(list.get(i).getGender() == Math.round(out)){
                correctCount++;
            }
            else { incorrectCount++; }
        }

        error = (correctCount - incorrectCount) / list.size();
        System.out.println("Error for weight: "+error);

        return error;
    }
}

class Person {

    public double height;
    public double weight;
    public int gender;

    Person(double height, double weight, int gender) {
        this.height = height;
        this.weight = weight;
        this.gender = gender;
    }

    double getHeight() {
        return this.height;
    }

    double getWeight() {
        return this.weight;
    }

    int getGender() {
        return this.gender;
    }
}
