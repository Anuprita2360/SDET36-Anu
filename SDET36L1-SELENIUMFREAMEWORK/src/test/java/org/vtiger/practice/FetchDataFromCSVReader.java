package org.vtiger.practice;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class FetchDataFromCSVReader {

	public static void main(String[] args) throws IOException, CsvException 
	{
		CSVReader reader = new CSVReader(new FileReader("./src/test/resources/CSVfile.csv"));
		List<String[]> allData = reader.readAll();
		String[] dataLine = allData.get(3);
		String data = dataLine[1];
		System.out.println(data);
	}

}
