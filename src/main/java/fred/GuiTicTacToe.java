package fred;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class GuiTicTacToe implements ActionListener {
    JButton[][] button=new JButton[3][3];
    Container container=new Container();
    Container north=new Container();
    JFrame frame=new JFrame("Tic Tac Toe!");
    JLabel xLabel=new JLabel("X has won 0 times.");
    JLabel oLabel=new JLabel("O has won 0 times.");
    JButton xChangeName=new JButton("Change X's name");
    JButton oChangeName=new JButton("Change O's name");
    JTextField xNameField=new JTextField();
    JTextField oNameField=new JTextField();
    HashMap<String,JButton> buttonMap=new HashMap<String,JButton>();
    int moves=0;
    int[][] board=new int[3][3];
    int xWins=0;
    int oWins=0;
    private String xPlayerName="X";
    private String oPlayerName="O";
    public static void main(String[] args) {
        new GuiTicTacToe();
    }

    public GuiTicTacToe(){
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        container.setLayout(new GridLayout(3,3));
        for (int i = 0; i < button.length; i++) {
            for (int x = 0; x < button[0].length; x++) {
                button[i][x]=new JButton();
                String command=Integer.toString(i)+Integer.toString(x);
                button[i][x].setActionCommand(command);
                buttonMap.put(command,button[i][x]);
                container.add(button[i][x]);
                button[i][x].addActionListener(this);
            }
        }
        frame.add(container,BorderLayout.CENTER);
        north.setLayout(new GridLayout(3,2));
        north.add(xLabel);
        north.add(oLabel);
        north.add(xNameField);
        north.add(oNameField);
        north.add(xChangeName);
        north.add(oChangeName);
        xChangeName.addActionListener(this);
        oChangeName.addActionListener(this);
        frame.add(north, BorderLayout.NORTH);
        frame.setVisible(true);
    }

    public int checkWin(int [][] board){
/*		checkWin uses a series of for loops to check every row, column
		and diagonal for three in a row.  The variable "count" keeps track
		of the amount of letters in a row.  If a win is detected, checkWin
		returns 1.  ticTacToeGame calls checkWin after every move and if
		checkWin(board)==1 then the method announces the win.          */
        int count=0;
        int c=0;
        int win=0;
        for (int i=0; i<3; i++){
            count=0;
            for (int x=0; x<3; x++){
                if (board[i][x] != 0){
                    if (x==0){
                        c=board[i][x];
                    }
                    else if (c==board[i][x]){
                        count++;
                        if (count==2){
                            win=1;
                        }
                    }
                }
                else {
                    x=3;
                }
            }
        }

        count=0;
        for (int i=0; i<3; i++){
            count=0;
            for (int x=0; x<3; x++){
                if (board[x][i] != 0){
                    if (x==0){
                        c=board[x][i];
                    }
                    else if (c==board[x][i]){
                        count++;
                        if (count==2){
                            win=1;
                        }
                    }
                }
                else {
                    x=3;
                }
            }
        }

        count=0;
        c=board[0][0];
        for (int i=1; i<3; i++){
            if (board[i][i] != 0){
                if (c==board[i][i]){
                    count++;
                    if (count==2){
                        win=1;
                    }
                }
            }
            else {
                i=3;
            }
        }

        count=0;
        c=board[2][0];
        int x=1;
        for (int i=1; i>=0; i--){
            if (board[i][x] != 0){
                if (c==board[i][x]){
                    count++;
                    if (count==2){
                        win=1;
                    }
                }
                x++;
            }
            else {
                i=-1;
            }
        }
        return win;
    }

    public void clearBoard(){
        moves=0;
        for (int i = 0; i < 3; i++) {
            for (int x=0; x<3; x++){
                button[i][x].setEnabled(true);
                button[i][x].setText("");
                board[i][x]=0;
            }
        }
    }


    public void actionPerformed(ActionEvent event) {

        if (event.getSource().equals(xChangeName)){
            xPlayerName=xNameField.getText();
            xLabel.setText(xPlayerName+" (X) has won "+xWins+" time(s).");
        }
        else if (event.getSource().equals(oChangeName)) {
            oPlayerName=oNameField.getText();
            oLabel.setText(oPlayerName+" (O) has won "+oWins+" time(s).");
        }
        else {
            String command=((JButton) event.getSource()).getActionCommand();
            int row=Integer.parseInt(command.substring(0, 1));
            int column=Integer.parseInt(command.substring(1));
            if (board[row][column] ==0){
                moves++;
                JButton buttonPressed=buttonMap.get(command);
                if (moves % 2==0){
                    buttonPressed.setText("O");
                    buttonPressed.setEnabled(false);
                    board[row][column]=1;
                    if (checkWin(board)==1){
                        oWins++;
                        oLabel.setText(oPlayerName+" (O) has won "+oWins+" time(s).");
                        clearBoard();
                    }
                }
                else {
                    buttonPressed.setText("X");
                    buttonPressed.setEnabled(false);
                    board[row][column]=2;
                    if (checkWin(board)==1){
                        xWins++;
                        xLabel.setText(xPlayerName+" (X) has won "+xWins+" time(s).");
                        clearBoard();
                    }
                }
            }
        }
    }


}

