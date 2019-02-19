package com.offerApi.offersApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CurrencyConverter {

	public Map<String, String> getCurrencyCodes(){
		String path = "http://apilayer.net/api/list?access_key=03112edb9ea4cc634986796c6224faa9";
		try {
			return parseResponse(path, "currencies");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Map<String, String> getCurrencyConversions(){
		String path = "http://apilayer.net/api/live?access_key=03112edb9ea4cc634986796c6224faa9";
		try {
			return parseResponse(path, "quotes");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Map<String, String> parseResponse(String path, String parsePath) throws IOException {
		Map<String, String> requiredNodeMap = new HashMap<>();
		URL url = new URL(path);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		String line;
		StringBuilder stringBuilder = new StringBuilder();
		while((line = br.readLine())!=null){
			stringBuilder.append(line);
		}
		String json = stringBuilder.toString();
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(json);
		JsonNode currencies = jsonNode.get(parsePath);
		requiredNodeMap = objectMapper.treeToValue(currencies, requiredNodeMap.getClass());
		return requiredNodeMap;
	}
}
