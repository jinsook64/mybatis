package cs.dit.dao;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cs.dit.dto.LoginDTO;

public class LoginDAO {
	private static SqlSessionFactory sqlMapper = null;  //static 변수로 sqlMapper객체변수 선언
			//한번 만든뒤 SqlSessionFactory는 애플리케이션을 실행하는 동안 존재해야만 함
			//SqlSessionFactory 의 가장 좋은 스코프는 애플리케이션임으로 static으로 선언

//객체를 찾아 DataSource 로 받는다.
	public static SqlSessionFactory getInstance() {
		if (sqlMapper == null) {
			try {
				InputStream inputStream = Resources.getResourceAsStream("cs/dit/dao/mybatis-config.xml"); // 설정파일을 읽어들인다.
				sqlMapper = new SqlSessionFactoryBuilder().build(inputStream);// mybatis를이용하는 sqlMapper객체를 생성한다.
				inputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sqlMapper;
	}
	

// 전체 멤버 목록보기	
	public List<LoginDTO> list() {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();// member-mapper.xml의 SQL문 호출에 사용되는 SqlSession객체를 가져온다.
		try {
			return session.selectList("cs.dit.dao.selectAll"); //member-mapper.xml의 namespace와 각 SQL의 id

		} finally {
			session.close();
		}
	}


	// 멤버 추가하기
	  public int insert(LoginDTO dto) { 
		  sqlMapper = getInstance();
		  SqlSession session = sqlMapper.openSession();
			try {
				int i = session.insert("cs.dit.dao.insert", dto);
				session.commit();
				return i;
			} finally { session.close();
			}
	  }
	  
	//멤버 상세 보기	
	  public LoginDTO view(String id) {
		  sqlMapper = getInstance();
		  SqlSession session = sqlMapper.openSession();
		  try {
			  return session.selectOne("cs.dit.dao.selectOne", id);
			  
		  } finally {
			  session.close();
		  }
		  }
	  // 멤버 정보 수정하기 
	  public int update(LoginDTO dto) {
		  sqlMapper = getInstance();
		  SqlSession session = sqlMapper.openSession();
			try {
				int i = session.update("cs.dit.dao.update", dto);
				session.commit();
				return i;
			} finally {
				session.close();
			}
	  } 
	  
	  // 멤버 삭제 하기 
	  public int delete(String id) { 
		  sqlMapper = getInstance();
		  SqlSession session = sqlMapper.openSession();
			try {
				int i = session.delete("cs.dit.dao.delete", id);
				session.commit();
				return i;
			} finally {
				session.close();
			}
	  }
}
