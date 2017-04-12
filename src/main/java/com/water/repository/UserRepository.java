package com.water.repository;

import com.water.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2017/4/11.
 */
public interface UserRepository extends JpaRepository<User,Integer> {
}
