package fred;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Sudoku implements ActionListener {
    private JButton enter =new JButton("Enter");
    private JButton buttonPressed;
    private JTextField input=new JTextField();
    private HashMap<String,JButton> buttonMap =new HashMap<String, JButton>();
    private int[][] board = new int[][]{
            {5, 0, 1, 0, 0, 3, 0, 0, 4},
            {0, 2, 0, 0, 8, 0, 0, 0, 6},
            {3, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 8, 0, 0},
            {0, 4, 0, 0, 0, 7, 9, 0, 3},
            {0, 0, 9, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 6, 0, 0, 1, 7},
            {0, 0, 4, 0, 0, 0, 2, 0, 0},
            {0, 8, 0, 9, 0, 0, 0, 3, 0}
    };
    private String[][] possibilities=new String[9][9];
    private JFrame frame = new JFrame("Sudoku Solver!");
    private SudokuPanel sudokuPanel=new SudokuPanel();
    public static void main(String args[]){
        new Sudoku();
    }

    private Sudoku(){


        frame.setSize(400,400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getRootPane().setDefaultButton(enter);
        frame.setLayout(new BorderLayout());
        Container container = new Container();
        container.setLayout(new GridLayout(9,9));
        container.setLayout(new GridLayout(9,9));
        JButton[][] buttons = new JButton[9][9];

        for (int y = 0; y < buttons.length; y++) {
            for (int x = 0; x < buttons[0].length; x++) {
                buttons[y][x]=new JButton();
                String command=Integer.toString(y)+Integer.toString(x);
                buttons[y][x].setActionCommand(command);
                buttonMap.put(command, buttons[y][x]);
                container.add(buttons[y][x]);
                buttons[y][x].addActionListener(this);
                if (board[y][x] != 0)
                buttons[y][x].setText(Integer.toString(board[y][x]));
            }
        }
        sudokuPanel.setOpaque(false);
        frame.add(container,BorderLayout.CENTER);
        Container north = new Container();
        north.setLayout(new GridLayout(1,2));
        north.add(input);
        enter.addActionListener(this);
        north.add(enter);
        frame.add(north, BorderLayout.NORTH);
        possibilities();
        frame.setVisible(true);
    }

    public void test(){
        String s="123456";
        System.out.print(s.substring(0,3));
    }

    public void actionPerformed(ActionEvent event) {
        frame.add(sudokuPanel,BorderLayout.CENTER);
        if (event.getSource().equals(enter)){
            buttonPressed.setText(input.getText());
            buttonPressed.setEnabled(true);
        }
        else {
            String command=((JButton) event.getSource()).getActionCommand();
            buttonPressed=buttonMap.get(command);
            buttonPressed.setEnabled(false);
            input.setText("");
            input.requestFocus();
        }

    }

    private void possibilities(){
        String[] columns=new String[9];
        String[][] boxes=new String[3][3];
        boolean firstTime=true;
        for (int x=0; x<9; x++){
            for (int y=0; y<9; y++){
                if (firstTime) {
                    columns[x] =Integer.toString(board[y][x]);
                    firstTime=false;
                }
                else {
                    columns[x] =columns[x]+Integer.toString(board[y][x]);
                }
            }
            firstTime=true;
        }
        for (int y=0; y<9; y=y+3){
            for (int x=0; x<9; x=x+3){
                int boxy=(int)Math.floor(y/3);
                int boxx=(int)Math.floor(x/3);
                boxes[boxy][boxx] =columns[x].substring(y, y + 3);
                boxes[boxy][boxx] =boxes[boxy][boxx]+columns[x + 1].substring(y, y + 3);
                boxes[boxy][boxx] =boxes[boxy][boxx]+columns[x + 2].substring(y, y + 3);
            }
        }
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                if (board[y][x] == 0) {
                    for (int count = 1; count <= 9; count++) {
                        if (!(columns[x].contains(Integer.toString(count))) && !(boxes[(int)Math.floor(y/3)][(int)Math.floor(x/3)].contains(Integer.toString(count)))){
                            if (possibilities[y][x] != null) {
                                possibilities[y][x] = possibilities[y][x] + Integer.toString(count);
                            }
                            else {
                                possibilities[y][x]=Integer.toString(count);
                            }
                        }
                    }
                    String command=Integer.toString(y)+Integer.toString(x);
                    JButton buttonSelected=buttonMap.get(command);
                    buttonSelected.setForeground(Color.RED);
                    buttonSelected.setText(possibilities[y][x]);
                }
            }
        }

    }

}
