/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Alistair
 */
public class Puzzle {
    private Grid data;
    private boolean hasChanged;
    private boolean changed;
    
    public Puzzle() {
        data = new Grid();
        hasChanged = false;
        changed = false;
    }
    
    public Grid getGrid() {
        return data;
    }
    
    public boolean changedStatus() {
        return hasChanged;
    }
    
    public void clearPossibilities() {
        hasChanged = false;
        
        for(int i = 0; i < 81; i++) {
            data.getCell(i/9, i%9).clearPossValues();
        }
    }
    
    public void findPossibilities() {
        
        for(int i = 0; i < 81; i++) {
            Cell selectedCell = data.getCell(i/9, i%9);
            
            if(selectedCell.getValue() == 0) {
                
                //Take a guess
                for(int j = 1; j < 10; j++) {
                    
                    if(!(data.getBoxValues(i/9, i%9).contains(j) ||
                         data.getRowValues(i/9).contains(j) ||
                         data.getColumnValues(i%9).contains(j) || 
                         selectedCell.getPossValues().contains(j))) {
                        
                        selectedCell.addPossValue(j);
                    }
                }
            }
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.data);
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
        
        final Puzzle other = (Puzzle) obj;
        
        return this.getGrid().equals(other.getGrid()); 
    }
    
    public boolean isSolved() {
        
        for(int i = 0; i < 81; i++) {
            
            //Check getValue in Cell class for how it evaluates to zero
            
            if(data.getCell(i/9, i%9).getValue() == 0) {
                return false;
            }
        }
        
        return true;
    }
    
    //Return type indicates if method has found a value this pass
    
    public void find_FH_LD_NS() {
        
        for(int i = 0; i < 81; i++) {
            Cell selectedCell = data.getCell(i/9, i%9);
            
            if(data.getCell(i/9, i%9).getValue() == 0) {
                
                //Check for size 1 possValue lists
                if (data.getCell(i/9, i%9).getPossValues().size() == 1) {
                    
                    data.getCell(i/9, i%9).setText(data.getCell(i/9, i%9).getPossValues().get(0).toString());
                    hasChanged = true;
                    changed = true;
                }
            }
        }
    }
    
    public void find_RHS() {
        
        for(int i = 0; i < 9; i++) {
            
            ArrayList<Cell> selectedRow = data.getRowCells(i);
            
            //Check for a single occurence of j in each row/column/box
            
            for(int j = 1; j < 10; j++) {
                int count = 0;
                int rowIndex = 0;
                int colIndex = 0;
                
                for(int k = 0; k < 9; k++) {
                    Cell selectedCell = selectedRow.get(k);
                    
                    if(selectedCell.getValue() == 0) {
                        
                        if(selectedCell.getPossValues().contains(j)) {
                            
                            count++;
                            rowIndex = i;
                            colIndex = k;
                        }
                    }
                    
                    if(count > 1) {
                        break;
                    }
                    
                    if(count == 1 && k == 8) {
                        data.getCell(rowIndex, colIndex).setText(
                                Integer.toString(j));
                        data.getCell(rowIndex, colIndex).repaint();
                        
                        hasChanged = true;
                        changed = true;
                    }
                }
            }
        }
    }
    
    public void find_CHS() {
        
        for(int i = 0; i < 9; i++) {
            
            ArrayList<Cell> selectedCol = data.getColCells(i);
            
            //Check for a single occurence of j in each column
            
            for(int j = 1; j < 10; j++) {
                int count = 0;
                int rowIndex = 0;
                int colIndex = 0;
                
                for(int k = 0; k < 9; k++) {
                    Cell selectedCell = selectedCol.get(k);
                    
                    if(selectedCell.getValue() == 0) {
                        
                        if(selectedCell.getPossValues().contains(j)) {
                            
                            count++;
                            rowIndex = k;
                            colIndex = i;
                        }
                    }
                    
                    if(count > 1) {
                        break;
                    }
                    
                    if(count == 1 && k == 8) {
                        data.getCell(rowIndex, colIndex).setText(
                                Integer.toString(j));
                        data.getCell(rowIndex, colIndex).repaint();
                        
                        hasChanged = true;
                        changed = true;
                    }
                }
            }
        }
    }
    
    public void find_BHS() {
        
        for(int i = 0; i < 9; i++) {
            
            ArrayList<Cell> selectedBox = data.getBoxCells(i, (i%3)*3);
            
            //Check for a single occurence of j in each box
            
            for(int j = 1; j < 10; j++) {
                int count = 0;
                int rowIndex = 0;
                int colIndex = 0;
                
                for(int k = 0; k < 9; k++) {
                    
                    Cell selectedCell = selectedBox.get(k);
                    
                    if(selectedCell.getValue() == 0) {
                        
                        if(selectedCell.getPossValues().contains(j)) {
                            
                            count++;
                            rowIndex = k;
                            colIndex = i;
                        }
                    }
                    
                    if(count > 1) {
                        break;
                    }
                    
                    if(count == 1 && k == 8) {
                        data.getCell(rowIndex, colIndex).setText(
                                Integer.toString(j));
                        data.getCell(rowIndex, colIndex).repaint();
                        
                        hasChanged = true;
                        changed = true;
                    }
                }
            }
        }
    }

    public boolean changed() {
        return changed;
    }
    
    public void restChanged() {
        changed = false;
    }
}
