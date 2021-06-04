import java.util.Scanner;
import java.util.Random;

public class whatPercentForAccurate {
    public static void main(String[] args) {
        Random RNG = new Random();
        Scanner scan = new Scanner(System.in);

        System.out.println("Insert population size:");
        int popSize = scan.nextInt();
        System.out.println("Insert the percentage of people that believe it to be true, without the % symbol:");
        double popTrue = scan.nextDouble();
        System.out.println("How many times do you want each sample size to be tested? The higher the number, the more accurate the final result:");
        int repeat = scan.nextInt();
        System.out.println("What's the maximum the sample percentage is allowed to differ from the real percentage? Write in percentages:");
        final double difference = scan.nextDouble();
        scan.close();

        boolean[] opinions = new boolean[popSize];
        for(int i = 0; i<popSize; i++)
        {
            if(i < (popSize*popTrue/100))
            {
                opinions[i] = true;
                continue;
            }
            opinions[i] = false;
        }

        int sampleSum = 0;
        for(int h=0; h<100;h++) //gives us 100 results
        {
            double realPercent = popTrue/100;
            int sampleSize = 1;
            boolean accurate = false;
            while(accurate == false)
            {
                accurate = true;
                double[] samplePercent = new double[repeat]; //From here
                for(int i = 0; i<repeat; i++) //This loop starts a single percentages calculation
                {
                    int sampleTrue = 0;
                    for(int j = 0; j<sampleSize;j++)
                    {
                        int currentPerson = RNG.nextInt(popSize);
                        if(opinions[currentPerson] == true)
                        {
                            sampleTrue++;
                        }
                    }
                    samplePercent[i] = (double) sampleTrue/sampleSize; //To here: Get a "repeat" amount of percentages
                }


                double sum = 0; //From here
                for(int i = 0; i < repeat; i++)
                {
                    sum += samplePercent[i];
                }
                double average = sum/repeat; //To here: Get the average percentage 

                //int howmanyfailures = 0;
                if(Math.abs(realPercent-average) > difference/100)
                {
                    sampleSize+=50;
                    accurate = false;
                }
                else
                {
                    /*for(int i = 0; i<repeat; i++) //From here
                    {
                        if(Math.abs(samplePercent[i]-average) > difference/100)
                        {
                            howmanyfailures++;
                        }
                    }*/
                    //System.out.print(howmanyfailures + " ");

                    for(int i = 0; i<repeat; i++) //From here
                    {
                        if(Math.abs(samplePercent[i]-average) > difference/100)
                        {
                            sampleSize+=50;
                            accurate = false;
                            break;
                        }
                    } //To here: Check if any of the calculated percentages differ from the average more than entered amount
                }
                //System.out.println(sampleSize);
            }
            sampleSum += sampleSize;
        }
        System.out.print("The average minimum amount of people to include in your sample is:"); System.out.println(sampleSum/100);
    }
}
