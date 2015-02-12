/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.JTextField;

/**
 *
 * @author Alistair
 */
public class Cell extends JTextField {
    private ArrayList<Integer> possValues;
    
    public Cell() {
        possValues = new ArrayList<>();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        this.setHorizontalAlignment(JTextField.CENTER);
        this.setFont(new Font("Arial", Font.PLAIN, 20));
    }
    
    public int getValue() {
        
        if("".equals(this.getText())) {
            return 0;
        }
        
        return Integer.parseInt(this.getText());
    }
    
    public void addPossValue(int value) {
        possValues.add(value);
    }
    
    public ArrayList<Integer> getPossValues() {
        return possValues;
    }

    public void clearPossValues() {
        possValues.clear();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.getText());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        
        if (this == obj) {
            return true;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final Cell other = (Cell) obj;
        
        return other.getText().equals(this.getText());
    }
    
    
}