package com.jxut.Control;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jxut.dao.UserDao;
import com.jxut.dao.impl.UserDaoImpl;
import com.jxut.po.Sex;
import com.jxut.po.User;
import com.jxut.util.PageUtil;
import com.jxut.util.StringUtil;

/**
 * Servlet implementation class UseServlet
 */
public class UseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//解决中文乱码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		if("login".equals(method)) {
			login(request,response);
		} else if("getAll".equals(method)) {
			getAll(request,response);
		} else if("updateStatus".equals(method)) {
			updateStatus(request,response);
		} else if("logout".equals(method)) {
			logout(request,response);
		} else if("preAdd".equals(method)) {
			preAdd(request,response);
		} else if("addUser".equals(method)) {
			addUser(request,response);
		} else if("delUser".equals(method)) {
			delUser(request,response);
		} else if("preUpdate".equals(method)) {
			preUpdate(request,response);			
		} else if("updateUser".equals(method)) {
			updateUser(request,response);			
		}
	}
	
	protected void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		UserDao dao = new UserDaoImpl();
		User user = new User();
		String realname = request.getParameter("realname");//获取表单数据
		String sex = request.getParameter("sex");//获取表单数据
		String birthday = request.getParameter("birthday");//获取表单数据
		String tel = request.getParameter("tel");//获取表单数据
		String address = request.getParameter("address");//获取表单数据
		int id = Integer.parseInt(request.getParameter("id"));
		//将这六个值射入到User对象中
		user.setRealname(realname);
		user.setSex(sex);
		user.setBirthday(birthday);
		user.setTel(tel);
		user.setAddress(address);
		user.setId(id);
		boolean result = dao.updateUser(user);
		if(result) {
			response.sendRedirect("use?method=getAll");
		}
	}
	
	protected void preUpdate(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		UserDao dao = new UserDaoImpl();
		User user = dao.getUserById(id);
		List<Sex> sexs = dao.getAllsex();
		request.setAttribute("sexs", sexs);
		request.setAttribute("user", user);
		request.getRequestDispatcher("userUpdate.jsp").forward(request, response);
	}

	protected void delUser(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		UserDao dao = new UserDaoImpl();
		boolean result = dao.delUserById(id);
		if(result) {
			response.sendRedirect("use?method=getAll");
		}
	}
	
	protected void addUser(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//创建磁盘工厂 指向硬盘
		DiskFileItemFactory df = new DiskFileItemFactory();
		//创建上传对象 把工厂加进去
		ServletFileUpload upload = new ServletFileUpload(df);
		User user = new User();
		try {
			//解析表单数据
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem its : items) { //表单遍历
				if(its.isFormField()) {//文本域
					String name = its.getFieldName();
					if("username".equals(name)) {
					      user.setUsername(its.getString("utf-8"));
					     }else if("password".equals(name)) {
					      user.setPassword(its.getString("utf-8"));
					     }else if("realname".equals(name)) {
					      user.setRealname(its.getString("utf-8"));
					     }else if("sex".equals(name)) {
					      user.setSex(its.getString("utf-8"));
					     }else if("birthday".equals(name)) {
					      user.setBirthday(its.getString("utf-8"));
					     }else if("tel".equals(name)) {
					      user.setTel(its.getString("utf-8"));
					     }else if("address".equals(name)) {
					      user.setAddress(its.getString("utf-8"));
					     }else if("type".equals(name)) {
					      user.setType(its.getString("utf-8"));
					     }
				} else {//文件域			//获取文件名
					//简历.doc 		1999998.doc
					String fileName = its.getName();				//获取到'.'的位置
					//获取文件后缀.doc
					String fileType = fileName.substring(fileName.lastIndexOf("."));
					//取当前时间距离 1970年的毫秒数
					long currentTime = System.currentTimeMillis();
					//指定文件 上传后 放置的位置	//贯穿线：函数过时有点low
					String path = request.getRealPath("/fileupload");
					//String path = "D:/kj01/fileupload";
					//如果目录不存在 那么自动创建
					File f = new File(path);
					if(!f.exists()) {
						f.mkdir();
					}
					//拼接文件名
					File file = new File(path, currentTime+fileType);
					try {//持久化到硬盘中
						its.write(file);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					user.setPhoto("fileupload/" + currentTime+fileType);
				}
				
			}
			UserDao dao = new UserDaoImpl();
			boolean result = dao.addUser(user);
			if(result) {
				response.sendRedirect("use?method=getAll");
			}
			//System.out.println(user.toString()); //查看表单是否拿到 user重写tostring
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void preAdd(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		UserDao dao = new UserDaoImpl();
		List<Sex> sexs = dao.getAllsex();
		request.setAttribute("sexs", sexs); //转发
		request.getRequestDispatcher("userAdd.jsp").forward(request, response);
	}
		
	protected void logout(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("login.jsp");
	}
	
	protected void updateStatus(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String status = request.getParameter("status");
		int id = Integer.parseInt(request.getParameter("id")); //string转换int 装箱拆箱
		UserDao dao = new UserDaoImpl();
		boolean result = dao.updateStatus(status, id);
		if(result) {
			response.sendRedirect("use?method=getAll"); //跳
		}
		System.out.println(status);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void login(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User u = new User();
		User user = null;
		u.setUsername(username);
		u.setPassword(password);
		UserDao dao = new UserDaoImpl();
		user = dao.login(u);
		if(user != null) {//存session 获取数据
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);
			response.sendRedirect("index.jsp");
		} else {
			response.sendRedirect("login.jsp");
		}
		System.out.println(username+"\t"+password);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */ 			//預留doget+dopost 為了接收嚮應用戶的請求和能力
	protected void getAll(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String username = request.getParameter("username");
		Map map=new HashMap();
		map.put("username",username);
		//从页面获取页码
		String pageNo = request.getParameter("pageNo");
		int currentPage = 1;//初始化为第1页
		if(!StringUtil.empty(pageNo)) {//如果不为空
			currentPage = Integer.parseInt(pageNo);
		}
		UserDao dao = new UserDaoImpl();
		int totalSize = dao.getUserCount(map);
		PageUtil pageu = new PageUtil();	//分页所有信息封装
		pageu.setPageSize(5);//设置每页显示条数
		pageu.setTotalSize(totalSize);//总条数
		pageu.setCurrentPage(currentPage);//设置当前页
		pageu.setTotalPage(totalSize);//计算总页数
		
		//數據庫所有的用戶記錄
		List<User> users = dao.getAll(pageu,map);//作为实参传入
		request.setAttribute("page", pageu); //传输页面
		request.setAttribute("users", users);
		request.setAttribute("username", username);
		request.getRequestDispatcher("userList.jsp").forward(request, response);
	}

}
