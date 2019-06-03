import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

public class TugasGraph
{
  int V;
  static final int INF = 99999;
  
  public static void main(String[] paramArrayOfString)
  {
    print("Halo...");
    printSlow("Namaku Kyon, aku akan membantumu mengerjakan tugas terakhir\nPak Janoe tercinta :)");
    printSlow("Task nya agak berat, jadi sebelum mulai di benchmark doeloe ya");
    System.out.print("\nPreparing benchmark test with Lucas Lehmer algorithm...\nStarting benchmark");
    fakeLoading();
    System.out.println();
    printSlow("Wah, PC mu cupu sekali :(");
    printSlow("Tapi tak apa, kita coba test run dulu ya dengan jumlah vertex 10");
    new TugasGraph(10, true);
    printSlow("Wah, kelihatannya benar");
    printSlow("Tapi aku masih ragu apakah CPU ini kuat, menurutmu bagaimana?");
    System.out.println("1. Jalankan Dijsktra & Floyd");
    System.out.println("2. Lihat source code saja");
    Scanner localScanner = new Scanner(System.in);
    if (localScanner.nextInt() == 2) {
      printSlow("Untuk melihat source code ku ada dua cara yaitu:");
      printSlow("A. Melakukan dekompilasi (decompile)");
      printSlow("B. Menjawab salah satu pertanyaan di bawah ini");
      printSlow("Namun, syaratnya adalah orang yang menjawab tidak boleh yang namanya ada di pertanyaan.");
      System.out.println("Pertanyaan :");
      System.out.println("1.Siapa nama cici nya Hashfi ? (Salah satu)");
      System.out.println("2.Siapa nama bebebnya Rian yg di filsafat ?");
      System.out.println("3.Berapa tebal maksimum kumis Arda semester kemarin ? (cm)");
      System.out.println("4.Jam berapa klinik TongFang buka ?");
      System.out.println("5.Siapa orang paling pedo di Ilkom-B ?");
      System.out.println("6.Berapa kali saya titip KTM ke Raven tapi dia malah ketiduran ?");
      System.out.println("7.Siapa waifu Fadhlan ?");
      System.out.println("8.Berapa maks shutter speed kamera Rafel ?");
      System.out.println("9.Pohon jenis apa yang mecahin kaca mobil Acong ?");
      System.out.println("10.Berapa harga pijat plus plus yang ditawarkan ke Richie di depan stasiun/Hotel Neo ?");
      System.out.println("11.Lebih sering bolos saya, Raven, atau Naufan ? (di kertas)");
      System.out.println("12.Berapa jumlah dakimakura Ferdi ?");
      System.out.println("13.Berapa sisa credit Azure yang dimiliki Adhit ?");
      System.out.println("14.Siapa nama mantan Pi'i ?");
      System.out.println("15.Siapa nama anak Pak Shippuden ?");
      localScanner.nextLine();
      for (;;) {
        System.out.println("Pilih pertanyaan nomor : ");
        localScanner.nextLine();
        System.out.println("Jawaban :");
        localScanner.nextLine();
        printSlow("Jawaban salah");
      }
    }
    System.out.println("Memulai task...");
    new TugasGraph(1000, false);
    new TugasGraph(1000, true);
    new TugasGraph(10000, false);
    new TugasGraph(10000, true);
    new TugasGraph(20000, false);
    new TugasGraph(20000, true);
    new TugasGraph(50000, false);
    new TugasGraph(50000, true);
    new TugasGraph(100000, false);
    new TugasGraph(100000, true);
    
    localScanner.close();
  }
  
  public TugasGraph(int paramInt, boolean paramBoolean) {
    V = paramInt;
    int[][] arrayOfInt = generateGraph(paramBoolean);
    
    long l1 = System.currentTimeMillis();
    dijkstra(arrayOfInt, 0);
    long l2 = System.currentTimeMillis();
    System.out.println("Dijkstra 0 " + paramInt + (paramBoolean ? " Edge lengkap " : " Edge setengah ") + (l2 - l1) + " ms");
    
    l1 = System.currentTimeMillis();
    for (int i = 0; i < paramInt; i++) {
      dijkstra(arrayOfInt, i);
    }
    l2 = System.currentTimeMillis();
    System.out.println("Dijkstra n " + paramInt + (paramBoolean ? " Edge lengkap " : " Edge setengah ") + (l2 - l1) + " ms");
    
    l1 = System.currentTimeMillis();
    floydWarshall(arrayOfInt);
    l2 = System.currentTimeMillis();
    System.out.println("Floyd " + paramInt + (paramBoolean ? " Edge lengkap " : " Edge setengah ") + (l2 - l1) + " ms");
  }
  



  int[][] generateGraph(boolean paramBoolean)
  {
    int[][] arrayOfInt = new int[V][V];
    Random localRandom = new Random();
    int i; int j; if (paramBoolean) {
      for (i = 0; i < V; i++) {
        for (j = i; j < V; j++) {
          if (i == j) {
            arrayOfInt[i][j] = 0;
          } else {
            arrayOfInt[i][j] = (localRandom.nextInt(999) + 1);
            arrayOfInt[j][i] = arrayOfInt[i][j];
          }
        }
      }
    } else {
      i = 0;
      j = V * (V - 1) / 4;
      while (i < j) {
        for (int k = 0; k < V; k++) {
          for (int m = k; m < V; m++) {
            if (k == m) {
              arrayOfInt[k][m] = 0;
            } else {
              if (arrayOfInt[k][m] == 0) {
                arrayOfInt[k][m] = 99999;
                arrayOfInt[m][k] = arrayOfInt[k][m];
              }
              if ((localRandom.nextBoolean()) && (arrayOfInt[k][m] == 99999) && (i < j)) {
                arrayOfInt[k][m] = (localRandom.nextInt(999) + 1);
                arrayOfInt[m][k] = arrayOfInt[k][m];
                i++;
              }
            }
          }
        }
      }
    }
    return arrayOfInt;
  }
  
  int minDistance(int[] paramArrayOfInt, Boolean[] paramArrayOfBoolean)
  {
    int i = 99999;int j = -1;
    for (int k = 0; k < V; k++)
      if ((!paramArrayOfBoolean[k].booleanValue()) && (paramArrayOfInt[k] <= i)) {
        i = paramArrayOfInt[k];
        j = k;
      }
    return j;
  }
  


  void dijkstra(int[][] paramArrayOfInt, int paramInt)
  {
    int[] arrayOfInt = new int[V];
    



    Boolean[] arrayOfBoolean = new Boolean[V];
    

    for (int i = 0; i < V; i++) {
      arrayOfInt[i] = 99999;
      arrayOfBoolean[i] = Boolean.valueOf(false);
    }
    
    arrayOfInt[paramInt] = 0;
    
    for (i = 0; i < V - 1; i++)
    {


      int j = minDistance(arrayOfInt, arrayOfBoolean);
      
      arrayOfBoolean[j] = Boolean.valueOf(true);
      

      for (int k = 0; k < V; k++)
      {


        if ((!arrayOfBoolean[k].booleanValue()) && (paramArrayOfInt[j][k] != 0) && (paramArrayOfInt[j][k] != 99999) && (arrayOfInt[j] != 99999) && (arrayOfInt[j] + paramArrayOfInt[j][k] < arrayOfInt[k]))
        {
          arrayOfInt[j] += paramArrayOfInt[j][k]; }
      }
    }
    if (V < 100) {
      printSolution(arrayOfInt, V);
    }
  }
  
  void printSolution(int[] paramArrayOfInt, int paramInt) {
    System.out.println("Vertex\t\tDistance from Source");
    for (int i = 0; i < V; i++)
      System.out.println(i + "\t\t" + (paramArrayOfInt[i] == 99999 ? "INF" : Integer.valueOf(paramArrayOfInt[i])));
  }
  
  void floydWarshall(int[][] paramArrayOfInt) {
    int[][] arrayOfInt = new int[V][V];
    


    int j;
    


    for (int i = 0; i < V; i++) {
      for (j = 0; j < V; j++) {
        arrayOfInt[i][j] = paramArrayOfInt[i][j];
      }
    }
    






    for (int k = 0; k < V; k++)
    {
      for (i = 0; i < V; i++)
      {

        for (j = 0; j < V; j++)
        {

          if (arrayOfInt[i][k] + arrayOfInt[k][j] < arrayOfInt[i][j]) {
            arrayOfInt[i][k] += arrayOfInt[k][j];
          }
        }
      }
    }
    if (V < 100)
      printSolution(arrayOfInt);
  }
  
  void printSolution(int[][] paramArrayOfInt) {
    System.out.println("Matriks jarak terpendek antara dua vertex");
    for (int i = 0; i < V; i++) {
      for (int j = 0; j < V; j++) {
        if (paramArrayOfInt[i][j] == 99999) {
          System.out.print("INF ");
        } else
          System.out.print(paramArrayOfInt[i][j] + "\t");
      }
      System.out.println();
    }
  }
  
  private static void printSlow(String paramString) {
    Random localRandom = new Random();
    for (int i = 0; i < paramString.length(); i++) {
      try {
        Thread.sleep(localRandom.nextInt(250));
      }
      catch (Exception localException) {}
      
      System.out.print(paramString.charAt(i));
    }
    System.out.println();
  }
  
  private static void fakeLoading() {
    System.out.println();
    Random localRandom = new Random();
    int i = 0;
    System.out.print("   ");
    while (i <= 100) {
      System.out.print("\b\b\b");
      if (i < 10) {
        System.out.print("0");
      }
      System.out.print(i + "%");
      try {
        Thread.sleep(localRandom.nextInt(300));
      }
      catch (Exception localException) {}
      
      i++;
    }
  }
}