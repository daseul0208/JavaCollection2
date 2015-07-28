package util.vector;
// 전체에서 특정 글자 바꾸기 단축키 CTRL + F 
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import sun.nio.cs.ext.GB18030;
/*
프로그램을 코딩하다 보면 자료구조를 다루는데 있어서 
CRUD 액션의 반복을 경험하게 됩니다.
Create : 입력, 생산
Read : 출력, 조회
Update : 수정 -> 저장된 값을 수정 DB
Delete : 삭제 -> 완전삭제 DB, null 값으로 바꾸는 삭제
 */

public class GradeServiceImpl implements GradeService{
	// 필드에 아래 메소드들이 공유할 자료구조를 뭘 쓸까?
	// 1. ArrayList 2. Vector 3. Stack 4. HashMap
	// 순서 0, 중복 0
	private List<Grade> vec = new ArrayList<Grade>(); 
	Grade grade = new Grade(); // ㅇ디폴트 생성자가 있어서 에러 제거
	
	@Override
	public void input(Grade grade) {
		// 성적표 입력
	// 만약 자료구조로 배열을 사용했다면 vec[0] = hong;
		vec.add(grade);
	}

	@Override
	public void pintList() {
		// 전체 출력
		System.out.println(vec.toString());
		
	}

	@Override
	public String searchGradeByhakbun(String hakbun) {
		// 학번으로 검색
		String msg = "";
		Grade grade = null; // 지변으로 인스턴스를 선언했으므로 초기화 해야함
		// List 계열의 클래스 길이는 size() 구한다.
		for (int i = 0; i < vec.size(); i++) {
			// 만약 vec가 배열이라면 vec.get(i)
			String searchHakbun = vec.get(i).getHakbun();
			// 객체.메소드.메소드.메소드.메소드 이런
			// 패턴이 가능할 때는 반드시 return 값이 있는 메소드들 끼리 연결시에만 가능하다
			// 이런 방식을 메소드 체인이라고함
				
			if (hakbun.equalsIgnoreCase(searchHakbun)) {
			String name = vec.get(i).getName();
			int kor = vec.get(i).getKor();
			int eng = vec.get(i).getEng();
			int math = vec.get(i).getMath();
			grade = new Grade(searchHakbun,name,kor,eng,math);
			msg = grade.toString();
			break; // 중간이라도 학번이 일치하면 그대로 스톱
			}  else {
				msg = "조회하는 학번이 없습니다.";
			}
		}
		return msg;
	}

	
	@Override
	public Vector<Grade> searchGradeByName(String name) {
		Vector<Grade> temp = new Vector<Grade>();
		Grade grade = null;
		for (int i = 0; i < vec.size(); i++) {
			String sarchName = vec.get(i).getName();
			// 고정값(파라미터).equals(변수값)
			if (name.equalsIgnoreCase(sarchName)) {
				grade = new Grade(vec.get(i).getHakbun(), 
						name, 
						vec.get(i).getKor(), 
						vec.get(i).getEng(), 
						vec.get(i).getMath());
				temp.add(grade);
				
			} else {
				// temp라는 벡터를 완전히 비워서 null로 리턴
				temp.remove(new Grade());
			}
			
		}
		return temp;
	}

	@Override
	public void decsByTotal() {
		// java API중에 정렬담당 클래스 
		// Comparator
		// 인터페이스를 구현한 익명 내부 클래스
		// anonymous inner class
		Comparator<Grade> desc = new Comparator<Grade>(){ 
		
				
			@Override
			public int compare(Grade g1, Grade g2) {
				// 삼항연산자
				/*
				 if(condition){
				 	--> true면 statement 실행
				 }else{
				 	--> false면 statement 실행
				 }
				 (조건식) ? 참 : 거짓;
				 */
				
				/*if ((g1.getTotal()<g2.getTotal())) {
					temp = 1;
				} else {
					if (g1.getTotal()==g2.getTotal()) {
						temp = 0;
					} else {
						temp = -1;
					}
				}
				return temp;
				*/
				return (g1.getTotal()<g2.getTotal()) ? 1 : 
					   (g1.getTotal()==g2.getTotal()) ? 0 : -1 ;
			}
		};
		Collections.sort(vec,desc);
		System.out.println(vec.toString());
	}

	@Override
	public void ascByName() {
		Comparator<Grade> asc = new Comparator<Grade>() {
			
			@Override
			public int compare(Grade o1, Grade o2) {
				// 값(value)이 int타입이 아니고 String 타입의 우선순위 결정할 때는...
				// compareTo() 메소드를 사용해야한다
				return o1.getName().compareTo(o2.getName());
			}
		};
		Collections.sort(vec, asc);
		System.out.println(vec.toString());
	}

}
