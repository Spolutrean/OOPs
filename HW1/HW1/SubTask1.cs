using System;
using System.IO;

namespace HW1 {
    public class SubTask1 {
        public static void SubMain() {
            string inputFilePath = "input.txt";
            string outputFilePath = "output.txt";
            
            if (!File.Exists(inputFilePath)) {
                Console.WriteLine("Input file " + inputFilePath + " doesn't exists");
                return;
            }
            
            double sum = 0;
            try {
                using (StreamReader reader = new StreamReader(inputFilePath)) {
                    String line;
                    while ((line = reader.ReadLine()) != null) {
                        string[] numbers = line.Split(' ');
                        foreach (var number in numbers) {
                            double res;
                            if (!Double.TryParse(number, out res)) {
                                Console.WriteLine("Cannot parse " + number + " to double type.");
                                return;
                            }
                            
                            sum += res;
                        }
                    }
                }
            }
            catch (FileNotFoundException e) {
                Console.WriteLine("Input file " + inputFilePath + " doesn't exists");
                return;
            }
            catch (UnauthorizedAccessException e) {
                Console.WriteLine("Cannot get access to " + inputFilePath);
                return;
            }
            
            
            if (!File.Exists(outputFilePath)) {
                Console.WriteLine("Output file " + outputFilePath + " doesn't exists");
                return;
            }

            try {
                using (StreamWriter writer = new StreamWriter(outputFilePath)) {
                    writer.WriteLine(sum.ToString());
                }
            }
            catch (FileNotFoundException e) {
                Console.WriteLine("Output file " + outputFilePath + " doesn't exists");
                return;
            }
            catch (UnauthorizedAccessException e) {
                Console.WriteLine("Cannot access to " + outputFilePath);
                return;
            }
        }
    }
}