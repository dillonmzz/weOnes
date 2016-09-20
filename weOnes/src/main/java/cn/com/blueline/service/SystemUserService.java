package cn.com.blueline.service;

import java.util.List;

import cn.com.blueline.entity.SystemUser;

public interface SystemUserService {

	/**
	 * 添加
	 * @param user 后台系统用户
	 * @return 
	 */
	int save(SystemUser	user);
	
	/**
	 * 根据用户名查询用户信息
	 * @param userName 用户名
	 * @return 用户信息
	 */
	SystemUser queryByUserName(String userName);
	
	/**
	 * 根据用户编号修改用户信息
	 * @param userId 用户编号
	 * @return 
	 */
	int updateById(SystemUser SystemUser);

	/**
	 * 商家认证
	 * @param userId 商家编号
	 * @param certificateImg 认证的图片
	 * @return
	 */
	int certificate(Long userId, String certificateImg);
	
	/**
	 * 管理员对商家的认证进行审批,通过后将是否认证改为Y
	 * @param userId 商家编号
	 * @return
	 */
	int changeIsAuthenticatioin(Long userId);
	

	/**
	 * 管理员查看已提交认证单未审批的用户列表
	 * @return
	 */
	List<SystemUser> queryCertificateUsers();
	
	
}
