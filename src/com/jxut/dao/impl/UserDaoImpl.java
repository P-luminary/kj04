package com.jxut.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jxut.dao.UserDao;
import com.jxut.po.Sex;
import com.jxut.po.User;
import com.jxut.util.DbUtil;
import com.jxut.util.PageUtil;
//ʵ���� ����ʵ�ֽӿڵ����з���
public class UserDaoImpl implements UserDao {

	@Override
	public User login(User user) {
		//�����ӿ�һ�����ټ�һ������ֵ
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		User u = null;
		try {
			conn = DbUtil.getConnection();
			sql.append("SELECT * FROM t_user WHERE username = ? and password = ? ");
			sql.append("and if_valid=1 "); //����ӿո� ��ֹ��bug
			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1, user.getUsername()); //����ֵ
			pstm.setString(2, user.getPassword());
			rs = pstm.executeQuery() ;
			while(rs.next()) {
				u = new User(); //�ֲ�����
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setRealname(rs.getString("realname"));
				u.setPassword(rs.getString("password"));
				u.setSex(rs.getString("sex"));
				u.setBirthday(rs.getString("birthday"));
				u.setTel(rs.getString("tel"));
				u.setAddress(rs.getString("address"));
				u.setType(rs.getString("type"));
				u.setIf_valid(rs.getString("if_valid"));
				u.setPhoto(rs.getString("photo"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.closeAll(conn, pstm, rs);
		}
		return u;
	}
	
	@Override
	public List<User> getAll(PageUtil pageUtil,Map map){ //ʵ����
		//�����ӿ�һ�����ټ�һ������ֵ
		List<User> users = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		User u = null; //������new
		try {
			conn = DbUtil.getConnection();//���ӿո�Ȼ�ᱨsql����
			sql.append("SELECT * FROM t_user WHERE 1=1 "); //WHERE 1=1(true) ��������������Ͼͻ����,WHERE����д&&�쾭����
			if(map.get("username")!=null && !"".equals(map.get("username"))) {
				sql.append("and username like ? ");
			}
			sql.append("limit  "); //ÿҳ��ʾ��������������Ϊֹ
			sql.append((pageUtil.getCurrentPage()-1)*pageUtil.getPageSize());
			sql.append(",");
			sql.append(pageUtil.getPageSize());
			pstm = conn.prepareStatement(sql.toString());
			int i = 1;
			if(map.get("username")!=null && !"".equals(map.get("username"))){
				 pstm.setString(i++, "%"+map.get("username").toString()+"%" );
			}
			rs = pstm.executeQuery();//ֻ�в�ѯ��Query
			while(rs.next()) {
				u = new User();
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setRealname(rs.getString("realname"));
				u.setPassword(rs.getString("password"));
				u.setSex(rs.getString("sex"));
				u.setBirthday(rs.getString("birthday"));
				u.setTel(rs.getString("tel"));
				u.setAddress(rs.getString("address"));
				u.setType(rs.getString("type"));
				u.setIf_valid(rs.getString("if_valid"));
				u.setPhoto(rs.getString("photo"));
				users.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.closeAll(conn, pstm, rs);
		}
		return users;
	
	}

	@Override
	public boolean updateStatus(String status, int id) {
		boolean result = true;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			conn = DbUtil.getConnection();
			sql.append("UPDATE t_user SET if_valid = ? WHERE id = ?"); // WHERE username = ? and password = ?
			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1, status); //�������а�
			pstm.setInt(2, id);
			pstm.executeUpdate(); //ɾ��Ϊupdate
		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		} finally {
			DbUtil.closeAll(conn, pstm, rs);
		}
		return result;
	
	}
	
	@Override
	public List<Sex> getAllsex(){
		//�����ӿ�һ�����ټ�һ������ֵ
		List<Sex> sexs = new ArrayList<Sex>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		Sex sex = null; //������new
		try {
			conn = DbUtil.getConnection();
			sql.append("SELECT * FROM t_sex"); // WHERE username = ? and password = ?
			pstm = conn.prepareStatement(sql.toString());
			rs = pstm.executeQuery();//ֻ�в�ѯ��Query
			while(rs.next()) {
				sex = new Sex();
				sex.setId(rs.getInt("id"));
				sex.setName(rs.getString("name"));
				sexs.add(sex);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.closeAll(conn, pstm, rs);
		}
		return sexs;
	
	
	}

	@Override
	public boolean addUser(User user) {
		boolean result = true;
		//�����ӿ�һ�����ټ�һ������ֵ
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			conn = DbUtil.getConnection();
			sql.append("INSERT INTO t_user(username,realname,password,sex,birthday, ");
			sql.append("tel,address,type,photo)values "); //����ӿո� ��ֹ��bug
			sql.append("(?,?,?,?,?,?,?,?,?) "); //����ӿո� ��ֹ��bug
			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1, user.getUsername()); //����ֵ
			pstm.setString(2, user.getRealname());
			pstm.setString(3, user.getPassword());
			pstm.setString(4, user.getSex());
			pstm.setString(5, user.getBirthday());
			pstm.setString(6, user.getTel());
			pstm.setString(7, user.getAddress());
			pstm.setString(8, user.getType());
			pstm.setString(9, user.getPhoto());
			pstm.executeUpdate() ;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.closeAll(conn, pstm, rs);
		}
		return result;
	
	}
 
	@Override
	public boolean delUserById(int id) {
		boolean result = true;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			conn = DbUtil.getConnection();
			sql.append("DELETE FROM t_user WHERE id=?"); // WHERE username = ? and password = ?
			pstm = conn.prepareStatement(sql.toString());
			pstm.setInt(1, id);
			pstm.executeUpdate(); //ɾ��Ϊupdate
		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		} finally {
			DbUtil.closeAll(conn, pstm, rs);
		}
		return result;
	}

	@Override
	public User getUserById(int id) {

		//�����ӿ�һ�����ټ�һ������ֵ
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		User u = null;
		try {
			conn = DbUtil.getConnection();
			sql.append("SELECT * FROM t_user WHERE id = ? ");
			pstm = conn.prepareStatement(sql.toString());
			pstm.setInt(1, id);
			rs = pstm.executeQuery() ;
			while(rs.next()) {
				u = new User(); //�ֲ�����
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setRealname(rs.getString("realname"));
				u.setPassword(rs.getString("password"));
				u.setSex(rs.getString("sex"));
				u.setBirthday(rs.getString("birthday"));
				u.setTel(rs.getString("tel"));
				u.setAddress(rs.getString("address"));
				u.setType(rs.getString("type"));
				u.setIf_valid(rs.getString("if_valid"));
				u.setPhoto(rs.getString("photo"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.closeAll(conn, pstm, rs);
		}
		return u;
	
	}

	@Override
	public boolean updateUser(User user) {

		boolean result = true;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			conn = DbUtil.getConnection();
			sql.append("UPDATE t_user SET realname = ?,sex=?,birthday=?,tel=?,address=? WHERE id= ?"); // WHERE username = ? and password = ?
			pstm = conn.prepareStatement(sql.toString()); //����ֵ
			pstm.setString(1, user.getRealname());
			pstm.setString(2, user.getSex());
			pstm.setString(3, user.getBirthday());
			pstm.setString(4, user.getTel());
			pstm.setString(5, user.getAddress());
			pstm.setInt(6, user.getId());
			pstm.executeUpdate(); //ɾ��Ϊupdate
		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		} finally {
			DbUtil.closeAll(conn, pstm, rs);
		}
		return result;
	
	
	}

	@Override
	public int getUserCount(Map map) {
		//�����ӿ�һ�����ټ�һ������ֵ
		int count = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			conn = DbUtil.getConnection();
			sql.append("SELECT COUNT(*) FROM t_user WHERE 1=1 "); // WHERE username = ? and password = ?
			if(map.get("username")!=null && !"".equals(map.get("username"))) {
				sql.append("and username like ? ");
			}
			pstm = conn.prepareStatement(sql.toString());
			int i = 1;
			if(map.get("username")!=null && !"".equals(map.get("username"))) {
				pstm.setString(i++, "%"+map.get("username").toString()+"%");
			}
			rs = pstm.executeQuery();//ֻ�в�ѯ��Query
			while(rs.next()) {
				count = rs.getInt(1); //1��
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.closeAll(conn, pstm, rs);
		}
		return count;
	
	
	}
}


