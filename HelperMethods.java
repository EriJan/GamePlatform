//package CardGame;

import java.io.*;
import java.net.*;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
/**
 * Created by Niklas on 19/10/15.
 *
 */
public class HelperMethods {

	public static final String ERROR_TRY_AGAIN = "Något har blivit fel. Tänk på att svara genom att skriva en siffra.";

	public static boolean fileExists(String URLName){
    try {
      HttpURLConnection.setFollowRedirects(false);
      // note : you may also need
      //        HttpURLConnection.setInstanceFollowRedirects(false)
      HttpURLConnection con =
              (HttpURLConnection) new URL(URLName).openConnection();
      con.setRequestMethod("HEAD");
      return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

	/**
	 * get string from input (scanner nextline)
	 *
	 */
  public static String inPutFromNextLine() {
		String input= null;
		Scanner in = new Scanner(System.in);
		input = in.nextLine();
		return input;
	}
	private enum OSType {
		Windows, MacOS, Linux, Other
	};
	protected static OSType detectedOS;
	/**
	 * return OS :
	 * Windows, MacOS, Linux, Other
	 *good for path if used..
	 */
	public static OSType getOperatingSystemType() {
		if (detectedOS == null) {
			String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
			if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0)) {
				detectedOS = OSType.MacOS;
			} else if (OS.indexOf("win") >= 0) {
				detectedOS = OSType.Windows;
			} else if (OS.indexOf("nux") >= 0) {
				detectedOS = OSType.Linux;
			} else {
				detectedOS = OSType.Other;
			}
		}
		return detectedOS;
	}
	/**
	 * get int from input (scanner nextline)
	 *
	 */
	public static int inPutFromNextInt() {
		String input= null;
		char check;
		int returnInt;
    Scanner in = new Scanner(System.in);
    while (true) {
			input = in.nextLine();
			check = input.charAt(0);
			if (Character.isDigit(check)) {
				returnInt = Integer.parseInt(input);
				break;
			} else {
				System.out.println(ERROR_TRY_AGAIN);
			}
		}
		return returnInt;
	}
	/**
	 * print slowly a System.out.print, fixed delay
	 *
	 */
	public static void printSlowly(String text) {//skriver ut texter lÃ¥ngsamt ut en string..
		try {
			for (char character : text.toCharArray()) {
				System.out.print(character);  // skriver varje char (i introText)

				if ( character != ' ' ){ //om det inte vara var ett mellanslag!
					Thread.sleep(5);  // vÃ¤nta lite tills nÃ¤sta.. (skapar en trÃ¥d och vÃ¤ntar liiite)
				}//annars vÃ¤ntar vi inte...
			}

		} catch (InterruptedException e) {//om trÃ¥den blir fel eller liknande
			System.out.println(text);//skriv ut som vanligt instÃ¤llet..
		}


	}
	/**
	 * print slowly a System.out.print, and adjusting mSecond
	 *
	 */
	public static void printSlowly(String text, int mSecondsDelay) {//skriver ut texter lÃ¥ngsamt ut en string..
		try {
			for (char character : text.toCharArray()) {
				System.out.print(character);  // skriver varje char (i introText)

				if ( character != ' ' ){ //om det inte vara var ett mellanslag!
					Thread.sleep(mSecondsDelay);  // vÃ¤nta lite tills nÃ¤sta.. (skapar en trÃ¥d och vÃ¤ntar liiite)
				}//annars vÃ¤ntar vi inte...
			}
		} catch (InterruptedException e) {//om trÃ¥den blir fel eller liknande
			System.out.println(text);//skriv ut som vanligt instÃ¤llet..
		}
	}

	/**
	 * @param menyElements
	 * @return the name of the selected option
	 * 	  switch (Methods.choseFromMeny("TestGame,BlackJack,Patians,21")) {
	case "TestGame":
	System.out.println("Run the testgame");
	break;

	default:
	System.out.println("do nothing..");
	break;

	}
	 */
	public static String choseFromMeny(String menyElements) {



		String[] menyElements_ = menyElements.split(",");
		StringBuilder sb = new StringBuilder(
				"*********** Chose ***********\n");
		for(int i = 0; i< menyElements_.length; i++){
			sb.append(" "
					+ (i+1) + " : " + menyElements_[i] + "\n");
		}
		sb.append("*****************************\n");
		printSlowly(sb.toString());
		String choice;
		do{
			System.out.print("chose a number(1 to " + menyElements_.length + ") :");
			choice = inPutFromNextLine();
			if(choice.isEmpty()|| !choice.matches("[0-9]+")) {
				choice = "-1";

			}
		}while ( (Integer.parseInt(choice)<0) || (((Integer.parseInt(choice))) > (menyElements_.length)) );

		return menyElements_[(Integer.parseInt(choice)-1)];
	}
	public static String choseFromMenyNew(String... menyElements) {

		StringBuilder sb = new StringBuilder(
				"*********** Chose ***********\n");
		for(int i = 0; i< menyElements.length; i++){
			sb.append(" "
					+ (i+1) + " : " + menyElements[i] + "\n");
		}
		sb.append("*****************************\n");
		printSlowly(sb.toString());
		String choice;
		do{
			System.out.print("chose a number(1 to " + menyElements.length + ") :");
			choice = inPutFromNextLine();
			if(choice.isEmpty()|| !choice.matches("[0-9]+")) {
				choice = "-1";

			}
		}while ( (Integer.parseInt(choice)<0) || (((Integer.parseInt(choice))) > (menyElements.length)) );

		return menyElements[(Integer.parseInt(choice)-1)];
	}

  public static int choseFromMenyInt(String... menyElements) {

    StringBuilder sb = new StringBuilder(
            "*********** Chose ***********\n");
    for(int i = 0; i< menyElements.length; i++){
      sb.append(" "
              + (i+1) + " : " + menyElements[i] + "\n");
    }
    sb.append("*****************************\n");
    printSlowly(sb.toString());
    String choice;
    do{
      System.out.print("chose a number(1 to " + menyElements.length + ") :");
      choice = inPutFromNextLine();
      if(choice.isEmpty()|| !choice.matches("[0-9]+")) {
        choice = "-1";

      }
    }while ( (Integer.parseInt(choice)<0) || (((Integer.parseInt(choice))) > (menyElements.length)) );

    return Integer.parseInt(choice)-1;
  }
	/**
	 * read filename, if not exist return " "
	 *
	 */
  public static String readFile(String filename)  {
/*		File file = new File(filename);
		int len = (int) file.length();
		byte[] bytes = new byte[len];
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(file);
			assert len == fis.read(bytes);
			return new String(bytes, "UTF-8");
		} catch (IOException e) {
			return " ;";
		}
		*/
		StringBuilder conent = new StringBuilder("");
		try{

			//Create object of FileReader
			FileReader inputFile = new FileReader(filename);

			//Instantiate the BufferedReader Class
			BufferedReader bufferReader = new BufferedReader(inputFile);

			//Variable to hold the one line data
			String line;



			// Read file line by line and print on the console
			while ((line = bufferReader.readLine()) != null)   {
				conent.append(line);
			}
			//Close the buffer reader
			bufferReader.close();
		}catch(Exception e){
			return " ";
		}
		return conent.toString();

	}
	/**
	 * write to filename
	 *
	 */
	public static void writeFile(String filename, String text) throws IOException {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filename);
			fos.write(text.getBytes("UTF-8"));
		} catch (IOException e) {
			close(fos);
			throw e;
		}
	}

	public static void close(Closeable closeable) {
		try {
			closeable.close();
		} catch(IOException ignored) {
		}
	}
	/**
	 * print nice logo eNug
	 *
	 */
	public static String introtext() {
		StringBuilder str = new StringBuilder("");
		str.append("" +
				"                                                                        \n" +
				"                  _/      _/         _/                              \n" +
				"      _/_/       _/_/    _/                 _/    _/        _/_/_/   \n" +
				"   _/_/_/_/     _/  _/  _/         _/      _/    _/      _/    _/    \n" +
				"  _/           _/    _/_/         _/      _/    _/      _/    _/     \n" +
				"   _/_/_/     _/      _/         _/        _/_/_/        _/_/_/      \n" +
				"                                _/                          _/       \n" +
				"                             _/                        _/_/          \n");




		return str.toString();
	}

	public static String introtext2() {
		StringBuilder str = new StringBuilder("");
		str.append("" +
				" ______     __   __       __     __  __     ______    \n" +
				"/\\  ___\\   /\\ \"-.\\ \\     /\\ \\   /\\ \\/\\ \\   /\\  ___\\   \n" +
				"\\ \\  __\\   \\ \\ \\-.  \\   _\\_\\ \\  \\ \\ \\_\\ \\  \\ \\ \\__ \\  \n" +
				" \\ \\_____\\  \\ \\_\\\\\"\\_\\ /\\_____\\  \\ \\_____\\  \\ \\_____\\ \n" +
				"  \\/_____/   \\/_/ \\/_/ \\/_____/   \\/_____/   \\/_____/ \n" +
				"                         ");
		return str.toString();
	}

    public static int getRandomInt(int min , int max) {
        Random rand = new Random();
        return ((rand.nextInt((max - 0) + min) + min));
    }


	public static String introtext3() {
		StringBuilder str = new StringBuilder("");
		str.append("" +
				" \n" +
				"    ▄████████    ███▄▄▄▄          ▄█  ███        █▄        ▄██████▄  \n" +
				"    ███      ███   ███▀▀▀██▄     ███  ███        ███      ███      ███ \n" +
				"    ███      █▀     ███     ███     ███  ███        ███      ███      █▀  \n" +
				"  ▄███▄▄▄         ███      ███     ███  ███        ███    ▄███        \n" +
				"▀▀███▀▀▀         ███      ███     ███  ███         ███  ▀▀███   ████▄  \n" +
				"   ███       █▄    ███     ███       ███  ███        ███      ███       ███ \n" +
				"   ███       ███  ███     ███       ███  ███       ███       ███      ███ \n" +
				"   ██████████   ▀█      █▀  █▄ ▄███  ████████▀        ████████▀  \n" +
				"                                       ▀▀▀▀▀▀                          \n");
		return str.toString();
	}

/*	public static String stringTOACCII(String text) { // throws IOException {

		int width = 100;
		int height = 30;
		StringBuilder sb = new StringBuilder();;

		//BufferedImage image = ImageIO.read(new File("/logo.jpg"));
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setFont(new Font("SansSerif", Font.BOLD, 24));

		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		graphics.drawString(text, 10, 20);


		//save this to image
		//ImageIO.write(image, "png", new File("/ascii-art.png"));

		for (int y = 0; y < height; y++) {
			sb = new StringBuilder("");
			for (int x = 0; x < width; x++) {

				sb.append(image.getRGB(x, y) == -16777216 ? " " : "$");

			}

			if (sb.toString().trim().isEmpty()) {
				continue;
			}


		}
		return sb.toString();
	}

*/



}

