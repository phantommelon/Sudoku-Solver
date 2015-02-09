/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic;

import java.awt.Toolkit;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/*
 * @author Alistair
 */

public class SudokuIntFilter extends DocumentFilter {
    private final int MAX_WIDTH = 1;
    
    public SudokuIntFilter() {

    }
    
    public boolean isInteger(String input) {
        
        try {
            Integer.parseInt(input);
            return Integer.parseInt(input) != 0;
        }
        
        catch(NumberFormatException nfe) {
            return false;
        }
    }
    
    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offs,
            String str, AttributeSet a) throws BadLocationException {

        if (((fb.getDocument().getLength() + str.length()) <= MAX_WIDTH && 
                isInteger(str)) || str.equals("")) {
            super.insertString(fb, offs, str, a);
        }
        
        else {
            Toolkit.getDefaultToolkit().beep();
        }
    }
    
    @Override
    public void replace(FilterBypass fb, int offs, int length, String str, 
            AttributeSet a)
    throws BadLocationException {
        
        if (((fb.getDocument().getLength() + str.length()
                - length) <= MAX_WIDTH && isInteger(str)) || str.equals("")) {
            super.replace(fb, offs, length, str, a);
        }
        
        else {
            Toolkit.getDefaultToolkit().beep();
        }
    }
}