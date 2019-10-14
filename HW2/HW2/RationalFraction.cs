using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HW2 {
    class RationalFraction : IComparable<RationalFraction> {
        private int numerator, denominator;

        public RationalFraction(RationalFraction fraction) 
            : this(fraction.numerator, fraction.denominator){

        }
        public RationalFraction(string serialized) {
            if (serialized == "0") {
                this.numerator = 0;
                this.denominator = 1;
            }
            else {
                this.numerator = Int32.Parse(serialized.Split('/')[0]);
                this.denominator = Int32.Parse(serialized.Split('/')[1]);
            }
            
        }
        public RationalFraction(int numerator, int denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
            normalize();
        }
        public RationalFraction(int natural) : this(natural, 1) {
        }

        
        private void normalize() {
            if (denominator == 0) {
                throw new DivideByZeroException();
            }

            if (denominator < 0) {
                numerator = -numerator;
                denominator = -denominator;
            }

            simplify();
        }
        public void simplify() {
            int g = MathFunctions.gcd(Math.Abs(numerator), Math.Abs(denominator));
            numerator /= g;
            denominator /= g;
        }
        public static void toCommonDenominator(ref RationalFraction r1, ref RationalFraction r2) {
            int l = MathFunctions.lcm(Math.Abs(r1.denominator), Math.Abs(r2.denominator));
            r1 *= l / r1.denominator;
            r2 *= l / r2.denominator;
        }

        public char getSign() {
            return Math.Sign(numerator) == -1 ? '-' : '+';
        }

        public RationalFraction abs() {
            return new RationalFraction(Math.Abs(numerator), denominator);
        }


        public static bool operator >(RationalFraction r1, RationalFraction r2) {
            return fractionComparator(r1, r2, (a, b) => a > b);
        }

        public static bool operator <(RationalFraction r1, RationalFraction r2) {
            return fractionComparator(r1, r2, (a, b) => a < b);
        }
        private static bool fractionComparator(RationalFraction r1, RationalFraction r2, Func<long, long, bool> f) {
            return f(1L * r1.numerator * r2.denominator, 1L * r2.numerator * r1.denominator);
        }


        public static RationalFraction operator +(RationalFraction r1, RationalFraction r2) {
            RationalFraction r1Copy = new RationalFraction(r1),
                r2Copy = new RationalFraction(r2);
            toCommonDenominator(ref r1Copy, ref r2Copy);
            RationalFraction result = new RationalFraction(r1Copy.numerator + r2Copy.numerator, r1Copy.denominator);
            result.simplify();
            return result;
        }

        public static RationalFraction operator *(RationalFraction r, int p) {
            r.numerator *= p;
            if (p != 0) {
                r.denominator *= p;
            }

            return r;
        }

        
        public override string ToString() {
            if (numerator == 0) {
                return "0";
            }
            
            return numerator + "/" + denominator;
        }

        public int CompareTo(RationalFraction obj) {
            if (this > obj) {
                return 1;
            }

            if (this < obj) {
                return -1;
            }

            return 0;
        }
        public override bool Equals(object obj) {
            if (obj == null) {
                return false;
            }

            return fractionComparator(this, (RationalFraction) obj, (a, b) => a == b);
        }
    }
}