package solutions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class SudokuChecker
{

    public static boolean checkSudoku(int[][] inputArr)
    {
        // A map to hold column number as Key and the set of numbers in that column as Value
        Map<Integer, Set<Integer>> columnMap = new HashMap<Integer, Set<Integer>>();
        
        // Grid Map stores the Key as the grid number and values in 3*3 grid as the Value
        Map<Integer, Set<Integer>> gridMap = null;
        
        // A single set to check the uniqueness of the numbers in each row
        Set<Integer> rowCheckSet;
        boolean gridCheck;
        
        for(int i = 0; i < inputArr.length; i++)
        {
            gridCheck = true;
            rowCheckSet = new HashSet<Integer>();
            for(int j = 0; j < inputArr.length; j++)
            {
                // row checker
                if(rowCheckSet.contains(inputArr[i][j]))
                {
                    return false; 
                }
                else
                {
                    rowCheckSet.add(inputArr[i][j]);
                }
                
                
                //column checker
                Set<Integer> columnSet = new HashSet<Integer>();
                if(columnMap.get(j) != null)
                {
                    columnSet = columnMap.get(j);
                }
                
                if(columnSet.contains(inputArr[i][j]))
                {
                    return false;
                }
                else
                {
                    columnSet.add(inputArr[i][j]);
                    columnMap.put(j, columnSet);
                }
                
                
                //grid checker: resets after every 3 rows, to start a new set of 2 grids
                if(gridCheck && i%3 == 0)
                {
                    gridMap = new HashMap<Integer, Set<Integer>>();
                    gridCheck = false;
                }
                
                // gridKey: To put the number in the Set corresponding to the correct key
                int gridKey = j / 3;
                Set<Integer> gridSet = new HashSet<Integer>();
                
                if(gridMap.get(gridKey) != null)
                {
                    gridSet = gridMap.get(gridKey);
                }
                
                if(gridSet.contains(inputArr[i][j]))
                {
                    return false;
                }
                else
                {
                    gridSet.add(inputArr[i][j]);
                    gridMap.put(gridKey, gridSet);
                }
            }
        }
        return true;
    }
    
    
    public static void main(String[] args)
    {
      int[][] arr = new int[9][9];
      
      Scanner stdin = new Scanner(System.in);
      
      // Read the input values from 1 to 9 left to right row by row.
      String values = stdin.next();
      char[] chArr = values.toCharArray();
      int charIndex = 0;
      for(int i = 0; i < 9; i++)
      {
          for(int j = 0; j < 9; j++)
          {
              arr[i][j] = Character.getNumericValue(chArr[charIndex++]);
          }
      }
      stdin.close();
      
      if(checkSudoku(arr))
      {
          System.out.println("Correct solution");
      }
      else
      {
          System.out.println("Incorrect solution");
      }
    }
    
}

