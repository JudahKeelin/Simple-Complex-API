package com.example.SimpleComplex.Repository;
import com.example.SimpleComplex.Records.UserInfo;
import org.springframework.data.repository.CrudRepository;

public interface UserInfoRepository extends CrudRepository<UserInfo, Integer> {
    UserInfo findByEmail(String email);
}
