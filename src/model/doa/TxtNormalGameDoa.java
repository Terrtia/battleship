package model.doa;

import java.io.FileOutputStream;
import java.io.PrintStream;

import model.Model.GameStatut;
import model.Model.GameType;
import model.players.Grid;

public class TxtNormalGameDoa implements DoaNormalGame {

	public TxtNormalGameDoa() {
		
	}

	@Override
	public void sauvegarder(GameType gameType, GameStatut gameStatut, Grid humain, Grid ia) {
		
		//String filePath;
		FileOutputStream out; // declare a file output object
        PrintStream p; // declare a print stream object
         
        try {
        	// Create a new file output stream
            // connected to "myfile.txt"
            out = new FileOutputStream("save.txt");

            // Connect print stream to the output stream
            p = new PrintStream( out );
            // write in the file
            p.append (this.toStringPartie(gameType, gameStatut, humain, ia));
            p.close();
        	
        } catch (Exception e) {
        	System.err.println ("Error create save");
        	e.printStackTrace();
        }
        
	}
	
	public String toStringPartie(GameType gameType, GameStatut gameStatut, Grid humain, Grid ia){
		
		StringBuilder res = new StringBuilder();
		res.append(gameType.toString());
		res.append(System.lineSeparator());
		res.append(gameStatut.toString());
		res.append(System.lineSeparator());
		res.append(humain.toStringFriendlyGrid());
		res.append(System.lineSeparator());
		res.append(humain.toStringFleet());
		res.append(System.lineSeparator());
		res.append("-");
		res.append(System.lineSeparator());
		res.append(ia.toStringFriendlyGrid());
		res.append(System.lineSeparator());
		res.append(ia.toStringFleet());
		
		return res.toString();
	}

	@Override
	public void charger() {

	}

}
