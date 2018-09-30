import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Project2 {
    public static void main(String[] args) throws IOException {
        readData();
        hardActivation(0.75);
        softActivation(0.75);
        hardActivation(0.25);
        softActivation(0.25);
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

    public static void softActivation(double trainingData) {

    }

    public static void hardActivation(double trainingData) {

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
