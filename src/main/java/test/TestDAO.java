package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TestDAO {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public TestDAO() {
		con = DBConnect.getConnect();
		System.out.println("dao 생성자 실행");
	}
	
	public ArrayList<TestDTO> list() {
		ArrayList<TestDTO> list = new ArrayList<>();
		String sql = "select * from test_board order by idgroup desc, step asc";

		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				TestDTO dto = new TestDTO();
				
				dto.setId(rs.getInt("id"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
		
				dto.setContent(rs.getString("content"));
				dto.setSavedate(rs.getTimestamp("savedate"));
				dto.setHit(rs.getInt("hit"));
				
				dto.setIdgroup(rs.getInt("idgroup"));
				dto.setStep(rs.getInt("step"));
				dto.setIndent(rs.getInt("indent"));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int writeSave(TestDTO dto) {
		int result = 0;
		String sql = "insert into test_board(id, name, title, content, idgroup, step, indent)"
					+"values(test_board_seq.nextval, ?,?,?,test_board_seq.currval,0,0)";
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getTitle());
			ps.setString(3, dto.getContent());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private void upHit(int id) {
		String sql = "update test_board set hit= hit+1 where id="+id;
		try {
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public TestDTO contentView(int id) {
		upHit(id);
		TestDTO dto = null;
		String sql = "select * from test_board where id="+id;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				dto = new TestDTO();
				
				dto.setId(rs.getInt("id"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
		
				dto.setContent(rs.getString("content"));
				dto.setSavedate(rs.getTimestamp("savedate"));
				dto.setHit(rs.getInt("hit"));
				
				dto.setIdgroup(rs.getInt("idgroup"));
				dto.setStep(rs.getInt("step"));
				dto.setIndent(rs.getInt("indent"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public void modify(TestDTO dto) {
		String sql = "update test_board set name=?, title=?, content=? where id=?";
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getTitle());
			ps.setString(3, dto.getContent());
			ps.setInt(4, dto.getId());
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int id) {
		String sql = "delete from test_board where id="+id;
		try {
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void replyShape(TestDTO d) {
		String sql = "update test_board set step= step+1 where idgroup=? and step>?";
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, d.getIdgroup());
			ps.setInt(2, d.getStep());
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void reply(TestDTO dto) {
		TestDTO d = contentView(dto.getId());
		System.out.println(d.getIdgroup());
		
		replyShape(d);
		
		String sql = "insert into test_board(id, name, title, content, idgroup, step, indent)"
				+ "values(test_board_seq.nextval, ?,?,?,?,?,?)";
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getTitle());
			ps.setString(3, dto.getContent());
			
			ps.setInt(4, d.getIdgroup());
			ps.setInt(5, d.getStep()+1);
			ps.setInt(6, d.getIndent()+1);

			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
