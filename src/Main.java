import java.io.*;
import java.util.*;


public class Main {
        static final int V = 81;
        static String[] sehirler = new String[81];
        static int[][] komsulukMatrisi = new int[81][81];
        static int[][] kusUcusuUzaklik = new int[81][81]; 
        static double [][] koordinatlar = new double[81][2];
        static ArrayList<Integer> gidilenSehirler = new ArrayList<Integer>();
        static ArrayList<Integer> kullanilanYol = new ArrayList<Integer>();
    public static void main(String[] args) throws IOException{
       sehirOzellikleriKayit();
       System.out.print("Hedef sehirleri aralarina virgul koyarak giriniz : ");
       Scanner sc = new Scanner(System.in);
       String  girdi = sc.nextLine();
       String []sehirAd= girdi.split(",");
       sehirAd = SezgiselUzaklikSirala(sehirAd);
       for(int i = 0;i<sehirAd.length;i++) {
           if(i == 0) {
               AStar(40, plakaBul(sehirAd[0]));
              
           }
          else {
               AStar(plakaBul(sehirAd[i-1]), plakaBul(sehirAd[i]));
           }
           gidilenSehirler.clear();
       }
        AStar(plakaBul(sehirAd[sehirAd.length-1]), 40);
        
       for(int i = 0;i<kullanilanYol.size();i++) {
           System.out.println(sehirler[kullanilanYol.get(i)]);
           if(i == kullanilanYol.size()-1) {
                    TurkiyeHaritasi harita = new TurkiyeHaritasi((int)koordinatlar[kullanilanYol.get(i)][0],(int)koordinatlar[kullanilanYol.get(i)][1], 1);

           } 
           else {
                    TurkiyeHaritasi harita = new TurkiyeHaritasi((int)koordinatlar[kullanilanYol.get(i)][0],(int)koordinatlar[kullanilanYol.get(i)][1], 0);
           }           
       }
    }
    public static void AStar(int Cikis, int Varis) {
      
      
      int AStarUzunlugu = Integer.MAX_VALUE;
      int Plaka = -1;
       for(int i = 0;i<V;i++) {
           if(komsulukMatrisi[Cikis][i] != 0 && gidilenSehirler.indexOf(i) == -1) {
               int yeni = komsulukMatrisi[Cikis][i] + kusUcusuUzaklik[Varis][i];
               if(AStarUzunlugu > yeni) {
                    AStarUzunlugu = yeni;
                    Plaka = i;
               }
           }
       }
       kullanilanYol.add(Plaka);
       gidilenSehirler.add(Plaka);
       if(Plaka == Varis) {
           return;
       }
        AStar(Plaka, Varis);
    }
    public static String[] SezgiselUzaklikSirala(String []sehirAd) {
          int []kayit = new int [sehirAd.length];

          for(int i = 0;i<sehirAd.length;i++) 
                 kayit[i] = kusUcusuUzaklik[40][plakaBul(sehirAd[i])];
         
          
          for (int i = 0; i < kayit.length-1; i++) {
            for (int j = 0; j < kayit.length-i-1; j++) { 
                 if (kayit[j] > kayit[j+1]) 
                { 
                    int temp = kayit[j]; 
                    kayit[j] = kayit[j+1]; 
                    kayit[j+1] = temp; 
                    String strCopy = String.valueOf(sehirAd[j]);
                    sehirAd[j] = String.valueOf(sehirAd[j+1]);
                    sehirAd[j+1] = String.valueOf(strCopy);
                } 
            }
          }
          return sehirAd;
    }
    public static int plakaBul(String ad) {
        
         for(int j = 0;j<81;j++) {
                 if(ad.equals(sehirler[j])) {
                     return j;
                 }
         }
         return -1;
    }
    public static void sehirOzellikleriKayit() throws FileNotFoundException, IOException {
        FileReader fileReader = new FileReader(new File("sehirkayit.txt"));
        String line;

        BufferedReader br = new BufferedReader(fileReader);

        line = br.readLine();

        sehirler = line.split(",");
        
        fileReader = new FileReader(new File("komsulukMatrisi.txt"));

        br = new BufferedReader(fileReader);

        String[] myWord;
        int k = 0;
        while ((line = br.readLine()) != null) {
            myWord = line.split(",");
            for (int i = 0; i < 81; i++)
                komsulukMatrisi[k][i] = Integer.parseInt(myWord[i]);
            k++;
        }
        
        fileReader = new FileReader(new File("kusUcusuUzaklik.txt")); // Seazgisel uzaklik icin

        br = new BufferedReader(fileReader);
        String[] myWord2;
        k = 0;
        while ((line = br.readLine()) != null) {
            myWord2 = line.split(",");
            for (int i = 0; i < 81; i++)
                kusUcusuUzaklik[k][i] = Integer.parseInt(myWord2[i]);
            k++;
        }
        
         fileReader = new FileReader(new File("gercekkoordinat.txt")); 
         br = new BufferedReader(fileReader);
       
        k = 0;
        while ((line = br.readLine()) != null) {
            myWord = line.split(",");
            for (int i = 0; i < 2; i++)
                koordinatlar[k][i] = Double.parseDouble(myWord[i]);
            k++;
        }
          
        br.close();
    }
    
}
