import java.io.*;

public class Sauvegarde {//Sauvegarde Automatique
	public void nouvelleSauvegarde(VariablesDeJeu variables){//creer une sauvegarde en fichier csv avec chaque case une variable
		
		VariablesDeJeu VariablesSession=variables;		
		final String chemin = System.getProperty("user.dir");
		String NomFichier = "sauvegarde.csv";
		String cheminfichier = chemin + File.separator + NomFichier;
			
		try(FileWriter fileWriter = new FileWriter(cheminfichier)) {//Creation du fichier sauvegarde
			String ContenuDuFichier = VariablesSession.numeroCarte+","+VariablesSession.xDepart+","+VariablesSession.yDepart+","+VariablesSession.xpMeloche;
			for (int i=0; i<VariablesSession.listeInterractionsAvecDresseurs.length; i++) {
				ContenuDuFichier+=","+VariablesSession.listeInterractionsAvecDresseurs[i];
			}
			fileWriter.write(ContenuDuFichier);
		} 
		catch (IOException e) {
				// Gestion des exceptions
		}
		
	}	
     
     
	public boolean sauvegardeExiste(){//teste si sauvegarde existe

		final String chemin = System.getProperty("user.dir");
		String NomFichier = "sauvegarde.csv";
		String cheminfichier = chemin + File.separator + NomFichier;

		if(new File(cheminfichier).isFile()) {//si le fichier sauvegarde existe
			return true;
		}
		else 
			return false;

	}
	
	
	public VariablesDeJeu restaurerSauvegarde(){//Lis le fichier de sauvegarde sauvegarde.csv et remplis les variables correspondant aux cases du tableau de svgrde
		VariablesDeJeu VariablesSession= new VariablesDeJeu();
		final String chemin = System.getProperty("user.dir");
		String NomFichier = "sauvegarde.csv";
		String cheminfichier = chemin + File.separator + NomFichier;	
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
		int[] variablesInt = new int[54];//tableau contenenant les variables de la sauvegarde
        try {

            br = new BufferedReader(new FileReader(cheminfichier));
				while ((line = br.readLine()) != null) {
					String[] variablesString = line.split(cvsSplitBy);
					for (int i=0; i<variablesInt.length; i++){
						variablesInt[i] = Integer.parseInt(variablesString[i]);
					}
					VariablesSession.numeroCarte=variablesInt[0];
					VariablesSession.nouvelleCarte(VariablesSession.numeroCarte);
					VariablesSession.xDepart=variablesInt[1];
					VariablesSession.yDepart=variablesInt[2];
					VariablesSession.xpMeloche=variablesInt[3];

					for (int i=4; i<variablesInt.length;i++) {
						VariablesSession.listeInterractionsAvecDresseurs[i-4]+=variablesInt[i];
					}
				}
            } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		return VariablesSession;
   }
}

		
