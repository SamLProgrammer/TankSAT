import controller.Controller;

public class App {
    public static void main(String[] args) throws Exception {
        new Controller();
    }
}

// import javax.swing.*;
// import java.awt.event.KeyAdapter;
// import java.awt.event.KeyEvent;

// public class App {

//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(new Runnable() {
//             @Override
//             public void run() {
//                 createAndShowGUI();
//             }
//         });
//     }

//     private static void createAndShowGUI() {
//         JFrame frame = new JFrame("Key Listener Example");
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.setSize(400, 300);

//         JPanel panel = new JPanel();
//         panel.setFocusable(true);
//         panel.requestFocusInWindow();
//         frame.add(panel);

//         panel.addKeyListener(new KeyAdapter() {
//             @Override
//             public void keyPressed(KeyEvent e) {
//                 if (e.getKeyCode() == KeyEvent.VK_A) {
//                     System.out.println("A key pressed");
//                 } else if (e.getKeyCode() == KeyEvent.VK_B) {
//                     System.out.println("B key pressed");
//                 }
//             }
//         });

//         frame.setVisible(true);
//     }
// }

