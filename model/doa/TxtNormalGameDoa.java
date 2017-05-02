package battleship.model.doa;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;

import battleship.model.Model;
import battleship.model.Model.GameStatut;
import battleship.model.game.GameMode.Epoch;
import battleship.model.game.GameMode.Gamemode;
import battleship.model.grid.Grid;
import battleship.model.grid.Grid.Square;
import battleship.model.ships.Ship;


public class TxtNormalGameDoa implements DoaNormalGame {

	public TxtNormalGameDoa() {
		
	}

	@Override
	public void sauvegarder(Gamemode gameMode, GameStatut gameStatut, Grid humain, Grid ia, Epoch epoch) {
		
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
            p.append (this.toStringPartie(gameMode, gameStatut, humain, ia, epoch));
            p.close();
        	
        } catch (Exception e) {
        	System.err.println ("Error create save");
        	e.printStackTrace();
        }
        
	}
	
	public String toStringPartie(Gamemode gameMode, GameStatut gameStatut, Grid humain, Grid ia, Epoch epoch){
		
		StringBuilder res = new StringBuilder();
		res.append(gameMode.toString());
		res.append(System.lineSeparator());
		res.append(epoch.toString());
		res.append(System.lineSeparator());
		res.append(gameStatut.toString());
		res.append(System.lineSeparator());
		res.append(humain.toStringFriendlyGrid());
		res.append(System.lineSeparator());
		res.append(humain.toStringFleet());
		res.append("-");
		res.append(System.lineSeparator());
		res.append(ia.toStringFriendlyGrid());
		res.append(System.lineSeparator());
		res.append(ia.toStringFleet());
		res.append("-");
		
		return res.toString();
	}

	@Override
	public void charger(Model model, String path) throws Exception {
		String line;
		String[] parts;
		BufferedReader inFile = new BufferedReader(new FileReader(path));
		
		model.recreateShip();
		
		// read first line = GameType
		line = inFile.readLine();
		
		//CLASSIC game
		if(line.equals(Gamemode.CLASSIC.toString()) ){
			model.setGamemode(Gamemode.CLASSIC);
		//Error
		} else {
			inFile.close();
			throw new Exception("incorrect save file, GameType");
		}
		
		line = inFile.readLine();
		//EPOCH
		if(line.equals(Epoch.MODERN.toString()) ){
			model.setEpoch(Epoch.MODERN);
		//Error
		} else {
			inFile.close();
			throw new Exception("incorrect save file, Epoch");
		}
		
		// GameStatut
		line = inFile.readLine();
		// HUMAIN_TOUR
		if(line.equals(GameStatut.HUMAIN_TOUR.toString()) ){
			model.setGameStatut(GameStatut.HUMAIN_TOUR);
		// IA_TOUR
		} else if(line.equals(GameStatut.IA_TOUR.toString()) ){
			model.setGameStatut(GameStatut.IA_TOUR);
		// HUMAIN_DEFAITE
		} else if(line.equals(GameStatut.HUMAIN_DEFAITE.toString()) ){
			model.setGameStatut(GameStatut.HUMAIN_DEFAITE);
		// HUMAIN_VICTOIRE
		} else if(line.equals(GameStatut.HUMAIN_VICTOIRE.toString()) ){
			model.setGameStatut(GameStatut.HUMAIN_VICTOIRE);
		// EGALITE
		} else if(line.equals(GameStatut.EGALITE.toString()) ){
			model.setGameStatut(GameStatut.EGALITE);
		// ERROR
		} else {
			inFile.close();
			throw new Exception("incorrect save file, GameStatut");
		}
		
		// Human friendlyGrid
		line = inFile.readLine();
		parts = line.split(";");
		int index = 0;
		int gridSize = model.getGridSize();
		Square humanFriendlyGrid[][] = new Square[gridSize][gridSize];
		
		/*for(String part: parts){
			System.out.println(part);
		}*/
		
		for(int i=0; i < gridSize;i++){
			for(int j = 0; j < gridSize;j++){
				if(parts[index].equals(Square.EMPTY.toString())){
					humanFriendlyGrid[i][j] = Square.EMPTY;
				} else if ( parts[index].equals(Square.HIT.toString())){
					humanFriendlyGrid[i][j] = Square.HIT;
				} else if ( parts[index].equals(Square.MISS.toString())){
					humanFriendlyGrid[i][j] = Square.MISS;
				} else {
					inFile.close();
					throw new Exception("not Square Object");
				}
				index++;
			}
		}
		model.setHumanFriendlyGrid(humanFriendlyGrid);
		
		// Human Fleet
		int size;
		int hitPoints;
		int topLeftX;
		int topLeftY;
		boolean horizontal = false;
		ArrayList<Ship> fleetHuman = model.getHumanFleet();
			
		for(int i=0; i<5 ; i++){
			
			
			size = -1;
			hitPoints = -1;
			topLeftX = -1;
			topLeftY = -1;
			horizontal = false;
			
			line = inFile.readLine();
			parts = line.split(";");
			
			//size
			size = Integer.valueOf(parts[0]);
			//hitPoints
			hitPoints = Integer.valueOf(parts[1]);
			//topLeftX
			topLeftX = Integer.valueOf(parts[2]);
			//topLeftY
			topLeftY = Integer.valueOf(parts[3]);
			//horizontal=false
			horizontal = Boolean.valueOf(parts[4]);
			
			if(size == -1 | hitPoints == -1 | topLeftX == -1 | topLeftY == -1 ){
				inFile.close();
				throw new Exception("Error load file, ia ship values");
			}
			
			fleetHuman.get(i).setSize(size);
			model.getHumanGrid().placeShip(fleetHuman.get(i), topLeftX, topLeftY, horizontal);
			fleetHuman.get(i).setHitPoints(hitPoints);
		}
		
		line = inFile.readLine();
		if(!line.equals("-")) {
			inFile.close();
			throw new Exception("incorrect save file");
		}
		
		// IA friendlyGrid
		line = inFile.readLine();
		parts = line.split(";");
		index = 0;
		gridSize = model.getGridSize();
		Square iaFriendlyGrid[][] = new Square[gridSize][gridSize];
		
		for(int i=0; i < gridSize;i++){
			for(int j = 0; j < gridSize;j++){
				if(parts[index].equals(Square.EMPTY.toString())){
					iaFriendlyGrid[i][j] = Square.EMPTY;
				} else if ( parts[index].equals(Square.HIT.toString())){
					iaFriendlyGrid[i][j] = Square.HIT;
				} else if ( parts[index].equals(Square.MISS.toString())){
					iaFriendlyGrid[i][j] = Square.MISS;
				} else {
					inFile.close();
					throw new Exception("not Square Object");
				}
				index++;
			}
		}
		model.setIaFriendlyGrid(iaFriendlyGrid);
				
		
		// IA Fleet
		ArrayList<Ship> fleetIa = model.getIAFleet();
		
		for(int i=0; i<5 ; i++){	
			
			size = -1;
			hitPoints = -1;
			topLeftX = -1;
			topLeftY = -1;
			horizontal = false;
			
			line = inFile.readLine();
			parts = line.split(";");
			
			//size
			size = Integer.valueOf(parts[0]);
			//hitPoints
			hitPoints = Integer.valueOf(parts[1]);
			//topLeftX
			topLeftX = Integer.valueOf(parts[2]);
			//topLeftY
			topLeftY = Integer.valueOf(parts[3]);
			//horizontal=false
			horizontal = Boolean.valueOf(parts[4]);
			
			if(size == -1 | hitPoints == -1 | topLeftX == -1 | topLeftY == -1 ){
				inFile.close();
				throw new Exception("Error load file, ia ship values");
			}
			
			fleetIa.get(i).setSize(size);
			model.getIAGrid().placeShip(fleetIa.get(i), topLeftX, topLeftY, horizontal);
			fleetIa.get(i).setHitPoints(hitPoints);
		}
		
		line = inFile.readLine();
		if(!line.equals("-")) {
			inFile.close();
			throw new Exception("incorrect save file");
		}
		
		inFile.close();
	}

	
}
