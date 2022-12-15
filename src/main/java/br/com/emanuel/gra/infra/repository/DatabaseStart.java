package br.com.emanuel.gra.infra.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.emanuel.gra.domain.nominee.Nominee;
import br.com.emanuel.gra.domain.nominee.usecase.NewNominee;
import jakarta.annotation.PostConstruct;

@Component
public class DatabaseStart {
	
	@Autowired
	private NewNominee newNominee;
	
	@Value("${movielist.file.location}")
	private String movieListFile;
	
	@Value("${movielist.file.idx.year}")
	private int idxYear;
	
	@Value("${movielist.file.idx.title}")
	private int idxTitle;
	
	@Value("${movielist.file.idx.studios}")
	private int idxStudios;
	
	@Value("${movielist.file.idx.producers}")
	private int idxProducers;
	
	@Value("${movielist.file.idx.winner}")
	private int idxWinner;
	
	@PostConstruct
	public void loadDataFromCSV() {
		
		File file = new File(movieListFile);
		if (!file.exists()) {
			Logger.getLogger(getClass().getName()).warning("No data file found!!! System will start with a blank database.");
			return;
		}
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(file.getPath()))) {

			List<String> lines = br.lines().collect(Collectors.toList());
			
			long count = lines.stream().filter(line -> importNominee(line)).count();
			
			Logger.getLogger(getClass().getName()).info(String.format(">>>>>>>>>>>>>>>>> Successfully imported %d registers from %s file", count, movieListFile));

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private boolean importNominee(String line) {
		
		String[] columns = line.split(";");
		
		if (columns[idxYear].toLowerCase().equals("year")) return false; //title row
		
		try {
			Nominee nominee = new Nominee();
			nominee.setYear(Integer.valueOf(columns[idxYear]));
			nominee.setProducers(columns[idxProducers]);
			nominee.setStudios(columns[idxStudios]);
			nominee.setTitle(columns[idxTitle]);
			nominee.setWinner(columns.length > idxWinner && "yes".equals(columns[idxWinner]));
			
			newNominee.save(nominee);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
