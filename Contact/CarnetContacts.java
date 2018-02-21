
import java.io.*;

/**
 * A COMPLETER...
 * 
 */
public class CarnetContacts {
   
   //-----------
   //CONSTANTES
   //-----------
   
   //DIVERS
   
   public static final File FIC_CONTACTS = new File("contacts.txt");
   public static final int LNG_INIT_TAB_CONTACTS = 2;
   public static final String SEP = ":";
   
   //BORNES DE VALIDATION
   
   public final static String MENU_MIN = "1";
   public final static String MENU_MAX = "5";
  
   //MESSAGES DIVERS
  
   public static final String PRESENTATION = "Ce programme permet de gerer un "
           + "carnet de contacts.";
   public final static String MSG_PAUSE = "\nAppuyez sur \"ENTREE\" pour "
           + "continuer...";
   
   public static final String MENU = "\n-----\nMENU\n-----\n"
           + "1. AJOUTER UN CONTACT\n2. SUPPRIMER UN CONTACT"
           + "\n3. VIDER LE CARNET DE CONTACTS\n4. AFFICHER LES CONTACTS"
           + "\n5. QUITTER\n\n"
           + "Entrez votre choix au menu : ";
   
   public final static String MSG_ERR_MENU = "\nErreur, entrez une valeur entre "
           + MENU_MIN + " et " + MENU_MAX + "... Recommencez.\n";
   
   public static final String SOUS_MENU_AFF_CONTACTS = 
           "1. AFFICHER TOUS LES CONTACTS"
           + "\n2. AFFICHER LES FAVORIS\n\n"
           + "Entrez votre choix : ";
   
   public final static String MSG_FIN_PROG = "\n\nFIN NORMALE DU PROGRAMME.";
   
   //-------------------------------------------------------
   //METHODES D'ENREGISTREMENT DES CONTACTS DANS UN FICHIER 
   //-------------------------------------------------------
   
   /**
    * Lit la premiere ligne du fichier ficContacts qui est un nombre entier, 
    * et le retourne. Ce nombre correspond au nombre de contacts enregistres
    * dans le fichier. Si ficContacts n'existe pas, retourne 0.
    * @param ficContacts le fichier dont on veut lire la premiere ligne.
    * @return l'entier lu sur la premiere ligne du fichier ficContacts s'il
    *         existe, 0 sinon.
    */
   public static int lireFichierNbrContacts (File ficContacts) {
      int nbr = 0;
      BufferedReader in;
      if (ficContacts.exists()) {
         try {
            in = new BufferedReader(new FileReader(ficContacts));
            nbr = Integer.parseInt(in.readLine().trim());
            in.close(); 
         } catch (IOException e) {
            //ne se produira pas
         } catch (NullPointerException e) {
            }
      }
      return nbr;
   }
   
      /**
    * Lit les contacts enregistres dans le fichier ficContacts et retourne
    * un tableau contenant ces contacts. Si le fichier n'existe pas, ou s'il ne
    * contient aucun contact, retourne un tableau de longueur LNG_INIT_TAB_CONTACTS 
    * dont toutes les cases sont initialisees a null.
    * @param ficContacts le fichier dans lequel lire les contacts.
    * @return un tableau contenant les contacts lus.
    */
   public static Contact [] lireFichierContacts (File ficContacts, 
                                                  int nbrContacts) {
      BufferedReader in;
      Contact [] contacts = new Contact[LNG_INIT_TAB_CONTACTS];
      Contact contact;
      String nom;
      String prenom;
      String tmp;
      int i = 0;
      int nbr;
      
      if (ficContacts.exists() && nbrContacts != 0) {
         contacts = new Contact[nbrContacts + 2];
         try {
            in = new BufferedReader(new FileReader(ficContacts));
            in.readLine(); //sauter le nombre de contacts
            
            while (in.ready()) {
               nom = in.readLine();
               prenom = in.readLine();
               contact = new Contact(nom, prenom);
               lireTelephonesDansFic(in, contact);
               lireAdresseDansFic(in, contact);
               
               tmp = in.readLine();
               if (!tmp.equals("null"))
                  contact.setCourriel(tmp);
               
               tmp = in.readLine();
               if (tmp.equals("true"))
                  contact.setFavori(true);
               
               contacts[i] = contact; 
               i++;
            }
            in.close();
            
         } catch (IOException | ContactInvalideException
                 | TelephoneInvalideException | AdresseInvalideException e) {
            //ne devrait pas se produire
         } 
      }
      return contacts;
   }
   
   /**
    * Lit l'adresse d'un contact dans le fichier associe au parametre in, puis
    * assigne l'adresse lue au contact donne.
    * @param in le flux associe au fichier a lire.
    * @param contact le contact auquel assigner l'adresse lue.
    * @throws AdresseInvalideException si l'adresse lue est invalide
    * @throws IOException s'il se produit une erreur d'entree/sortie.
    */
   private static void lireAdresseDansFic(BufferedReader in, Contact contact) 
                                  throws AdresseInvalideException, IOException {
      String tmp;
      String [] tokens;
      Adresse adresse = null;
      tmp = in.readLine();
      
      if (!tmp.equals("null")) {
         adresse = new Adresse();
         tokens = tmp.split(SEP);
         adresse.setNoPorte(tokens[0]);
         adresse.setNomRue(tokens[1]);
         tmp = tokens[2];
         if (tokens[2].equals("null"))
            tmp = null;
         adresse.setApt(tmp);
         adresse.setVille(tokens[3]);
         adresse.setProvinceEtat(tokens[4]);
         adresse.setPays(tokens[5]);
         tmp = tokens[6];
         if (tmp.equals("null"))
            tmp = null;
         adresse.setCodePostal(tmp);
      }
      contact.setAdresse(adresse);
      
   }
   
   /**
    * Lit les telephones d'un contact dans le fichier associe au parametre in, 
    * puis assigne les telephones lus au contact donne.
    * @param in le flux associe au fichier a lire.
    * @param contact le contact auquel assigner les telephones lus.
    * @throws TelephoneInvalideException si un telephone lu est invalide.
    * @throws IOException s'il se produit une erreur d'entree/sortie.
    */
   private static void lireTelephonesDansFic (BufferedReader in, Contact contact)
            throws TelephoneInvalideException, IOException {
      Telephone tel;
      String type;
      String no;
      String poste;
      String nbr = in.readLine().trim();
      int nbrTel = Integer.parseInt(nbr);
      for (int i = 0 ; i < nbrTel ; i++) {
         type = in.readLine();
         no = in.readLine();
         poste = in.readLine();
         if (poste.equals("null"))
            poste = null;
         tel = new Telephone(type, no, poste);
         contact.ajouterTelephone(tel);
      }
   }
   
   //-------------------------------------------------------
   //METHODES DE SAUVEGARDE DES CONTACTS DANS UN FICHIER 
   //-------------------------------------------------------
   
   /**
    * Ecrit tous les contacts non null presents dans contacts, dans le fichier
    * ficContacts. La methode ecrit d'abord le nombre de contacts a ecrire 
    * (nbrContacts) sur la premiere ligne du fichier, et ecrit ensuite les 
    * contacts non null, l'un a la suite de l'autre. Si le fichier ficContacts 
    * existe, il sera ecrase.
    * @param contacts les contacts a ecrire (sauvegarder).
    * @param ficContacts le fichier dans lequel ecrire les contacts.
    * @param nbrContacts le nombre de contacts non null dans contacts.
    */
   public static void sauvegarderContacts (Contact[] contacts, File ficContacts,
           int nbrContacts) {
      PrintWriter out;
      try {
         //si le fichier existe, il sera ecrase
         out = new PrintWriter(new FileWriter(ficContacts));
         out.println(nbrContacts);
         
         for (Contact c : contacts) {
            if (c != null) {
               out.println(c.getNom());
               out.println(c.getPrenom());
               out.println(c.getNbrTelephones());
               sauvegarderTelephonesDansFic(c, out);
               sauvegarderAdresseDansFic(c, out);
               out.println(c.getCourriel());
               out.println(c.isFavori());
            }
         }
         out.close();
         
      } catch (IOException e) {
         //ne se produira pas.
      }catch (NullPointerException e) {
      }
    
   }
   
   /**
    * Ecrit l'adresse du contact donne dans le fichier associe au parametre 
    * out. Si l'adresse du contact est null, la methode ecrit "null".
    * @param contact le contact possedant l'adresse qu'on veut ecrire dans le 
    *                fichier.
    * @param out le flux associe au fichier dans lequel on veut ecrire l'adresse
    *            du contact.
    */
   private static void sauvegarderAdresseDansFic(Contact contact, PrintWriter out) {
      
      if (contact.getAdresse() != null) {
         out.print(contact.getAdresse().getNoPorte() + SEP 
                  + contact.getAdresse().getNomRue() + SEP
                  + contact.getAdresse().getApt() + SEP
                  + contact.getAdresse().getVille() + SEP
                  + contact.getAdresse().getProvinceEtat() + SEP
                  + contact.getAdresse().getPays() + SEP
                  + contact.getAdresse().getCodePostal() + "\n"
          );
      } else {
         out.println("null");
      }
   }

   /**
    * Ecrit les telephones du contact donne dans le fichier associe au parametre 
    * out. Si le contact n'a aucun telephone, la methode n'ecrit rien dans 
    * le fichier.
    * @param contact le contact possedant les telephones qu'on veut ecrire dans 
    *                le fichier.
    * @param out le flux associe au fichier dans lequel on veut ecrire les
    *            telephones de contact.
    */
   private static void sauvegarderTelephonesDansFic(Contact contact, PrintWriter out) {
      int i = 1;
      while (contact.obtenirIemeTelephone(i) != null) {
         out.println(contact.obtenirIemeTelephone(i).getType());
         out.println(contact.obtenirIemeTelephone(i).getNumero());
         out.println(contact.obtenirIemeTelephone(i).getPoste());
         i++;
      }
   }

   //-------------------------------------------------------
   //AUTRES METHODES UTILITAIRES
   //-------------------------------------------------------
   
   /**
    * Affiche une breve presentation de ce logiciel.
    */
   public static void presenterLogiciel() {
      System.out.println(PRESENTATION);
   }
   
   /**
    * Affiche le msg donne, puis demande a l'utilisateur d'appuyer sur ENTREE
    * pour continuer.
    * @param msg le message a afficher.
    */
   public static void pause (String msg) {
      System.out.println(msg);
      System.out.print(MSG_PAUSE);
      Clavier.lireFinLigne();
   }
   
   /**
    * Construit une ligne formee avec le symbole donne, de la longueur donnee.
    * Exemple : 
    *    ligne('*', 10)  retourne la chaine "**********".
    * 
    * @param symbole le symbole a utiliser pour construire la ligne.
    * @param longueur le nombre de symboles dans la ligne construite.
    * @return une ligne formee avec le symbole donne, de la longueur donnee.
    */
   public static String ligne(char symbole, int longueur) {
      String ligne = "";
      for (int i = 0 ; i < longueur ; i++) {
          ligne = ligne + symbole;
      }
      return ligne;
   }
   
   /**
    * Encadre le titre donne dans une boite formee du symbole donne.
    * 
    * Exemple : boiteTitre('#', "TITRE") retourne la chaine qui lorsqu'affichee
    *           donnera ceci : 
    * 
    *          #########
    *          # TITRE #
    *          #########
    * 
    * @param symbole le symbole avec lequel former la boite (le cadre).
    * @param titre le titre a encadrer.
    * @return la chaine representant le titre encadre dans une boite formee
    *         avec le symbole donne.
    */
   public static String boiteTitre(char symbole, String titre) {
      String titreBoite = "\n";
      int longueur;
      if (titre != null && titre.length() > 0) {
         longueur = titre.length() + 4;
         titreBoite = titreBoite + ligne(symbole, longueur)
                 + "\n" + symbole + " " + titre.toUpperCase() 
                 + " " + symbole + "\n" + ligne(symbole, longueur);
      }
      return titreBoite + "\n";
   }
   
   /**
    * Affiche le msgSol donne (question) puis saisit, et valide la reponse 
    * (oui ou non) de l'utilisateur. Une reponse valide est 'o' ou 'O' pour OUI 
    * et 'n' ou 'N' pour non.
    * @param msgSol le message de sollicitation (une question) demandant une 
    *               reponse oui ou non.
    * @param msgErr le message d'erreur affiche lors d'un entree invalide.
    * @return true si l'utilisateur a repondu oui, false sinon.
    */
   public static boolean questionOuiNon(String msgSol, String msgErr) {
      String rep; //reponse de l'utilisateur

      System.out.print(msgSol);
      rep = Clavier.lireString();

      while (!rep.equalsIgnoreCase("O") && !rep.equalsIgnoreCase("N")) {
         System.out.println(msgErr);
         System.out.print(msgSol);
         rep = Clavier.lireString();
      }
      return rep.equalsIgnoreCase("O");
   }
   
   /**
    * Affiche le msgSol donne pour solliciter une chaine de caracteres, et 
    * retourne la chaine entree par l'utilisateur.
    * @param msgSol le message de sollicitation de la chaine de caracteres.
    * @return la valeur entree par l'utilisateur.
    */
   public static String lireChaine (String msgSol) {
      String chaine;
      System.out.print(msgSol);
      chaine = Clavier.lireString();
      return chaine;
   }
   
   /**
    * Sollicite, lit, et valide une chaine de caracteres qui doit etre 
    * incluse entre longeur min et longeur max.
    * 
    * @param msgSol le message de sollicitation de la chaine de caracteres.
    * @param msgErr le message d'erreur affiche lorsque la chaine entree est 
    *               invalide.
    * @param lngMin longueur minimum valide de la chaine entree.
    * @param lngMax longueur maximum valide de la chaine entree.
    * @return la chaine entree, de longueur valide.
    */
   public static String validerChaine (String msgSol, String msgErr, 
           int lngMin, int lngMax) {
      String chaine;
      do {
         System.out.print(msgSol);
         chaine = Clavier.lireString();
         if (chaine.length() < lngMin || chaine.length() > lngMax) {
            System.out.println(msgErr);
         }
      } while (chaine.length() < lngMin || chaine.length() > lngMax);
      
      return chaine;
   }
   

   //-----------------------------
   //MÉTHODES À COMPLÉTER
   //-----------------------------
  
  /**
    * ajoute un contact a un tableau de contacts 
    * user doit entrer un nom et prenom validé : un contact est cree
    * user choisit entre valider un numero de telephone, adresse et courriel
    * le contact est mis a jour et ajoute a un tableau
    * @param contacts le tableau de contacts.
    * @return le tableau de contacts mis à jour.
    */ 
   public static Contact[] ajouterContact (Contact [] contacts) {
      String nom = "";
      boolean answer = false;
      boolean answerTel = false;
      boolean answerAdresse = false;
      Adresse adresse = new Adresse();
      Contact contact = new Contact ();
      Telephone tel = null;
      String telephone = null;
      int nbr;
      String courriel = null;
      String debut= null;
      String prenom = null;
      String type = null;     
      String numero = null;    
      String poste = null;    
      String porte = null;
      String rue = null;
      String apt = null;
      String ville = null;
      String province = null;
      String pays = null;
      String cp = null;
      char c = '*';
      boolean favori = false;
     
      
      debut = boiteTitre(c,"AJOUT D'UN CONTACT");
      System.out.print(debut);
      System.out.println();
      
      nom = validerChaine ("Nom du contact : ", "\nErreur, le nom doit contenir entre 1 et 25 caracteres... Recommencez", 1,25);           
      prenom =  validerChaine("Prenom du contact : ", "\nErreur, le prenom doit contenir entre 1 et 25 caracteres... Recommencez", 1, 25);
      try {
          contact = new Contact (nom,prenom);
      }catch (Exception e) {
      }
     answerTel = questionOuiNon("Voulez-vous entrez un telephone (o/n) : ", "\nErreur, repondez par (o)ui ou (n)on !");
     if ( answerTel == true) {
          do {
             
              type = lireChaine("Type du telephone (Tapez ENTER pour Domicile)"); 
              do {
                  numero = validerChaine("Numero de telephone : ", "\nErreur, le numero doit contenir exactement 7 ou 10 chiffres... Recommencez.",7,10);
                  answer = Telephone.numeroTelValide(numero);
              }while (answer == false);
              poste = lireChaine("Poste telephonique (Tapez ENTER si aucun poste) : "); 
              
                try {
                 
                   
                   Telephone tel1 = new Telephone (type , numero, poste) ;
                   tel = tel1;
                   contact.ajouterTelephone(tel);
               }catch (Exception e) {
               }
               answerTel = questionOuiNon("Voulez-vous entrez un telephone (o/n) : ", "\nErreur, repondez par (o)ui ou (n)on !");
          }while (answerTel == true) ;
        answerTel= true;
     }
      answerAdresse = questionOuiNon("Voulez-vous entrez une adresse (o/n) : ", "\nErreur, repondez par (o)ui ou (n)on !");
      if  (answerAdresse == true) {
          porte = validerChaine("No de porte : ","\nErreur, le numero de porte doit contenir entre 1 et 8 caracteres... Recommencez.",1,8);
          porte.toUpperCase();
          rue = validerChaine("Rue : ","\nErreur, le nom de la rue doit contenir entre 1 et 50 caracteres... Recommencez.",1,50);
          apt = lireChaine("Numero d'appartement (Taper ENTER si aucun apt.) : ");
          ville = validerChaine("Ville : ", "\nErreur, le nom de la ville doit contenir entre 1 et 50 caracteres... Recommencez.",1,50);
          province = validerChaine("Province/etat : ", "\nErreur, le nom de la province/etat doit contenir entre 1 et 50 caracteres... Recommencez.",1,50);
          pays = validerChaine("Pays : ", "\nErreur, le nom du pays doit contenir entre 1 et 50 caracteres... Recommencez.",1,50);
          cp = lireChaine("Code postal (Taper ENTER si aucun code postal) : "); 
          
      }
      answer = questionOuiNon("Voulez-vous entrez un courriel (o/n) : ", "\nErreur, repondez par (o)ui ou (n)on !");
      if (answer == true) {
          courriel = validerChaine("Courriel : " ,"\nErreur, le courriel doit contenir entre 5 et 100 caracteres... Recommencez.",5,100);          
      }
      favori = questionOuiNon("Voulez-vous ajouter ce contact a vos favoris (o/n) :", "\nErreur, repondez par (o)ui ou (n)on !");   
      
      if (answerTel ==true ) {
          try {
               
               
              if (answerAdresse ==true ) {
                  try {
                      Adresse adresse1= new Adresse (porte, rue, apt, ville, province, pays, cp); 
                      adresse = adresse1;
                      Contact contact3 = new Contact (nom, prenom, tel, adresse, courriel);
                      Contact.modifierNbrContacts (Contact.obtenirNbrContacts() -1);
                      Telephone teltab [] = contact.getTel();
                      for (int i = 1; i < teltab.length; i++) {
                          
                          contact3.ajouterTelephone(teltab[i]);
                          
                      }
                      contact = contact3;
                    
                      
                       if (favori == true ) {
                           contact.setFavori(true);
                 
                       }
                      contacts =  putIntab(contact, contacts);
                     
                  }catch (Exception e) {
                  }
              }else {
                  try {                      
                      adresse = null;
                      Contact contact1 = new Contact (nom, prenom, tel, adresse, courriel);
                      Contact.modifierNbrContacts (Contact.obtenirNbrContacts() -1);
                       Telephone teltab [] = contact.getTel();
                      for (int i = 1; i < teltab.length; i++) {
                          
                          contact1.ajouterTelephone(teltab[i]);
                          
                      }
                      contact = contact1;
                    
                 
                       if (favori == true ) {
                           contact.setFavori(true);
                 
                       }
                      contacts = putIntab(contact, contacts);
                  }catch (Exception e) {
                  }
              }    
            
             
          }catch (Exception e) {
          
          } 
      }else {
          if (answerAdresse ==true ) {
                  
                  try {
                      Telephone tel1 = null;
                      Adresse adresse1= new Adresse (porte, rue, apt, ville, province, pays, cp); 
                      adresse = adresse1;
                      Contact contact3 = new Contact (nom, prenom, tel, adresse, courriel);
                      Contact.modifierNbrContacts (Contact.obtenirNbrContacts() -1);
                      contact = contact3;
                       if (favori == true ) {
                           contact.setFavori(true);
                 
                       }
                       contacts = putIntab(contact, contacts);
                  }catch (Exception e) {
                  }
          }else {
                  try {  
                      Telephone tel1 = null;
                      adresse = null;
                      Contact contact1 = new Contact (nom, prenom, tel, adresse, courriel);
                      Contact.modifierNbrContacts (Contact.obtenirNbrContacts() -1);
                      contact = contact1;
                       if (favori == true ) {
                           contact.setFavori(true);
                 
                       }
                      contacts = putIntab(contact, contacts);
                  }catch (Exception e) {
                      
                  }
              }  
      }
      return contacts; 
   }
     /**
     * Permet de voir si le tableau contient un objet null. 
     * @param contacts le tableau de contacts
     * @return true si il y a un objet null, false sinon.
     * 
     */
   public static boolean libre (Contact [] contacts) {
        
        boolean libre = false;
        for (int i = 0 ; i < contacts.length ; i++) {
                if (contacts [i] == null) {
                    libre = true;
                }else {                    
                    libre = false;
                }
            }        
        return libre;
   }
    /**
    * Met le contacts enregistres dans le tableau et retourne  un tableau contenant ce contacts. 
    * Si le tableau est plein, un nouveau tableau de deux cases extra est cree. 
    * @param contact le contact
    * @param contacts le tableau de contacts
    * @return un tableau contenant les contacts.
    */
   public static Contact [] putIntab (Contact contact, Contact [] contacts) {
      boolean libre;      
      libre = libre(contacts);
      

      if ( libre != true) {
     
          Contact [] t = new Contact [contacts.length + 2];
          for (int j = 0; j < contacts.length; j++) {
              t [j] = contacts [j];
          }
          contacts = t;
          
      }
                             
      for (int h = 0; h < contacts.length; h++){
         if (contacts[h] == null) {
              contacts[h] = contact;
              h = contacts.length ;
              
         }
      }
     
      return contacts;
   }
    /**
    * supprime un contact du tableau de contacts .
    * user doit entrer un nom et prenom validé.
    * si le nom+prenom egual selui du tableau alors un message demande si cet element du tableau doit etre
      remplace par null. 
    * si le nom+prenom  n existe pas, un message averti le user.
    * si l element du tableau est remplace, le nbr de contacts et/ou le nbr de contacts favori est diminue de 1.
    * @param Contact contacts = un tableau de contacts. 
    * @return 0.
    */
   public static int supprimerContact(Contact[] contacts) {
      String nom = "";
      boolean answer = false;
      String prenom = null;
      String debut;
      char c = '*';
      int nbr;
      String fullname = "";
      //A COMPLETER
      
      debut = boiteTitre(c,"SUPPRIMER UN CONTACT");
      System.out.print(debut);
      System.out.println();
      
      nom = validerChaine ("Nom du contact : ", "\nErreur, le nom doit contenir entre 1 et 25 caracteres... Recommencez", 1,25);           
      prenom = validerChaine("Prenom du contact : ", "\nErreur, le prenom doit contenir entre 1 et 25 caracteres... Recommencez", 1, 25);
      
      try {
          for (int i = 0 ; i < contacts.length && contacts[i] != null ; i++) {
              fullname = contacts [i].getNom() + contacts [i].getPrenom();
              if (fullname.toUpperCase().equals(nom.toUpperCase() + prenom.toUpperCase())) {
                 answer = true;
              }
              
          }
          if (answer == false) {
                 System.out.println("\nLe carnet ne contient aucun contact portant le nom : " + prenom.toUpperCase() + " " + nom.toUpperCase());
                 pause ("");
          }else {
              for (int i = 0 ; i < contacts.length ; i++) {
                    fullname = contacts [i].getNom() + contacts [i].getPrenom();
                    try {
                         if (fullname.toUpperCase().equals(nom.toUpperCase() + prenom.toUpperCase())) {
                            
                         
                             contacts [i].toString2();
                             answer = questionOuiNon("\nVoulez-vous vraiment supprimer ce contact (o/n) : ", "\nErreur, repondez par (o)ui ou (n)on !");
                             if (answer == true) {
                                 contacts [i] = null;
                                 nbr = Contact.obtenirNbrContacts();
                                 Contact.modifierNbrContacts(nbr-1);
                                 nbr = Contact.obtenirNbrContacts();
                                 
                                     if (contacts [i].isFavori()){
                                         nbr = Contact.obtenirNbrContactFavoris ();
                                         Contact.modifierNbrContactsFavoris(nbr-1);
                                     }
                                
                             }
                             pause ("");
                             
                        }
                    }catch (Exception e) {
                    }
              }
              
          }
     } catch ( NullPointerException e) {
     }
     nbr = Contact.obtenirNbrContacts();
      return 0;
   }
   /**
    * vide un carnet de contacts 
    * doit choisir entre effacer tout les contacts ou pas
    * @param Contact contacts = un tableau de contacts 
    * @return true si le carnet a ete vide, false sinon
    */
   public static boolean viderCarnet(Contact[] contacts) {
      boolean answer;
      
      
      answer = questionOuiNon("Voulez-vous vraiment effacer tous les contacts (o/n) : ", "\nErreur, repondez par (o)ui ou (n)on !");
      if (answer == true) {
          for (int i = 0; i < contacts.length; i++) {
              contacts [i] = null;
          }
          Contact.modifierNbrContacts (0) ;
          Contact.modifierNbrContactsFavoris (0);
      }
      
      return answer; 
   }
   
   /**
    * affiche un carnet de contacts 
    * doit choisir entre l option 1-afficher tout les contacts ou l option 2- afficher les contacts favoris
    * @param Contact contacts = un tableau de contacts 
    * @param int nbrContacts = le nombre de contacts 
    */
   public static void afficherContacts (Contact [] contacts, int nbrContacts) {
      String debut = "";
      String answer = "";
      char c = '*';
      char c2 = '-';
      int nbrContact = 0;
      int nbrContactFavori = 0;
      int exit = 0 ;
      
     
      
      debut = boiteTitre(c,"AFFICHER LES CONTACTS");
      System.out.println(debut);   
      
      System.out.println("1. AFFICHER TOUS LES CONTACTS");
      System.out.println("2. AFFICHER LES FAVORIS");
      System.out.println();      
      System.out.print("Entrez votre choix : ");
      
      
      answer = Clavier.lireString();
      
      
      while (!(answer.equals("1") || answer.equals("2"))){
          System.out.println();
          System.out.println("Erreur, entrez une valeur entre 1 et 2... Recommencez.");
          System.out.println();
          System.out.println("1. AFFICHER TOUS LES CONTACTS");
          System.out.println("2. AFFICHER LES FAVORIS");
          System.out.println();
          System.out.print("Entrez votre choix : ");
          
          answer = Clavier.lireString();
      }
      try {
          
          
          if (answer.equals("1")) {
            nbrContact = Contact.obtenirNbrContacts();
            System.out.println("\nCARNET DE CONTACTS (" + nbrContact + ")\n");
            
            for (int i = 0; i < contacts.length; i ++) {  
                try {
                    if (i == nbrContact - 1) {
                       contacts [i].toString2(); 
                       i = contacts.length;
                    }else {
                        contacts [i].toString2(); 
                        pause (""); 
                    }  
                }catch (Exception e) {
                }
            }
          }else {
            nbrContact = Contact.obtenirNbrContacts();
            nbrContactFavori = Contact.obtenirNbrContactFavoris();
            System.out.println("\nCARNET DE CONTACTS (" + nbrContactFavori + ")\n");  
              
            for (int i = 0; i < contacts.length ; i ++) {                       
                if (i <= nbrContact -1 ) {
                    if (contacts [i].isFavori()) {
                       contacts [i].toString2();
                       exit++;
                  
                       if (exit != nbrContactFavori) {
                            pause (""); 
                       }
                    }
                }
            }  
          }
      }catch (NullPointerException e) {
      }
      System.out.println();
      pause ("FIN DE LA LISTE DE CONTACTS");
   }
   

   /**
    * Point d'entree de l'application de gestion d'un carnet de contacts 
    * personnels.
    * @param args (aucun)
    */
   public static void main (String [] args) {

      //nombre de contacts dans le tableau de contacts
      int nbrContacts = lireFichierNbrContacts(FIC_CONTACTS);
      
      //tableau de contacts
      Contact [] contacts = lireFichierContacts(FIC_CONTACTS, nbrContacts);
      
      //Choix au menu principal
      String choix;

      presenterLogiciel();
      
      do {
         System.out.print(MENU);
         choix = Clavier.lireString();
         
         switch (choix) {
            case "1" : 
               contacts = ajouterContact(contacts);
               nbrContacts++;
               break;
               
            case "2" :
               nbrContacts = nbrContacts - supprimerContact(contacts);
               break;
               
            case "3" : 
               if (viderCarnet(contacts)) {
                  nbrContacts = 0;
                  contacts = new Contact[LNG_INIT_TAB_CONTACTS];
               }
               break;
               
            case "4" :
               afficherContacts(contacts, nbrContacts);
               break;
               
            case MENU_MAX :
               //Sauvegarde des contacts dans le fichier FIC_CONTACTS
               sauvegarderContacts (contacts, FIC_CONTACTS, nbrContacts);
               break;
            default : 
               System.out.println(MSG_ERR_MENU);
         }
         
      } while (!choix.equals(MENU_MAX));
      
      //afficher la fin du programme
      System.out.println(MSG_FIN_PROG);
   }
   
} //fin classe CarnetContacts
