package cs.dit.controller;

import java.io.IOException;
import java.util.List;  

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs.dit.dao.LoginDAO;
import cs.dit.dto.LoginDTO;
import cs.dit.service.LoginService;
import cs.dit.service.LoginServiceImpl;

@WebServlet("*.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
//		LoginDAO dao = new LoginDAO();
		LoginDTO dto = new LoginDTO();
		LoginService service = new LoginServiceImpl();
		
		String viewPage =null;
		
		String uri = request.getRequestURI(); 	//uri :/member-mvc/insert.do
		String com= uri.substring(uri.lastIndexOf("/")+ 1, uri.lastIndexOf(".do")); //command :insert
		
		if(com !=null && com.trim().equals("list")) {
			List<LoginDTO> dtos = service.getList();
			request.setAttribute("dtos", dtos);
			viewPage = "/WEB-INF/view/mList.jsp";
			
		}else if(com !=null && com.trim().equals("insertForm")) {
			viewPage = "/WEB-INF/view/mInsertForm.jsp";
			
		}else if(com !=null && com.trim().equals("insert")) {
			dto.setId(request.getParameter("id"));		//DTO에 폼에서 전달된 데이터를 저장
			dto.setPwd(request.getParameter("pwd"));
			dto.setName(request.getParameter("name"));
			
			service.register(dto);
			viewPage ="list.do";
			
		}else if(com !=null && com.trim().equals("view")) {
			String id = request.getParameter("id");

			dto = service.getOne(id);
			request.setAttribute("dto", dto);
			viewPage = "/WEB-INF/view/mView.jsp";
			
		}else if(com !=null && com.trim().equals("update")){
			dto.setId(request.getParameter("id"));
			dto.setPwd(request.getParameter("pwd"));
			dto.setName(request.getParameter("name"));

			service.modify(dto);
			viewPage = "list.do";
			
		}else if(com !=null && com.trim().equals("delete")){
			String id = request.getParameter("id");

			service.remove(id);
			viewPage = "list.do";
			
		}else if(com !=null && com.trim().equals("index")) {
			viewPage = "/WEB-INF/view/index.html";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		rd.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
}



