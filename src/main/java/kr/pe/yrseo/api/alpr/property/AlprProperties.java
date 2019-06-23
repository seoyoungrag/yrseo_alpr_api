package kr.pe.yrseo.api.alpr.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "alpr")
public class AlprProperties {

    private String binDir;

	public String getBinDir() {
		return binDir;
	}

	public void setBinDir(String binDir) {
		this.binDir = binDir;
	}
    
}
