package Utilidades;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class MLFile {
	
	public static void gravarArquivo(String path, String content) {
		File file = new File(path);
		
		try (FileOutputStream arq = new FileOutputStream(file);	PrintStream ps = new PrintStream(arq)) {
			ps.println(content);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String lerArq(String path) {
		StringBuilder result = new StringBuilder();
		File file = new File(path);
		
		try (FileInputStream arq = new FileInputStream(file)) {
			int caracterLido = arq.read();
			
			while (caracterLido != -1) {
				result.append((char) caracterLido);
				caracterLido = arq.read();
			}
			
			return result.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}

}