package common.listener;


import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import member.model.service.MemberService;

@WebListener
public class SessionCounterListener implements HttpSessionListener{
	
	private static int activeSessions;
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		System.out.println("[sid@SessionCounterListener.sessionCreated="+session.getId());
		activeSessions++;
		System.out.println("[현재 세션객체수: "+activeSessions+"]");
		if(activeSessions==1) {
			
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		System.out.println("[sid@SessioncounterListener.sessionDestryed="+session.getId()+"]");
		
		activeSessions--;
		System.out.println("[현재 세션객체수: "+activeSessions+"]");
	}

}
