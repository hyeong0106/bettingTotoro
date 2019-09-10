package Message.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import Message.model.vo.Message;
import board.model.vo.Board;
import member.model.vo.Member;

public class MessageDAO {
	private Properties prop = new Properties();
	
	public MessageDAO() {
		try {
            String fileName = MessageDAO.class.getResource("/sql/message/message-query.properties").getPath();
            prop.load(new FileReader(fileName));
            
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public List<Message> selectMessage(Connection conn,String messageType, String memberId, int cPage, int numPerPage) {
		List<Message> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String query = null;
        
        if(messageType.equals("send")) {
        	query = prop.getProperty("selectSendMessageByPaging");        	
        }
        else if(messageType.equals("receive")){
        	query = prop.getProperty("selectReceiveMessageByPaging"); 
        }
        
        try{
            pstmt = conn.prepareStatement(query);
            int startRowNum = (cPage-1)*numPerPage+1;
            int endRowNum = cPage * numPerPage;
            pstmt.setString(1, memberId);
            pstmt.setInt(2, startRowNum);
            pstmt.setInt(3, endRowNum);
            rset = pstmt.executeQuery();
            
            while(rset.next()){
            	Message m = new Message();
            	
            	m.setMessageNo(rset.getInt("messageNo"));
            	m.setSendId(rset.getString("send_Member_id"));
            	m.setReceiveId(rset.getString("receive_Member_id"));
            	m.setContent(rset.getString("content"));
            	m.setSendTime(rset.getDate("send_time"));
                
                list.add(m);
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close(rset);
            close(pstmt);
        }
        
        
        return list;
	}

	public int insertMessage(Connection conn, String sendId, String receiveId, String messageContent) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMessage");
		System.out.println("디버깅중 여기옴?");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sendId);
			pstmt.setString(2, receiveId);
			pstmt.setString(3, messageContent);

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<String> selectByName(Connection conn, String srchName) {
		List<String> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectByName");
		
		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, "%"+srchName+"%");
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				list.add(rset.getString("member_id"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public Member selectOne(Connection conn, String srchName) {
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOne");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, srchName);
			//쿼리실행
			rset = pstmt.executeQuery();
			
			//rset의 정보를 member객체에 옮겨담기
			if(rset.next()) {
				m = new Member();
				
				m.setMemberNo(rset.getInt("member_No"));
				m.setMemberId(rset.getString("member_id"));
				m.setPassword(rset.getString("password"));
				m.setMemberName(rset.getString("member_name"));
				m.setGender(rset.getString("gender"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setHint(rset.getString("hint"));
				m.setEnrollDate(rset.getDate("enrolldate"));
				m.setPoint(rset.getInt("point"));
				m.setScore(rset.getInt("score"));
				
			}
//			System.out.println("member@dao="+m);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return m;
	}

	public int selectMessageCount(Connection conn, String messageType, String memberId) {
		int totalContent = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = null;
		
		if(messageType.equals("send")) {
			query = prop.getProperty("selectSendMessageCount");        	
		}
		else if(messageType.equals("receive")){
			query = prop.getProperty("selectReceiveMessageCount"); 
		}
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				totalContent = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return totalContent;
	}

	public Message selectMessageOne(Connection conn, int messageNo) {
		Message m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMessageOne");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, messageNo);
			//쿼리실행
			rset = pstmt.executeQuery();
			
			//rset의 정보를 member객체에 옮겨담기
			if(rset.next()) {
				m = new Message();
				
				m.setMessageNo(rset.getInt("MESSAGENO"));
				m.setSendId(rset.getString("SEND_MEMBER_ID"));
				m.setReceiveId(rset.getString("RECEIVE_MEMBER_ID"));
				m.setContent(rset.getString("CONTENT"));
				m.setSendTime(rset.getDate("SEND_TIME"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return m;
	}

	public int deleteMessage(Connection conn, int messageNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteMessage");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, messageNo);

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertReplyMessage(Connection conn, String sendId, String receiveId, String messageContent) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertReplyMessage");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sendId);
			pstmt.setString(2, receiveId);
			pstmt.setString(3, messageContent);

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	

}
