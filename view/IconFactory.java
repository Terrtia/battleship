package battleship.view;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Classe stockant les images
 *
 */
public class IconFactory {
	static String path = "src/battleship/";
	 private static IconFactory instance = new IconFactory();
	 
	 /**
	  * 
	  * @return
	  * L'instance de la factory
	  */
	 public static IconFactory getInstance(){
		 return instance;
	 }

	 private Icon explosion = new ImageIcon(path+"explosion.png");
	 private Icon waterExplosion = new ImageIcon(path+"waterExplosion.png");
	 private Icon water = new ImageIcon(path+"water.png");
		
	 private Icon rearBoat = new ImageIcon(path+"boat2.png");
	 private Icon boat = new ImageIcon(path+"boat1.png");
	 private Icon frontBoat = new ImageIcon(path+"boat3.png");
		
	 private Icon rearBoatH = new ImageIcon(path+"boatH2.png");
	 private Icon boatH = new ImageIcon(path+"boatH1.png");
	 private Icon frontBoatH = new ImageIcon(path+"boatH3.png");
		
	 private Icon rearBoatExplosion = new ImageIcon(path+"boatExplosion2.png");
	 private Icon boatExplosion = new ImageIcon(path+"boatExplosion1.png");
	 private Icon frontBoatExplosion = new ImageIcon(path+"boatExplosion3.png");
		
	 private Icon rearBoatHExplosion = new ImageIcon(path+"boatExplosionH2.png");
	 private Icon boatHExplosion = new ImageIcon(path+"boatExplosionH1.png");
	 private Icon frontBoatHExplosion = new ImageIcon(path+"boatExplosionH3.png");
	 
	 /**
	  * 
	  * @return
	  * l'image d'une explosion
	  */
	 public Icon getExplosion(){
		 return explosion;
	 }
	 
	 /**
	  * 
	  * @return
	  * l'image d'un tir manqué
	  */
	 public Icon getWaterExplosion(){
		 return waterExplosion;
	 }
	 
	 /**
	  * 
	  * @return
	  * l'image de l'eau
	  */
	 public Icon getWater(){
		 return water;
	 }
	 
	 /**
	  * 
	  * @param horizontal
	  * boolean verifiant si le bateau est horizontal ou vertical
	  * @return
	  * l'avant du bateau
	  */
	 public Icon getFrontBoat(boolean horizontal){
		 if(horizontal){
			 return frontBoatH;
		 }else{
			 return frontBoat;
		 }
	 }
	 
	 /**
	  * 
	  * @param horizontal
	  * boolean verifiant si le bateau est horizontal ou vertical
	  * @return
	  * l'arriere du bateau
	  */
	 public Icon getRearBoat(boolean horizontal){
		 if(horizontal){
			 return rearBoatH;
		 }else{
			 return rearBoat;
		 }
	 }
	 
	 /**
	  * 
	  * @param horizontal
	  *  boolean verifiant si le bateau est horizontal ou vertical
	  * @return
	  * le corp du bateau
	  */
	 public Icon getBoat(boolean horizontal){
		 if(horizontal){
			 return boatH;
		 }else{
			 return boat;
		 }
	 }
	 
	 /**
	  * 
	  * @param horizontal
	  *  boolean verifiant si le bateau est horizontal ou vertical
	  * @return
	  * l'image de l'avant du bateau en feu
	  */
	 public Icon getExplosiveFrontBoat(boolean horizontal){
		 if(horizontal){
			 return frontBoatHExplosion;
		 }else{
			 return frontBoatExplosion;
		 }
	 }
	 
	 /**
	  * 
	  * @param horizontal
	  *  boolean verifiant si le bateau est horizontal ou vertical
	  * @return
	  * l'image de l'arriere du bateau en feu
	  */
	 public Icon getExplosiveRearBoat(boolean horizontal){
		 if(horizontal){
			 return rearBoatHExplosion;
		 }else{
			 return rearBoatExplosion;
		 }
	 }
	 
	 /**
	  * 
	  * @param horizontal
	  *  boolean verifiant si le bateau est horizontal ou vertical
	  * @return
	  * le corp du bateau en feu
	  */
	 public Icon getExplosiveBoat(boolean horizontal){
		 if(horizontal){
			 return boatHExplosion;
		 }else{
			 return boatExplosion;
		 }
	 }
}
