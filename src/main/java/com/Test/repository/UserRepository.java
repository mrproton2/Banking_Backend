package com.Test.repository;

import com.Test.dto.AppUser;
import com.Test.dto.UserDTO;

import java.util.Optional;

public interface UserRepository {

    void saveUser(UserDTO user);

    void accountDetails(int account_no,int branch_id,int user_id,String account_type);

    int findUserIDByEmail(String email);

    void markVerified(String email,String passoword);

    Optional<Boolean> isEmailExists(String email);

    Optional<AppUser> findByEmail(String email);



}
