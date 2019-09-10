package common.wrapper;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncryptWrapper extends HttpServletRequestWrapper {

	/**
	 * 부모타입에 기본생성자가 없기때문에 파라미터생성자 구현 강제화
	 * @param request wrapping하고자 하는 객체
	 */
	public EncryptWrapper(HttpServletRequest request) {
		super(request);
	}

	/**
	 * getParameter메소드중에 
	 * password에 대해서만 암호화 기능을 수행
	 */
	@Override
	public String getParameter(String key) {
		String value = "";
		if(key != null && (key.equals("password")||key.equals("input-pwd")||key.equals("changedpassword"))) {
			value = getSha512(super.getParameter(key));
		}
		else {
			value = super.getParameter(key);
//			System.out.println(value);
		}
		
		return value;
	}

	/**
	 * sha512 해시함수를 사용하여 암호화
	 * @param parameter
	 * @return
	 */
	private String getSha512(String password) {
		String encPwd = null;
		
		//암호화객체 생성(sha-512)
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		//전달받은 문자열 password를 byte[]로 변환
		byte[] bytes = password.getBytes(Charset.forName("UTF-8"));
		
		//md객체에 byte배열을 전달해서 갱신
		md.update(bytes);
		
		//java.util.Base64인코더를 이용해서 암호화된 바이트배열을 인코딩
		//문자열로 리턴
		encPwd = Base64.getEncoder().encodeToString(md.digest());
		
		return encPwd;
	}
}