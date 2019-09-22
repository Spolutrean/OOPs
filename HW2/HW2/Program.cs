using System.Collections.Generic;
using System.Diagnostics;

namespace HW2 {
    internal class Program {
        public static void Main(string[] args) {
            RationalFraction a = new RationalFraction(2, 3);
            RationalFraction a2 = new RationalFraction(-2, -3);
            RationalFraction b = new RationalFraction(0, 3);
            RationalFraction d = new RationalFraction(-3, -5);
            RationalFraction aa = new RationalFraction(2, -3);
            RationalFraction bb = new RationalFraction(0, -3);
            RationalFraction dd = new RationalFraction(3, -5);
            
            Debug.Assert((a + dd).Equals(new RationalFraction(1, 15)));

            FractionSequence fa = new FractionSequence();
            Debug.Assert(fa.getCount() == 0);
            Debug.Assert(fa.getLowerCount(new RationalFraction(1, 2)) == 0);
            Debug.Assert(fa.getUpperCount(new RationalFraction(1, 2)) == 0);

            fa.addElement(a);
            fa.addElement(a2);
            Debug.Assert(fa.getMin().Equals(a) && fa.getMin().Equals(a2));
            Debug.Assert(fa.getMax().Equals(a) && fa.getMax().Equals(a2));
            Debug.Assert(fa.getCount() == 2);
            Debug.Assert(fa.getLowerCount(new RationalFraction(1,2)) == 0);
            Debug.Assert(fa.getUpperCount(new RationalFraction(1, 2)) == 2);

            fa.addElement(d);
            fa.addElement(dd);
            Debug.Assert(fa.getMin().Equals(dd));
            Debug.Assert(fa.getMax().Equals(d));
            Debug.Assert(fa.getCount() == 4);
            Debug.Assert(fa.getLowerCount(new RationalFraction(1, 2)) == 1);
            Debug.Assert(fa.getUpperCount(new RationalFraction(1, 2)) == 3);

            fa.addElement(b);
            fa.addElement(bb);
            Debug.Assert(fa.getMin().Equals(dd));
            Debug.Assert(fa.getMax().Equals(a));
            Debug.Assert(fa.getCount() == 6);
            Debug.Assert(fa.getLowerCount(new RationalFraction(1, 2)) == 3);
            Debug.Assert(fa.getUpperCount(new RationalFraction(1, 2)) == 3);


            FractionSequence fb = new FractionSequence(5);
            Debug.Assert(fb.getMin().Equals(new RationalFraction(-5, 7)));
            Debug.Assert(fb.getMax().Equals(new RationalFraction(2, 3)));
            Debug.Assert(fb.getCount() == 5);
            Debug.Assert(fb.getLowerCount(new RationalFraction(1, 2)) == 4);
            Debug.Assert(fb.getUpperCount(new RationalFraction(1, 2)) == 1);

            //2/3 * x^5 + 2/3 * x^4 + 3/5 * x^3 - 3/5 * x^2 + 0 * x^1 + 0
            Polynomial pa = new Polynomial(fa);
            //0 * x^5 + 1/4 * x^4 + 1/5 * x^3 - 2/7 * x^2 - 5/7 * x^1 + 2/3
            Polynomial pb = new Polynomial(fb);
            //2/3 * x^5 + 11/12 * x^4 + 4/5 * x^3 - 31/35 * x^2 - 5/7 * x^1 + 2/3
            Polynomial pab = pa + pb;
            Debug.Assert(pab.Equals(new Polynomial(new FractionSequence(new List<RationalFraction>() {
                new RationalFraction(2,3),
                new RationalFraction(11, 12),
                new RationalFraction(4, 5),
                new RationalFraction(-31, 35),
                new RationalFraction(-5, 7),
                new RationalFraction(2, 3)
            }))));
        }
    }
}