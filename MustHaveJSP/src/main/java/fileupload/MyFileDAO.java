package fileupload;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import common.JDBConnect;

public class MyFileDAO extends JDBConnect {
	private static final long serialVersionUID = 1L;

//	public static void main(String[] args) {
//		MyFileDAO dao = new MyFileDAO();
//		MyFileDTO dto = new MyFileDTO();
//		dto.setTitle("title");
//		dto.setCate("cate");
//		dto.setOfile("ofile");
//		dto.setSfile("sfile");
//		System.out.println("Result : " + dao.insertFile(dto));
//	}

	public int insertFile(MyFileDTO dto) {
		int applyResult = 0;
		Connection con = getConnection();
		PreparedStatement pst = null;
		try {
			String query = "insert into myfile ( " + " title, cate, ofile, sfile) " + " values ( " + " ?, ?, ?, ?)";
			pst = con.prepareStatement(query);
			pst.setString(1, dto.getTitle());
			pst.setString(2, dto.getCate());
			pst.setString(3, dto.getOfile());
			pst.setString(4, dto.getSfile());

			applyResult = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("INSERT 중 예외 발생");
			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return applyResult;
	}

	public List<MyFileDTO> myFileList() {
		List<MyFileDTO> fileList = new Vector<MyFileDTO>();
		String query = "select * from myfile order by idx desc";
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {
				MyFileDTO dto = new MyFileDTO();
				dto.setIdx(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setCate(rs.getString(3));
				dto.setOfile(rs.getString(4));
				dto.setSfile(rs.getString(5));
				dto.setPostdate(rs.getString(6));
				fileList.add(dto);
			}
		} catch (Exception e) {
			System.out.println("select 시 예외 발생");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return fileList;
	}
}
