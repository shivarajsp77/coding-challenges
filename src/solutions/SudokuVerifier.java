package solutions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class SudokuVerifier
{

    // A map to hold column number as Key and the set of numbers in that column
    // as Value
    private static Map<Integer, Set<Integer>> sColumnMap = new HashMap<Integer, Set<Integer>>();

    // Grid Map stores the Key as the grid number and values in 3*3 grid as the
    // Value
    private static Map<Integer, Set<Integer>> sGridMap   = null;

    private static boolean                    sGridCheck;
    
    
    /**
     * 
     * @param rowCheckSet  The set containing the all the row values
     * @param row  current row number
     * @param col  current column number
     * @param val  current value being checked
     * @return false if this value is repeated in either row, column or the 3*3 grid, else true
     */
    private static boolean verifySudoku(Set<Integer> rowCheckSet, int row, int col, int val)
    {
        // row checker
        if(rowCheckSet.contains(val))
        {
            return false; 
        }
        else
        {
            rowCheckSet.add(val);
        }

        //column checker
        Set<Integer> columnSet = new HashSet<Integer>();
        if(sColumnMap.get(col) != null)
        {
            columnSet = sColumnMap.get(col);
        }
        if(columnSet.contains(val))
        {
            return false;
        }
        else
        {
            columnSet.add(val);
            sColumnMap.put(col, columnSet);
        }

        //grid checker: resets after every 3 rows, to start a new set of 3 grids
        if(sGridCheck && row%3 == 0)
        {
            sGridMap = new HashMap<Integer, Set<Integer>>();
            sGridCheck = false;
        }

        // gridKey: To put the number in the Set corresponding to the correct key
        int gridKey = col / 3;
        Set<Integer> gridSet = new HashSet<Integer>();

        if(sGridMap.get(gridKey) != null)
        {
            gridSet = sGridMap.get(gridKey);
        }

        if(gridSet.contains(val))
        {
            return false;
        }
        else
        {
            gridSet.add(val);
            sGridMap.put(gridKey, gridSet);
        }
        return true;
    }
    
    
    public static void main(String[] args)
    {
      
      Scanner stdin = new Scanner(System.in);
      // Read the input values from 1 to 9 left to right row by row.
      String values = stdin.next();
      char[] chArr = values.toCharArray();
      int charIndex = 0;
      
      // A single set to check the uniqueness of the numbers in each row
      Set<Integer> rowCheckSet;
      boolean sudokuCheck = true;
      int rowCount, colCount;
      rowCount = colCount = 9;
      
      for(int row = 0; row < rowCount; row++)
      {
          sGridCheck = true;
          rowCheckSet = new HashSet<Integer>();
          for(int col = 0; col < colCount; col++)
          {
              int val = Character.getNumericValue(chArr[charIndex++]);
              if(!verifySudoku(rowCheckSet, row, col, val))
              {
                  System.out.println("Incorrect solution");
                  row = rowCount;
                  sudokuCheck = false;
                  break;
              }
          }
      }
      stdin.close();
      
      if(sudokuCheck)
      {
          System.out.println("Correct solution");
      }
      
    }
}

