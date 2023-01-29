package Assignment8.RaceTrack1;
import javax.swing.*;
import java.awt.*;
import java.util.Random;
class RaceTrack extends JFrame {

        JPanel panel;
  private   Canvas panelCanvas;
  static final int max_x =450;
    int car1V =20;
    int car2V = 20;
    int car3V = 20;
    Button[] bus = new Button[3];
    Button startbutton,Pausetbutton,Resetbutton;
Thread moving;
Thread moving1;
Thread moving2;
    Car car1;
    Car car2;
    Car car3;

    Boolean On = false;
    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);

        g.drawImage(car1.car, car1V, 70,null);
        g.drawImage(car2.car, car2V, 100,null);
        g.drawImage(car3.car, car3V, 130,null);
        g.drawRect(20,70,450,25);
        g.drawRect(20,100,450,25);
        g.drawRect(20,130,450,25);

        if(car1V > 20 && car2V > 20 && car3V > 20 && car2V <742&& car1V <742&& car3V <742&& this.On ==false){
            repaint();
        }
    }

    RaceTrack() {
super("Richmond Race");
setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
this.setSize(500,200);
this.setMaximumSize(new Dimension(500,200));
this.setMinimumSize(new Dimension(500,200));

setLayout(null);
panelCanvas=new Canvas();
panelCanvas.setPreferredSize(new Dimension(500,200));

startbutton = new Button("Start");
Pausetbutton = new Button("Pause");
Resetbutton = new Button("Reset");
bus[0]=startbutton;
bus[1]=Pausetbutton;
bus[2]=Resetbutton;
panel = new JPanel();
panel.setBounds(70,10,250,20);
panel.setLayout(new GridLayout(1,1,3,3));
panel.add(startbutton);
panel.add(Pausetbutton);
panel.add(Resetbutton);
startbutton.addActionListener(this::btnStartActionPerformed);
Pausetbutton.addActionListener(this::btnStopActionPerfomed);
Resetbutton.addActionListener(this::btnResetActionPerfomed);
this.add(panelCanvas);
this.add(panel);
car1 = new Car();
car2 = new Car();
car3 = new Car();
setVisible(true);
    }
    public static class Car {
        Image car;
        Car(){
            car = new ImageIcon("src/sample/sportive-car.png").getImage();
        }
    }
private void btnStartActionPerformed(java.awt.event.ActionEvent e){
        this.On=false;
        moving= new Thread(() -> {
            Random r = new Random();
            while(car1V < max_x&& car2V<max_x&& car3V<max_x&& !this.On) {
                int low =1;
                int high =10;
                int x = r.nextInt(high-low) + low;
                if(moving.isInterrupted()){
                    break;

                }
                    this.car1V = car1V + x;
                    try {
                        Thread.sleep(50);
                        repaint();

                    } catch (InterruptedException interruptedException) {
                        break;
                    }
                }
            if(car1V  >= max_x){
                this.On =true;
                repaint();
                JOptionPane.showMessageDialog(null,"Car 1 has Won","Winner",JOptionPane.INFORMATION_MESSAGE);
            }
            }
        );
    moving1= new Thread(() -> {

        while(car1V < max_x&& car2V<max_x&& car3V<max_x&& !this.On) {
            Random r = new Random();
            int low =1;
            int high =10;
            int x = r.nextInt(high-low) + low;
            this.car2V = car2V +x;
            if(moving1.isInterrupted()){
                break;

            }
            try {
                Thread.sleep(50);
                repaint();

            }
            catch (InterruptedException interruptedException) {
                break;
            }
        }
        if(car2V  >= max_x){
            this.On =true;
            repaint();
            JOptionPane.showMessageDialog(null,"Car 2 has Won","Problem",JOptionPane.INFORMATION_MESSAGE);
        }


    }

    );

    moving2= new Thread(() -> {
        while(car1V < max_x&& car2V<max_x&& car3V<max_x&& !this.On) {
            Random r = new Random();
            int low =1;
            int high =10;
            int x = r.nextInt(high - low) + low;
            this.car3V = car3V + x;
            if(moving2.isInterrupted()){
                break;

            }

            try {
                Thread.sleep(50);
                repaint();
            } catch (InterruptedException interruptedException) {
              break;
            }


        }
        if(car3V  >= max_x){
           this.On =true;
            repaint();
            JOptionPane.showMessageDialog(null,"Car 3 has Won","Problem",JOptionPane.INFORMATION_MESSAGE);
        }
    });
        moving.start();
        moving1.start();
        moving2.start();

}
private void btnStopActionPerfomed(java.awt.event.ActionEvent evt){
           this.On = true;
            moving2.interrupt();

            moving1.interrupt();

            moving.interrupt();

            repaint();
}
private void btnResetActionPerfomed(java.awt.event.ActionEvent evt){
        this.car2V =20;
        this.car1V =20;
        this.car3V =20;
        repaint();

    }
    public static void main(String [] args){
        new RaceTrack();
    }
}
