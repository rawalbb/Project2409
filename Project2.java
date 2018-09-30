import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Project2 {
    public static void main(String[] args) throws IOException {
    	ArrayList <Person> arr = new ArrayList <Person>();
		double a = 0.3;
        readData();
        hardActivation(0.75, a, arr);
        softActivation(0.75, a, arr);
        hardActivation(0.25, a, arr);
        softActivation(0.25, a, arr);
    }

    public static void readData() throws IOException {
        ArrayList<Person> allDataPoints= new ArrayList<>();
        File dataFile = new File("data.csv");
        Scanner scanFile = new Scanner(dataFile);

        while(scanFile.hasNextLine()) {
            String dataPoint = scanFile.nextLine();
            String[] data = dataPoint.split(",");
            allDataPoints.add(new Person(Double.parseDouble(data[0]), Double.parseDouble(data[1]), Integer.parseInt(data[2].trim())));
        }
    }

    public static void softActivation(double trainingData, double learning, ArrayList<Person> list) {
    	
		double net = 0.0;
		double weightMultiplier = 0.0;
		double[] weights = new double[3];
		double[] weightChange = new double[3];
		for(int i =0;i<((list.size())*trainingData); i++)
		{
			int out;
			net= ((list.get(i).getHeight()*weights[0]) + (list.get(i).getHeight()*weights[1]) + (list.get(i).getGender()*weights[2]));
			if (net > 0)
			{
				out = 1;
			}
			else
			{
				out = 0;
			}
			weightMultiplier = learning * (list.get(i).getGender() - out);
			weightChange[0] = list.get(i).getHeight() * weightMultiplier;
			weightChange[1] = list.get(i).getWeight() * weightMultiplier;
			weightChange[2] = list.get(i).getGender() * weightMultiplier;
			
			for(int j=0; j<weights.length; j++)
			{
				weights[j] = weights[j]-weightChange[j];
			}
		}

    }

    public static void hardActivation(double trainingData, double learning, ArrayList<Person> list) {

    }
}

class Person {

    private double height;
    private double weight;
    private int gender;

    public Person(double height, double weight, int gender) {
        this.height = height;
        this.weight = weight;
        this.gender = gender;
    }

    public double getHeight() {
        return this.height;
    }

    public double getWeight() {
        return this.weight;
    }

    public int getGender() {
        return this.gender;
    }
}
