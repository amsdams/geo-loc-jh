package com.amsdams.jh.web.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.geolatte.geom.Position;
import org.geolatte.geom.crs.CrsRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amsdams.jh.service.MyGeocityService;
import com.amsdams.jh.service.dto.GeocityDTO;

@RestController
@RequestMapping("/api/my")
public class MyGeocityResource extends GeocityResource {

	public MyGeocityResource(MyGeocityService myGeocityService) {
		super(myGeocityService);
		// TODO Auto-generated constructor stub
		this.myGeocityService = myGeocityService;
	}

	private final Logger log = LoggerFactory.getLogger(MyGeocityResource.class);

	private static final String FRGeonamesCityFile = "/files/NL.txt";
	private final MyGeocityService myGeocityService;

	@GetMapping("/loadGeoNamesCitiesInBatches")
	public void loadGeoNamesCitiesInBatches() {
		log.info("Loading GeoName file " + FRGeonamesCityFile);
		log.info(env.getProperty("spring.jpa.properties.hibernate.jdbc.batch_size "));
		
		InputStream stream = this.getClass().getResourceAsStream(FRGeonamesCityFile);

		try (BufferedReader br = new BufferedReader(new InputStreamReader(stream))) {

			String sCurrentLine;
			int nbCities = 0;
			List<GeocityDTO> geocityDTOs = new ArrayList<>();

			while ((sCurrentLine = br.readLine()) != null) {
				String[] columns = sCurrentLine.split("\t");
				if (columns[7].startsWith("PPL")) {
					GeocityDTO geocity = new GeocityDTO();
					geocity.setName(columns[1]);
					geocity.setAsciiname(columns[2]);
					String alternateNames = columns[3];
					alternateNames = StringUtils.abbreviate(alternateNames, 255);
					geocity.setAlternatenames(alternateNames);

					Position g2d = new G2D(Double.valueOf(columns[5]), Double.valueOf(columns[4]));
					Point point = new Point(g2d, CrsRegistry.getCoordinateReferenceSystemForEPSG(4326, null));
					// geocity.setLocation(point);
					// Also copy lat / lon to duplicate columns in the Database
					geocity.setLat(Double.valueOf(columns[4]));
					geocity.setLon(Double.valueOf(columns[5]));

					geocity.setFeatureclass(columns[6]);
					geocity.setFeaturetype(columns[7]);
					geocity.setCountrycode(columns[8]);
					geocity.setCc2(columns[9]);
					geocity.setAdmin1code(columns[10]);
					geocity.setAdmin2code(columns[11]);
					geocity.setAdmin3code(columns[12]);
					geocity.setAdmin4code(columns[13]);
					geocity.setPopulation(Integer.valueOf(columns[14]));
					if (columns[15].trim().length() == 0)
						geocity.setElevation(0);
					else
						geocity.setElevation(Integer.valueOf(columns[15]));
					geocity.setDem(columns[16]);

					// geocityService.save(geocity);
					geocityDTOs.add(geocity);
					nbCities++;
				}
			}
			myGeocityService.saveAll(geocityDTOs);
			log.info("Cities uploaded : " + nbCities);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Autowired
	private Environment env;
	
	@GetMapping("/loadGeoNamesCities")
	public void loadGeoNamesCities() {
		log.info("Loading GeoName file " + FRGeonamesCityFile);
		InputStream stream = this.getClass().getResourceAsStream(FRGeonamesCityFile);

		try (BufferedReader br = new BufferedReader(new InputStreamReader(stream))) {

			String sCurrentLine;
			int nbCities = 0;
			while ((sCurrentLine = br.readLine()) != null) {
				String[] columns = sCurrentLine.split("\t");
				if (columns[7].startsWith("PPL")) {
					GeocityDTO geocity = new GeocityDTO();
					geocity.setName(columns[1]);
					geocity.setAsciiname(columns[2]);
					String alternateNames = columns[3];
					alternateNames = StringUtils.abbreviate(alternateNames, 255);
					geocity.setAlternatenames(alternateNames);

					Position g2d = new G2D(Double.valueOf(columns[5]), Double.valueOf(columns[4]));
					Point point = new Point(g2d, CrsRegistry.getCoordinateReferenceSystemForEPSG(4326, null));
					// geocity.setLocation(point);
					// Also copy lat / lon to duplicate columns in the Database
					geocity.setLat(Double.valueOf(columns[4]));
					geocity.setLon(Double.valueOf(columns[5]));

					geocity.setFeatureclass(columns[6]);
					geocity.setFeaturetype(columns[7]);
					geocity.setCountrycode(columns[8]);
					geocity.setCc2(columns[9]);
					geocity.setAdmin1code(columns[10]);
					geocity.setAdmin2code(columns[11]);
					geocity.setAdmin3code(columns[12]);
					geocity.setAdmin4code(columns[13]);
					geocity.setPopulation(Integer.valueOf(columns[14]));
					if (columns[15].trim().length() == 0)
						geocity.setElevation(0);
					else
						geocity.setElevation(Integer.valueOf(columns[15]));
					geocity.setDem(columns[16]);

					myGeocityService.save(geocity);
					nbCities++;
				}
			}
			log.info("Cities uploaded : " + nbCities);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
