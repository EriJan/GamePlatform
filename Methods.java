//package CardGame;

		import java.io.Closeable;
		import java.io.File;
		import java.io.FileInputStream;
		import java.io.FileOutputStream;
		import java.io.IOException;
		import java.util.Scanner;

public class Methods {
	public static String inPutFromNextLine() {
		String input= null;
		Scanner in = new Scanner(System.in);
		input = in.nextLine();
		return input;
	}

	public static void printSlowly(String text) {//skriver ut texter lÃ¥ngsamt ut en string..
		try {
			for (char character : text.toCharArray()) {
				System.out.print(character);  // skriver varje char (i introText)

				if ( character != ' ' ){ //om det inte vara var ett mellanslag!
					Thread.sleep(50);  // vÃ¤nta lite tills nÃ¤sta.. (skapar en trÃ¥d och vÃ¤ntar liiite)
				}//annars vÃ¤ntar vi inte...
			}
		} catch (InterruptedException e) {//om trÃ¥den blir fel eller liknande
			System.out.println(text);//skriv ut som vanligt instÃ¤llet..
		}
	}

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

	public static String readFile(String filename) throws IOException {
		File file = new File(filename);
		int len = (int) file.length();
		byte[] bytes = new byte[len];
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			assert len == fis.read(bytes);
		} catch (IOException e) {
			close(fis);
			throw e;
		}
		return new String(bytes, "UTF-8");
	}

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


}

