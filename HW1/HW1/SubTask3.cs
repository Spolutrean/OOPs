using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;

namespace HW1 {
    public class SubTask3 {
        public static void SubMain(string[] args) {
            if (args.Length != 1) {
                Console.WriteLine("Wrong arguments, you should give one string - number in Roman, not more than MMMCMXCIX (3999)");
                return;
            }

            string roman = args[0];

            char[] alpha = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
            if (!roman.All(c => alpha.Contains(c))) {
                Console.WriteLine("You can use only I = 1, V = 5, X = 10, L = 50, C = 100, D = 500, M = 1000");
                return;
            }
            
            Regex regex = new Regex("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");
            if (!regex.IsMatch(roman)) {
                Console.WriteLine(roman + " is wrong Roman expression.");
                return;
            }

            List<Tuple<string, int>> fullAlpha = new List<Tuple<string, int>> {
                new Tuple<string, int>("M", 1000),
                new Tuple<string, int>("CM", 900),
                new Tuple<string, int>("D", 500),
                new Tuple<string, int>("CD", 400),
                new Tuple<string, int>("C", 100),
                new Tuple<string, int>("XC", 90),
                new Tuple<string, int>("L", 50),
                new Tuple<string, int>("XL", 40),
                new Tuple<string, int>("X", 10),
                new Tuple<string, int>("IX", 9),
                new Tuple<string, int>("V", 5),
                new Tuple<string, int>("IV", 4),
                new Tuple<string, int>("I", 1),
            }; 
            
            int result = 0;
            for (int i = 0; i < roman.Length;) {
                foreach (var symbol in fullAlpha) {
                    string symbolString = symbol.Item1;
                    int symbolValue = symbol.Item2;
                    if (i + symbolString.Length <= roman.Length  
                        && roman.Substring(i, symbolString.Length) == symbolString) {
                        result += symbolValue;
                        i += symbolString.Length;
                        break;
                    }
                }
            }
            Console.WriteLine(result);
        }
    }
}