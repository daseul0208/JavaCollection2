package util.hashmap;

import java.util.HashMap;
import java.util.Map;

public class MemberServiceImpl implements MemberService{
	/*=== 필드 ===*/
// 	Map<String,Member> map = new HashMap<String,Member>();
//  위 처럼 하는 것이 정석입니다. JSP, DB를 만지게 되면
//	<String,Object>가 정석
// 	하지만 이 예제에서는 <String,String>으로 하겠습니다.
//	이유는 쉽게 가려고 합니다
	Map<String,String> map = new HashMap<String,String>();
	Member member = new Member();
	String userid, password, name, address;
	int age;
	/*=== 생성자 ===*/
	/*=== 멤버메소드 ===*/
	@Override
	public void join(String userid, String password, String name, int age, String address) {
		member.setUserid(userid);
		member.setPassword(password);
		member.setName(name);
		member.setAge(age);
		member.setAddress(address);
		
		map.put("userid", member.getUserid());
		map.put("password", member.getPassword());
		map.put("name", member.getName());
		map.put("age", String.valueOf(member.getAge())); // int => String
		map.put("address", member.getAddress());
	}

	@Override
	public String login(String userid, String password) {
		String msg = "";
		if (map.get("userid").equals(userid)&&
				map.get("password").equals(password)) { // 두 조건을 모두 충족
			msg = "환영합니다."+member.getAddress()+"에 사시는"
				+member.getAddress()+" 세 되시는"
				+member.getName()+" 님.";
		} else if(map.get("userid").equals(userid)&&
				!map.get("password").equals(password)){
			msg = "비번이 다릅니다. 다시 입력하세요";
		} else {
			msg = "입력하신 ID는 존재하지 않거나, 일치하지 않습니다. 다시 입력하세요.";
		}
		return msg;
		
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		
	}

}
