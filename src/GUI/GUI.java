/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import Logic.Cell;
import Logic.Puzzle;
import Logic.SudokuIntFilter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.Document;

/**
 *
 * @author Alistair
 */

public class GUI extends JPanel {
    
    private JFrame frame;
    private JPanel controlHolder;
    private JPanel gamePanel;
    private Puzzle puzzle;
    private Puzzle lastPuzzle;
    private JButton solveBtn;
    private JButton clearBtn;
    private JPanel btnHolder;
    private JPanel contentHolder;
    private GridBagConstraints layoutConstraints;
    
    public GUI() {
        controlHolder = new JPanel();
        gamePanel = new JPanel();
        puzzle = new Puzzle();
        solveBtn = new JButton("Solve");
        solveBtn.addActionListener(new SolveListener());
        clearBtn = new JButton("Clear All");
        clearBtn.addActionListener(new ClearListener());
        btnHolder = new JPanel();
        contentHolder = new JPanel(new GridBagLayout());
        layoutConstraints = new GridBagConstraints();
    }
    
    public static void main(String[] args) {
        GUI guiTest = new GUI();
        guiTest.run();
    }
    
    public void run() {
        
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridBagLayout());
        frame.setSize(900,800);
        
        gamePanel.setLayout(new GridLayout(9,9));
        
        gamePanel.setPreferredSize(new Dimension(500,500));
        
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 0;
        layoutConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        
        //Loop to create all the text fields.     
        for(int i = 0; i < 81; i++) {
            Cell textBox = new Cell();
            
            Document styledDoc = textBox.getDocument();
            
            //Modify the sudokuIntFilter for the clear button.
            if (styledDoc instanceof AbstractDocument) {
                AbstractDocument doc = (AbstractDocument)styledDoc;
                doc.setDocumentFilter(new SudokuIntFilter());
            }
            
            int top = 1;
            int bot = 1;
            int lhs = 1;
            int rhs = 1;
            
            if ((i/9)/8 == 0 && (i/9)%8 == 0) {
                top = 4;
            }
            
            if ((i%9)/8 == 0 && (i%9)%8 == 0) {
                lhs = 4;
            }
            
            if ((i/9)/8 == 1 && (i/9)%8 == 0) {
                bot = 4;
            }
            
            if ((i%9)/8 == 1 && (i%9)%8 == 0) {
                rhs = 4;
            }
            
            if ((i/9)%8 == 3) {
                top = 2;
            }
            
            if ((i/9)%8 == 2) {
                bot = 2;
            }
            
            if ((i/9)%8 == 5) {
                bot = 2;
            }
            
            if ((i/9)%8 == 6) {
                top = 2;
            }
            
            if ((i%9)%8 == 2) {
                rhs = 2;
            }
            
            if ((i%9)%8 == 2) {
                rhs = 2;
            }
            
            if ((i%9)%8 == 3) {
                lhs = 2;
            }
            
            if ((i%9)%8 == 5) {
                rhs = 2;
            }
            
            if ((i%9)%8 == 6) {
                lhs = 2;
            }
            
            //Set border
            textBox.setBorder(BorderFactory.createMatteBorder(top, lhs, bot, rhs, Color.black));
            gamePanel.add(textBox, this);
            puzzle.getGrid().addCell(textBox, i/9);
        }
        

        btnHolder.add(solveBtn);
        btnHolder.add(clearBtn);
        
        frame.getContentPane().add(gamePanel, layoutConstraints);
        
        layoutConstraints.gridy = 1;
        
        frame.getContentPane().add(btnHolder, layoutConstraints);
        frame.setVisible(true);
    }
    
    //Still doesn't get everything!
    public void solveRoutine() {
        
        while(!puzzle.isSolved()) {

            do {
                solve_FH_LD_NS();
            }
            while(puzzle.changedStatus());

            do {
                solve_RHS();
            }
            while(puzzle.changedStatus());

            solve_FH_LD_NS();

            if(puzzle.changedStatus()) {
                continue;
            }

            do {
                solve_CHS();
            }
            while(puzzle.changedStatus());

            solve_FH_LD_NS();

            if(puzzle.changedStatus()) {
                continue;
            }

            solve_RHS();

            if(puzzle.changedStatus()) {
                continue;
            }

            solve_CHS();

            if(puzzle.changedStatus()) {
                continue;
            }
            
            do {
                solve_BHS();
            }
            while(puzzle.changedStatus());
            
            solve_FH_LD_NS();

            if(puzzle.changedStatus()) {
                continue;
            }

            solve_RHS();

            if(puzzle.changedStatus()) {
                continue;
            }

            solve_CHS();

            if(puzzle.changedStatus()) {
                continue;
            }
            
            solve_BHS();
            
            if(!puzzle.changedStatus()) {
                break;
            }
        }
        
        frame.repaint();
        frame.setVisible(true);

    }
    
    private void solve_FH_LD_NS() {
        puzzle.clearPossibilities();
        puzzle.findPossibilities();
        puzzle.find_FH_LD_NS();
    }
    
    private void solve_RHS() {
        puzzle.clearPossibilities();
        puzzle.findPossibilities();
        puzzle.find_RHS();
    }
    
    private void solve_CHS() {
        puzzle.clearPossibilities();
        puzzle.findPossibilities();
        puzzle.find_CHS();
    }
    
    private void solve_BHS() {
        puzzle.clearPossibilities();
        puzzle.findPossibilities();
        puzzle.find_BHS();
    }
    
    class SolveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            solveRoutine();
        }
    }
    
    class ClearListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            
            for(int i = 0; i < 81; i++) {
                puzzle.getGrid().getCell(i/9, i%9).setText("");
            }
            frame.repaint();
            frame.setVisible(true);
        }
    }
    
    class CellListener implements FocusListener {

        @Override
        public void focusGained(FocusEvent fe) {
            //Intentionally blank
        }

        @Override
        public void focusLost(FocusEvent fe) {
            //Not needed - to do - swich boxes by arrows, tab etc
        }
    }
}
