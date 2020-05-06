package com.electronic.service.impl;

import com.electronic.base.BaseResponse;
import com.electronic.base.PageResult;
import com.electronic.base.VO.UserRequest;
import com.electronic.contants.BusinessConstants;
import com.electronic.contants.UserConstants;
import com.electronic.dao.mapper.bo.*;
import com.electronic.dao.mapper.interfaces.SysDeptMapper;
import com.electronic.dao.mapper.interfaces.SysRoleUserMapper;
import com.electronic.dao.mapper.interfaces.SysUserMapper;
import com.electronic.service.SysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public SysUser selectSysUser(SysUser user) throws Exception {
        SysUserExample sysUserExample = new SysUserExample();
        SysUserExample.Criteria criteria = sysUserExample.createCriteria();
        if (!StringUtils.isBlank(user.getUserName())){
            criteria.andUserNameEqualTo(user.getUserName());
        }
        criteria.andStatusEqualTo(UserConstants.VALID_STATUS);
        if (!StringUtils.isBlank(user.getStaffName())){
            SysUserExample.Criteria criteria1 = sysUserExample.createCriteria();
            criteria1.andStaffNameEqualTo(user.getStaffName());
            sysUserExample.or(criteria1);
        }
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        return CollectionUtils.isEmpty(sysUsers)? null:sysUsers.get(0);
    }

    @Override
    public Integer addSysUser(SysUser user) throws Exception {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(UserConstants.VALID_STATUS);
        user.setOperateTime(new Date());
        int insertSelective = sysUserMapper.insertSelective(user);
        SysRoleUser sysRoleUser = new SysRoleUser();
        sysRoleUser.setUserId(user.getUserId());
        sysRoleUser.setRoleId(Integer.parseInt(user.getLevel()));
        sysRoleUserMapper.insertSelective(sysRoleUser);
        return insertSelective;
    }

    @Override
    public Integer updateSysUser(SysUser user) throws Exception {
        String password = user.getPassword();
        if (!StringUtils.isBlank(password)){
            password = passwordEncoder.encode(password);
            user.setPassword(password);
        }else{
            user.setPassword(null);
        }
        user.setOperateTime(new Date());
        int updateByPrimaryKeySelective = sysUserMapper.updateByPrimaryKeySelective(user);
        return updateByPrimaryKeySelective;
    }

    @Override
    public BaseResponse<PageResult<UserRequest>> queryuser(UserRequest userRequest) throws Exception {
        BaseResponse baseResponse = new BaseResponse(BusinessConstants.BUSI_SUCCESS,BusinessConstants.BUSI_SUCCESS_CODE,BusinessConstants.BUSI_SUCCESS_MESSAGE);
        PageResult<UserRequest> pageResult = new PageResult<>();
        SysUserExample sysUserExample = new SysUserExample();
        SysUserExample.Criteria criteria = sysUserExample.createCriteria();
       // criteria.andStatusEqualTo(UserConstants.VALID_STATUS);
        if (!StringUtils.isEmpty(userRequest.getUserName())){
            criteria.andUserNameLike("%"+userRequest.getUserName()+"%");
        }
        sysUserExample.setOrderByClause("operate_time desc");
        PageHelper.startPage(userRequest.getPageNum(),userRequest.getPageSize());
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        PageInfo pageInfo = new PageInfo(sysUsers);
        List<UserRequest> list = new ArrayList<>();

        for (SysUser sysUser:sysUsers){
            UserRequest request = new UserRequest();
            SysDeptExample sysDeptExample = new SysDeptExample();
            SysDeptExample.Criteria criteria1 = sysDeptExample.createCriteria();
            criteria1.andStatusEqualTo(UserConstants.S_VALID_STATUS);
            criteria1.andDeptIdEqualTo(sysUser.getDeptId());
            List<SysDept> sysDept = sysDeptMapper.selectByExample(sysDeptExample);
            sysUser.setPassword("");
            BeanUtils.copyProperties(sysUser,request);
            if (CollectionUtils.isEmpty(sysDept)){
                request.setDeptId(0);
            }else {
                request.setSysDept(sysDept.get(0));
            }
            list.add(request);
        }

        pageResult.setResult(list);
        pageResult.setPageCount(pageInfo.getPages());
        pageResult.setCount(pageInfo.getTotal());
        baseResponse.setResult(pageResult);
        return baseResponse;
    }

    @Override
    public BaseResponse restPassword(Integer userId) throws Exception {
        BaseResponse baseResponse = new BaseResponse(BusinessConstants.BUSI_SUCCESS,BusinessConstants.BUSI_SUCCESS_CODE,BusinessConstants.BUSI_SUCCESS_MESSAGE);
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        if (sysUser == null){
            baseResponse.setSuccess(false);
            baseResponse.setResult(BusinessConstants.BUSI_FAILURE_CODE);
            baseResponse.setResultMessage("操作员工信息不存在");
            return baseResponse;
        }
        String encode = passwordEncoder.encode("123456");
        sysUser.setPassword(encode);
        int i = sysUserMapper.updateByPrimaryKeySelective(sysUser);
        if (i > 0) {
            return  baseResponse;
        }else {
            baseResponse.setSuccess(false);
            baseResponse.setResult(BusinessConstants.BUSI_FAILURE_CODE);
            baseResponse.setResultMessage("重置失败");
            return baseResponse;
        }
    }
}
