using System;
using System.Collections.Generic;

namespace HW1 {
    public class SubTask2 {
        public static void SubMain(string[] args) {
            int n;
            if (args.Length != 1 || !int.TryParse(args[0], out n) || n < 0) {
                Console.WriteLine("Wrong arguments, you should give one integer non-negative number - count of Fibonacci numbers.");
                return;
            }

            decimal f0 = -1, f1 = 1;
            for(int index = 0; index < n; ++index) {
                decimal f2 = f0 + f1;
                f0 = f1;
                f1 = f2;
                Console.WriteLine(index + ": " + f2);
            }
        }
    }
}