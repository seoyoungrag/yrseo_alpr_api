package kr.pe.yrseo.api.alpr.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.yrseo.api.alpr.exception.FileStorageException;
import kr.pe.yrseo.api.alpr.property.AlprProperties;
import kr.pe.yrseo.api.alpr.property.FileStorageProperties;

@Service
public class AlprCallService {

    private final Path alprLocation;
    private final Path fileStorageLocation;

    @Autowired
    public AlprCallService(AlprProperties alprProperties, FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        this.alprLocation = Paths.get(alprProperties.getBinDir())
                .toAbsolutePath().normalize();

        try {
            Files.exists(this.alprLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the alpr will be executed.", ex);
        }
    }
    
    public String executeAlpr(String fileNm) {
    	ProcessBuilder processBuilder = new ProcessBuilder();
        Path targetLocation = this.fileStorageLocation.resolve(fileNm);
    	processBuilder.command("bash", "-c", alprLocation+" -c kr "+targetLocation);

    	try {

    		Process process = processBuilder.start();

    		StringBuilder output = new StringBuilder();

    		BufferedReader reader = new BufferedReader(
    				new InputStreamReader(process.getInputStream()));

    		String line;
    		while ((line = reader.readLine()) != null) {
    			output.append(line + "\n");
    		}

    		int exitVal = process.waitFor();
    		if (exitVal == 0) {
    			return output.toString();
    		} else {
    			return "";
    		}

    	} catch (IOException e) {
    		e.printStackTrace();
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    	
    	return "";
    }
}
