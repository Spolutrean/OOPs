using System;
using System.IO;
using System.Linq;
using System.Net;
using System.Text.RegularExpressions;

namespace HW1 {
    public class SubTask3Extra {
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
            
            string html, url = @"https://www.tools4noobs.com/?action=ajax_roman_decimal&m=1&number=" + roman;

            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);
            request.AutomaticDecompression = DecompressionMethods.GZip;

            using (HttpWebResponse response = (HttpWebResponse)request.GetResponse())
            using (Stream stream = response.GetResponseStream())
            using (StreamReader reader = new StreamReader(stream))
            {
                html = reader.ReadToEnd();
            }

            Console.WriteLine(html.Split('>')[1].Split('<')[0]);
        }
    }
}