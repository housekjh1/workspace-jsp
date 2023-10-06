package model2.mvcboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.JDBConnect;

public class MVCBoardDAO extends JDBConnect {
	public MVCBoardDAO() {
		super();
	}

	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
		String query = "select count(*) from mvcboard";
		if (map.get("searchWord") != null) {
			query += " where " + map.get("searchField") + " " + " like '%" + map.get("searchWord") + "%'";
		}
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("게시물 카운트 중 예외 발생");
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
		return totalCount;
	}

	public List<MVCBoardDTO> selectListPage(Map<String, Object> map) {
		List<MVCBoardDTO> board = new Vector<MVCBoardDTO>();
		String query = "select * from mvcboard ";
		if (map.get("searchWord") != null) {
			query += " where " + map.get("searchField") + " like '%" + map.get("searchWord") + "%' ";
		}
		query += " order by idx desc limit ?, ?";
		Connection con = getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(query);
			pst.setInt(1, Integer.parseInt(map.get("start").toString()));
			pst.setInt(2, Integer.parseInt(map.get("end").toString()));
			rs = pst.executeQuery();
			while (rs.next()) {
				MVCBoardDTO dto = new MVCBoardDTO();
				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setPass(rs.getString(9));
				dto.setVisitcount(rs.getInt(10));
				board.add(dto);
			}
		} catch (Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return board;
	}

	public int insertWrite(MVCBoardDTO dto) {
		int result = 0;
		Connection con = getConnection();
		PreparedStatement pst = null;
		try {
			String query = "insert into mvcboard ( " + " name, title, content, ofile, sfile, pass) " + " values ( "
					+ " ?, ?, ?, ?, ?, ?)";
			pst = con.prepareStatement(query);
			pst.setString(1, dto.getName());
			pst.setString(2, dto.getTitle());
			pst.setString(3, dto.getContent());
			pst.setString(4, dto.getOfile());
			pst.setString(5, dto.getSfile());
			pst.setString(6, dto.getPass());
			result = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public int insertWrite2(MVCBoardDTO dto) {
		int result = 0;
		Connection con = getConnection();
		PreparedStatement pst = null;
		try {
			con.setAutoCommit(false);
			String query = "insert into mvcboard2 ( " + " name, title, content, pass) " + " values ( "
					+ " ?, ?, ?, ?)";
			pst = con.prepareStatement(query);
			pst.setString(1, dto.getName());
			pst.setString(2, dto.getTitle());
			pst.setString(3, dto.getContent());
			pst.setString(4, dto.getPass());
			pst.executeUpdate();
			String query2 = "insert into mvcboardfile (idx) values (?)";
			pst = con.prepareStatement(query2);
			pst.setInt(1, Integer.parseInt(dto.getIdx()));
			pst.executeUpdate();
			String query3 = "insert into mvcboardfile (ofile, sfile) values (?, ?) where mvcboard2.idx = mvcboardfile.idx";
			pst = con.prepareStatement(query3);
			pst.setString(1, dto.getOfile());
			pst.setString(2, dto.getSfile());
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

//	public static void main(String[] args) {
//		JDBConnect jdbc = new JDBConnect();
//		Connection con = jdbc.getConnection();
//		PreparedStatement pst = null;
//		try {
//			String sql = "insert into mvcboard (name, title, content, pass) values (?, ?, ?, ?)";
//			int i = 1;
//			while (i <= 100) {
//				pst = con.prepareStatement(sql);
//				pst.setString(1, "이름" + i);
//				pst.setString(2, "제목" + i);
//				pst.setString(3, "내용" + i);
//				pst.setString(4, "1234");
//				pst.executeUpdate();
//				i++;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (pst != null)
//					pst.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}

	public MVCBoardDTO selectView(String idx) {
		MVCBoardDTO dto = new MVCBoardDTO();
		String query = "select * from mvcboard where idx=?";
		Connection con = getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, idx);
			rs = pst.executeQuery();

			if (rs.next()) {
				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setPass(rs.getString(9));
				dto.setVisitcount(rs.getInt(10));
			}
		} catch (Exception e) {
			System.out.println("게시물 상세보기 중 예외 발생");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dto;
	}

	public void updateVisitCount(String idx) {
		String query = "update mvcboard set " + " visitcount = visitcount+1" + " where idx=?";
		Connection con = getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, idx);
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 조회수 증가 중 예외 발생");
			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void downCountPlus(String idx) {
		String query = "update mvcboard set " + " downcount = downcount+1" + " where idx=?";
		Connection con = getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, idx);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean confirmPassword(String pass, String idx) {
		boolean isCorr = true;
		Connection con = getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "select count(*) from mvcboard where pass=? and idx=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, pass);
			pst.setString(2, idx);
			rs = pst.executeQuery();
			rs.next();
			if (rs.getInt(1) == 0) {
				isCorr = false;
			}
		} catch (Exception e) {
			isCorr = false;
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isCorr;
	}

	public int deletePost(String idx) {
		int result = 0;
		Connection con = getConnection();
		PreparedStatement pst = null;
		try {
			String query = "delete from mvcboard where idx=?";
			pst = con.prepareStatement(query);
			pst.setString(1, idx);
			result = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 삭제 중 예외 발생");
			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int updatePost(MVCBoardDTO dto) {
		int result = 0;
		Connection con = getConnection();
		PreparedStatement pst = null;
		try {
			String query = "update mvcboard " + " set title=?, name=?, content=?, ofile=?, sfile=? "
					+ " where idx=? and pass=?";
			pst = con.prepareStatement(query);
			pst.setString(1, dto.getTitle());
			pst.setString(2, dto.getName());
			pst.setString(3, dto.getContent());
			pst.setString(4, dto.getOfile());
			pst.setString(5, dto.getSfile());
			pst.setString(6, dto.getIdx());
			pst.setString(7, dto.getPass());

			result = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 수정 중 예외 발생");
			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
