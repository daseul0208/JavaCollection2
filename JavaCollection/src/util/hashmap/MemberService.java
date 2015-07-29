package util.hashmap;

public interface MemberService{
	public void join(String userid, String password, String name, int age, String address);
	
	public String login(String userid, String password);
	
	public void logout();
	

}
