package cs.dit.service;

import java.util.List;

import cs.dit.dao.LoginDAO;
import cs.dit.dto.LoginDTO;
 
public class LoginServiceImpl implements LoginService {

	LoginDAO dao = new LoginDAO();
	
	@Override
	public List<LoginDTO> getList() {
		List<LoginDTO> list = dao.list();
		//여러 업무를 정리할 수 있는 공간 확보
		return list;
	}

	@Override
	public void register(LoginDTO dto) {
		dao.insert(dto);
		
	}

	@Override
	public LoginDTO getOne(String id) {
		
		return dao.view(id);
	}

	@Override
	public void modify(LoginDTO dto) {
		dao.update(dto);
		
	}

	@Override
	public void remove(String id) {
		dao.delete(id);
		
	}

}
