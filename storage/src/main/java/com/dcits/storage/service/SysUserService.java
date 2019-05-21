package com.dcits.storage.service;

import com.dcits.storage.model.SysUser;
import com.dcits.storage.repository.SysUserRepository;
import com.dcits.storage.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 用户事务层
 * @author xieds
 * @date 2019/4/10 9:29
 * @updater xieds
 * @updatedate 2019/4/10 9:29
 */
@Service
public class SysUserService {

    @Autowired
    private SysUserRepository sysUserRepository;

    /**
     * 根据用户名和密码查询用户
     * @param loginName 用户名
     * @param password 密码
     * @return ResultObject 返回结果集
     * @author xieds
     * @date 2019/4/10 9:29
     * @updater xieds
     * @updatedate 2019/4/10 9:29
     */
    public ResultObject login(String loginName, String password){
        Specification<SysUser> specification = new Specification<SysUser>() {
            @Override
            public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                predicate.add(cb.equal(root.get("username"), loginName));
                return cb.and(predicate.toArray(new Predicate[predicate.size()]));
            }
        };
        SysUser sysUser = sysUserRepository.findOne(specification).get();
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder(4);
        encode.encode(password);
        return ResultObject.createInstance(encode.matches(password, sysUser.getPassword()));
    }
}
