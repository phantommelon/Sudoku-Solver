/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic;

import java.awt.Font;
import java.util.ArrayList;
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

    void clearPossValues() {
        possValues.clear();
    }
}