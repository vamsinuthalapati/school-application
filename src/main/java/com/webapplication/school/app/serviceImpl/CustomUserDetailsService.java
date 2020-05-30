package com.webapplication.school.app.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.webapplication.school.app.domain.CustomUserDetails;
import com.webapplication.school.app.domain.Users;
import com.webapplication.school.app.repository.UserRepository;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository usersRepository;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<Users> optionalUsers = usersRepository.findByName(username);
//
//        optionalUsers
//                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
//        return optionalUsers
//                .map(CustomUserDetails::new).get();
//    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//		Student student = studentRepository.findByUserId(username);
//		if (student == null)
//			throw new UsernameNotFoundException("User not found");
//
//		return new UserPrincipal(student);
		
		Users user = usersRepository.findUserById(username);
		if(user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new CustomUserDetails(user);
	}

}
