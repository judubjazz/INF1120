
/**
 * I N F x 1 2 0
 *
 * D�crivez votre classe sansdoublon ici.
 * 
 * @author (votre nom) 
 * @version (une date)
 *
 * (votre code permanent)
 * (votre adresse de courriel)
 */
 
public class sansdoublon {

    // Autres m�thodes s'il y a lieu
    public static boolean sansDoublon (int [] tab ) {
         boolean answer= true;
         int k = 1;
        
        for (int i= 0; i < tab.length; i++) {
            
            for (int j= k ; j< tab.length; j++) {
                if (tab[j] == tab[i]) {
                    answer= false;
                }
            }
            k++;
        }
        return answer;
    }
    public static void main (String[] params) {
        int [] tab = {1,2,3,4,5,6,8,9};
        
        System.out.print (sansDoublon (tab));
        
    } // main
    
} // sansdoublon
