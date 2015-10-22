/**
 * Created by NIK1114 on 2015-10-13.
 *
 * Dice:
 * A dice has 6 pictures (dicefacesimg) and 6 values( int diceFaces)
 * must "be in a cup" (shake-cup) to be rolled..
 **/
import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class Dice implements Runnable{
  //},Subject{ //Subject = detta borde man göra med en "CUP class.."
  //private ArrayList<Observer> observers;//detta borde man göra med en "CUP class.."
  //private Object listener;;

  private int [] diceFaces = {1,2,3,4,5,6};
  private int result = 0;
  private Boolean isSpinning = false;
  boolean inCup;
    /*region Description
    Image dice1Img = new ImageIcon(this.getClass().getResource("/red-die-1.jpg")).getImage();
    Image dice2Img = new ImageIcon(this.getClass().getResource("/red-die-2.jpg")).getImage();
    Image dice3Img = new ImageIcon(this.getClass().getResource("/red-die-3.jpg")).getImage();
    Image dice4Img = new ImageIcon(this.getClass().getResource("/red-die-4.jpg")).getImage();
    Image dice5Img = new ImageIcon(this.getClass().getResource("/red-die-5.jpg")).getImage();
    Image dice6Img = new ImageIcon(this.getClass().getResource("/red-die-6.jpg")).getImage();
    */

  Image dice1Img = new ImageIcon("C:\\location\\dice\\red-die-1.jpg").getImage();
  Image dice2Img = new ImageIcon("C:\\location\\dice\\red-die-2.jpg").getImage();
  Image dice3Img = new ImageIcon("C:\\location\\dice\\red-die-3.jpg").getImage();
  Image dice4Img = new ImageIcon("C:\\location\\dice\\red-die-4.jpg").getImage();
  Image dice5Img = new ImageIcon("C:\\location\\dice\\red-die-5.jpg").getImage();
  Image dice6Img = new ImageIcon("C:\\location\\dice\\red-die-6.jpg").getImage();


  //new ImageIcon("/Users/Janne/JavaProj/src/logo/location/logo1.png")); //is under " C:\location\"


  Image[] dicefacesimg = {dice1Img,dice2Img,dice3Img,dice4Img,dice5Img,dice6Img};
  Image resultFaceImg = dicefacesimg[0];

  /** if NOT thread is used! and If the dice is in cup **/
  public void rowle() {
    if(inCup){
      System.out.println("katsat en av dem(innan run)");
      run();
      System.out.println("katsat en av dem(efter run)");
    }

  }

  /** get the dice int-value: 1,2,3,4,5,6 **/
  public int getDiceValue() {
    //här borde jag uppdatera jframes EVENTUELLT!, men hur skapar jag den länken?!
    return result;
  }

  public boolean isDiceSpinning() {
    return isSpinning;
  }

  /** the Roller method! if we use thread  = If the dive is in cup then roll dice (the isSpinning it true while spinning, and update setResult while spinning**/
  @Override
  public void run() {
    System.out.println("försöker att katsa en av dem(innan kontroll i run)");
    if(inCup){


      System.out.println("katsat en av dem(efter run)");


      isSpinning = true;
//

      for (int i = 0 ; i<40; i++){
        Random rand = new Random();
        //int randomNum = rand.nextInt(dice.length-1);
//			setResult(diceFaces[rand.nextInt(diceFaces.length-1)]);	//0-5 => 1-6
        int randomNum = rand.nextInt(diceFaces.length);	//0-5 => 1-6
        System.out.println("randomNum " + randomNum);
        setResult(randomNum);
        //System.out.println("sätter från run : " + dice[rand.nextInt(dice.length)]);
        //updateGui(result);

        try {
          Thread.sleep(20);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }

    isSpinning = false;

  }

  /** the run() set this 40 times/roll **/
  private void setResult(int i) {
    result = diceFaces[i];
    System.out.println("i dice : " + i);
    setFaceImg(i);
  }

  /** "activate" the role function **/
  public void setInCup() {
    inCup = true;
  }

  /** "de-activate" the role function **/
  public void removeInCup() {
    inCup = false;
  }

  /**  check if dice is in cup **/
  public boolean isInCup() {
    return inCup;
  }

  /** update the dice face up image (resultFaceImg)**/
  private void setFaceImg(int i) {
    System.out.println("uppdaterar img: "+ i);
    resultFaceImg = dicefacesimg[i];
//		notifyObservers(); //nu har vi en ändring..
  }

  /**  Call this to get the (resultFaceImg) **/
  public Image getFaceImg() {
    return resultFaceImg;
  }

//	@Override
//	public void registerObserver(Observer o) {
//		observers.add(o);
//
//	}
//
//	@Override
//	public void removeObserver(Observer o) {
//		int i = observers.indexOf(o);
//		if(i>=0){
//			observers.remove(i);
//		}
//
//	}
//
//	@Override
//	public void notifyObservers() {
//		int DiceID = 0;
//
//		for(int i=0; i< observers.size(); i++){
//			Observer observer = (Observer)observers.get(i);
//			observer.update(getFaceImg(), getDiceValue(), DiceID);
//		}
//
//
//	}

//	private void notifyListeners(Object object, String property, String oldValue, String newValue) {
//	    for (PropertyChangeListener name : listener) {
//	      name.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
//	    }
//	  }
//
//	  public void addChangeListener(PropertyChangeListener newListener) {
//	    listener.add(newListener);
//	  }
//
}
