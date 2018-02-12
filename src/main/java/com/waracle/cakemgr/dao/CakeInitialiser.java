package com.waracle.cakemgr.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.waracle.cakemgr.CakeEntity;
import com.waracle.cakemgr.HibernateUtil;

public class CakeInitialiser {

	private static final String JSON_URL = "https://gist.githubusercontent.com/hart88/198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json";

	public void setup() throws IOException {
		JsonParser parser = getJSONParser();
		persist(parser);
	}

	private JsonParser getJSONParser() throws IOException, MalformedURLException, JsonParseException {
        System.out.println("downloading cake json");
		InputStream inputStream = new URL(JSON_URL).openStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

		StringBuffer buffer = new StringBuffer();
		String line = reader.readLine();
		while (line != null) {
			buffer.append(line);
			line = reader.readLine();
		}

		System.out.println("parsing cake json");
		JsonParser parser = new JsonFactory().createParser(buffer.toString());
		if (JsonToken.START_ARRAY != parser.nextToken()) {
			throw new IOException("bad token");
		}
		return parser;
	}

	private void persist(JsonParser parser) throws IOException {
		JsonToken nextToken = parser.nextToken();
		while (nextToken == JsonToken.START_OBJECT) {
			System.out.println("creating cake entity");

			CakeEntity cakeEntity = new CakeEntity();
			System.out.println(parser.nextFieldName());
			cakeEntity.setTitle(parser.nextTextValue());

			System.out.println(parser.nextFieldName());
			cakeEntity.setDescription(parser.nextTextValue());

			System.out.println(parser.nextFieldName());
			cakeEntity.setImage(parser.nextTextValue());

			Session session = HibernateUtil.getSessionFactory().openSession();
			try {
				session.beginTransaction();
				session.persist(cakeEntity);
				System.out.println("adding cake entity");
				session.getTransaction().commit();
			} catch (ConstraintViolationException ex) {

			}
			session.close();

			nextToken = parser.nextToken();
			System.out.println(nextToken);

			nextToken = parser.nextToken();
			System.out.println(nextToken);
		}
	}

}
