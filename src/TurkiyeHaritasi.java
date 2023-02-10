
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;


public class TurkiyeHaritasi extends JFrame{
     ImageIcon turkiyeHaritasi = new ImageIcon("src\\img\\türkiyeSiyasiHaritasi.png");
     class A {
         int k1;
         int k2;
         public A(int k1, int k2) {
             this.k1 = k1;
             this.k2 = k2;
         }
     }
    static ArrayList<A> koordinat = new ArrayList<A>();
    private int x1;
    private int y1;
    private int kontrol;
   public TurkiyeHaritasi(int x1, int y1, int kontrol) {
        setSize(1366, 760);
        if(kontrol == 0) {
            setVisible(false);
        }
        if(kontrol == 1) {
            setVisible(true);
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.x1 = x1;
        this.y1 = y1;
        this.kontrol = kontrol;
        A a = new A(x1, y1);
        koordinat.add(a);
    }
    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getKontrol() {
        return kontrol;
    }

    public void setKontrol(int kontrol) {
        this.kontrol = kontrol;
    }
    
    public void paint(Graphics graphics) {
        if(kontrol == 1) {
             graphics.drawImage(turkiyeHaritasi.getImage(), 0, 0, getWidth(), getHeight(), null);
            drawLines(graphics);  
        }
    }
    public void drawLines(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        Stroke stroke = new BasicStroke(2f);
        g2d.setStroke(stroke);
        for(int i = 0;i<koordinat.size()-1;i++) {
             g2d.drawLine(koordinat.get(i).k1, koordinat.get(i).k2, koordinat.get(i+1).k1, koordinat.get(i+1).k2);
        }
       
 
}
}  