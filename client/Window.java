import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.Font;


public class Window extends JFrame{  
    private String input;
    JLabel maingame;
    public void run() {  
        InputListener iListener = new InputListener();        
        setFocusable(true);
        JPanel panel = new JPanel();
        maingame = new JLabel("HELLO");
        maingame.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        panel.add(maingame);
        panel.setLayout(new FlowLayout());
        this.add(panel);
        this.addKeyListener(iListener);
        this.setSize(400,600);//400 width and 500 height  
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
            input = Character.toString(e.getKeyChar());
        }

        @Override
        public void keyReleased(KeyEvent e) {
		}
    } 
}

