using System;
using System.Collections.Generic;

namespace Lab5
{
    class Program
    {
        private static List<String> hts = new List<String>();

        static void Main(string[] args)
        {
            hts.Add("www.cs.ubbcluj.ro/~rlupsa/edu/pdp");
            hts.Add("youtube.com");
            hts.Add("google.com");

//            DirCallbacks.run(hts);
            BaseTask.run(hts, true);
        }
    }
}