package com.application.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.application.constants.GoogleApiConstants;
import com.application.domain.ResponseObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

@Service
public class DriveService implements IDriveService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DriveService.class);

	private static Gson gson = new Gson();

	@Value("${Client_ID}")
	private String clientId;

	@Value("${Client_secret}")
	private String clientSecret;

	@Value("${redirect_uri}")
	private String redirectUri;

	public String getAccessTokenRefreshToken(String code) {

		try {

			// create a POST request containing all the parameters Google wants.
			// See Google's OAuth documentation. There are some optional params not shown
			// here.

			LOGGER.info("authorization code:" + code);
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost post = new HttpPost("https://oauth2.googleapis.com/token");

			// Google uses form-encoded parameters.

			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			formparams.add(new BasicNameValuePair("code", code));
			formparams.add(new BasicNameValuePair("client_id", clientId));
			formparams.add(new BasicNameValuePair("client_secret", clientSecret));
			formparams.add(new BasicNameValuePair("redirect_uri", redirectUri));
			formparams.add(new BasicNameValuePair("grant_type", "authorization_code"));
			post.setEntity(new UrlEncodedFormEntity(formparams));

			CloseableHttpResponse response = httpclient.execute(post);
			int status = response.getStatusLine().getStatusCode();
			HttpEntity body = response.getEntity();
			String responseData = EntityUtils.toString(body);

			LOGGER.info("Response code from access token request: " + status);
			if (status == 200) {
				LOGGER.info("responseData::" + responseData);
				JSONParser parser = new JSONParser();
				JSONObject obj = (JSONObject) parser.parse(responseData);
				String accessToken = obj.get("access_token").toString();
				String refreshToken = obj.get("refresh_token").toString();
				String expiryTime = obj.get("expires_in").toString();
				return accessToken;
			} else {
				System.out.println("Error response from access request: " + responseData);
				return "Error response from access request: " + responseData;
			}

		} catch (UnsupportedEncodingException e) {
			LOGGER.info(e.getMessage() + " at " + Calendar.getInstance().getTime());
			e.printStackTrace();
			return e.getMessage();
		} catch (ClientProtocolException e) {
			LOGGER.info(e.getMessage() + " at " + Calendar.getInstance().getTime());
			e.printStackTrace();
			return e.getMessage();
		} catch (IOException e) {
			LOGGER.info(e.getMessage() + " at " + Calendar.getInstance().getTime());
			e.printStackTrace();
			return e.getMessage();
		} catch (Exception e) {
			LOGGER.info(e.getMessage() + " at " + Calendar.getInstance().getTime());
			e.printStackTrace();
			return e.getMessage();
		}

	}

	@Override
	public ResponseObject getDriveFilesList(String code, String fileType) {

		String accessToken = getAccessTokenRefreshToken(code);

		try {

			String filterByType = "";

			if (fileType.equalsIgnoreCase("Sheets")) {
				filterByType = GoogleApiConstants.SHEETS;
			} else if (fileType.equalsIgnoreCase("Docs")) {
				filterByType = GoogleApiConstants.DOCS;
			} else if (fileType.equalsIgnoreCase("Slides")) {
				filterByType = GoogleApiConstants.SLIDES;
			} else if (fileType.equalsIgnoreCase("Forms")) {
				filterByType = GoogleApiConstants.FORMS;
			} else if (fileType.equalsIgnoreCase("Folder")) {
				filterByType = GoogleApiConstants.FOLDER;
			} else if (fileType.equalsIgnoreCase("MS_Sheets")) {
				filterByType = GoogleApiConstants.MS_SHEETS;
			}
			HttpResponse<String> response = Unirest
					.get("https://www.googleapis.com/drive/v3/files?q=mimeType='" + filterByType + "'")
					.header("Authorization", "Bearer " + accessToken).asString();

			String responseObject = response.getBody().toString();
			LOGGER.info("Object :" + responseObject);
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(responseObject);

			JSONArray filesArray = (JSONArray) obj.get("files");
			return new ResponseObject(filesArray, null, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseObject(null, "Error", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseObject shareFileWithPermissions(String code, String fileId) {

		String accessToken = getAccessTokenRefreshToken(code);

		try {

			JSONObject object = new JSONObject();
			object.put("role", "reader");
			object.put("type", "user");
			object.put("emailAddress", "nvjprasad@gmail.com");
			ObjectMapper mapper = new ObjectMapper();

			HttpResponse<String> response = Unirest
					.post("https://www.googleapis.com/drive/v3/files/" + fileId + "/permissions")
					.header("Authorization", "Bearer " + accessToken).header("Content-Type", "application/json")
					.body(mapper.writeValueAsString(object)).asString();

			String responseObject = response.getBody().toString();
			LOGGER.info("Object :" + responseObject);
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(responseObject);

			return new ResponseObject(obj, null, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseObject(e.getMessage(), "Error", HttpStatus.BAD_REQUEST);
		}
	}

}
