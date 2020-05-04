package com.electronic.config.security;

import com.electronic.base.SessionUser;
import com.electronic.dao.mapper.bo.SysUser;
import com.electronic.service.SysUserService;
import com.electronic.service.UserAndRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyUserDetailService implements UserDetailsService {
    private static  final Logger LOGGER = LoggerFactory.getLogger(MyUserDetailService.class);

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private UserAndRoleService userAndRoleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = new SysUser();
        user.setUserName(username);
        SysUser sysUser = null;
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
        try {
            sysUser = sysUserService.selectSysUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("获取用户失败 {} {}", e.getMessage());
        }
        if (sysUser == null) throw new UsernameNotFoundException("Username " + username + " not found");
//        return SessionUser.withUsername(sysUser.getUserName())
//                .password(sysUser.getPassword())
//                .authorities("ROLE_ADMIN")
//                .build();
        return new SessionUser(sysUser.getUserId(),sysUser.getUserPhone(),sysUser.getUserEmail(),sysUser.getSex(),sysUser.getDeptId(),sysUser.getUserName(),sysUser.getPassword(), getAuthority(sysUser));


    }

    public List<SimpleGrantedAuthority> getAuthority(SysUser sysUser) {
        //List<SysRole> SysRoles = userAndRoleService.queryRolesByUser(sysUser);
        List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
//        for (SysRole role : SysRoles) {
//            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
//        }
        list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        return list;
    }

}
