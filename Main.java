package Save;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
	
	public static void main(String[] args) {
		saveGame("save1.txt",new GameProgress(1,1,1,2));
		saveGame("save2.txt",new GameProgress(2,2,2,2));
		saveGame("save3.txt",new GameProgress(3,3,3,3));
		
		zipFiles("zip1.zip","save1.txt");
		zipFiles("zip2.zip","save2.txt");
		zipFiles("zip3.zip","save3.txt");
	}
	
	private static void zipFiles(String s, String s1) {
		try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(s));
			FileInputStream file = new FileInputStream(s1))
		{
			ZipEntry z = new ZipEntry(s1);
			zip.putNextEntry(z);
			byte[] buffer = new byte[file.available()];
			file.read(buffer);
			zip.write(buffer);
			zip.closeEntry();
		
		
			
		} catch (IOException ex) {
			
			System.out.println(ex.getMessage());
		}
		 File f =new File(s1);
		f.delete();
	}
	
	private static void saveGame(String s,GameProgress g) {
		File file = new File(s);
		try {
			if (file.createNewFile()) {
				FileOutputStream writer = new FileOutputStream(file);
				ObjectOutputStream ous = new ObjectOutputStream(writer);
				ous.writeObject(g);
			}
			
		} catch (IOException ex) {
			
			System.out.println(ex.getMessage());
		}
	}
	
}
