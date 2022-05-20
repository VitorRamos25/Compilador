package Utilidades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MLFile {
	
	private final String caminhoArquivo;
	
	private final String nomeArquivo;
	
	private File file;
	
	private BufferedReader br;
	
	public MLFile(final String caminhoArquivo, final String nomeArquivo) {
		this.caminhoArquivo = caminhoArquivo;
		this.nomeArquivo = nomeArquivo;
	}
	
	public void Escrever(final String texto) throws IOException {
		
		BufferedWriter bw = null;
		try {
			this.file = new File(this.caminhoArquivo+this.nomeArquivo);
			
			if (this.file.exists()) {
				FileOutputStream output = new FileOutputStream(this.file);
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(output);
				bw = new BufferedWriter(outputStreamWriter);
				bw.write(texto);
			}
			else {
				throw new FileNotFoundException("Arquivo: "+this.caminhoArquivo+this.nomeArquivo+"Não encontrado");
				
			}
		}
		finally {
			if (bw != null) {
				bw.close();
			}
		}
		
	}
	
	public String Ler(){
		
		StringBuilder result = new StringBuilder();
		this.file = new File(this.caminhoArquivo+this.nomeArquivo);
		
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
	
	protected void finalize() throws Throwable {
		
		if (br != null) {
			System.out.println("Executou o finalize");
			br.close();
		}
	}

}