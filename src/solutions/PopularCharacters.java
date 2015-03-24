
package solutions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * @author spatil
 *
 */
public class PopularCharacters
{

    public static void main(String[] args)
    {
        Scanner stdin = new Scanner(System.in);
        int numbers = stdin.nextInt();
        
        int j = 0;
        List<String> inputList = new ArrayList<String>();
        while(j<numbers)
        {
            inputList.add(stdin.next());
            j++;
        }
        stdin.close();
        
        System.out.println("Number of popular character/s is/are: " + getPopularCount(inputList));
    }
    
    private static int getPopularCount(List<String> inputList)
    {
        if(inputList.size() != 0)
        {
            if(inputList.size() == 1)
                return inputList.get(0).length();
            
            String popular = inputList.get(0);
            for(int i = 1; i < inputList.size(); i++)
            {
                popular = getCommon(popular, inputList.get(i));
            }
            if(popular.length() > 0)
            {
                System.out.println("Popular character/s is/are : " + popular);
            }
            return popular.length();
        }
        return 0;
    }
    
    private static String getCommon(String a , String b)
    {
        HashSet<Character> h1 = new HashSet<Character>(), h2 = new HashSet<Character>();
        for(int i = 0; i < a.length(); i++)                                            
        {
            h1.add(a.charAt(i));
        }
        for(int i = 0; i < b.length(); i++)
        {
            h2.add(b.charAt(i));
        }
        h1.retainAll(h2);
        
        StringBuilder sb = new StringBuilder(100);
        Iterator<Character> iterator = h1.iterator();
        while(iterator.hasNext()){
            sb.append(iterator.next());
        }
        
        return sb.toString();
    }

}
