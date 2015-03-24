/**
 * 
 */
package solutions;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author spatil
 *
 */
public class ManasaAndStones
{

    public static void main(String[] args)
    {
        Scanner stdin = new Scanner(System.in);
        int noOfStones = stdin.nextInt();
        int a = stdin.nextInt();
        int b = stdin.nextInt();
        stdin.close();
        
        // Do checks on the input values before proceeding
        Set<Integer> finalSet = getPossibleStones(noOfStones, a, b);
        for (Iterator<Integer> it = finalSet.iterator(); it.hasNext(); )
        {
            System.out.print(it.next() + " ");
        }
    }
    
    public static Set<Integer> getPossibleStones(int n, int a, int b)
    {
        SortedSet<Integer> finalSet = new TreeSet<Integer>();
        for(int i = 0; i < n; i++)
        {
            finalSet.add(a*i + (n-1-i)*b);
        }
        return finalSet;
    }

}
