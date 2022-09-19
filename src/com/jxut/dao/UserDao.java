package com.jxut.dao;
//�ײ�
import java.util.List;
import java.util.Map;

import com.jxut.po.Sex;
import com.jxut.po.User;
import com.jxut.util.PageUtil;

public interface UserDao {
	public User login(User user);
	public List<User> getAll(PageUtil pageUtil,Map map);
	public boolean updateStatus(String status, int id);
	public List<Sex> getAllsex();
	public boolean addUser(User user); //�洢�û�����
	public boolean delUserById(int id);
	public User getUserById(int id);
	public boolean updateUser(User user);
	public int getUserCount(Map map);
}
