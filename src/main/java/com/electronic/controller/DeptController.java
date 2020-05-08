package com.electronic.controller;

import com.electronic.base.BaseResponse;
import com.electronic.base.PageResult;
import com.electronic.base.SessionUser;
import com.electronic.base.VO.DeptRequest;
import com.electronic.contants.BusinessConstants;
import com.electronic.dao.mapper.bo.SysDept;
import com.electronic.service.SysDeptService;
import com.electronic.utils.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dept")
public class DeptController {

    public static  final Logger LOGGER = LoggerFactory.getLogger(DeptController.class);


    @Autowired
    private SysDeptService sysDeptService;

    /*
   查询Dept
    */
    @RequestMapping("/queryDept")
    public BaseResponse queryDept(@RequestBody DeptRequest DeptRequest) throws Exception {
        BaseResponse<PageResult<SysDept>> baseResponse = sysDeptService.queryDept(DeptRequest);
        return baseResponse;
    }

    /*
    添加Dept
     */
    @RequestMapping("addDept")
    public BaseResponse addDept(@RequestBody DeptRequest DeptRequest) throws Exception {
        BaseResponse baseResponse = new BaseResponse(BusinessConstants.BUSI_FAILURE,BusinessConstants.BUSI_FAILURE_CODE,BusinessConstants.BUSI_FAILURE_MESSAGE);
        SysDept sysDept = new SysDept();
        String deptName = DeptRequest.getDeptName();
        sysDept.setDeptName(deptName);
        SysDept selectSysDept = sysDeptService.selectSysDept(sysDept);
        if (selectSysDept!=null){
            baseResponse.setResultMessage("部门已存在");
            return baseResponse;
        }
        BeanUtils.copyProperties(DeptRequest,sysDept);
        SessionUser sessionUser = SessionUtils.getSessionUser();
        sysDept.setOperator(String.valueOf(sessionUser.getUserId()));
        Integer integer = sysDeptService.addSysDept(sysDept);
        if (integer>0){
            baseResponse = new BaseResponse(BusinessConstants.BUSI_SUCCESS,BusinessConstants.BUSI_SUCCESS_CODE,BusinessConstants.BUSI_SUCCESS_MESSAGE);
        }
        return baseResponse;
    }



    /*
   更新Dept
    */
    @RequestMapping("updateDept")
    public BaseResponse updateDept(@RequestBody DeptRequest deptRequest) throws Exception {
        BaseResponse baseResponse = new BaseResponse(BusinessConstants.BUSI_FAILURE,BusinessConstants.BUSI_FAILURE_CODE,BusinessConstants.BUSI_FAILURE_MESSAGE);
        SysDept sysDept = new SysDept();
        if (!deptRequest.getOperType().equals("1")){
            String deptName = deptRequest.getDeptName();
            sysDept.setDeptName(deptName);
            SysDept selectSysDept = sysDeptService.selectSysDept(sysDept);
            if (null!=selectSysDept&&!selectSysDept.getDeptId().equals(deptRequest.getDeptId())){
                baseResponse.setResultMessage("部门已存在");
                return baseResponse;
            }
        }
        BeanUtils.copyProperties(deptRequest,sysDept);
        SessionUser sessionUser = SessionUtils.getSessionUser();
        sysDept.setOperator(String.valueOf(sessionUser.getUserId()));
        Integer integer = sysDeptService.updateSysDept(sysDept);
        if (integer>0){
            baseResponse = new BaseResponse(BusinessConstants.BUSI_SUCCESS,BusinessConstants.BUSI_SUCCESS_CODE,BusinessConstants.BUSI_SUCCESS_MESSAGE);
        }
        return baseResponse;
    }
}
