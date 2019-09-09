using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HW1 {
    class FractionSequence {
        private List<RationalFraction> sequence;
        private RationalFraction cachedMax, cachedMin;

        private Dictionary<RationalFraction, int> lowerCache;
        private Dictionary<RationalFraction, int> upperCache;

        private void init() {
            sequence = new List<RationalFraction>();
            lowerCache = new Dictionary<RationalFraction, int>();
            upperCache = new Dictionary<RationalFraction, int>();
            cachedMax = null;
            cachedMin = null;
        }

        public FractionSequence() {
            init();
        }

        public FractionSequence(List<RationalFraction> sequence) {
            init();
            this.sequence = new List<RationalFraction>(sequence);
        }

        public FractionSequence(int randomFractionsCount, int seed = 100500) {
            init();

            if (randomFractionsCount < 1) {
                throw new ArgumentOutOfRangeException("randomFractionsCount should be positive");
            }

            Random r = new Random(seed);
            for (int i = 0; i < randomFractionsCount; ++i) {
                addElement(new RationalFraction(r.Next(-20, 20), r.Next(1, 20) * (r.Next() % 2 == 0 ? 1 : -1)));
            }
        }

        public FractionSequence(string filePath) {
            init();
            if (!File.Exists(filePath)) {
                throw new FileNotFoundException();
            }

            string[] lines = File.ReadAllLines(filePath);
            foreach (string line in lines) {
                sequence.Add(new RationalFraction(line));
            }
        }


        public void addElement(RationalFraction element) {
            sequence.Add(element);
            clearCache();
        }

        public RationalFraction getMax() {
            if (sequence.Count == 0) {
                throw new Exception("Sequence is empty");
            }

            if (cachedMax != null) {
                return cachedMax;
            }

            cachedMax = sequence.Max();
            return cachedMax;
        }

        public RationalFraction getMin() {
            if (sequence.Count == 0) {
                throw new Exception("Sequence is empty");
            }

            if (cachedMin != null) {
                return cachedMin;
            }

            cachedMin = sequence.Min();
            return cachedMin;
        }

        public int getUpperCount(RationalFraction x) {
            if (upperCache.ContainsKey(x)) {
                return upperCache[x];
            }

            int count = sequence.Count(current => (current > x));
            upperCache[x] = count;
            return count;
        }

        public int getLowerCount(RationalFraction x) {
            if (lowerCache.ContainsKey(x)) {
                return lowerCache[x];
            }

            int count = sequence.Count(current => (current < x));
            lowerCache[x] = count;
            return count;
        }

        public int getCount() {
            return sequence.Count();
        }

        private void clearCache() {
            cachedMax = null;
            cachedMin = null;
            lowerCache.Clear();
            upperCache.Clear();
        }

        public List<RationalFraction> getSequence() {
            return sequence;
        }
    }
}