using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;

namespace HW2 {
    class Polynomial {
        private List<RationalFraction> coefficients;

        public Polynomial(FractionSequence sequence) {
            this.coefficients = new List<RationalFraction>(sequence.getSequence());
        }

        public Polynomial(Polynomial polynomial) {
            this.coefficients = new List<RationalFraction>(polynomial.coefficients);
        }

        public static Polynomial operator +(Polynomial p1, Polynomial p2) {
            Polynomial p1Copy = new Polynomial(p1),
                p2Copy = new Polynomial(p2);
            
            FractionSequence resultSequence = new FractionSequence();

            int delta = p1Copy.coefficients.Count - p2Copy.coefficients.Count;

            if (delta < 0) {
                (p1Copy, p2Copy) = (p2Copy, p1Copy);
                delta = -delta;
            }

            p2Copy.coefficients.Reverse();
            for (int i = 0; i < delta; ++i) {
                p2Copy.coefficients.Add(new RationalFraction(0));
            }
            p2Copy.coefficients.Reverse();

            var e1 = p1Copy.coefficients.GetEnumerator();
            var e2 = p2Copy.coefficients.GetEnumerator();
            while (e1.MoveNext() && e2.MoveNext()) {
                RationalFraction e = e1.Current + e2.Current;
                resultSequence.addElement(e);
            }

            return new Polynomial(resultSequence);
        }

        public int getMaxPower() {
            return coefficients.Count;
        }
        
        public override string ToString() {
            StringBuilder sb = new StringBuilder();
            for (int power = coefficients.Count - 1, i = 0; power >= 0; --power, ++i) {
                if (coefficients[i].Equals(new RationalFraction(0))) {
                    continue;
                }
                
                char sign = coefficients[i].getSign();
                if (sign == '-') {
                    sb.Append(sign);
                }
                else if(i != 0) {
                    sb.Append(sign);
                }
                
                sb.Append(" " + coefficients[i].abs());
                if (power >= 1) {
                    sb.Append("*x");
                }

                if (power >= 2) {
                    sb.Append("^" + power);
                }

                sb.Append(" ");
            }

            string result = sb.ToString();
            if (result.Length == 0) {
                result = "0";
            }
            return result;
        }

        public override bool Equals(object obj) {
            if (obj == null) {
                return false;
            }
            
            Polynomial objPolynomial = (Polynomial) obj;
            
            if (objPolynomial.getMaxPower() != getMaxPower()) {
                return false;
            }
            
            bool ok = true;
            int power = getMaxPower();
            for (int i = 0; i < power; ++i) {
                ok &= coefficients[i].Equals(objPolynomial.coefficients[i]);
            }

            return ok;
        }
    }
}