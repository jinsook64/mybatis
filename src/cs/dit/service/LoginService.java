package cs.dit.service;

import java.util.List;

import cs.dit.dto.LoginDTO;


public interface LoginService {  

		public List<LoginDTO> getList();
		
		public void register(LoginDTO dto);
		
		public LoginDTO getOne(String id);
		
		public void modify(LoginDTO dto);
		
		public void remove(String id);
}
