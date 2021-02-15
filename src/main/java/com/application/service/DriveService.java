package com.application.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

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

import com.application.constants.ErrorMessages;
import com.application.constants.GoogleApiConstants;
import com.application.constants.MessageConstants;
import com.application.domain.FilesListFromDrive;
import com.application.domain.ListOfFilesList;
import com.application.domain.ResponseObject;
import com.application.domain.StudentFilesDomain;
import com.application.domain.StudentFilesObject;
import com.application.domain.Users;
import com.application.jwt.AuthUser;
import com.application.repository.StudentFilesRepository;
import com.application.repository.UserDetailsRepository;
import com.application.roles.RolesEnum;
import com.application.utils.CommonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.nimbusds.jwt.SignedJWT;

@Service
public class DriveService implements IDriveService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DriveService.class);

	@Value("${Client_ID}")
	private String clientId;

	@Value("${Client_secret}")
	private String clientSecret;

	@Value("${redirect_uri}")
	private String redirectUri;

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Autowired
	private StudentFilesRepository studentFilesRepository;

	public static String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

	public static String getUserExternalId(String authToken) {

		// String token = resolveToken(authToken);
		String token = authToken;
		if (token == null) {
			return MessageConstants.UNAUTHORIZED;
		}
		SignedJWT signedJWT;
		ObjectMapper mapper = new ObjectMapper();
		try {
			signedJWT = SignedJWT.parse(token);
			String object = signedJWT.getPayload().toJSONObject().toJSONString();
			AuthUser user = mapper.readValue(object, AuthUser.class);
			if (System.currentTimeMillis() > user.getSub()) {
				return MessageConstants.UNAUTHORIZED;
			} else {
				return user.getJti();
			}
		} catch (ParseException | JsonProcessingException jpe) {
			LOGGER.info(jpe.getMessage() + " at " + Calendar.getInstance().getTime());
			LOGGER.error(jpe.getMessage(), jpe);

		} catch (Exception e) {
			LOGGER.info(e.getMessage() + " at " + Calendar.getInstance().getTime());
			LOGGER.error(e.getMessage(), e);
		}

		return MessageConstants.UNAUTHORIZED;
	}

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
//				String refreshToken = obj.get("refresh_token").toString();
//				String expiryTime = obj.get("expires_in").toString();
				return accessToken;
			} else {
				System.out.println("Error response from access request: " + responseData);
				return "Error response from access request: " + responseData;
			}

		} catch (UnsupportedEncodingException e) {
			LOGGER.info(e.getMessage() + " at " + Calendar.getInstance().getTime());
			return e.getMessage();
		} catch (ClientProtocolException e) {
			LOGGER.info(e.getMessage() + " at " + Calendar.getInstance().getTime());
			return e.getMessage();
		} catch (IOException e) {
			LOGGER.info(e.getMessage() + " at " + Calendar.getInstance().getTime());
			return e.getMessage();
		} catch (Exception e) {
			LOGGER.info(e.getMessage() + " at " + Calendar.getInstance().getTime());
			return e.getMessage();
		}

	}

	public String googleAnalyticsNewAccessToken(String refreshToken) {
		try {

			LOGGER.info("authorization code:" + refreshToken);
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost post = new HttpPost("https://oauth2.googleapis.com/token");
			// Google uses form-encoded parameters.
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			formparams.add(new BasicNameValuePair("client_id", clientId));
			formparams.add(new BasicNameValuePair("client_secret", clientSecret));
			formparams.add(new BasicNameValuePair("grant_type", "refresh_token"));
			formparams.add(new BasicNameValuePair("refresh_token", refreshToken));
			post.setEntity(new UrlEncodedFormEntity(formparams));

			CloseableHttpResponse response = httpclient.execute(post);
			int status = response.getStatusLine().getStatusCode();
			HttpEntity body = response.getEntity();
			String responseData = EntityUtils.toString(body);
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(responseData);
			String newAccessToken = obj.get("access_token").toString();

			if (status == 200) {
				return newAccessToken;
			} else {
				System.out.println("Error response from access request: " + responseData);
				return "Error response from access request: " + responseData;
			}

		} catch (Exception e) {
			LOGGER.info(e.getMessage() + " at " + Calendar.getInstance().getTime());
			e.printStackTrace();
			return e.getMessage();
		}

	}

	@Override
	public ResponseObject getDriveFilesList(String accessToken, String fileType, String authToken) {

//		String accessToken = getAccessTokenRefreshToken(code);

		try {

			String authToken2 = authToken.substring(7);
			String externalId = getUserExternalId(authToken2);
			if (CommonUtils.isNotNull(externalId)) {
				if (externalId.equalsIgnoreCase(MessageConstants.UNAUTHORIZED)) {
					return new ResponseObject(null, null, HttpStatus.UNAUTHORIZED);
				}
			}
			Users user = userDetailsRepository.getUserByExternalId(externalId);
			if (user == null) {
				return new ResponseObject(null, ErrorMessages.USER_NOT_REGISTERED, HttpStatus.BAD_REQUEST);
			} else {
				if (user.getType().equalsIgnoreCase(RolesEnum.STUDENT.toString())) {
					return new ResponseObject(null, "You are not authorized to access this resource",
							HttpStatus.UNAUTHORIZED);
				}
			}
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
			} else if (fileType.equalsIgnoreCase("files")) {
				filterByType = "";
			}
			HttpResponse<String> response = Unirest
					.get("https://www.googleapis.com/drive/v3/files?q=mimeType='" + filterByType + "'")
					.header("Authorization", "Bearer " + accessToken).asString();

			String responseObject = response.getBody().toString();
			LOGGER.info("Object :" + responseObject);
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(responseObject);

			JSONArray filesArray = (JSONArray) obj.get("files");

			ListOfFilesList listOfFilesList;

			List<FilesListFromDrive> listOfFiles1 = new ArrayList<>();
			List<FilesListFromDrive> listOfFiles2 = new ArrayList<>();
			List<FilesListFromDrive> listOfFiles3 = new ArrayList<>();
			List<FilesListFromDrive> listOfFiles4 = new ArrayList<>();
			List<FilesListFromDrive> listOfFiles5 = new ArrayList<>();
			List<FilesListFromDrive> listOfFiles6 = new ArrayList<>();
			List<FilesListFromDrive> listOfFiles7 = new ArrayList<>();
			List<FilesListFromDrive> listOfFiles8 = new ArrayList<>();
			List<FilesListFromDrive> listOfFiles9 = new ArrayList<>();
			List<FilesListFromDrive> listOfFiles10 = new ArrayList<>();
			FilesListFromDrive fileList;
			for (int i = 0; i < filesArray.size(); i++) {
				JSONObject fileObject = (JSONObject) filesArray.get(i);
				fileList = new FilesListFromDrive(fileObject.get("id").toString(), fileObject.get("name").toString(),
						fileObject.get("mimeType").toString(), null);
				if (listOfFiles1.size() < 10) {
					listOfFiles1.add(fileList);
				} else if (listOfFiles2.size() < 10 && listOfFiles1.size() == 10) {
					listOfFiles2.add(fileList);
				} else if (listOfFiles3.size() < 10 && listOfFiles1.size() == 10 && listOfFiles2.size() == 10) {
					listOfFiles3.add(fileList);
				} else if (listOfFiles4.size() < 10 && listOfFiles3.size() == 10 && listOfFiles2.size() == 10
						&& listOfFiles1.size() == 10) {
					listOfFiles4.add(fileList);
				} else if (listOfFiles5.size() < 10 && listOfFiles4.size() == 10 && listOfFiles3.size() == 10
						&& listOfFiles2.size() == 10 && listOfFiles1.size() == 10) {
					listOfFiles5.add(fileList);
				} else if (listOfFiles6.size() < 10 && listOfFiles5.size() == 10 && listOfFiles4.size() == 10
						&& listOfFiles3.size() == 10 && listOfFiles2.size() == 10 && listOfFiles1.size() == 10) {
					listOfFiles6.add(fileList);
				} else if (listOfFiles7.size() < 10 && listOfFiles6.size() == 10 && listOfFiles5.size() == 10
						&& listOfFiles4.size() == 10 && listOfFiles3.size() == 10 && listOfFiles2.size() == 10
						&& listOfFiles1.size() == 10) {
					listOfFiles7.add(fileList);
				} else if (listOfFiles8.size() < 10 && listOfFiles7.size() == 10 && listOfFiles6.size() == 10
						&& listOfFiles5.size() == 10 && listOfFiles4.size() == 10 && listOfFiles3.size() == 10
						&& listOfFiles2.size() == 10 && listOfFiles1.size() == 10) {
					listOfFiles8.add(fileList);
				} else if (listOfFiles9.size() < 10 && listOfFiles8.size() == 10 && listOfFiles7.size() == 10
						&& listOfFiles6.size() == 10 && listOfFiles5.size() == 10 && listOfFiles4.size() == 10
						&& listOfFiles3.size() == 10 && listOfFiles2.size() == 10 && listOfFiles1.size() == 10) {
					listOfFiles9.add(fileList);
				} else if (listOfFiles10.size() < 10 && listOfFiles9.size() == 10 && listOfFiles8.size() == 10
						&& listOfFiles7.size() == 10 && listOfFiles6.size() == 10 && listOfFiles5.size() == 10
						&& listOfFiles4.size() == 10 && listOfFiles3.size() == 10 && listOfFiles2.size() == 10
						&& listOfFiles1.size() == 10) {
					listOfFiles10.add(fileList);
				}
			}

			listOfFilesList = new ListOfFilesList(listOfFiles1, listOfFiles2, listOfFiles3, listOfFiles4, listOfFiles5,
					listOfFiles6, listOfFiles7, listOfFiles8, listOfFiles9, listOfFiles10);

			LOGGER.info("Files List :" + listOfFiles1 + " /n" + listOfFiles2 + " /n" + listOfFiles3);
			LOGGER.info("List size :" + listOfFiles1.size() + " " + listOfFiles2.size() + " " + listOfFiles3.size());
			return new ResponseObject(listOfFilesList, null, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			return new ResponseObject(null, "Error", HttpStatus.BAD_REQUEST);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResponseObject shareFileWithPermissions(String accessToken, String fileId, String authToken) {

//		String accessToken = getAccessTokenRefreshToken(code);

		try {
			String authToken2 = authToken.substring(7);
			String externalId = getUserExternalId(authToken2);
			if (CommonUtils.isNotNull(externalId)) {
				if (externalId.equalsIgnoreCase(MessageConstants.UNAUTHORIZED)) {
					return new ResponseObject(null, null, HttpStatus.UNAUTHORIZED);
				}
			}
			Users user = userDetailsRepository.getUserByExternalId(externalId);
			if (user == null) {
				return new ResponseObject(null, ErrorMessages.USER_NOT_REGISTERED, HttpStatus.BAD_REQUEST);
			} else {
				if (user.getType().equalsIgnoreCase(RolesEnum.STUDENT.toString())) {
					return new ResponseObject(null, "You are not authorized to access this resource",
							HttpStatus.UNAUTHORIZED);
				}
			}

			HttpResponse<String> responseFileObject = Unirest.get("https://www.googleapis.com/drive/v3/files/" + fileId)
					.header("Authorization", "Bearer " + accessToken).header("Content-Type", "application/json")
					.asString();

			String fileObject = responseFileObject.getBody().toString();
			JSONParser parser2 = new JSONParser();
			JSONObject obj2 = (JSONObject) parser2.parse(fileObject);
			String mimeType = (String) obj2.get("mimeType");
//			String fileName = (String) obj2.get("name");
			String fileUrl = "";
			if (mimeType.equalsIgnoreCase(GoogleApiConstants.DOCS)) {
				fileUrl = "https://docs.google.com/document/d/" + fileId + "/";
			} else if (mimeType.equalsIgnoreCase(GoogleApiConstants.SHEETS)) {
				fileUrl = "https://docs.google.com/spreadsheets/d/" + fileId + "/";
			} else if (mimeType.equalsIgnoreCase(GoogleApiConstants.SLIDES)) {
				fileUrl = "https://docs.google.com/presentation/d/" + fileId + "/";
			}

			StudentFilesDomain studentFilesObject = new StudentFilesDomain(UUID.randomUUID().toString(), fileId,
					fileUrl, mimeType, user.getEmail(), user.getStream(), Calendar.getInstance(),
					Calendar.getInstance());
			studentFilesRepository.saveAndFlush(studentFilesObject);

			List<Users> usersList = userDetailsRepository.getAllUsersByType(RolesEnum.STUDENT.toString());
			for (Users users : usersList) {
				JSONObject object = new JSONObject();
				object.put("role", "reader");
				object.put("type", "user");
				object.put("emailAddress", users.getEmail());
				ObjectMapper mapper = new ObjectMapper();

				HttpResponse<String> response = Unirest
						.post("https://www.googleapis.com/drive/v3/files/" + fileId + "/permissions")
						.header("Authorization", "Bearer " + accessToken).header("Content-Type", "application/json")
						.body(mapper.writeValueAsString(object)).asString();

				String responseObject = response.getBody().toString();
				LOGGER.info("Object :" + responseObject);
			}

			return new ResponseObject("Shared files successfully!", null, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseObject(e.getMessage(), "Error", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseObject getListOFStudentFiles(String authToken) {

		try {
			String authToken2 = authToken.substring(7);
			String externalId = getUserExternalId(authToken2);
			if (CommonUtils.isNotNull(externalId)) {
				if (externalId.equalsIgnoreCase(MessageConstants.UNAUTHORIZED)) {
					return new ResponseObject(null, null, HttpStatus.UNAUTHORIZED);
				}
			}
			Users user = userDetailsRepository.getUserByExternalId(externalId);
			if (user == null) {
				return new ResponseObject(null, ErrorMessages.USER_NOT_REGISTERED, HttpStatus.BAD_REQUEST);
			}
			List<StudentFilesObject> filesObject = studentFilesRepository.getFiles();
			return new ResponseObject(filesObject, null, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseObject(null, ErrorMessages.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseObject getListOfFilesSharedMe(String authToken) {
		try {
			String authToken2 = authToken.substring(7);
			String externalId = getUserExternalId(authToken2);
			if (CommonUtils.isNotNull(externalId)) {
				if (externalId.equalsIgnoreCase(MessageConstants.UNAUTHORIZED)) {
					return new ResponseObject(null, null, HttpStatus.UNAUTHORIZED);
				}
			}
			Users user = userDetailsRepository.getUserByExternalId(externalId);
			if (user == null) {
				return new ResponseObject(null, ErrorMessages.USER_NOT_REGISTERED, HttpStatus.BAD_REQUEST);
			} else {
				if (user.getType().equalsIgnoreCase(RolesEnum.STUDENT.toString())) {
					return new ResponseObject(null, "You are not authorized to access this resource",
							HttpStatus.UNAUTHORIZED);
				}
			}
			List<StudentFilesObject> filesObject = studentFilesRepository.getFilesByEmail(user.getEmail());
			return new ResponseObject(filesObject, null, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseObject(null, ErrorMessages.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseObject getChildFilesInFolder(String authToken, String folderId, String accessToken) {

		try {

			String authToken2 = authToken.substring(7);
			String externalId = getUserExternalId(authToken2);
			if (CommonUtils.isNotNull(externalId)) {
				if (externalId.equalsIgnoreCase(MessageConstants.UNAUTHORIZED)) {
					return new ResponseObject(null, null, HttpStatus.UNAUTHORIZED);
				}
			}
			Users user = userDetailsRepository.getUserByExternalId(externalId);
			if (user == null) {
				return new ResponseObject(null, ErrorMessages.USER_NOT_REGISTERED, HttpStatus.BAD_REQUEST);
			} else {
				if (user.getType().equalsIgnoreCase(RolesEnum.STUDENT.toString())) {
					return new ResponseObject(null, "You are not authorized to access this resource",
							HttpStatus.UNAUTHORIZED);
				}
			}
			HttpResponse<String> response = Unirest
					.get("https://www.googleapis.com/drive/v2/files/" + folderId + "/children")
					.header("Authorization", "Bearer " + accessToken).header("Content-Type", "application/json")
					.asString();

			String responseObject = response.getBody().toString();
			LOGGER.info("Object :" + responseObject);
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(responseObject);

			return new ResponseObject(obj, null, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseObject(null, ErrorMessages.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
		}
	}

}
