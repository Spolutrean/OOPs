namespace HW1 {
    public static class MathFunctions {
        public static int gcd(int x, int y) {
            while (y != 0) {
                int tmp = x % y;
                x = y;
                y = tmp;
            }

            return x;
        }

        public static int lcm(int x, int y) {
            return x * y / gcd(x, y);
        }
    }
}