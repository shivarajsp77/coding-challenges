package solutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RoboTraveler {
   
    private static final String ARROW_HEAD    = " -> ";
    private static final String EMPTY_STRING  = "";
    private static final String TAB_SEPARATOR = "\t";

    
    public static void calculateSum(int[][] arr) 
    {
        int totalRows = arr.length;
        int totalCols = arr[0].length;
        int sum = 0;
        int j = 0;
        String path = EMPTY_STRING;
        
        for (int i = 0; i < totalRows; i++) {
            boolean goRight = true;
            boolean goDown = false;
            
            while (j < totalCols) {
                int curr = arr[i][j];
                
                // check mainly for the 1st element, if it is negative
                if(curr >= 0){
                    path += String.valueOf(curr) + ARROW_HEAD;
                    sum += curr;
                }
                
                
                // check right
                if (checkNextElementExists(j + 1, totalCols)) {
                    if (arr[i][j + 1] < 0) {
                        sum += arr[i][j + 1];
                        // move down
                        goDown = true;
                        goRight = false;
                    }
                }

                // check up
                if (checkNextElementExists(i - 1, totalRows)) {
                    if (arr[i - 1][j] < 0) {
                        sum += arr[i - 1][j];
                    }

                }

                // check left
                if (checkNextElementExists(j - 1, totalCols)) {
                    if (arr[i][j - 1] < 0) {
                        sum += arr[i][j - 1];
                    }
                }

                // check down
                if (checkNextElementExists(i + 1, totalRows)) {
                    if (arr[i + 1][j] < 0) {
                        sum += arr[i + 1][j];
                        goDown = false;
                    } else {
                        goDown = true;
                    }
                }

                // Deadlock situation, when the robot can neither move right or down
                if(!goDown && !goRight){
                    i = totalRows;
                    break;
                }
                if (goDown) {
                    // go to next row
                    break;
                }
                if (goRight) {
                    // increment the column
                    j++;
                }
            }
        }

        // Print sum and the path
        System.out.println(sum);
        System.out.println(path.substring(0, path.lastIndexOf(ARROW_HEAD)));
    }

    public static boolean checkNextElementExists(int index, int length) 
    {
        return (index >= length || index < 0) ? false : true;
    }
    
    
    public static void main(String[] args)     
    {
        int[][] inputArr = null;
        
        // Pass the filepath as program args.
        String filePath = args[0];
        
        // Reading from the file
        File file = new File(filePath);
        int rowCount = 0;

        try 
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while (reader.readLine() != null)
            {
                rowCount++;
            }
            reader.close();
        } 
        catch (IOException e1) 
        {
            e1.printStackTrace();
        }
        try 
        {
            BufferedReader br = new BufferedReader(new FileReader(file));
            int row, col;
            row = col = 0;
            for (String line; (line = br.readLine()) != null;) 
            {
                // Split the line by tab
                String[] lineArr = line.split(TAB_SEPARATOR);
                if (inputArr == null) 
                {
                    inputArr = new int[rowCount][lineArr.length];
                }
                for (String strNum : lineArr) 
                {
                    int num = Integer.parseInt(strNum);
                    inputArr[row][col++] = num;
                }
                col = 0;
                row++;
            }
            br.close();
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        // to calculate the max sum and display path
        calculateSum(inputArr);

    }

}

