package Message.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import Message.model.dao.MessageDAO;
import Message.model.vo.Message;
import member.model.vo.Member;
public class MessageService {

	public List<Message> selectMessage(String messageType, String memberId, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Message> message = new MessageDAO().selectMessage(conn,messageType,memberId,cPage, numPerPage);
		close(conn);
		return message;
	}

	public int insertMessage(String sendId, String receiveId, String messageContent) {
		Connection conn = getConnection();
		int result = new MessageDAO().insertMessage(conn,sendId,receiveId,messageContent);
		if(result > 0) {
			commit(conn);
		}
		else
			rollback(conn);
		close(conn);
		return result;
	}
	public List<String> selectByName(String srchName) {
		Connection conn = getConnection();
		List<String> list = new MessageDAO().selectByName(conn, srchName);
		close(conn);
		
		return list;
	}
	public Member selectOne(String srchName) {
		Connection conn = getConnection();
		Member m = new MessageDAO().selectOne(conn, srchName);
		close(conn);
		return m;
	}

	public int selectMessageCount(String messageType, String memberId) {
		Connection conn = getConnection();
		int totalContent = new MessageDAO().selectMessageCount(conn, messageType,memberId);
		close(conn);
		return totalContent;
	}

	public Message selectMessageOne(int messageNo) {
		Connection conn = getConnection();
		Message message = new MessageDAO().selectMessageOne(conn, messageNo);
		close(conn);
		return message;
	}

	public int deleteMessage(int messageNo) {
		Connection conn = getConnection();
		int result = new MessageDAO().deleteMessage(conn, messageNo);
		if(result > 0) {
			commit(conn);
		}
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int insertReplyMessage(String sendId, String receiveId, String messageContent) {
		Connection conn = getConnection();
		int result = new MessageDAO().insertReplyMessage(conn,sendId,receiveId,messageContent);
		if(result > 0) {
			commit(conn);
		}
		else
			rollback(conn);
		close(conn);
		return result;
	}

	

}
