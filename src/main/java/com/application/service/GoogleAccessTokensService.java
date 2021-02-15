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
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.application.constants.ErrorMessages;
import com.application.constants.MessageConstants;
import com.application.domain.GetNewAccessTokenObject;
import com.application.domain.ResponseObject;
import com.application.domain.UserAccessTokens;
import com.application.domain.UserInfo;
import com.application.domain.Users;
import com.application.jwt.AuthUser;
import com.application.repository.GoogleAccessTokensRepository;
import com.application.repository.UserDetailsRepository;
import com.application.utils.CommonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jwt.SignedJWT;

@Service
public class GoogleAccessTokensService implements IGoogleAccessTokensService {

	@Value("${Client_ID}")
	private String clientId;

	@Value("${Client_secret}")
	private String clientSecret;

	@Value("${redirect_uri}")
	private String redirectUri;

	@Autowired
	private GoogleAccessTokensRepository googleAccessTokensRepository;

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(GoogleAccessTokensService.class);

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

	public ResponseObject googleNewAccessToken(String refreshToken) {
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
//			String refreshToken = obj.get("refresh_token").toString();
			String expiryTime = obj.get("expires_in").toString();
			LOGGER.info("" + responseData);

			if (status == 200) {
				GetNewAccessTokenObject tokens = new GetNewAccessTokenObject(newAccessToken, expiryTime);
				return new ResponseObject(tokens, null, HttpStatus.OK);
			} else {
				return new ResponseObject(null, "Invalid Refresh token!", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			LOGGER.info(e.getMessage() + " at " + Calendar.getInstance().getTime());
			e.printStackTrace();
			return new ResponseObject(null, "Invalid Refresh token!", HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	public ResponseObject getUser(String authToken) {

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
				UserAccessTokens accessTokensAccount = googleAccessTokensRepository.getUserById(user.getId());
				if (accessTokensAccount != null) {
					ResponseObject newAccessToken = googleNewAccessToken(accessTokensAccount.getRefreshToken());
					int status = newAccessToken.getStatus();
					if (status == 200) {
						GetNewAccessTokenObject newToken = (GetNewAccessTokenObject) newAccessToken.getResponse();
						UserInfo userInfo = new UserInfo(user.getEmail(), user.getFirstName(), user.getLastName(),
								user.getType(), user.getStream(), newToken.getAccessToken(), newToken.getExpiryTime());
						return new ResponseObject(userInfo, null, HttpStatus.OK);
					} else {
						UserInfo userInfo = new UserInfo(user.getEmail(), user.getFirstName(), user.getLastName(),
								user.getType(), user.getStream(), "Refresh token expired!", null);
						return new ResponseObject(userInfo, null, HttpStatus.OK);
					}
				} else {
					UserInfo userInfo = new UserInfo(user.getEmail(), user.getFirstName(), user.getLastName(),
							user.getType(), user.getStream(), "Please store refresh token!", null);
					return new ResponseObject(userInfo, null, HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			return new ResponseObject(null, ErrorMessages.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseObject storeTokens(String authToken, String code) {

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

			UserAccessTokens accessTokensAccount = googleAccessTokensRepository.getUserById(user.getId());

			LOGGER.info("" + accessTokensAccount);
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
//				String accessToken = obj.get("access_token").toString();
				String refreshToken = obj.get("refresh_token").toString();
//				String expiryTime = obj.get("expires_in").toString();

				if (accessTokensAccount == null) {
					UserAccessTokens userAccessTokens = new UserAccessTokens(UUID.randomUUID().toString(), refreshToken,
							null, Calendar.getInstance(), Calendar.getInstance(), user);
					googleAccessTokensRepository.saveAndFlush(userAccessTokens);

					return new ResponseObject(refreshToken, "Tokens stored successfully!", HttpStatus.OK);
				} else {
					googleAccessTokensRepository.updateToken(refreshToken, Calendar.getInstance(), user.getId());
					return new ResponseObject("Token updated successfully!", null, HttpStatus.OK);
				}

			} else {
				System.out.println("Error response from access request: " + responseData);
				return new ResponseObject(null, "Error response from access request: " + responseData,
						HttpStatus.BAD_REQUEST);
			}
		} catch (UnsupportedEncodingException e) {
			LOGGER.info(e.getMessage() + " at " + Calendar.getInstance().getTime());
			e.printStackTrace();
			return new ResponseObject(null, e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (ClientProtocolException e) {
			LOGGER.info(e.getMessage() + " at " + Calendar.getInstance().getTime());
			e.printStackTrace();
			return new ResponseObject(null, e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			LOGGER.info(e.getMessage() + " at " + Calendar.getInstance().getTime());
			e.printStackTrace();
			return new ResponseObject(null, e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			LOGGER.info(e.getMessage() + " at " + Calendar.getInstance().getTime());
			e.printStackTrace();
			return new ResponseObject(null, e.getMessage(), HttpStatus.BAD_REQUEST);

		}
	}

}
