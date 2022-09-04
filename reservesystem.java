import java.util.*;     //Scanner 와 equals함수 등 여러기능을 쓰기위해 util의 기능들을 쓸수있게 함
/*가독성을 위해 빈 줄처리*/
class reservationmachine             //reservationmachine(예약머신)은 예약, 조회,취소의 기능을 보유하고 있다 
{                                  //class의 시작
	Scanner std=new Scanner(System.in);   //클래스내 입력받기위해 입력변수를 선언한다.
    /*가독성을 위해 빈 줄처리*/
	private String name="";        //사람이름
	private int seatkind=0;       //좌석 종류(S,A,B)
	private static String[] seatnumS= {"---","---","---","---","---","---","---","---","---","---"};   //S좌석의 현황상태 (모든 객체가 공동으로 사용하니까 static 선언함)
	private static String[] seatnumA={"---","---","---","---","---","---","---","---","---","---"};   //A좌석의 현황상태 (모든 객체가 공동으로 사용하니까 static 선언함)
	private static String[] seatnumB={"---","---","---","---","---","---","---","---","---","---"};   //B좌석의 현황상태 (모든 객체가 공동으로 사용하니까 static 선언함)	
	/*가독성을 위해 빈 줄처리*/
	public void reserve()      /*예약기능 함수*/
	{      //reserve()함수 시작
		int seat_idx=0;  //S좌석이든, A좌석이든, B좌석이든 몇번째 자리에 앉을지 물어보고 그 값을 임시로 받는 변수
		System.out.print("좌석구분 S(1) A(2) B(3)>>");       //어느 자리에 앉을지 묻는다
		seatkind=std.nextInt();    //seatkind에 S,A,B중 어디에 앉을지 입력해준다
		switch (seatkind)          //seatkind에 따라 case 1은 S좌석, case 2는 A좌석, case 3은 B좌석을 뜻한다
		{   //스위치 문시작
		case 1:    //1은 S좌석을 뜻한다
		{   //case 1문 시작
			System.out.print("S>>");   //이후 출력내용이 S좌석임을 알린다
			for(int i=0;i<10;i++)   //현재좌석현황을 전부 출력한다.
			{  //for문 시작
				System.out.print(seatnumS[i]+" ");  //static배열 각 현재 각요소 출력(S좌석)
			}  //for문 끝
			System.out.println("");  //현재S좌석현황을 나타내고 개행실시
			
			System.out.print("이름>>");  //이름을 입력하라고 묻는다
			name=std.next();            //name에 이름을 입력한다.
			System.out.print("번호>>");  //몇번째 자리에 앉을지 묻는다.
			seat_idx=std.nextInt();     //seat_idx에 몇번째 자리에 앉을지 입력한다.
			if(seat_idx<=0 || seat_idx>=10)    //만약 자리번호가 0이하거나 10이상이면 존재하지않는 좌석이기에 다시 reserve()를 호출하게한다.
			{  //if 문 시작
				System.out.println("없는번호입니다");     //없는번호라고 알려준다.
				reserve();         //다시 예약하게하기위해 재호출한다.
			}  //if문 끝 
			else if(seatnumS[seat_idx-1]!="---")   //자리번호는 적절범위에 있으나, "---"(빈 공간)이 아닐때 마찬가지로 재호출하게한다.
			{  //else if문 시작
				System.out.println("이 자리는 누군가 이미 예약한 상태입니다.");   //이미 예약된좌석이라고 알려준다.
				reserve();   //다시 예약하게하기위해 재호출한다.
			}  //else if문 끝
			else   //적절한 좌석번호이고, "---"(빈 공간) 일때 =정상일때 예약을 진행한다.
			{  //else 문 시작
				seatnumS[seat_idx-1]=name;     //이름과 S좌석에서 앉을자리를 선택하면 그자리에 이름을 넣는다.
			}  //else 문 끝
			break;  //case 1문이 끝나면 스위치문을 종료시킨다.
		}    //case 1 문 끝
		case 2:   //2는 A좌석을 의미한다
		{    //case 2문 시작
			System.out.print("A>>");   //이후 출력내용이 A좌석임을 알린다.
			for(int i=0;i<10;i++)   //현재좌석현황을 전부 출력한다.
			{  //for문 시작
				System.out.print(seatnumA[i]+" ");  //static배열 각 현재 요소 출력(A좌석)
			}  //for문 끝
			System.out.println("");  //현재좌석현황을 나타내고 개행실시
			/*가독성을 위해 빈 줄처리*/
			System.out.print("이름>>");  //이름을 입력하라고 묻는다.
			name=std.next();            //name에 이름을 입력한다.
			System.out.print("번호>>");   //번호를 입력하라고 묻는다.
			seat_idx=std.nextInt();     //seat_idx에 몇번쨰 자리에 앉을지 입력한다.
			if(seat_idx<=0 || seat_idx>=10)  //만약 자리번호가 0이하거나 10이상이면 존재하지않는 좌석이기에 다시 reserve()를 호출하게한다.
			{  //if문 시작
				System.out.println("없는번호입니다");    //없는번호라고 알려준다.
				reserve();    //다시 예약하게하기위해 재호출한다.
			}  //if문 끝
			else if(seatnumA[seat_idx-1]!="---")  //자리번호는 적절범위에 있으나, "---"(빈 공간)이 아닐때 마찬가지로 재호출하게한다.
			{  //else if문 시작
				System.out.println("이 자리는 누군가 이미 예약한 상태입니다.");    //이미 예약된좌석이라고 알려준다.
				reserve();     //다시 예약하게하기위해 재호출한다.
			}  //else if문 끝
			else   //적절한 좌석번호이고, "---"(빈 공간) 일때 =정상일때 예약을 진행한다.
			{   //else 문 시작
				seatnumA[seat_idx-1]=name;     //이름과 A좌석에서 앉을자리를 선택하면 그자리에 이름을 넣는다.
			}  //else 문 끝
			break;   //case 2문이 끝나면 스위치문을 종료시킨다.
		}  //case 2문 끝
		case 3:  //3은 B좌석을 의미한다.
		{    //case 3 문 시작
			System.out.print("B>>");  //이후 출력내용이 B좌석임을 알린다.
			for(int i=0;i<10;i++)   //현재좌석현황을 전부 출력한다.
			{  //for 문 시작
				System.out.print(seatnumB[i]+" ");   //static배열 각 현재 요소 출력(B좌석)
			}  //for 문 끝
			System.out.println("");  //현재좌석현황을 나타내고 개행실시
			/*가독성을 위해 빈 줄처리*/
			System.out.print("이름>>");  //이름을 입력하라고 묻는다.
			name=std.next();        //name에 이름을 입력한다.
			System.out.print("번호>>");    //번호를 입력하라고 묻는다.
			seat_idx=std.nextInt();        //seat_idx에 몇번쨰 자리에 앉을지 입력한다.
			if(seat_idx<=0 || seat_idx>=10)   //만약 자리번호가 0이하거나 10이상이면 존재하지않는 좌석이기에 다시 reserve()를 호출하게한다.
			{  //if 문 시작
				System.out.println("없는번호입니다");  //없는번호라고 알려준다.
				reserve();   //다시 예약하게하기위해 재호출한다.
			}  //if 문 끝
			else if(seatnumB[seat_idx-1]!="---")   //자리번호는 적절범위에 있으나, "---"(빈 공간)이 아닐때 마찬가지로 재호출하게한다.
			{  //else if 문시작
				System.out.println("이 자리는 누군가 이미 예약한 상태입니다.");   //이미 예약된좌석이라고 알려준다.
				reserve();    //다시 예약하게하기위해 재호출한다.
			}  //else if 문 끝
			else  //적절한 좌석번호이고, "---"(빈 공간) 일때 =정상일때 예약을 진행한다.
			{  //else 문 시작
				seatnumB[seat_idx-1]=name;     //이름과 S좌석에서 앉을자리를 선택하면 그자리에 이름을 넣는다.
			}  //else 문 끝
			break;   //case 3문이 끝나면 스위치문을 종료시킨다.
		}  //case 3문 끝
		}  //switch 문끝
	}  //reserve()함수 끝    
	/*가독성을 위해 빈 줄처리*/
	public void listarr()    /*예약조회 함수*/
	{  //listarr함수 시작
		System.out.print("S>>");   //S좌석 나열하기 전 ">>"옆에 나열되는 문자열들은 모두 S좌석의 현황을 나타냄
		for(int i=0;i<10;i++)     //S좌석의 현재좌석현황을 전부 출력한다.
		{  //for문 시작
			System.out.print(seatnumS[i]+" ");   //static배열 각 현재 요소 출력(S좌석)
		}  //for문 끝
		System.out.println("");    //S좌석을 나열한 뒤 개행처리
		/*가독성을 위해 빈 줄처리*/
		System.out.print("A>>");  //A좌석 나열하기 전 ">>"옆에 나열되는 문자열들은 모두 A좌석의 현황을 나타냄
		for(int i=0;i<10;i++)    //A좌석의 현재좌석현황을 전부 출력한다.
		{  //for문 시작
			System.out.print(seatnumA[i]+" ");   //static배열 각 현재 요소 출력(A좌석)
		}  //for문 끝
		System.out.println("");    //A좌석을 나열한 뒤 개행처리
		/*가독성을 위해 빈 줄처리*/
		System.out.print("B>>");  //B좌석 나열하기 전 ">>"옆에 나열되는 문자열들은 모두 B좌석의 현황을 나타냄
		for(int i=0;i<10;i++)     //B좌석의 현재좌석현황을 전부 출력한다.
		{  //for문 시작
			System.out.print(seatnumB[i]+" ");     //static배열 각 현재 요소 출력(B좌석)
		}  //for문 끝
		System.out.println("");    //B좌석을 나열한 뒤 개행처리
		/*가독성을 위해 빈 줄처리*/
		System.out.println("<<<조회를 완료하였습니다.>>>");  //모든 좌석을 출력했음을 알리는 문자열 출력
	}  //listarr 함수 끝
	/*가독성을 위해 빈 줄처리*/
	public void cancel()      /*예약취소 함수*/
	{  //cancel 함수 시작
		int num=0;   //스위치문을 실행하기위해 S는 1,A는 2, B는 3을 입력하면 각 라인을 출력되게한다.
		int cnt=0;    //cnt는 이름검색을 하면서 일치하는 이름이 나오지않을때 마다 1씩증가시켜준다. 만약 10이 되면 그 배열에 동일이름이 존재하지 않는다는 뜻임
		/*가독성을 위해 빈 줄처리*/
		String tempname=""; //이름을 받아서 일치하는 이름이 있는지 확인하기위해 만든 변수
		System.out.print("좌석 S:1 A:2 B:3>>"); //삭제할 대상이 어느 좌석라인에 있는지 묻는다
		num=std.nextInt();   //어느 줄대상을 취소대상으로 할지 정하는 인수를 받는다 (S=1,A=2,B=3)
		/*가독성을 위해 빈 줄처리*/
		switch(num)    //num을 대상으로 스위치문 시작
		{  //switch문 시작
		  case 1:  //S좌석만 대상
		  {  // case 1 문 시작
			  System.out.print("S>>");  //이후의 줄이 S줄임을 알린다.
			  for(int i=0;i<10;i++)     //S좌석의 현재좌석현황을 전부 출력한다.
			  {  //for문 시작
				  System.out.print(seatnumS[i]+" ");  //static배열 각 현재 요소 출력(S좌석)
			  }  //for문 끝
			  System.out.println("");     //S좌석을 나열한 뒤 개행처리
			  System.out.print("이름>>");  //삭제대상의 이름을 입력하라고 알려주는 문자열 출력
			  tempname=std.next();    //삭제할 대상의 이름을 tempname에 삽입	 
			  /*가독성을 위해 빈 줄처리*/
			  for(int i=0;i<10;i++)    //S좌석 각 배열요소에 접근하면서 같은문자열이 존재하는지 확인한다
			  {  //for문 시작
				  if(tempname.equals(seatnumS[i]))  //S좌석배열에 i번째 인덱스에 같은 문자열이 있으면 그 공간에 다시"---"(빈 공간)을 넣어준다   
				  {  //for문의 if문 시작
					  seatnumS[i]="---";     //그 위치의 이름을 삭제하고 다시"---"(입력하지 않은상태)를 넣는다.
				  }   //for문의 if문 끝
				  else   //S좌석배열의 i번째 인덱스에 같은 문자열이 아니라면 cnt를 증가시켜줌 
				  {   //for문의 else문 시작
					  cnt++;  //찾지 못할때마다 1씩증가시켜준다.
				  }	  //for문의 else문 끝	  
			  }  //for문 끝
			  if(cnt==10)    //10번을 for문이 돌동안, 한번도 찾지 못함을 의미한다. 즉, 다시 취소대상을 찾으라 한다   
			  {  //if 문 시작
				  System.out.println("없는 이름입니다!");   //없는 이름이라고 알려준다.
				  cancel();  //다시 취소하게하기위해 재호출한다.		  
			  }  //if문 끝
			  break;  //case1문이 끝나면 스위치문을 종료시킨다.
		  }  //case 1문 끝
		  case 2:  //A좌석만 대상
		  {  //case 2문 시작
			  System.out.print("A>>");  //이후의 줄이 A줄임을 알린다.
			  for(int i=0;i<10;i++)    //A좌석의 현재좌석현황을 전부 출력한다.
			  {  //for문 시작
				  System.out.print(seatnumA[i]+" ");  //static배열 각 현재 요소 출력(A좌석)
			  }  //for문 끝
			  System.out.println("");   //A좌석을 나열한 뒤 개행처리
			  System.out.print("이름>>");    //삭제대상의 이름을 입력하라고 알려주는 문자열 출력
			  tempname=std.next();    //삭제할 대상의 이름을 tempname에 삽입
			  /*가독성을 위해 빈 줄처리*/
			  for(int i=0;i<10;i++)   //A좌석 각 배열요소에 접근하면서 같은문자열이 존재하는지 확인한다
			  {  //for문 시작
				  if(tempname.equals(seatnumA[i]))    //A좌석배열에 i번째 인덱스에 같은 문자열이 있으면 그 공간에 다시"---"(빈 공간)을 넣어준다  
				  {    //for문의 if문 시작
					  seatnumA[i]="---";     //그 위치의 이름을 삭제하고 다시"---"(입력하지 않은상태)를 넣는다.
				  }    //for문의 if문 끝
				  else   //A좌석배열의 i번째 인덱스에 같은 문자열이 아니라면 cnt를 증가시켜줌
				  {      //for문의 else문 시작
					  cnt++;  //찾지 못할때마다 1씩증가시켜준다.
				  }		 //for문의 else문 끝
			  }  //for문 끝
			  if(cnt==10)    //10번을 for문이 돌동안, 한번도 찾지 못함을 의미한다. 즉, 다시 취소대상을 찾으라 한다   
			  {   //if문 시작
				  System.out.println("없는 이름입니다!");   //없는 이름이라고 알려준다.
				  cancel();    //다시 취소하게하기위해 재호출한다.	
			  }  //if문 끝
			  break;   //case2문이 끝나면 스위치문을 종료시킨다.
		  }  //case 2문 끝
		  case 3:  //B좌석만 대상
		  {   //case 3문 시작
			  System.out.print("B>>");  //이후의 줄이 B줄임을 알린다.
			  for(int i=0;i<10;i++)     //B좌석의 현재좌석현황을 전부 출력한다.
			  {   //for 문 시작 
				  System.out.print(seatnumB[i]+" ");    //static배열 각 현재 요소 출력(B좌석)
			  }    //for 문 끝
			  System.out.println("");   //B좌석을 나열한 뒤 개행처리
			  System.out.print("이름>>");  //삭제대상의 이름을 입력하라고 알려주는 문자열 출력
			  tempname=std.next();     //삭제할 대상의 이름을 tempname에 삽입
			  /*가독성을 위해 빈 줄처리*/
			  for(int i=0;i<10;i++)    //B좌석 각 배열요소에 접근하면서 같은문자열이 존재하는지 확인한다
			  {   //for문 시작
				  if(tempname.equals(seatnumB[i]))    //B좌석배열에 i번째 인덱스에 같은 문자열이 있으면 그 공간에 다시"---"(빈 공간)을 넣어준다 
				  {  //for문의 if문 시작
					  seatnumB[i]="---";     //그 위치의 이름을 삭제하고 다시"---"(입력하지 않은상태)를 넣는다.
				  }   //for문의 if문 시작
				  else   //B좌석배열의 i번째 인덱스에 같은 문자열이 아니라면 cnt를 증가시켜줌
				  {  //for문의 else문 시작
					  cnt++;  //찾지 못할때마다 1씩증가시켜준다.
				  }	 //for문의 else문 끝	   
			  }   //for문 끝
			  if(cnt==10)    //10번을 for문이 돌동안, 한번도 찾지 못함을 의미한다. 
			  {  //if 문 시작
				  System.out.println("없는 이름입니다!");   //없는 이름이라고 알려준다.
				  cancel();     //다시 취소하게하기위해 재호출한다.
			  }  //if 문 끝
			  /*가독성을 위해 빈 줄처리*/
			  break;   //case3문이 끝나면 스위치문을 종료시킨다.
		  }  //case 3 문 끝
		}    //스위치문 끝		
	}      //cancel 메소드 끝
}   //class 의 끝
/*가독성을 위해 빈 줄처리*/
public class reservesystem {   //main 메소드의 클래스
	/*가독성을 위해 빈 줄처리*/
	public static void main(String[] args) {  //main 메소드
		Scanner std=new Scanner(System.in);    //입력값을 받아내기위해 선언
		int k;  //예약,조회,취소,끝내기를 선택할수있는 값을가지는 정수
		int personidx=0; //현재 몇명이 입력되었는지 혹은 몇번째 입력인지 확인하는 reservation[] x배열의 인덱스
		/*가독성을 위해 빈 줄처리*/
		/*가독성을 위해 빈 줄처리*/
		reservationmachine[] x=new reservationmachine[30];  //S가 10자리 A가 10자리 B가 10자리  //personidx로 인원수를 조절하며, 객체에 어느값이 들어가든 상관없이, 인원수(personidx)가 30이 되면 더이상 입력을 받지 못한다.
		/*가독성을 위해 빈 줄처리*/
		/*가독성을 위해 빈 줄처리*/
		for(int i=0;i<30;i++)  //객체 배열의 각요소를 인스턴스화 해준다.
		{   //for문의 시작
			x[i]=new reservationmachine();   //i번째 객체를 인스턴스화 실시
		}   //for문의 끝
		/*가독성을 위해 빈 줄처리*/
		System.out.println("명품콘서트홀 예약 시스템입니다.");  //시스템의 알림
		/*가독성을 위해 빈 줄처리*/
		while(true)    //끝내기(메뉴 4)가 입력되기 전까지 무한으로 어느 메뉴를 선택할지 물어보고 메뉴를 실행시킨다
		{   //while문 시작
			System.out.print("예약:1 조회:2 취소:3 끝내기:4>>");   //어느 메뉴를 선택할지 묻는다
			k=std.nextInt();    //원하는 메뉴를 선택해서 입력한다
			if(k<=0 || k>=5)   //만약 없는메뉴를 선택한다면 재 입력하라고 묻는다
			{   //while 문의 if문 시작
				System.out.println("없는 메뉴입니다!");   //존재하지않는 메뉴라고 알린다
				continue;  //while문(메뉴물어보기)를 다시 실시한다.
			}   //while문의 if 문 끝
			/*가독성을 위해 빈 줄처리*/
			switch(k)  //원하는 메뉴를 case별로 진행시킨다
			{     //while문의 스위치문 시작
			  case 1:     //1은 예약
			  {    //case 1문 시작
				  x[personidx++].reserve();  //예약을 함으로써 인원이 한명 늘었으므로, personidx위치에 예약값을 입력하고 personidx를 1증가시켜준다.
				  break; //예약기능이 끝나면 스위치문 종료
			  }   //case 1문 끝
			  case 2:    //2는 조회
			  {  //case 2문 시작
				  x[personidx].listarr();  //2를 입력할시, 현재예약상황을 보여준다.
				  break; //예약조회가 끝나면 스위치문 종료
			  }   //case 2문 끝
			  case 3:    //3은취소
			  {   //case 3문 시작
				 if(personidx==0)  //personidx==0 즉, 텅 빈공간이라면 지울게 없으므로 while문을 다시실행하게 한다.
				 {    //if문 시작
					 System.out.println("잘못된 취소입니다!(취소할 내용이 없음)");  //잘못된 취소라고 알려준다.
					 continue;   //while문(메뉴물어보기)를 다시 실시한다.
				 }   //if문 끝
				 else  //텅 빈 공간이 아니라면 예약을 취소시키고, personidx값을 줄여준다. 그렇게되면 인원을 더 받을수 있게된다. 어차피 각 객체에 어느값이 들어가든 상관없다. static배열이 텅빈 공간인지 가득 찬공간인지만 확인하면 되니까 
				 {    // else 문의 시작
					 x[personidx--].cancel();      //인덱스(사람)를 하나 줄여줌으로써, 한명 더받을수 있게된다
				 }    //else문의 끝
				 break;  //예약취소가 끝나면 스위치문 종료
			  }  //case 3문의 끝
			  case 4:   //4는 끝내기
			  {  //case 4문의 시작
				  System.exit(0);      //시스템 종료
			  }  //case 4문의 끝
			}	//스위치문의 끝
		}	  //while문의 끝 
	}  //메인 메소드의 끝
} //메인 클래스의 끝
