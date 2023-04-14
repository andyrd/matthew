public class Program
{
    public static void Main(string[] args)
    {
        Console.WriteLine("This is a program that factors equations that are in the format of 'ax^2 + bx + c = 0'");
        Console.WriteLine("Type '!start' to start");
        Console.WriteLine();
        while (true)
        {
            var input = Console.ReadLine();

            if (input == "!start")
            {
                Console.Write("Enter 'a': ");
                double a = double.Parse(Console.ReadLine());
                Console.Write("Enter 'b': ");
                double b = double.Parse(Console.ReadLine());
                Console.Write("Enter 'c': ");
                double c = double.Parse(Console.ReadLine());

                c *= a;
                double f1 = Math.Abs(b);
                double f2 = Math.Abs(c);
                while (!(f1 + f2 == b && f1 * f2 == c))
                {
                    if (f1 > Math.Abs(c) * -1)
                    {
                        f1--;
                    }
                    else
                    {
                        f1 = Math.Abs(c);
                        f2--;
                    }
                }
                double x1 = f1 / a * -1;
                double x2 = f2 / a * -1;
                Fraction fFrac1 = RealToFraction(f1 / a, 0.01);
                Fraction fFrac2 = RealToFraction(f2 / a, 0.01);
                Fraction xFrac1 = RealToFraction(x1, 0.01);
                Fraction xFrac2 = RealToFraction(x2, 0.01);
                String i1 = "+ ";
                String i2 = "+ ";
                String answer1 = xFrac1.N.ToString() + "/" + xFrac1.D.ToString();
                String answer2 = xFrac2.N.ToString() + "/" + xFrac2.D.ToString();
                if (xFrac1.D == 1)
                    answer1 = xFrac1.N.ToString();
                if (xFrac2.D == 1)
                    answer2 = xFrac2.N.ToString();
                if (Math.Abs(xFrac1.D) == Math.Abs(xFrac1.N))
                    answer1 = "1";
                if (Math.Abs(xFrac2.D) == Math.Abs(xFrac2.N))
                    answer2 = "1";
                if (fFrac1.N != Math.Abs(fFrac1.N) || fFrac1.N != Math.Abs(fFrac1.N))
                {
                    i1= "- ";
                }
                if (fFrac2.N != Math.Abs(fFrac2.N))
                {
                    i2= "- ";
                }
                Console.Write("Factored Form:  (");
                if (fFrac1.D != 1)
                {
                    Console.Write(fFrac1.D);
                }
                Console.Write("x " + i1 + Math.Abs(fFrac1.N) + ")(");
                if (fFrac2.D != 1)
                {
                    Console.Write(fFrac2.D);
                }
                Console.WriteLine("x " + i2 + Math.Abs(fFrac2.N) + ")");
                Console.WriteLine("Answers:  x = " + answer1 + " or x = " + answer2);
            }

            if (input == "!exit")
                System.Environment.Exit(0);
        }
    }

    public struct Fraction
    {
        public Fraction(int n, int d)
        {
            N = n;
            D = d;
        }

        public int N { get; private set; }
        public int D { get; private set; }
    }

    public static Fraction RealToFraction(double value, double accuracy)
    {
        if (accuracy <= 0.0 || accuracy >= 1.0)
        {
            throw new ArgumentOutOfRangeException("accuracy", "Must be > 0 and < 1.");
        }

        int sign = Math.Sign(value);

        if (sign == -1)
        {
            value = Math.Abs(value);
        }

        // Accuracy is the maximum relative error; convert to absolute maxError
        double maxError = sign == 0 ? accuracy : value * accuracy;

        int n = (int)Math.Floor(value);
        value -= n;

        if (value < maxError)
        {
            return new Fraction(sign * n, 1);
        }

        if (1 - maxError < value)
        {
            return new Fraction(sign * (n + 1), 1);
        }

        // The lower fraction is 0/1
        int lower_n = 0;
        int lower_d = 1;

        // The upper fraction is 1/1
        int upper_n = 1;
        int upper_d = 1;

        while (true)
        {
            // The middle fraction is (lower_n + upper_n) / (lower_d + upper_d)
            int middle_n = lower_n + upper_n;
            int middle_d = lower_d + upper_d;

            if (middle_d * (value + maxError) < middle_n)
            {
                // real + error < middle : middle is our new upper
                upper_n = middle_n;
                upper_d = middle_d;
            }
            else if (middle_n < (value - maxError) * middle_d)
            {
                // middle < real - error : middle is our new lower
                lower_n = middle_n;
                lower_d = middle_d;
            }
            else
            {
                // Middle is our best fraction
                return new Fraction((n * middle_d + middle_n) * sign, middle_d);
            }
        }
    }
}