//Design a Java program to copy the contents of one file into another file.
import java.io.*;
class Copy
	{
		public static void main(String args[]) throws IOException,FileNotFoundException {
			FileInputStream fis=new FileInputStream("abc.txt");
			FileOutputStream fos=new FileOutputStream("bbc.txt");
			int data;
			while((data=fis.read())!=-1) {
				fos.write(data); }
				System.out.println("Success");
				fis.close();
				fos.flush();
				fos.close();
				}
			
	}
