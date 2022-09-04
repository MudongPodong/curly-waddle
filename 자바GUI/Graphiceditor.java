import java.util.*;    //util에 포함된 클래스들을 사용할 때마다 가져온다.
abstract class Shape   //추상클래스인 Shape이다 
{   //추상클래스 시작
	abstract String get();   //추상메소드를 설정하여 다형성의 특징을 사용되게한다.
}   //추상클래스 끝
class Line extends Shape     //Shape를 상속받는 Line 클래스
{    //Line클래스 시작
	private String name1;    //Line의 이름을 저장받는 변수이다    
	public Line()  //Line의 기본생성자
	{   //생성자 시작
		name1="Line";    //선언되자마자 변수에 Line의 이름 "Line"을 저장한다 
	}   //생성자 끝
	String get()     //자신의 이름은 반환하는 메소드
	{   //get메소드 시작
		return name1;    //자신의 이름을 반환한다.
	}   //get메소드 끝
}   //Line 클래스의 끝
class Rect extends Shape    //Shape를 상속받는 Rect클래스
{  //Rect클래스의 시작
	private String name2;   //Rect의 이름을 저장받는 변수이다
	public Rect()     //Rect의 기본생성자
	{  //생성자 시작
		name2="Rect";    //선언되자마자 변수에 Rect의 이름 "Rect"를 저장한다
	}   //생성자 끝	
	String get()    //자신의 이름을 반환하는 메소드
	{   //메소드 시작
		return name2;    //자신의 이름을 반환한다.
	}   //메소드 끝
}  //Rect클래스의 끝
/*가독성을 위한 빈 줄 처리*/
class Circle extends Shape      //Shape를 상속받는 Circle 클래스
{  //Ciccle의 시작
	private String name3;   //Circle의 이름을 저장받는 변수이다
	public Circle()         //Circle의 기본생성자
	{   //생성자 시작
		name3="Circle";   //선언되자마자 변수에 Circle의 이름 "Circle"을 저장한다
	}   //생성자 끝	
	String get()   //자신의 이름을 반환하는 메소드
	{   //메소드 시작
		return name3;   //자신의 이름을 반환한다.
	}   //메소드 끝
}  //Circle의 끝
public class Graphiceditor {            //메인클래스
	public static void main(String[] args) {       //메인메소드
		Scanner std=new Scanner(System.in);    //변수std로 스캐너클래스를 선언한다.
		Vector<Shape> m=new Vector<Shape>();   //벡터의 객체를 선언해준다.
		/*가독성을 위한 빈 줄 처리*/
		System.out.println("그래픽 에디터 beauty을 시작합니다.");     //프로그램 시작을 알린다.
		while(true)       //프로그램 종료를 선택할때 까지 무한 반복한다.
		{   //반복문 시작
			System.out.print("삽입(1) 삭제(2) 모두보기(3) 종료(4)>>");    //처음 시작하면 옵션선택을 하라고 알린다.
			int num=std.nextInt();   //메뉴 선택 변수
			/*가독성을 위한 빈 줄 처리*/
			switch(num)     //메뉴 선택변수로 스위치 문을 실행시킨다
			{   //스위치 문 시작
			case 1:   //삽입선택
				System.out.print("Line(1) Rect(2) Circle(3)>>");   //어느 도형을 입력할건지 선택하라고 알린다.
				int num2=std.nextInt();    //어떤 도형을 선택하는지에 대한 변수
				/*가독성을 위한 빈 줄 처리*/
				if(num2==1)    //Line을 선택할시
				{  //if문 시작
					Shape a=new Line();   //객체를 만들고 업캐스팅 시킨다 (다형성을 위해)
					m.add(a);       //벡터에 업캐스팅 된 객체를 삽입한다.
				}  //if문 끝
				else if(num2==2)   //Rect를 선택할시
				{  //else if문 시작
					Shape a=new Rect();  //객체를 만들고 업캐스팅 시킨다 (다형성을 위해)
					m.add(a);      //벡터에 업캐스팅 된 객체를 삽입한다.
				}   //else if문 끝
				else if(num2==3)    //Circle을 선택할 시
				{  //else if문 끝
					Shape a=new Circle();  //객체를 만들고 업캐스팅 시킨다 (다형성을 위해)
					m.add(a);      //벡터에 업캐스팅 된 객체를 삽입한다.
				}  //else if문 끝
				break;     //스위치 문을 중지시키고 다시 반복문을 돌게한다
			case 2:       //삭제
				System.out.print("삭제할 도형의 위치>>");     //어느위치를 삭제할 지 선택하라고 알린다.
				int num3=std.nextInt();      //삭제할 도형의 위치저장 변수
				try    // 삭제할 대상이 있으면 그대로 삭제하면 되지만, 삭제할 대상이 없는 위치를 선택하면 삭제 메소드를 진행시키지않고, catch의 알림을 출력한다.
				{  //try문 시작
					m.remove(num3);  //벡터에서 num3위치의 데이터를 삭제
				}  //try문 끝
				catch(ArrayIndexOutOfBoundsException e)   //오류(배열범위 벗어남)가 뜬다면 알림을 알린다
				{    //catch문 시작
					System.out.println("삭제할 수 없습니다.");   //오류가 났음을 알린다.
				}    //catch문 끝 
				break;    //스위치 문을 중지시키고 다시 반복문을 돌게한다
			case 3:       //리스팅
				Iterator<Shape> it=m.iterator();     //Iterator로 벡터의 값들을 움직이게 하게끔 선언한다.
				/*가독성을 위한 빈 줄 처리*/
				while(it.hasNext())      //it(벡터의 값들)의 다음값이 없을때까지 반복
				{   //반복문 시작
					Shape b=it.next();     //b에다가 해당 it의 객체들을 반환한다.
					System.out.println(b.get());    //업캐스팅 된 b를 이용해 다형성의 특징을 살려 get()메소드를 실행시켜 줄력시킨다.
				}  //반복문 끝
				break;   //스위치 문을 중지시키고 다시 반복문을 돌게한다
			case 4:      //종료
				System.out.println("beauty을 종료합니다.");   //프로그램이 종료됨을 알린다.
				System.exit(0);    //프로그램 종료
			}  //스위치 문 끝
		}  //반복문 끝
	} //메인 메소드의 끝
}     //메인 클래스의 끝
