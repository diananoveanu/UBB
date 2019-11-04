using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Net;
using System.Threading.Tasks;

namespace multipleDownload
{
    class Program
    {
        private static readonly int DegreeOfParallelism = Environment.ProcessorCount;
        private static string _currentWorkingDirectory = Directory.GetCurrentDirectory();
        private static readonly string outputDirectory = "";
        private static readonly Random random = new Random(); 
       
        public static string RandomString(int length)
        {
            const string chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            return new string(Enumerable.Repeat(chars, length)
                .Select(s => s[random.Next(s.Length)]).ToArray());
        }
        
        static void Main(string[] args)
        {

            Stopwatch sw = Stopwatch.StartNew();
            String toDownload = "https://picsum.photos/200/300";
            int numOfFiles;
            Console.WriteLine("Give the number of files to be downloaded: ");
            numOfFiles = Int32.Parse(Console.ReadLine());
            List<string> urls = new List<string>();
            for (int i = 0; i < numOfFiles; i++)
            {
                urls.Add(toDownload);
            }

            //Downloads
            foreach (var photoName in urls.Select(url => RandomString(20)))
            {
                
            }
            
            // Download
            foreach (var manga in mangaList)
            {
                var mangaName = Directory.CreateDirectory(outputDirectory + @"\" + GetStringUntilSlashFromBack(manga.mainUrl)).Name;
                Parallel.ForEach(manga.chapterList, new ParallelOptions { MaxDegreeOfParallelism = DegreeOfParallelism },
                chapter =>
                {
                    var chapterName = Directory.CreateDirectory(outputDirectory + @"\" + mangaName + @"\" + GetStringUntilSlashFromBack(chapter)).Name;

                    Console.WriteLine("starting to download manga {0} - chapter {1}", mangaName, chapterName);
                    Parallel.ForEach(manga.chapterImages[chapter], new ParallelOptions { MaxDegreeOfParallelism = DegreeOfParallelism },
                    s =>
                    {
                        using (var client = new WebClient())
                        {
                            if (!File.Exists(outputDirectory + @"\" + mangaName + @"\" + chapterName + @"\" + GetStringUntilSlashFromBack(s)))
                            {
                                //Console.WriteLine("starting to download {0}", s);
                                client.DownloadFile(s, outputDirectory + @"\" + mangaName + @"\" + chapterName + @"\" + GetStringUntilSlashFromBack(s));
                                //Console.WriteLine("finished downloading {0}", s);
                            }
                        }
                    });
                    Console.WriteLine("finished downloading image {0} - chapter {1}", mangaName, chapterName);
                });
            }

            sw.Stop();
            Console.WriteLine("Time taken: {0}s", sw.Elapsed.Seconds);
            Console.ReadKey();
        }
        
        private static string GetStringUntilSlashFromBack(string str)
        {
            int index = str.LastIndexOf('/');
            str = str.Substring(index + 1);
            return str;
        }
    }
}
