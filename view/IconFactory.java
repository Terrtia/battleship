package battleship.view;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class IconFactory {

	 private static IconFactory instance = new IconFactory();
	 
	 public static IconFactory getInstance(){
		 return instance;
	 }

	 private Icon explosion = new ImageIcon("src/explosion.png");
	 private Icon waterExplosion = new ImageIcon("src/waterExplosion.png");
	 private Icon water = new ImageIcon("src/water.png");
		
	 private Icon rearBoat = new ImageIcon("src/boat2.png");
	 private Icon boat = new ImageIcon("src/boat1.png");
	 private Icon frontBoat = new ImageIcon("src/boat3.png");
		
	 private Icon rearBoatH = new ImageIcon("src/boatH2.png");
	 private Icon boatH = new ImageIcon("src/boatH1.png");
	 private Icon frontBoatH = new ImageIcon("src/boatH3.png");
		
	 private Icon rearBoatExplosion = new ImageIcon("src/boatExplosion2.png");
	 private Icon boatExplosion = new ImageIcon("src/boatExplosion1.png");
	 private Icon frontBoatExplosion = new ImageIcon("src/boatExplosion3.png");
		
	 private Icon rearBoatHExplosion = new ImageIcon("src/boatExplosionH2.png");
	 private Icon boatHExplosion = new ImageIcon("src/boatExplosionH1.png");
	 private Icon frontBoatHExplosion = new ImageIcon("src/boatExplosionH3.png");
	 
	 public Icon getExplosion(){
		 return explosion;
	 }
	 
	 public Icon getWaterExplosion(){
		 return waterExplosion;
	 }
	 
	 public Icon getWater(){
		 return water;
	 }
	 
	 public Icon getFrontBoat(boolean horizontal){
		 if(horizontal){
			 return frontBoatH;
		 }else{
			 return frontBoat;
		 }
	 }
	 
	 public Icon getRearBoat(boolean horizontal){
		 if(horizontal){
			 return rearBoatH;
		 }else{
			 return rearBoat;
		 }
	 }
	 
	 public Icon getBoat(boolean horizontal){
		 if(horizontal){
			 return boatH;
		 }else{
			 return boat;
		 }
	 }
	 
	 public Icon getExplosiveFrontBoat(boolean horizontal){
		 if(horizontal){
			 return frontBoatHExplosion;
		 }else{
			 return frontBoatExplosion;
		 }
	 }
	 
	 public Icon getExplosiveRearBoat(boolean horizontal){
		 if(horizontal){
			 return rearBoatHExplosion;
		 }else{
			 return rearBoatExplosion;
		 }
	 }
	 
	 public Icon getExplosiveBoat(boolean horizontal){
		 if(horizontal){
			 return boatHExplosion;
		 }else{
			 return boatExplosion;
		 }
	 }
}
