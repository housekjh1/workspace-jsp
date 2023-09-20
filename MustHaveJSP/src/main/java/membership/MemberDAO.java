package membership;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import common.JDBConnect;
import jakarta.servlet.ServletContext;

public class MemberDAO extends JDBConnect {
	private JDBConnect jdbc;
	private Connection con;
	
	public MemberDAO() {
		super();
		this.con = super.getConnection();
	}
	
	public static void main(String[] args) {
		MemberDAO ma = new MemberDAO();
		MemberDTO mt = ma.getMemberDTO("musthave", "1234");
		System.out.println("member:"+mt);
	}
	
	public MemberDAO(ServletContext application) {
		jdbc = new JDBConnect(application);
		
	}
	
	public MemberDTO getMemberDTO(String uid, String upass) {
		MemberDTO dto = new MemberDTO();
		String query = "select * from member where id=? and pass=?";
		
		try {
			Connection con = jdbc.getConnection();
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, uid);
			pst.setString(2, upass);
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString(3));
				dto.setRegidate(rs.getString(4));
			}
			
			rs.close();
			pst.close();
			con.close();
			jdbc.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}
}
