import java.awt.*;        //awt의 클래스를 사용하기위해 선언
import java.awt.event.*;  //awt의 이벤트처리를 위해 선언
import javax.swing.*;     //스윙 관련 클래스를 사용하기위해 선언
import java.util.*;       //예외처리 등 사용하기위해 선언
public class JSliderTextarea extends JFrame{        //메인클래스(객체)를 JFrame을 상속받는다
	private int location=0;     //슬라이더의 기존값을 저장받는 변수
	private int point=0;        //삭제 시작지점(원래문자열의 마지막부분)
	public JSliderTextarea()    //메인 클래스의 생성자
	{  //메인클래스 생성자 시작
		setTitle("JAVA Assignment-3 hakbun:21812141");    //창 제목을 "JAVA Assignment"로 설정
		setDefaultCloseOperation(EXIT_ON_CLOSE);   //'x'(창 닫기)를 누르면 프로그램 종료시키기
		Container c=getContentPane();      //컨테이너 선언
		setSize(500,500);                  //창 사이즈를 가로500,세로500로 설정 
		c.setLayout(new BorderLayout());   //레이아웃 위치를 BorderLayout으로 설정
		JTextArea ta=new JTextArea(10,10); //텍스트area를 설정
		JSlider sl=new JSlider(0,100,0);   //슬라이더를 0~100범위로 설정하고 시작위치를 0으로 설정
		sl.setPaintLabels(true);           //레이블을 보이게한다
		sl.setPaintTicks(true);            //tick을 보이게한다
		sl.setPaintTrack(true);            //track을 보이게한다
		sl.setMajorTickSpacing(10);        //큰 눈금 간격을 10으로 지정
		sl.setMinorTickSpacing(1);         //작은 눈금 간격을 1로 지정
		c.add(BorderLayout.NORTH,ta);      //텍스트area를 위쪽에 위치
		c.add(BorderLayout.CENTER,sl);     //슬라이더를 센터에 위치
/*가독성을 위한 빈 줄 처리*/			
		Stack<String> stk=new Stack<String>();    //지운 문자들을 저장시킬 스택		
		ta.addKeyListener(new KeyAdapter(){       //텍스트area에 키보드로 입력되었을때를 위해 선언
			public void keyPressed(KeyEvent e)    //키를 누를때 반응이 일어나게한다
			{    //keyPressed메소드 시작
				try                               //예외처리를 위해 선언( 문자열 크기를 벗어날때를 위해)
				{  //try문 시작
					if(ta.getText().length()==100) //만약 텍스트area의 문자열의 길이가 100이 된다면 편집을 못하게한다
					{ //if문시작
						ta.setEditable(false);	   //편집불가능으로 선언	
					} //if문 끝		
					if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE)   //만약 백스페이스를 누르면 스택에 지운 문자를 넣고 현위치(location)을 감소시킨다
					{ //if문시작				
						stk.push(ta.getText().substring(location-1,location));    //마지막문자를 스택에 넣어준다
						if(stk.size()==1)        //만약 첫 삭제가 이루어지면 그 지점을 저장해놓는다.
						{  //if문 시작
							point=location;      //문자열의 마지막부분을 저장해놓는다
						}  //if문 끝
						location--;  //현 위치 감소
						System.out.print(stk.peek()+"를 저장 ");   //저장했다고 알림
						System.out.println(stk.size()+", "+location); //현재 스택크기와 현 슬라이드 위치를 알려준다			 			
					} //if문 끝
					else    //그 이외에 키보드로 다른걸 입력받게 된다면 현위치를 증가시킨다
					{ //else문 시작
						if(stk.size()>0)                  //만약 지우고나서 바로 복구를 안하고 그 상태에서 무언가를 키보드로 작성한다면 스택을 비워준다
						{  //if문 시작
							stk.clear();                  //스택을 비워준다
						}  //if문 끝*/
						if(e.getKeyCode()==KeyEvent.VK_SHIFT)    //만약 쉬프틀 갈이쓰는 문자를 넣는다면 location값이 2번 올라가게되는데 이를 방지하기위해 하나 弧娩. 
						{  //if문 시작
							location--;                   //location값 감소
							point--;                      //point값 감소
						}  //if문 끝
						location++;        //현 위치 증가
						point=location;    //끝 지점을 현위치와 같게한다(문자 입력시)
						System.out.println(stk.size()+", "+location);   //현재 스택사이즈와 현 슬라이드의 위치를 출력한다.
					} //else문 끝
					sl.setValue(location);	 //변경된 location으로 슬라이드를 조절한다
				}  //try문 끝
				catch(StringIndexOutOfBoundsException a)  //만약 문자열의 범위를 벗어난다면 예외 처리
				{  //catch문 시작
					System.out.println("범위를 벗어났습니다!");  //범위를 벗어났다고 공지
				}  //catch문 끝
				catch(EmptyStackException b)    //만약 문자열의 범위를 벗어난다면 예외처리
				{  //catch문 시작
					System.out.println("범위를 벗어났습니다!");  //범위를 벗어났다고 공지
				}  //catch문 끝
			}  //keyPressed 메소드 끝
		});    //익명 클래스 선언 끝
/*가독성을 위한 빈 줄 처리*/		
		sl.addMouseMotionListener(new MouseAdapter(){  //슬라이더를 마우스로 움직일때를 위해 선언
			public void mouseDragged(MouseEvent e)     //마우스를 드래그하면 변하게 한다
			{  //mouseDragged메소드 시작
				try {  //예외처리를 위한 선언문
					int m=sl.getValue();    //이동시킨 현재값
					if(m<location)    //슬라이더를 왼쪽으로 이동시킬때
					{  //if문 시작
						stk.push(ta.getText().substring(location-1,location));  //지운문자(마지막문자)를 스택에 집어넣는다
						if(stk.size()==1)        //만약 첫 삭제가 이루어지면 그 지점을 저장해놓는다.
						{  //if문 시작
							point=location;      //문자열의 마지막부분을 저장해놓는다
						}  //if문 끝
						String text=ta.getText().substring(0,m);   //마지막문자를 제외시키고 text에 저장
						ta.setText(text);                          //텍스트area에 빠귄문자열저장
						System.out.println(stk.size()+", "+location);   //현재 스택사이즈와 현 슬라이드의 위치를 출력한다.     
						location=m;                                //location을 바뀐 슬라이더값으로 바뀌게한다
					}  //if문 끝
					else if(m>location)    //슬라이더를 오른쪽으로 이동시킬때
					{  //else if문 시작
						if(m>point)         //만약 첫 삭제지점을 넘어가게 된다면 현 위치를 다시 첫 삭제부분으로 잡고, 슬라이더 틱도 마지막 지점으로 놓는다
						{  //if문 시작
							location=point; //현 위치를 다시 첫삭제부분으로 지정
							sl.setValue(point); //슬라이더 틱을 첫삭제부분으로 지정
						}  //if문 끝
						String txt1=stk.pop();    //가장 최근에 지운 문자를 스택에서 꺼내서 txt1에저장
						String txt2=ta.getText(); //현재 텍스트area의 문자열을 txt2에 저장
						ta.setText(txt2+txt1);    //텍스트area에 txt2를 먼저 저장하고 txt1을 그다음으로 저장시킨다.							
						System.out.println(stk.size()+", "+location);   //현재 스택사이즈와 현 슬라이드의 위치를 출력한다.
						location=m;               //location을 바뀐 슬라이더값으로 바뀌게한다
					}  //else if문 끝
				}   //try문 끝
				catch(StringIndexOutOfBoundsException a){   //만약 문자열의 범위를 벗어난다면 예외 처리
					System.out.println("범위를 벗어났습니다!");  //범위를 벗어났다고 공지
				}   //catch문 끝
				catch(EmptyStackException b){               //만약 문자열의 범위를 벗어난다면 예외 처리
					System.out.println("범위를 벗어났습니다!");  //범위를 벗어났다고 공지
				}	//catch문 끝		
			}  //mouseDragged메소드 끝		    
			});  //익명클래스 선언 끝
		setVisible(true);     //창을 보이게한다.
	}    //생성자 끝
	public static void main(String[] args) {    //메인메소드 선언
		new JSliderTextarea();                  //객체 실행
	}                                           //메인메소드 끝                          
}                                               //클래스 끝
