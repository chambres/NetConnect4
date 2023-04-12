import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;

public class TTTFrame extends JFrame implements KeyListener , MouseListener{
    private String text = "";
    private char player ='x';
    private char turn = 'x';
    private GameData gameData = null;
    private ObjectOutputStream oos;
    public TTTFrame(GameData gameData, ObjectOutputStream oos)
    {
        super("TTT Game");
        this.gameData = gameData;
        this.oos = oos;
        addKeyListener(this);
        addMouseListener(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(100*7,100*7);
    }

    public void paint(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(),getHeight());


        g.setColor(Color.RED);
        g.setFont(new Font("Times New Roman",Font.BOLD,30));
        g.drawString(text,959,50);

        g.setColor(Color.RED);

        
        //draw 7 circles evenly spaced for 6 rows
        for(int r=0; r<6; r++)
        {
            for(int c=0; c<7; c++)
            {
                
            }
        }

        for(int r=0; r<6; r++){
            for(int c=0; c<7; c++)
            {
                if(gameData.getGrid()[r][c] == 'x')
                {
                    g.setColor(Color.BLUE);
                    g.fillOval(c*100+10,r*100+60+10,100-20,100-20);
                    
                }
                else if(gameData.getGrid()[r][c] == 'o')
                {
                    g.setColor(Color.RED);
                    g.fillOval(c*100+10,r*100+60+10,100-20,100-20);
                }
            }
        }   
    }


    public void setText(String text) {
        this.text = text;
        repaint();
    }

    public char getPlayer() {
        return player;
    }

    public void setPlayer(char player) {
        this.player = player;
    }

    public char getTurn() {
        return turn;
    }

    public void setTurn(char turn) {
        this.turn = turn;
    }

    public void makeMove(int c, int r, char letter)
    {
        gameData.getGrid()[r][c] = letter;
        repaint();
    }


    @Override
    public void mousePressed(MouseEvent e) {
        try{
        switch((e.getX()/100)){
            case 0:
                oos.writeObject(new CommandFromClient(CommandFromClient.MOVE,"0"));
                break;
            case 1:
                oos.writeObject(new CommandFromClient(CommandFromClient.MOVE,"1"));
                break;
            case 2:
                oos.writeObject(new CommandFromClient(CommandFromClient.MOVE,"2"));
                break;
            case 3:
                oos.writeObject(new CommandFromClient(CommandFromClient.MOVE,"3"));
                break;
            case 4:
                oos.writeObject(new CommandFromClient(CommandFromClient.MOVE,"4"));
                break;
            case 5:
                oos.writeObject(new CommandFromClient(CommandFromClient.MOVE,"5"));
                break;
            case 6:
                oos.writeObject(new CommandFromClient(CommandFromClient.MOVE,"6"));
                break;
        }
        }
        catch(Exception ex)
        {}
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("typed"+e.getKeyChar());
        try {
            switch (e.getKeyChar()) {
                case '1':
                    oos.writeObject(new CommandFromClient(CommandFromClient.MOVE,"0,0"));
                    break;
                case '2':
                    oos.writeObject(new CommandFromClient(CommandFromClient.MOVE,"1,0"));
                    break;
                case '3':
                    oos.writeObject(new CommandFromClient(CommandFromClient.MOVE,"2,0"));
                    break;
                case '4':
                    oos.writeObject(new CommandFromClient(CommandFromClient.MOVE,"0,1"));
                    break;
                case '5':
                    oos.writeObject(new CommandFromClient(CommandFromClient.MOVE,"1,1"));
                    break;
                case '6':
                    oos.writeObject(new CommandFromClient(CommandFromClient.MOVE,"2,1"));
                    break;
                case '7':
                    oos.writeObject(new CommandFromClient(CommandFromClient.MOVE,"0,2"));
                    break;
                case '8':
                    oos.writeObject(new CommandFromClient(CommandFromClient.MOVE,"1,2"));
                    break;
                case '9':
                    oos.writeObject(new CommandFromClient(CommandFromClient.MOVE,"2,2"));
                    break;
                case 'r':
                    setText("Waiting for restart from "+other());
                    oos.writeObject(new CommandFromClient(CommandFromClient.RESTART,""));
                    break;
            }
        }
        catch(Exception ex)
        {}
    }



    public void reset()
    {
        gameData.reset();
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public char other()
    {
        if(player=='x')
            return 'o';
        else
            return 'x';
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

        
    }
}
