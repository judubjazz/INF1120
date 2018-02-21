
/**
 * I N F x 1 2 0
 *
 * D�crivez votre classe Contact ici.
 * 
 * @author Julien Guité-Vinet 
 * @version 26/11/2016
 *
 * GUIJ09058407
 * guite-vinet.julien@courrier.uqam.ca
 */

public class Contact {

    // variable(s) de classe s'il y a lieu
    private static int nbrContactsFavoris;
    private static int nbrContacts;
    
    
    // variable(s) d'instance s'il y a lieu
    private String nom;
    private String prenom;
    private Telephone []telephones;
    private int nbrTelephones;
    private Adresse adresse;
    private String courriel;
    private boolean favori;
    
    // constructeur(s) s'il y a lieu
    /**Ce constructeur construit un objet contact en initialisant ses attributs avec les valeurs passées en paramètre. De plus :
     * L’attribut favori doit être initialisé à false.
     * Le tableau de téléphones est initialisé avec un tableau de DEUX cases, chacune contenant la valeur null.
     * Si le paramètre tel est non null, il est ajouté au tableau de téléphones de ce contact. Sinon, aucun téléphone
     * n’est ajouté.
     * Met à jour l’attribut nbrTelephones, s’il y a lieu.
     * Si le paramètre courriel est vide, le courriel de ce contact est initialisé à null.
     * Si le paramètre nom ou le paramètre prenom est null ou vide, le constructeur lève une exception
     * ContactInvalideException.
     * @param nom: le nom du contact
     * @param prenom: le prenom du contact
     * @param tel: le telephone du contact
     * @param adresse: l adresse du contact 
     * @param courriel: le courriel du contact
     * throws ContactInvalideException si le contact est invalide.
     */
    public Contact (String nom, String prenom, Telephone tel, Adresse adresse, String courriel) throws ContactInvalideException  {
         if ( nom == null || nom.equals("") || prenom == null || prenom.equals("")) {
            ContactInvalideException e = new ContactInvalideException ();
            throw e;
        } 
        try {
            if (courriel.equals("") ){
                courriel = null;
            }
        }catch (Exception e) {
        }
            this.nom = nom;
            this.prenom = prenom;
            favori = false;
            telephones = new Telephone [] {null, null};
            this.adresse = adresse;
            this.courriel = courriel;
            nbrContacts++;    
        if (tel != null) {
            for (int i = 0 ; i < telephones.length ; i++) {
                try {
                    if (telephones [i] == null) {
                        telephones [i] = tel;
                        nbrTelephones++;
                        i = telephones.length;
                    }else {
                        Telephone [] t = new Telephone [telephones.length + 2];
                        for (int j = 0 ; j < telephones.length ; j++) {
                            t[i] = telephones[i];
                        }
                        telephones = t;
                        i=0;
                    }
                 }catch (ArrayIndexOutOfBoundsException e){
                     
                }          
            }
        }
        
                  
    }    
    /**Ce constructeur construit un objet contact en initialisant ses attributs nom et prenom avec les valeurs passées en
     * paramètre. De plus :
     * L’attribut favori est initialisé à false.
     * Le tableau de téléphones est initialisé avec un tableau de DEUX cases, chacune contenant la valeur null.
     * Aucun téléphone n’est ajouté au tableau de téléphones du contact à construire.
     * Les attributs adresse et courriel sont initialisés à null.
     * @param nom: le nom du contact
     * @param prenom: le prenom du contact
     * throws ContactInvalideException si le contact est invalide.
     */
    public Contact (String nom, String prenom ) throws ContactInvalideException {
         if ( nom == null || nom.equals("") || prenom == null || prenom.equals("")) {
            ContactInvalideException e =  new ContactInvalideException ();
            throw e;
            
        }         
        this.nom = nom;
        this.prenom = prenom;
        favori = false;
        telephones = new Telephone [] {null, null};
        adresse = null;
        courriel = null;
        nbrContacts++;
    }
     /**
    * Constructeur vide qui ne fait rien. 
    */
   public Contact () {
   }
    // m�thode(s) de classe s'il y a lieu
    /**Permet de modifier la valeur de l’attribut de classe nbrContactsFavoris par la valeur passée en paramètre
     * 
     */
    public static void modifierNbrContactsFavoris (int nbr) {
        nbrContactsFavoris = nbr;
        
    }
     /**Permet de modifier la valeur de l’attribut de classe nbrContacts par la valeur passée en paramètre
     * 
     */
    public static void modifierNbrContacts (int nbr) {
        nbrContacts = nbr;
        
    }
     /**Permet d’obtenir la valeur de l’attribut de classe nbrContactsFavoris
     * 
     */
    public static int obtenirNbrContactFavoris () {
       
        return nbrContactsFavoris;
        
    }
    /**Permet d’obtenir la valeur de l’attribut de classe nbrContacts
     * 
     */
    public static int obtenirNbrContacts () {
       
        return nbrContacts;
        
    }    
    // m�thode(s) d'instance s'il y a lieu
    
    /**Permet d’ajouter le tel donné en paramètre au tableau telephones de ce contact. N’oubliez pas d’ajuster l’attribut
     * nbrTelephones, s’il y a lieu. De plus :
     * Si tel est null, aucun téléphone n’est ajouté.
     * Si tel n’est pas null, celui-ci est ajouté dans LA PREMIÈRE CASE LIBRE du tableau telephones de ce
     * contact. Une case libre est une case contenant null.
     * Par exemple, si telephones = {tel1, tel2, null, tel3, null, null}, le téléphone sera ajouté dans
     * la case 2.
     * S’il n’y a plus de place dans le tableau telephones, la méthode doit agrandir le tableau telephones de DEUX
     * CASES supplémentaires avant d’y ajouter le tel donné en paramètre dans la première case libre.
     * @param tel :un telephone
     */
    public void ajouterTelephone (Telephone tel) {
        boolean libre = false;
        if (tel == null) {
            telephones = telephones;
        }else {
            for (int i = 0 ; i < telephones.length ; i++) {
                if (telephones [i] == null) {
                    libre = true;
                    i = telephones.length;
                }else {                    
                    libre = false;
                }
            }
            if (libre == true) {
                for (int i = 0; i < telephones.length; i++) {
                    if (telephones [i] == null) {
                        telephones [i] = tel;
                        nbrTelephones++;
                        i = telephones.length;
                    }
               }               
            }else {
                Telephone [] tab = new Telephone [telephones.length + 2];
                for (int j = 0; j < telephones.length; j++) {
                    tab [j] = telephones [j];
                   
                }
                telephones = tab;
                for (int i = 0; i < telephones.length; i++) {
                    if (telephones [i] == null) {
                        telephones [i] = tel;
                        nbrTelephones++;
                        i = telephones.length;
                    }
                }
            }                    
        }        
    }
    /**Permet d’obtenir le ième téléphone du tableau de téléphones de ce contact. S’il n’y a pas de ième téléphone dans le
     * tableau de téléphones, la méthode retourne null.
     * Dans le tableau telephones, on détermine le ième comme suit :
     * 1er = le premier téléphone non null dans le tableau de téléphones.
     * 2ième = le deuxième téléphone non null dans le tableau de téléphones.
     * nième = le nième téléphone non null dans le tableau de téléphones.
     * Par exemple, si telephones = {tel1, tel2, null, tel3, null, null} :
     * ieme = 1 correspond à tel1 dans le tableau.
     * ieme = 2 correspond à tel2 dans le tableau.
     * ieme = 3 correspond à tel3 dans le tableau.
     * ieme = 0 ou -1 ou 4 ne correspond à aucun téléphone dans le tableau
     * @param ieme le ieme numero que le user desir obtenir.
     * @return un telephone si present, sinon null
     */
    public Telephone obtenirIemeTelephone (int ieme) {
        int i = 0;
        Telephone answer = null;
    
        while (i < telephones.length){
            try{
                if (telephones [i] != null) {
                    if (ieme == i+1) {
                        answer = telephones [i];
                    }
                }            
            }catch (NullPointerException e) {                  
                
            }catch (ArrayIndexOutOfBoundsException e) {
                i=telephones.length;
            }
            i++;
        }
        return answer;
    }
    /**Permet de supprimer le ième téléphone du tableau de téléphones de ce contact. S’il n’y a pas de ième téléphone dans le
     * tableau de téléphones, aucun téléphone n’est supprimé.
     * ajuste l’attribut nbrTelephones.
     * @param ieme le numero de tel que le user veut supprimer
     * @return  true si la suppression a eu lieu, false sinon
     */
    public boolean supprimerTelephone (int ieme) {
        int i = 0;
        boolean answer = false;
         while (i < telephones.length){
            try{
                if (i == ieme -1) {
                    if (telephones [i] == null) {
                        i = telephones.length;
                    }else{
                        telephones [i] = null;
                        nbrTelephones = nbrTelephones - 1;
                        answer = true;
                    }
                }            
            }catch (NullPointerException e) {                  
                
            }catch (ArrayIndexOutOfBoundsException e) {
                i=telephones.length;
                answer = false;
            }
            i++;
        }        
        return answer;
    }
    /**Permet de modifier le type, le numero, et le poste du ième téléphone de ce contact. De plus :
     * Si le paramètre ieme ne correspond à aucun téléphone dans le tableau de téléphones de ce contact, aucune
     * modification n’est effectuée.
     * Si le paramètre type est null, le type du ième téléphone n’est pas modifié.
     * Si le paramètre poste est null, le poste du ième téléphone n’est pas modifié.
     * Si le paramètre numero est null, le poste du ième téléphone n’est pas modifié.
     * Si le paramètre numero n’est pas null, et n’est pas valide, le numéro du ième téléphone n’est pas modifié, et la
     * méthode lève une exception ContactInvalideException. Un numéro valide est une chaine ne contenant
     * que des caractères numériques de longueur Telephone.LNG_NUM_1 ou Telephone.LNG_NUM_2.
     * 
     */
    public void modifierTelephone (int ieme, String type , String numero, String poste) throws ContactInvalideException {
        boolean valide ;
        for (int i = 0 ; i < telephones.length; i++) {
            if (telephones [ieme - 1] == telephones [i]) {         
                if (type != null) {
                   telephones [ieme - 1].setType(type); 
                }
                if (poste != null) {
                   telephones [ieme - 1].setPoste(poste); 
                }
                valide = Telephone.numeroTelValide (numero);
                try {
                    if (type != null && valide == true) {                  
                          telephones [ieme - 1 ].setNumero(numero);                   
                    }else if (type != null && valide == false) {
                        ContactInvalideException e = new ContactInvalideException ();
                        throw e;
                    }
                }catch (TelephoneInvalideException e){
                }catch (NullPointerException e) {
                }
            }
        }
    }
    
    /**Retourne une représentation sous forme de chaine de caractères de ce contact. La chaine retournée doit respecter le
     * format montré dans les exemples suivants.
     * differe de toString() car rajoute les petites lignes
     * @return la chaine representant le contact
     */
    public String toString2 () {
        String s = "";
        String tel = "";
        String telBackup = "";
        String sfavori = " [FAVORI]";
        String ligne = "";
        char c = '-';
        int full = 0;
        boolean tabNull = false;
       for (int i = 0 ; i < telephones.length ; i++) {
            if (telephones [i] == null) {
                
                full++;
            }
       }
       if (full == telephones.length) {
           tabNull = true; 
       }
       for (int i = 0; i < telephones.length; i++) {
            telBackup = tel;
            tel = tel + "\n" + String.valueOf(i +1) + ". " + telephones[i];
            if (telephones[i] == null) {
                tel = telBackup;
                
                
            }
       }
       
        try {
            if (favori == false) { 
                ligne = CarnetContacts.ligne(c,nom.length() + prenom.length() + 2) + "\n" ;              
                s = ligne  + nom.toUpperCase() + ", " + prenom + "\n" + ligne;
                
            }else {
                ligne = CarnetContacts.ligne(c,nom.length() + prenom.length() + sfavori.length() + 2) + "\n";
                s = ligne + nom.toUpperCase() + ", " + prenom + " [FAVORI]\n" + ligne;
              
            }
            if (tabNull == true) {
                s = s + "\nTELEPHONE(S) : Aucun." ;                                   
            }else {
                s = s + "\nTELEPHONE(S) : " + tel;
            }
            if (adresse == null) {
                s = s + "\n\nADRESSE : Aucune.";
                
            }else {
                s = s + "\n\nADRESSE : \n" + adresse; 
            }
            if (courriel == null) {
                s = s + "\n\nCOURRIEL : Aucun.\n"; 
            }else {
                s = s + "\n\nCOURRIEL : " + courriel + "\n"; 
            }
       }catch (NullPointerException e ){
        }
        System.out.print(s);
        return s;
    }
     /**Retourne une représentation sous forme de chaine de caractères de ce contact. La chaine retournée doit respecter le
     * format montré dans les exemples suivants.
     * @return la chaine representant le contact
     */
    public String toString () {
        String s = "";
        String tel = "";
        String telBackup = "";
        boolean tabNull = false;
        int full = 0;
         for (int i = 0 ; i < telephones.length ; i++) {
            if (telephones [i] == null) {
                
                full++;
            }
       }
       if (full == telephones.length) {
           tabNull = true; 
       }
        for (int i = 0; i < telephones.length; i++) {
            telBackup = tel;
            tel = tel + "\n" + String.valueOf(i +1) + ". " + telephones[i];
            if (telephones[i] == null) {
                tel = telBackup;
         
                
            }
        }
       
        try {
            if (favori == false) { 
                s = nom.toUpperCase() + ", " + prenom;
            }else {
                s = nom.toUpperCase() + ", " + prenom + " [FAVORI]";
            }
            if (tabNull == true) {
                s = s + "\n\nTELEPHONE(S) : Aucun." ;                                   
            }else {
                s = s + "\n\nTELEPHONE(S) : " + tel;
            }
            if (adresse == null) {
                s = s + "\n\nADRESSE : Aucune.";
                
            }else {
                s = s + "\n\nADRESSE : \n" + adresse; 
            }
            if (courriel == null) {
                s = s + "\n\nCOURRIEL : Aucun.\n"; 
            }else {
                s = s + "\n\nCOURRIEL : " + courriel + "\n"; 
            }
       }catch (NullPointerException e ){
        }
        System.out.println(s);
       
        return s;
    }
    /**
    * Permet d'obtenir la valeur du nom de ce contact.
    * @return le nom de ce contact.
    */
    public String getNom () {
        return nom;
    }
    /**
    * Permet d'obtenir la valeur du prenom de ce contact.
    * @return le prenom de ce contact.
    */
     public String getPrenom () {
        return prenom;
    }
    /**
    * Permet d'obtenir la valeur du tableau de telephone de ce contact.
    * @return le tableau de telephone de ce contact.
    */
     public Telephone [] getTel () {
        return telephones;
    }
    /**
    * Permet d'obtenir la valeur de l adresse de ce contact.
    * @return l adresse de ce contact.
    */
     public Adresse getAdresse () {
        return adresse;
    }
    /**
    * Permet d'obtenir la valeur du courriel de ce contact.
    * @return le courriel de ce contact.
    */
     public String getCourriel() {
        return courriel;
    }
    /**
    * Permet d'obtenir la valeur du nbr de telephone de ce contact.
    * @return le nbr de telephone de ce contact.
    */
     public int getNbrTelephones () {
        return nbrTelephones;
    }
    /**
    * Permet de savoir si ce contact est un favori.
    * @return true si le contact est favori, false sinon.
    */
     public boolean isFavori () {
        return favori;
    }
      /**
    * Modifie le nom de ce contact par celui passe en parametre. 
    * @param nom le nouveau nom du contact.
    * throws ContactInvalideException Si le nom donne en parametre est null ou vide 
    */
     public void setNom (String nom) throws ContactInvalideException {
        if (nom == null || nom.length() < 1) {
            throw new ContactInvalideException ();
        }
        this.nom = nom;
    }
      /**
    * Modifie le prenom de ce contact par celui passe en parametre. 
    * @param prenom le nouveau prenom du contact.
    * throws ContactInvalideException Si le prenom donne en parametre est null ou vide 
    */
     public void setPrenom (String prenom) throws ContactInvalideException {
        if (prenom == null || prenom.length() < 1) {
            throw new ContactInvalideException ();
        } 
        this.prenom = prenom;
    }
       /**
    * Modifie l adresse de ce contact par celui passe en parametre. 
    * @param adresse la nouvelle adresse du contact. 
    */
     public void setAdresse (Adresse adresse) {
        this.adresse = adresse;
    }
       /**
    * Modifie le courriel de ce contact par celui passe en parametre. 
    * @param courriel le nouveau courriel du contact. 
    */
     public void setCourriel (String courriel) {
        this.courriel = courriel;
    }
       /**
    * Modifie l attribut favori par celui passe en parametre. 
    * @param favori true si favori, false sinon. 
    * Ajuste la variable de classe nbrContactsFavoris.
    */
     public void setFavori (boolean favori) {
         if (favori == true) {
             nbrContactsFavoris++;
            }else {
             nbrContactsFavoris-- ;  
            }
        this.favori = favori;
    }
   /**Methode a copier-coller dans votre classe Contact avant d'executer
   *  la classe de tests partiels.
   */
    public String toString2 (String attribut) {
      String s = "";
      if (attribut == null) {
         String contact = nom + " " + prenom + " " + favori + "\n";
         if (telephones == null) {
            contact = contact + "erreur";
         } else {
            for (Telephone tel : telephones) {
                  contact = contact + tel + "\n";
            }
         }
         contact = contact + adresse;
         contact = contact + "\n" + courriel;
      
         s = contact.trim();
      } else if (attribut.equals("nom")) {
         s = nom;
      } else if (attribut.equals("prenom")) {
         s = prenom;
      } else if (attribut.equals("adresse")) {
         s = "null";
         if (adresse != null) {
            s = adresse.toString();
         }
      } else if (attribut.equals("courriel")) {
         s = "null";
         if (courriel != null) {
            s = courriel;
         }
      } else if (attribut.equals("favori")) {
         s = favori + "";
      } else if (attribut.equals("nbrTelephones")) {
         s = nbrTelephones + "";
      } else if (attribut.equals("telephones")) {
         if (telephones == null) {
            s = "erreur";
         } else {
            s = "";
            for (Telephone tel : telephones) {
               s = s + tel + "\n";
            }
            s = s.trim();
         } 
      }
      return s;
   }
  
} // Contact
