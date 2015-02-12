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
public class Grid {
    private ArrayList<ArrayList<Cell>> numbers;
    
    public Grid() {
        numbers = new ArrayList<>();
        
        for(int i = 0; i < 81; i++) {
            
            if(i / 9 == 0) {
                numbers.add(new ArrayList<>(9));
            }
        }
    }
    
    public void addCell(Cell value, int rowIndex) {
        numbers.get(rowIndex).add(value);
    }
    
    public Cell getCell(int rowIndex, int colIndex) {
        return numbers.get(rowIndex).get(colIndex);
    }
    
    public ArrayList<Integer> getRowValues(int rowIndex) {
        ArrayList<Integer> row = new ArrayList<>();
        
        //Might be slightly contrived
        for(Cell element : numbers.get(rowIndex)) {
            row.add(element.getValue());
        }
        
        return row;
    }
    
    public ArrayList<Integer> getColumnValues(int columnIndex) {
        ArrayList<Integer> column = new ArrayList<>();
        
        for(int i = 0; i < 9; i++) {
            column.add(getRowValues(i).get(columnIndex));
        }
        
        return column;
    }
    
    //For the box, /3 and %3
    public ArrayList<Integer> getBoxValues(int rowIndex, int columnIndex) {
        ArrayList<Integer> box = new ArrayList<>();
        
        for(int i = (rowIndex / 3 + 2 * (rowIndex / 3)); 
                i < (rowIndex / 3 + 2 * (rowIndex / 3) + 3); i++) {
            
            for(int j = (columnIndex / 3 + 2 * (columnIndex / 3));
                    j < (columnIndex / 3 + 2 * (columnIndex / 3) + 3); j++) {
                
                box.add(this.getCell(i,j).getValue());
            }
        }
        
        return box;
    }

    public ArrayList<Cell> getRowCells(int rowIndex) {
        return numbers.get(rowIndex);
    }

    public ArrayList<Cell> getColCells(int colIndex) {
        ArrayList<Cell> column = new ArrayList<>();
        
        for(int i = 0; i < 9; i++) {
            column.add(numbers.get(i).get(colIndex));
        }
        
        return column;
    }
    
    //NB boxIndex set left to right, top to bottom.
    
    public ArrayList<Cell> getBoxCells(int rowIndex, int colIndex) {
        ArrayList<Cell> box = new ArrayList<>();
        
        for(int i = rowIndex; i < rowIndex + 2; i++) {
            
            for(int j = colIndex; j < colIndex + 2; j++) {
                
                box.add(this.getCell(i,j));
            }
        }
        
        return box;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.numbers);
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
        
        final Grid other = (Grid) obj;
        
        return Objects.equals(this.numbers, other.numbers);
    }
    
    
}
