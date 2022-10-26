import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.*;
public class VDN2 {
    public static int n,m;
    public static String beseda="";
    public static int poeni;
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("slovar.txt"));
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNext()){
            list.add(s.next());
        }
        s.close();

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        JFrame frame = new JFrame("MENU");
        frame.setResizable(false);
        JFrame frame1 = new JFrame("Word Search");
        JPanel panel = new JPanel();
        JButton start = new JButton("Start");

        JTextField tekst1 = new JTextField();
        JTextField tekst2 = new JTextField();
        JLabel label = new JLabel("Dimenzije: ");
        panel.add(label);
        panel.add(tekst1);
        panel.add(tekst2);
        tekst1.setPreferredSize(new Dimension(100,30));
        tekst2.setPreferredSize(new Dimension(100,30));

        frame.setSize(450,125);
        panel.setLayout(new FlowLayout());
        panel.add(start, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.NORTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Color color = new Color(255,175,175);
        start.setBackground(color);

        start.addActionListener(e -> {

            frame.setVisible(false);
            int n = Integer.parseInt(tekst1.getText());
            int m = Integer.parseInt(tekst2.getText());

            frame1.setVisible(true);
            frame1.setSize(800,600);
            BorderLayout border = new BorderLayout();
            frame1.setLayout(border);
            JPanel panel3 = new JPanel();
            panel3.setLayout(new BorderLayout());
            JButton button = new JButton("SAVE");
            button.setPreferredSize(new Dimension(100,100));
            panel3.add(button, BorderLayout.SOUTH);
            frame1.add(panel3, BorderLayout.SOUTH);

            JPanel panel2 = new JPanel();
            panel2.setLayout(new GridLayout(m,n));
            JButton buttons[][] = new JButton[m][n];
            String data[][] = new String[m][n];
            Random random = new Random();

            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    JButton b = new JButton("" + alphabet.charAt(random.nextInt(alphabet.length())));
                    b.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            b.setBackground(Color.CYAN);
                            System.out.print(b.getText());
                            b.setEnabled(false);
                            beseda+=b.getText();
                        }
                    });
                    b.setEnabled(true);
                    buttons[i][j] = b;
                    panel2.add(b);
                }
            }
            frame1.add(panel2);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(int i = 0;i< list.size();i++)
                    {
                        if(beseda.toLowerCase().equals(list.get(i)))
                        {
                            for(int j = 0;j<beseda.length();j++)
                            {
                                poeni+=Math.pow(2,j);
                            }
                            JOptionPane.showMessageDialog(frame1,"Poeni: " + poeni);
                        }
                    }
                    beseda ="";
                }
            });

        });


    }
}
