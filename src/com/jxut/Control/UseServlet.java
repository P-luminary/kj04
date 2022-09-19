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
		//�����������
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
		String realname = request.getParameter("realname");//��ȡ������
		String sex = request.getParameter("sex");//��ȡ������
		String birthday = request.getParameter("birthday");//��ȡ������
		String tel = request.getParameter("tel");//��ȡ������
		String address = request.getParameter("address");//��ȡ������
		int id = Integer.parseInt(request.getParameter("id"));
		//��������ֵ���뵽User������
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
		//�������̹��� ָ��Ӳ��
		DiskFileItemFactory df = new DiskFileItemFactory();
		//�����ϴ����� �ѹ����ӽ�ȥ
		ServletFileUpload upload = new ServletFileUpload(df);
		User user = new User();
		try {
			//����������
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem its : items) { //������
				if(its.isFormField()) {//�ı���
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
				} else {//�ļ���			//��ȡ�ļ���
					//����.doc 		1999998.doc
					String fileName = its.getName();				//��ȡ��'.'��λ��
					//��ȡ�ļ���׺.doc
					String fileType = fileName.substring(fileName.lastIndexOf("."));
					//ȡ��ǰʱ����� 1970��ĺ�����
					long currentTime = System.currentTimeMillis();
					//ָ���ļ� �ϴ��� ���õ�λ��	//�ᴩ�ߣ�������ʱ�е�low
					String path = request.getRealPath("/fileupload");
					//String path = "D:/kj01/fileupload";
					//���Ŀ¼������ ��ô�Զ�����
					File f = new File(path);
					if(!f.exists()) {
						f.mkdir();
					}
					//ƴ���ļ���
					File file = new File(path, currentTime+fileType);
					try {//�־û���Ӳ����
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
			//System.out.println(user.toString()); //�鿴���Ƿ��õ� user��дtostring
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void preAdd(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		UserDao dao = new UserDaoImpl();
		List<Sex> sexs = dao.getAllsex();
		request.setAttribute("sexs", sexs); //ת��
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
		int id = Integer.parseInt(request.getParameter("id")); //stringת��int װ�����
		UserDao dao = new UserDaoImpl();
		boolean result = dao.updateStatus(status, id);
		if(result) {
			response.sendRedirect("use?method=getAll"); //��
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
		if(user != null) {//��session ��ȡ����
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
	 */ 			//�A��doget+dopost ���˽��Շ����Ñ���Ո�������
	protected void getAll(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String username = request.getParameter("username");
		Map map=new HashMap();
		map.put("username",username);
		//��ҳ���ȡҳ��
		String pageNo = request.getParameter("pageNo");
		int currentPage = 1;//��ʼ��Ϊ��1ҳ
		if(!StringUtil.empty(pageNo)) {//�����Ϊ��
			currentPage = Integer.parseInt(pageNo);
		}
		UserDao dao = new UserDaoImpl();
		int totalSize = dao.getUserCount(map);
		PageUtil pageu = new PageUtil();	//��ҳ������Ϣ��װ
		pageu.setPageSize(5);//����ÿҳ��ʾ����
		pageu.setTotalSize(totalSize);//������
		pageu.setCurrentPage(currentPage);//���õ�ǰҳ
		pageu.setTotalPage(totalSize);//������ҳ��
		
		//���������е��Ñ�ӛ�
		List<User> users = dao.getAll(pageu,map);//��Ϊʵ�δ���
		request.setAttribute("page", pageu); //����ҳ��
		request.setAttribute("users", users);
		request.setAttribute("username", username);
		request.getRequestDispatcher("userList.jsp").forward(request, response);
	}

}
