package com.application.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.application.domain.RegisterUserWithExcel;
import com.application.domain.Users;
import com.application.repository.UserDetailsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AsyncUserRegisterService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AsyncUserRegisterService.class);

	@Autowired
	private UserDetailsRepository userDetailsRepository;

//	@Async
	public void registerUsersAsync(List list) {

		LOGGER.info("Async :" + list);
		if (list.isEmpty()) {
			// send email to User
		}
		try {

			List excelList = list;
			List<Users> dbList = userDetailsRepository.getAllUsers();
			List finalList = new ArrayList<RegisterUserWithExcel>();

			LOGGER.info("excel list : " + excelList);
			for (int i = 0; i < excelList.size(); i++) {
				JSONObject excelListObject = new JSONObject(excelList.get(i));
//				String excelListData = excelList.get(i).toString();
//				RegisterUserWithExcel registerObject = (RegisterUserWithExcel) excelList.get(i);
//				ObjectMapper mapper = new ObjectMapper();
//				RegisterUserWithExcel registerObject = mapper.readValue(excelListData.toString(), RegisterUserWithExcel.class);
				LOGGER.info("Register object : " + excelListObject);
				for (int j = 0; j < dbList.size(); j++) {

					Users dbListObject = dbList.get(j);
					if (excelListObject.getString("email").equalsIgnoreCase(dbListObject.getEmail())) {
						finalList.add(excelListObject);
						LOGGER.info("object to be added :" + excelListObject);
						LOGGER.info("added :" + dbListObject.getEmail());
					}
				}
			}

//			ObjectMapper mapper = new ObjectMapper();
//			RegisterUserWithExcel registerObject = mapper.readValue(finalList.toString(), RegisterUserWithExcel.class);
			List newList = new ArrayList<RegisterUserWithExcel>();
			for (int i = 0; i < excelList.size(); i++) {
				JSONObject excelListObject = new JSONObject(excelList.get(i));
				newList.add(excelListObject);
			}
			LOGGER.info("final List to register :" + finalList);
			LOGGER.info("This is it : " + finalList.size());

			System.out.println(finalList);
			
			System.out.println(newList);
			LOGGER.info(""+newList.get(1));
			LOGGER.info(""+newList.remove(finalList.get(0)));
			LOGGER.info("difference list :" + newList);
		} catch (Exception e) {
			LOGGER.info("Error occured :" + e.getMessage());
		}
	}
}
