package com.example.SimpleComplex.Repository;
import com.example.SimpleComplex.Records.UserInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserInfoRepository extends CrudRepository<UserInfo, Integer> {
    UserInfo findByEmailAndPassword(String email, String password);

    UserInfo findByUUID(int id);
}
