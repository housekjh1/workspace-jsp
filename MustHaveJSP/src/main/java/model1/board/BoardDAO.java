package model1.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.JDBConnect;
import jakarta.servlet.ServletContext;

public class BoardDAO extends JDBConnect {
	private JDBConnect jdbc;

	public BoardDAO() {
	};

	public BoardDAO(ServletContext application) {
		super(application);
	}

	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;

		String query = "select count(*) from board";

		if (map.get("searchWord") != null) {
			query += "where " + map.get("searchField") + " " + " like '%" + map.get("searchWord") + "%'";
		}
		Statement st = null;
		ResultSet rs = null;
		try {
			Connection con = getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);

		} catch (Exception e) {
			System.out.println("게시물 수를 구하는 중 예외 발생" + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return totalCount;
	}

	public List<BoardDTO> selectList(Map<String, Object> map) {
		List<BoardDTO> bbs = new Vector<BoardDTO>();

		String query = "select * from board";
		if (map.get("searchWord") != null) {
			query += "where " + map.get("searchField") + " " + " like '%" + map.get("searchWord") + "%'";
		}
		query += " order by num desc";

		Statement st = null;
		ResultSet rs = null;
		try {
			Connection con = getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setNum(rs.getString("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString("visitcount"));
				bbs.add(dto);
			}
		} catch (Exception e) {
			System.out.println("게시물 조회 중 예외 발생" + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bbs;
	}

	public List<BoardDTO> selectListPage(Map<String, Object> map) {
		List<BoardDTO> bbs = new Vector<BoardDTO>();
		Connection con = getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
//		String query = "select * from board where (num between ? and ?)";
		String query = "select * from board ";
		if (map.get("searchWord") != null) {
//			query += " and " + map.get("searchField") + " like '%" + map.get("searchWord") + "%'";
			query += ("where " + map.get("searchField") + " like '%" + map.get("searchWord") + "%'");
		}
		query += " order by num desc limit ?, ?";

		try {
			pst = con.prepareStatement(query);
//			pst.setString(1, map.get("start").toString());
//			pst.setString(2, map.get("end").toString());
			pst.setInt(1, Integer.parseInt(map.get("start").toString()) - 1);
			pst.setInt(2, Integer.parseInt(map.get("pageSize").toString()));
			rs = pst.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setNum(rs.getString("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString("visitcount"));
				bbs.add(dto);
			}
		} catch (Exception e) {
			System.out.println("게시물 조회 중 예외 발생" + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bbs;
	}

	public int insertWrite(BoardDTO dto) {
		int result = 0;
		PreparedStatement pst = null;
		try {
			String query = "insert into board (" + "title,content,id,visitcount) " + "values (?,?,?,0)";
			Connection con = getConnection();
			pst = con.prepareStatement(query);
			pst.setString(1, dto.getTitle());
			pst.setString(2, dto.getContent());
			pst.setString(3, dto.getId());
			result = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 입력 중 예외 발생" + e.getMessage());
		} finally {
			try {
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public BoardDTO selectView(String num) {
		BoardDTO dto = new BoardDTO();
		String query = "select B.*, M.name from member M inner join board B on M.id=B.id where num=?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Connection con = getConnection();
			pst = con.prepareStatement(query);
			pst.setString(1, num);
			rs = pst.executeQuery();

			if (rs.next()) {
				dto.setNum(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString(6));
				dto.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			System.out.println("게시물 상세보기 중 예외 발생" + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dto;
	}

	public void updateVisitCount(String num) {
		String query = "update board set visitcount=visitcount+1 where num=?";
		PreparedStatement pst = null;
		try {
			Connection con = getConnection();
			pst = con.prepareStatement(query);
			pst.setString(1, num);
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 조회수 증가 중 예외 발생" + e.getMessage());
		} finally {
			try {
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int updateEdit(BoardDTO dto) {
		int result = 0;
		PreparedStatement pst = null;
		try {
			Connection con = getConnection();
			String query = "update board set title=?, content=? where num=?";
			pst = con.prepareStatement(query);
			pst.setString(1, dto.getTitle());
			pst.setString(2, dto.getContent());
			pst.setString(3, dto.getNum());

			result = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 수정 중 예외 발생" + e.getMessage());
		} finally {
			try {
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public int deletePost(BoardDTO dto) {
		int result = 0;
		PreparedStatement pst = null;
		try {
			String query = "delete from board where num=?";
			Connection con = getConnection();
			pst = con.prepareStatement(query);
			pst.setString(1, dto.getNum());
			result = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 삭제 중 예외 발생" + e.getMessage());
		} finally {
			try {
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
}
