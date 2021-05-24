import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.Font;


public class Window extends JFrame{  
    private String input;
    JLabel maingame;
    public void open() {  
        InputListener iListener = new InputListener();        
        setFocusable(true);
        JPanel panel = new JPanel();
        maingame = new JLabel();
        maingame.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12)); //monospace font so the game is readable
        panel.add(maingame);
        panel.setLayout(new FlowLayout());
        this.setTitle("Rouge");
        this.add(panel);
        this.addKeyListener(iListener);
        this.setSize(400,650);//400 width and 650 height  
        this.setVisible(true);//making the frame visible
    }

    public String getInput(){
        String currentInput = input;
        input = "";
        return currentInput;
    }

    public void setOutput(String output){
        maingame.setText(output);
        this.repaint();
    }

    private class InputListener implements KeyListener  {

        @Override
        public void keyTyped(KeyEvent e) {
		}

        @Override
        public void keyPressed(KeyEvent e){
            int newInput = e.getKeyCode();
            switch(newInput){
                case KeyEvent.VK_W:
                case KeyEvent.VK_UP:
                    input = "UP";
                    break;
                case KeyEvent.VK_A:
                case KeyEvent.VK_LEFT:
                    input = "LEFT";
                    break;
                case KeyEvent.VK_S:
                case KeyEvent.VK_DOWN:
                    input = "DOWN";
                    break;
                case KeyEvent.VK_D:
                case KeyEvent.VK_RIGHT:
                    input = "RIGHT";
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
		}
    } 
}

