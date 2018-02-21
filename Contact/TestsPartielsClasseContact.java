import java.text.DecimalFormat;

//IMPORTANT :  
//Copier la methode toString2() donnee dans votre classe Contact 
//avant d'effectuer les tests.

/**
 * Tests PARTIELS de la classe Contact.
 * Cette classe fournit quelques tests pour tester les methodes de la classe
 * Contact. Ces tests sont partiels, donc ils ne testent pas tous les cas.
 * 
 * @author melanie lord
 * @version Aut. 2016
 */
public class TestsPartielsClasseContact {
   private static Telephone t1, t2, t3, t4, t5;
   private static Adresse a1, a2, a3;
   private static String mail1 = "bob@gmail.com";
   private static Contact c1 = null;
   private static Contact c2 = null;
   
   //---------------------------------
   //METHODES UTILITAIRES
   //---------------------------------
   
   /**
    * Affiche le titre donne en majuscule et entre deux lignes de tirets de 
    * meme longueur.
    * @param titre le titre a afficher.
    */
   private static void titre (String titre) {
      String s = "\n";
      for (int i = 0 ; i < titre.length() ; i++) {
         s = s + "-";
      }
      s = s + "\n" + titre.toUpperCase() + "\n";
      for (int i = 0 ; i < titre.length() ; i++) {
         s = s + "-";
      }
      System.out.println(s);
   }
   
   /**
    * Affiche un message d'erreur
    */
   private static void err(String msg) {
      System.out.println(msg + "\n");
   }
   
   /**
    * Affiche un message de confirmation
    */
   private static void ok() {
      System.out.println("OK");
   }
   
   /**
    * Affiche un message d'erreur pour une exception inatttendue levee dans une
    * methode
    * @param e l'exception qui a ete levee.
    */
   private static void exceptionInattendue(Exception e) {
      System.out.println("ERREUR - " + e.getClass().getSimpleName() 
              + " inattendue.");
   }

   /**
    * Preparation des variables de tests.
    * @return true si aucune exception levee, false sinon.
    */
   private static boolean preparationVariableTests () {
      boolean ok = true;
      try {
         t1 = new Telephone(null, "1231234", null);
         t2 = new Telephone("bureau", "1238998764", "23");
         t3 = new Telephone("cellulaire", "8195677743", null);
         t4 = new Telephone("chalet", "6771276774", null);
         t5 = new Telephone("cellulaire 2", "2343443", null);
         a1 = new Adresse();
         a2 = new Adresse("1234", "Sherbrooke", "2", "Montreal", "QC", "Canada", 
                 "1g1m3m");
         mail1 = "bob@gmail.com";
      } catch (Exception e) {
         ok = false;
      }
      return ok;
   }
   
   /*******************
    *     TESTS
    *******************/
   
   /**
    * Tests du constructeur a 5 parametres.
    */
   public static void testsConstructeur1() {
      String attendu;
      String trouve;

      titre("tests - constructeur a 5 parametres");
      
      try {
         preparationVariableTests();
         
         try {
            System.out.print("Construction d'un contact avec un nom vide... ");
            c1 = new Contact("", "john", t1, a1, mail1);
            err("ERREUR : devrait lever une exception ContactInvalideException");
         } catch (ContactInvalideException e) {
            ok();
         } catch (Exception e) {
            exceptionInattendue(e);
         }
         try {
            System.out.print("Construction d'un contact avec un prenom null... ");
            c1 = new Contact("doe", null, t1, a1, mail1);
            err("ERREUR : devrait lever une exception ContactInvalideException");
         } catch (ContactInvalideException e) {
            ok();
         } catch (Exception e) {
            exceptionInattendue(e);
         }
         try {
            System.out.print("Construction d'un contact valide avec un courriel vide... ");
            c1 = new Contact("doe", "john", t1, a1, "");
            attendu = "doe john false\n" +
                     "TEL. DOMICILE : 123-1234\n" +
                     "null\n" +
                     "1 rue, apt. apt\n" +
                     "ville, province, PAYS\n" +
                     "CODE POSTAL\n" +
                     "null";
            trouve = c1.toString2(null).trim();
            if (attendu.equals(trouve)) {
               ok();
            } else {
               err("ERREUR :\n\n" + "*** Attendu *** :\n" + attendu + "\n\n*** Trouve *** :\n" + trouve);
            }
         } catch (Exception e) {
            exceptionInattendue(e);
         }
         try {
            System.out.print("Construction d'un contact valide... ");
            c1 = new Contact("doe", "john", t1, a1, mail1);
            attendu = "doe john false\n" +
                     "TEL. DOMICILE : 123-1234\n" +
                     "null\n" +
                     "1 rue, apt. apt\n" +
                     "ville, province, PAYS\n" +
                     "CODE POSTAL\n" +
                     "bob@gmail.com";
            trouve = c1.toString2(null).trim();
            
            if (attendu.equals(trouve)) {
               ok();
            } else {
               err("ERREUR :\n\n" + "*** Attendu *** :\n" + attendu + "\n\n*** Trouve *** :\n" + trouve);
            }
         } catch (Exception e) {
            exceptionInattendue(e);
         }
      
      } catch (Exception e) {
         exceptionInattendue(e);
      }   
   }
   
   /**
    * Tests du constructeur a 2 parametres.
    */
   public static void testsConstruceur2() {
      String attendu;
      String trouve;
      
      titre("tests - constructeur a 2 parametres");
      try {
         preparationVariableTests();
      
         try {
            System.out.print("Construction d'un contact avec un nom vide... ");
            c1 = new Contact("", "john");
            err("ERREUR : devrait lever une exception ContactInvalideException");
         } catch (ContactInvalideException e) {
            ok();
         } catch (Exception e) {
            exceptionInattendue(e);
         }
         try {
            System.out.print("Construction d'un contact valide... ");
            c1 = new Contact("doe", "john");
            attendu = "doe john false\n" +
                     "null\n" +
                     "null\n" +
                     "null\n" +
                     "null";
            trouve = c1.toString2(null).trim();
            if (attendu.equals(trouve)) {
               ok();
            } else {
               err("ERREUR :\n\n" + "*** Attendu *** :\n" + attendu + "\n\n*** Trouve *** :\n" + trouve);
            }
         } catch (Exception e) {
            exceptionInattendue(e);
         }
         
      } catch (Exception e) {
         exceptionInattendue(e);
      }
   
   }
   
   /**
    * Tests des getters.
    */
   public static void testsGetters () {
      String attendu;
      String trouve;
      
      titre("tests - getters");
   
      try {
         preparationVariableTests();
         
         System.out.println("Preparation des tests : construction d'un contact valide.\n");
         c1 = new Contact("doe", "john", t1, a1, mail1);

         try {
            System.out.print("Test getNom... ");
            attendu = "doe";
            trouve = c1.getNom();
            if (attendu.equals(trouve)) ok();
            else err("ERREUR : " + "Attendu => " + attendu + " |Trouve => " + trouve);
         } catch (Exception e) {
            exceptionInattendue(e);
         }
         try {
            System.out.print("Test getPrenom... ");
            attendu = "john";
            trouve = c1.getPrenom();
            if (attendu.equals(trouve)) ok();
            else err("ERREUR : " + "Attendu => " + attendu + " |Trouve => " + trouve);
         } catch (Exception e) {
            exceptionInattendue(e);
         }
         try {
            System.out.print("Test getAdresse... ");
            attendu = a1.toString();
            trouve = c1.getAdresse().toString();
            if (attendu.equals(trouve)) {
               ok();
            } else {
               err("ERREUR :\n\n" + "*** Attendu *** :\n" + attendu + "\n\n*** Trouve *** :\n" + trouve);
            }
         } catch (Exception e) {
            exceptionInattendue(e);
         }
         try {
            System.out.print("Test getCourriel... ");
            attendu = mail1;
            trouve = c1.getCourriel();
            if (attendu.equals(trouve)) ok();
            else err("ERREUR : " + "Attendu => " + attendu + " |Trouve => " + trouve);
         } catch (Exception e) {
            exceptionInattendue(e);
         }
         try {
            System.out.print("Test getNbrTelephones... ");
            attendu = 1 + "";
            trouve = c1.getNbrTelephones() + "";
            if (attendu.equals(trouve)) ok();
            else err("ERREUR : " + "Attendu => " + attendu + " |Trouve => " + trouve);
         } catch (Exception e) {
            exceptionInattendue(e);
         }
         try {
            System.out.print("Test isFavori... ");
            attendu = false + "";
            trouve = c1.isFavori() + "";
            if (attendu.equals(trouve)) ok();
            else err("ERREUR : " + "Attendu => " + attendu + " |Trouve => " + trouve);
         } catch (Exception e) {
            exceptionInattendue(e);
         }

      } catch (Exception e) {
         exceptionInattendue(e);
      }
      
       
   }
   
   /**
    * Tests des setters.
    */
   public static void testsSetters ()   {
      String attendu;
      String trouve;
      
      titre("tests - setters");
      
      try {
         preparationVariableTests();
         
         System.out.println("Preparation des tests : construction d'un contact valide.\n");
         c1 = new Contact("doe", "john", t1, a1, mail1);
         
         try {
             c1 = new Contact("doe", "john", t1, a1, mail1);
            System.out.print("Test setNom avec un nom vide... ");
            c1.setNom("");
            err("ERREUR : devrait lever une exception ContactInvalideException");
         } catch (ContactInvalideException e) {
            ok();
         } catch (Exception e) {
            exceptionInattendue(e);
         }
         try {
            c1 = new Contact("doe", "john", t1, a1, mail1);
             System.out.print("Test setPrenom avec un prenom null... ");
            c1.setPrenom(null);
            err("ERREUR : devrait lever une exception ContactInvalideException");
         } catch (ContactInvalideException e) {
            ok();
         } catch (Exception e) {
            exceptionInattendue(e);
         }
         try {
            attendu = "Lafontaine";
            System.out.print("Test setNom avec un nom valide... ");
            c1.setNom(attendu);
            trouve = c1.toString2("nom");
            if (attendu.equals(trouve)) {
               ok();
            } else {
               err("ERREUR : " + "Attendu => " + attendu + " |Trouve => " + trouve);
            }
         } catch (Exception e) {
            exceptionInattendue(e);
         }
         try {
            attendu = "Isabelle";
            System.out.print("Test setPrenom avec un nom valide... ");
            c1.setPrenom(attendu);
            trouve = c1.toString2("prenom");
            if (attendu.equals(trouve)) {
               ok();
            } else {
               err("ERREUR : " + "Attendu => " + attendu + " |Trouve => " + trouve);
            }
         } catch (Exception e) {
            exceptionInattendue(e);
         }
         try {
            attendu = a2.toString();
            System.out.print("Test setAdresse avec une adresse valide... ");
            c1.setAdresse(a2);
            trouve = c1.toString2("adresse");
            if (attendu.equals(trouve)) {
               ok();
            } else {
               err("ERREUR : " + "Attendu => " + attendu + " |Trouve => " + trouve);
            }
         } catch (Exception e) {
            exceptionInattendue(e);
         }
         try {
            attendu = "toto@yahoo.ca";
            System.out.print("Test setCourriel avec un courriel valide... ");
            c1.setCourriel(attendu);
            trouve = c1.toString2("courriel");
            if (attendu.equals(trouve)) {
               ok();
            } else {
               err("ERREUR : " + "Attendu => " + attendu + " |Trouve => " + trouve);
            }
         } catch (Exception e) {
            exceptionInattendue(e);
         }
         try {
            attendu = "true";
            System.out.print("Test setFavori a true... ");
            c1.setFavori(true);
            trouve = c1.toString2("favori");
            if (attendu.equals(trouve)) {
               ok();
            } else {
               err("ERREUR : " + "Attendu => " + attendu + " |Trouve => " + trouve);
            }
         } catch (Exception e) {
            exceptionInattendue(e);
         }
         
      } catch(Exception e) {
         exceptionInattendue(e);
      }
      
       
   }
   
   /**
    * Tests de la methode ajouterTelephone.
    */
   public static void testsAjouterTelephone() {
      String attendu;
      String trouve;
      
      titre("tests - ajouter telephone");
      
      try { 
         preparationVariableTests();
         
         System.out.println("Preparation des tests : construction d'un contact sans telephone.");
         c1 = new Contact("doe", "john", null, a1, mail1);
         
         try {
            attendu = "null\nnull";
            System.out.print("Test ajouter telephone null... ");
            c1.ajouterTelephone(null);
            trouve = c1.toString2("telephones");
            if (attendu.equals(trouve)) {
               ok();
            } else {
               err("ERREUR : " + "Attendu => " + attendu + " |Trouve => " + trouve);
            }
         } catch (Exception e) {
            exceptionInattendue(e);
         }
         
         System.out.println("\nPreparation des tests : construction d'un contact sans telephone.");
         c1 = new Contact("doe", "john", null, a1, mail1);
         try {
            attendu = "TEL. DOMICILE : 123-1234\nnull";
            System.out.print("Test ajouter telephone non null... ");
            c1.ajouterTelephone(t1);
            trouve = c1.toString2("telephones");            
            if (attendu.equals(trouve)) {
               ok();
            } else {
               err("ERREUR :\n\n" + "*** Attendu *** :\n" + attendu + "\n\n*** Trouve *** :\n" + trouve);
            }
         } catch (Exception e) {
            exceptionInattendue(e);
         }
         
      } catch(Exception e) {
         exceptionInattendue(e);
      }
   }
   
   /**
    * Tests de la methode obtenirIemeTelephone
    */
   public static void testsObtenirIemeTelephone () {
      String attendu;
      String trouve;
      Telephone tel;
      
      titre("tests - obtenir ieme telephone");
      
      try {
         System.out.println("Preparation des tests : construction d'un contact avec 5 telephones.\n");
         t1 = new Telephone(null, "1231234", null);
         t2 = new Telephone("bureau", "1238998764", "23");
         t3 = new Telephone("cellulaire", "8195677743", null);
         t4 = new Telephone("chalet", "6771276774", null);
         t5 = new Telephone("cellulaire 2", "2343443", null);
         c1 = new Contact("doe", "john", t1, a1, mail1);
         c1.ajouterTelephone(t2);
         c1.ajouterTelephone(t3);
         c1.ajouterTelephone(t4);
         c1.ajouterTelephone(t5);
         
         try {
            attendu = "null";
            System.out.print("Test obtenir 0ieme tel... ");
            tel = c1.obtenirIemeTelephone(0);
            if (tel == null) trouve = "null"; else trouve = tel.toString();
            if (attendu.equals(trouve)) {
               ok();
            } else {
               err("ERREUR : " + "Attendu => " + attendu + " |Trouve => " + trouve);
            }
         } catch (Exception e) {
            exceptionInattendue(e);
         }
         try {
            attendu = "TEL. CELLULAIRE : (819) 567-7743";
            System.out.print("Test obtenir 3ieme tel... ");
            tel = c1.obtenirIemeTelephone(3);
            if (tel == null) trouve = "null"; else trouve = tel.toString();
            if (attendu.equals(trouve)) {
               ok();
            } else {
               err("ERREUR : " + "Attendu => " + attendu + " |Trouve => " + trouve);
            }
         } catch (Exception e) {
            exceptionInattendue(e);
         }
      } catch(Exception e) {
         exceptionInattendue(e);
      }
      
        
   }
   
   /**
    * Tests de la methode supprimerTelephone.
    */
   public static void testsSupprimerTelephone () {
      String attendu;
      String trouve;
      boolean supprime;
      
      titre("tests - supprimer telephone");
      
      try {
         preparationVariableTests();
         
         System.out.println("Preparation des tests : construction d'un contact avec 5 telephones.\n");
         c1 = new Contact("doe", "john", t1, a1, mail1);
         c1.ajouterTelephone(t2);
         c1.ajouterTelephone(t3);
         c1.ajouterTelephone(t4);
         c1.ajouterTelephone(t5);
         
         try {
            attendu = "TEL. DOMICILE : 123-1234\n" +
                     "TEL. BUREAU : (123) 899-8764, poste 23\n" +
                     "TEL. CELLULAIRE : (819) 567-7743\n" +
                     "TEL. CHALET : (677) 127-6774\n" +
                     "null\n" +
                     "null";
            System.out.print("Test supprimer 5ieme tel... ");
            supprime = c1.supprimerTelephone(5);
            trouve = c1.toString2("telephones");
            
            if (attendu.equals(trouve)) {
               ok();
            } else {
               err("ERREUR :\n\n" + "*** Attendu *** :\n" + attendu + "\n\n*** Trouve *** :\n" + trouve);
            }
            System.out.print("    Verification valeur retour (true)... ");
            if (supprime) ok(); 
            else err("ERREUR : " + "Attendu => " + supprime + " |Trouve => " + !supprime);
            
            System.out.print("    Verification nbrTelephones (4)... ");
            attendu = "4";
            trouve = c1.toString2("nbrTelephones");
            if (attendu.equals(trouve)) {
               ok();
            } else {
               err("ERREUR : " + "Attendu => " + attendu + " |Trouve => " + trouve);
            }
            
         } catch (Exception e) {
            exceptionInattendue(e);
         }
         
      } catch(Exception e) {
         exceptionInattendue(e);
      }
   }
        
   
   /**
    * Tests de la methode modifierTelephone.
    */
   public static void testsModifierTelephone () {
      String attendu;
      String trouve;
      
      titre("tests - modifier telephone");
      
      try {
         preparationVariableTests();

         System.out.println("Preparation des tests : construction d'un contact avec 3 telephones.");
         preparationVariableTests ();
         c1 = new Contact("doe", "john", t1, a1, mail1);
         c1.ajouterTelephone(t2);
         c1.ajouterTelephone(t3);
         try {
            attendu = "TEL. CELL : 123-4567, poste 228\n" +
                     "TEL. BUREAU : (123) 899-8764, poste 23\n" +
                     "TEL. CELLULAIRE : (819) 567-7743\n" +
                     "null";
            System.out.print("Test modifierTelephone(1, \"Cell\", \"1234567\", \"228\")... ");
            c1.modifierTelephone(1, "Cell", "1234567", "228");
            trouve = c1.toString2("telephones");
            
            if (attendu.equals(trouve)) {
               ok();
            } else {
               err("ERREUR :\n\n" + "*** Attendu *** :\n" + attendu + "\n\n*** Trouve *** :\n" + trouve);
            }
         } catch (Exception e) {
            exceptionInattendue(e);
         }
         
         System.out.println("\nPreparation des tests : construction d'un contact avec 3 telephones.");
         preparationVariableTests ();
         c1 = new Contact("doe", "john", t1, a1, mail1);
         c1.ajouterTelephone(t2);
         c1.ajouterTelephone(t3);
         try {
            attendu = "TEL. DOMICILE : 123-1234\n" +
                     "TEL. BUREAU : (123) 899-8764, poste 23\n" +
                     "TEL. CELLULAIRE : (819) 567-7743\n" +
                     "null";
            System.out.print("Test modifierTelephone(3, null, null, null)... ");
            c1.modifierTelephone(3, null, null, null);
            trouve = c1.toString2("telephones");
            
            if (attendu.equals(trouve)) {
               ok();
            } else {
               err("ERREUR :\n\n" + "*** Attendu *** :\n" + attendu + "\n\n*** Trouve *** :\n" + trouve);
            }
         } catch (Exception e) {
            exceptionInattendue(e);
         }
         
      } catch(Exception e) {
         exceptionInattendue(e);
      } 
   }
   
   /**
    * Tests de la methode toString.
    */
   public static void testsToString () {
      String attendu;
      String trouve;
      
      titre("tests - to string");
      
      try {
         preparationVariableTests ();
         
         System.out.println("Preparation des tests : construction d'un contact "
                 + "sans telephone, sans adresse et sans courriel.");
         
         try {
            c1 = new Contact("doe", "john", null, null, null); 
            attendu = "DOE, john\n" +
                     "\n" +
                     "TELEPHONE(S) : Aucun.\n" +
                     "\n" +
                     "ADRESSE : Aucune.\n" +
                     "\n" +
                     "COURRIEL : Aucun.";
            System.out.print("Test toString... ");
            trouve = c1.toString().trim();
            
            if (attendu.equals(trouve)) {
               ok();
            } else {
               err("ERREUR :\n\n" + "*** Attendu *** :\n" + attendu + "\n\n*** Trouve *** :\n" + trouve);
            }
         } catch (Exception e) {
            exceptionInattendue(e);
         }
         System.out.println("\nPreparation des tests : construction d'un contact "
                 + "sans telephone, avec une adresse et un courriel.");
         c1 = new Contact("doe", "john", null, a1, mail1);
         try {
            attendu = "DOE, john\n" +
                     "\n" +
                     "TELEPHONE(S) : Aucun.\n" +
                     "\n" +
                     "ADRESSE : \n" +
                     "1 rue, apt. apt\n" +
                     "ville, province, PAYS\n" +
                     "CODE POSTAL\n" +
                     "\n" +
                     "COURRIEL : bob@gmail.com";
            System.out.print("Test toString... ");
            trouve = c1.toString().trim();
            
            if (attendu.equals(trouve)) {
               ok();
            } else {
               err("ERREUR :\n\n" + "*** Attendu *** :\n" + attendu + "\n\n*** Trouve *** :\n" + trouve);
            }
         } catch (Exception e) {
            exceptionInattendue(e);
         }
         System.out.println("\nPreparation des tests : construction d'un contact "
                 + "avec un telephone, une adresse et un courriel.");
         c1 = new Contact("doe", "john", t1, a1, mail1);
         try {
            attendu = "DOE, john\n" +
                     "\n" +
                     "TELEPHONE(S) : \n" +
                     "1. TEL. DOMICILE : 123-1234\n" +
                     "\n" +
                     "ADRESSE : \n" +
                     "1 rue, apt. apt\n" +
                     "ville, province, PAYS\n" +
                     "CODE POSTAL\n" +
                     "\n" +
                     "COURRIEL : bob@gmail.com";
            System.out.print("Test toString... ");
            trouve = c1.toString().trim();
            
            if (attendu.equals(trouve)) {
               ok();
            } else {
               err("ERREUR :\n\n" + "*** Attendu *** :\n" + attendu + "\n\n*** Trouve *** :\n" + trouve);
            }
         } catch (Exception e) {
            exceptionInattendue(e);
         }
         System.out.println("\nPreparation des tests : construction d'un contact "
                 + "avec 4 telephones, une adresse et un courriel.");
         c1 = new Contact("doe", "john", t1, a1, mail1);
         c1.ajouterTelephone(t2);
         c1.ajouterTelephone(t3);
         c1.ajouterTelephone(t4);
         try {
            attendu = "DOE, john\n" +
                        "\n" +
                        "TELEPHONE(S) : \n" +
                        "1. TEL. DOMICILE : 123-1234\n" +
                        "2. TEL. BUREAU : (123) 899-8764, poste 23\n" +
                        "3. TEL. CELLULAIRE : (819) 567-7743\n" +
                        "4. TEL. CHALET : (677) 127-6774\n" +
                        "\n" +
                        "ADRESSE : \n" +
                        "1 rue, apt. apt\n" +
                        "ville, province, PAYS\n" +
                        "CODE POSTAL\n" +
                        "\n" +
                        "COURRIEL : bob@gmail.com";
            System.out.print("Test toString... ");
            trouve = c1.toString().trim();

            if (attendu.equals(trouve)) {
               ok();
            } else {
               err("ERREUR :\n\n" + "*** Attendu *** :\n" + attendu + "\n\n*** Trouve *** :\n" + trouve);
            }
         } catch (Exception e) {
            exceptionInattendue(e);
         }
      
      } catch(Exception e) {
         exceptionInattendue(e);
      }
   }

   /**
    * Tests divers avec plusieurs methodes.
    */
   public static void testsDivers() {
      int points = 0;
      final int TOTAL = 4;
      String attendu;
      String trouve;
      
      titre("tests divers");
      
      try {
         preparationVariableTests ();
         System.out.println("Preparation des tests :\n"
                 + "   1. Construction d'un contact sans telephone\n"
                 + "   2. Ajouter 4 telephones\n"
                 + "   3. Supprimer 1er et 3ieme tel\n"
                 + "   4. Ajouter un tel\n"
                 + "   5. Supprimer 2ieme tel\n"
                 + "   6. Ajouter 2 tel\n");
         preparationVariableTests ();
         c1 = new Contact("doe", "john", null, a1, mail1);
         c1.ajouterTelephone(t1);
         c1.ajouterTelephone(t2);
         c1.ajouterTelephone(t3);
         c1.ajouterTelephone(t4);
         c1.supprimerTelephone(1);
         c1.supprimerTelephone(3);
         c1.ajouterTelephone(t5);
         c1.supprimerTelephone(2);
         c1.ajouterTelephone(t4);
         c1.ajouterTelephone(t1);
         
         try {
            attendu = "doe john false\n" +
                     "TEL. CELLULAIRE 2 : 234-3443\n" +
                     "TEL. CHALET : (677) 127-6774\n" +
                     "TEL. CELLULAIRE : (819) 567-7743\n" +
                     "TEL. DOMICILE : 123-1234\n" +
                     "1 rue, apt. apt\n" +
                     "ville, province, PAYS\n" +
                     "CODE POSTAL\n" +
                     "bob@gmail.com";
            System.out.print("Test valeurs des attributs du contact... ");
            trouve = c1.toString2(null);

            if (attendu.equals(trouve)) {
               ok();
            } else {
               err("ERREUR :\n\n" + "*** Attendu *** :\n" + attendu + "\n\n*** Trouve *** :\n" + trouve);
            }
            
            System.out.print("Test nombre telephones... ");
            attendu = "4";
            trouve = c1.toString2("nbrTelephones");
            if (attendu.equals(trouve)) {
               ok();
            } else {
               err("ERREUR : " + "Attendu => " + attendu + " |Trouve => " + trouve);
            }
            
         } catch (Exception e) {
            exceptionInattendue(e);
         }
         
         preparationVariableTests ();
         System.out.println("\nPreparation des tests :\n"
                 + "   1.  Modifier le nombre de favoris a 0\n"
                 + "   2.  Construire 5 contacts, dont 3 favoris\n");
         Contact.modifierNbrContactsFavoris(0);;
         c1 = new Contact("doe", "john", t1, a1, mail1);
         c1.setFavori(true);
         c2 = new Contact("doe", "jane", t2, a1, mail1);
         c2.setFavori(true);
         Contact c3 = new Contact("carter", "bob", t3, a1, mail1); 
         Contact c4 = new Contact("Lapointe", "aline", t4, a1, mail1); 
         Contact c5 = new Contact("Tran", "van", t5, a1, mail1); 
         c5.setFavori(true);
         try {
            attendu = "3";
            System.out.print("Test nbr favoris (3)... ");
            trouve = Contact.obtenirNbrContactFavoris() + "";

            if (attendu.equals(trouve)) {
               ok();
            } else {
               err("ERREUR : " + "Attendu => " + attendu + " |Trouve => " + trouve);
            }
         } catch (Exception e) {
            exceptionInattendue(e);
         }
         
         System.out.println("\nPreparation des tests : rendre un des contacts favoris non favori.");
         
         c2.setFavori(false);
         try {
            attendu = "2";
            System.out.print("Test nbr favoris (2)... ");
            trouve = Contact.obtenirNbrContactFavoris() + "";

            if (attendu.equals(trouve)) {
               ok();
            } else {
               err("ERREUR : " + "Attendu => " + attendu + " |Trouve => " + trouve);
            }
         } catch (Exception e) {
            exceptionInattendue(e);
         }

      } catch(Exception e) {
         exceptionInattendue(e);
      }
   }

   /**
    * IMPORTANT : 
    * 
    * 1. Copier la methode toString2 dans votre classe Contact avant d'exécuter
    *    les tests.
    * 
    * 2. Corriger les erreurs des premiers tests en premier, puisque ceux-ci peuvent
    *    influencer les tests subsequents.
    * 
    * 
    * @param args 
    */
   public static void main(String[] args) {
      
      boolean ok = preparationVariableTests ();
      
      if (ok) {
         testsConstructeur1();
         testsConstruceur2();
         testsGetters();
         testsSetters();
         testsAjouterTelephone();
         testsObtenirIemeTelephone();
         testsSupprimerTelephone();
         testsModifierTelephone();
         testsToString ();
         testsDivers();

      } else {
         System.out.println("ERREUR FATALE. Impossible d'executer les tests");
      }
      
      System.out.println("\n\n\n");
   }

}
