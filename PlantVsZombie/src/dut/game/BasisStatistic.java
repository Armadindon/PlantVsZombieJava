package dut.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


public class BasisStatistic {
	private int sunNumber;
	private int nbZombies;
	private int refresh;
	
	public BasisStatistic(String path) {
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(path),StandardCharsets.UTF_8)) {
			String line;
			Scanner sc;
			while((line = reader.readLine())!=null) {
				if(line.startsWith("sunNumber")) {
					sunNumber = Integer.parseInt(line.replace("sunNumber = " , ""));
				}else if(line.startsWith("nbZombies")) {
					nbZombies =Integer.parseInt(line.replace("nbZombies = " , ""));
				}else if(line.startsWith("refresh")) {
					refresh = Integer.parseInt(line.replace("refresh = " , ""));
				}
			}
		} catch (IOException e) {
			System.out.println("Le fichier n'est pas bon ! Fermeture...");
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
	}
	
	public int getSunNumber() {
		return sunNumber;
	}
	public int getNbZombies() {
		return nbZombies;
	}
	public int getRefresh() {
		return refresh;
	}

}
