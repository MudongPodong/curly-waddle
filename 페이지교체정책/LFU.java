

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class LFU extends JFrame {                          //order= 페이지에 있었던 시간 order2=참조가 가장 많은거
	
	JLabel str1=new JLabel("Reference String");
	JLabel str2=new JLabel("#Frame");
	JLabel hitstr1=new JLabel("Hit횟수:");
	JLabel pfstr1=new JLabel("PageFault횟수:");
	JLabel migratestr1=new JLabel("Migrate횟수:");
	JLabel hitstr2=new JLabel();
	JLabel pfstr2=new JLabel();
	JLabel migratestr2=new JLabel();
	JLabel pfrate1=new JLabel("Page Fault Rate(%):");
	JLabel pfrate2=new JLabel();
	JLabel runtime=new JLabel("실행시간:");
	JLabel runtime2=new JLabel();
	
	JTextField str11=new JTextField();
	JTextField str22=new JTextField("4");
	JButton run=new JButton("Run");
	JButton random=new JButton("Random");
	String[] targetstr;
	Frame[] frame;
	
	int framenum;
	MyPanel panel=new MyPanel();

	JScrollPane scroll=new JScrollPane();
	Dimension size=new Dimension();
	CirclePanel panel2=new CirclePanel();
	
    ArrayList<Result> result=new ArrayList<Result>();
    ArrayList<Double> rate=new ArrayList<>();
	
	double hitcnt=0;
	double pagefault=0;
	double migrate=0;
	int ordernum=0;
	int time=0;
	Random rd=new Random();
	
	public LFU() {
		setTitle("LFU-21812141");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container c=getContentPane();
		setLayout(null);
	   
		size.setSize(1500,1000);
		panel.setPreferredSize(size);
		
		random.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				String makingstr=rd.ints(48,123).limit(20)
				.filter(i -> (i<=57 || i>=65) && (i<=90 || i>=97))
				.collect(StringBuilder::new,StringBuilder::appendCodePoint,StringBuilder::append)
				.toString()
				.toUpperCase();
				
				str11.setText(makingstr);
				
			}
		});
		
		run.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				init();
				panel2.removeAll();
				framenum=Integer.parseInt(str22.getText());      
				frame=new Frame[framenum];
				int max=0;
				int min=0;
				int point=0;
				Result r=null;
			
				for(int i=0;i<frame.length;i++) {
					frame[i]=new Frame();
					frame[i].setstr("-");   
					frame[i].setorder(0);
					frame[i].setorder2(0);
				}
				
				targetstr=str11.getText().split("");
				for(int i=0;i<targetstr.length;i++) {   //문자열 돌려보기
					point=0;
					for(int j=0;j<framenum;j++) {       //hit appear
						if(targetstr[i].equals(frame[j].getstr())) {	
							frame[j].setorder(0);
							frame[j].setorder2(frame[j].getorder2()+1);
							hitcnt++;
							time+=200;
							point=1;
							break;
						}
					}
					if(point==0) {
						for(int j=0;j<framenum;j++) {    //hit에 실패하고 아직 빈공간이 있을때
							if(frame[j].getstr().equals("-")) {    
								frame[j].setstr(targetstr[i]);
								frame[j].setorder(0);
								frame[j].setorder2(0);								
								pagefault++;
								time+=2000;
								point=2;
								break;
							}
						}
					}
					if(point==0) {
						min=frame[0].getorder2();
						max=frame[0].getorder();
						for(int j=0;j<framenum;j++) {   //hit에 실패하고 빈공간도 없을때
							if(min>frame[j].getorder2() || (min==frame[j].getorder2() && max<frame[j].getorder())) {
								min=frame[j].getorder2();
								max=frame[j].getorder();
							}
						}
						for(int j=0;j<framenum;j++) {
							if(frame[j].getorder2()==min && frame[j].getorder()==max) {
								frame[j].setstr(targetstr[i]);
								frame[j].setorder(0);
								frame[j].setorder2(0);
							    migrate++;
							    time+=2000;
							    point=3;
							    break;
							}
						}	
					}
					
					r=new Result();
					r.a=new Frame[framenum];
					for(int k=0;k<framenum;k++) {
						r.a[k]=new Frame();
					}
					for(int k=0;k<framenum;k++) {
						r.a[k].setstr(frame[k].getstr());
					}
					if(point==1) r.setstate("Hit");
					else if(point==2) r.setstate("P/F");
					else if(point==3) r.setstate("Migrate");
					
					r.setinput(targetstr[i]);
					result.add(r);
					
					for(int j=0;j<framenum;j++) {
						if(!frame[j].getstr().equals("-")) {
							frame[j].setorder(frame[j].getorder()+1);
						}
					}
				}
				runtime2.setText(Integer.toString(time));
				panel.setArray(result);
				panel.removeAll();
				panel.repaint();

				rate.add(hitcnt);
				rate.add(pagefault);
				rate.add(migrate);
				
				hitstr2.setText(Integer.toString((int)Math.round(hitcnt)));
				pfstr2.setText(Integer.toString((int)Math.round(pagefault)));
				migratestr2.setText(Integer.toString((int)Math.round(migrate)));
				pfrate2.setText(Double.toString(Math.round(((pagefault+migrate)/(hitcnt+pagefault+migrate))*10000)/100.0)+"%");
				panel2.setrate(rate);
				panel2.removeAll();
				panel2.repaint();
				
			}
		});
		random.setSize(90,40);
		run.setSize(90,40);
		random.setLocation(10,10);
		run.setLocation(110,10);
		str1.setSize(100,20);
		str1.setLocation(210,10);
		str2.setSize(100,20);
		str2.setLocation(520,10);
		str11.setSize(300,20);
		str11.setLocation(210,30);
		str22.setSize(80,20);
		str22.setLocation(520,30);
		hitstr1.setSize(140,20);
		pfstr1.setSize(140,20);
		migratestr1.setSize(140,20);
		hitstr2.setSize(80,20);
		pfstr2.setSize(80,20);
		migratestr2.setSize(80,20);
		hitstr1.setLocation(170,400);
		pfstr1.setLocation(170,430);
		migratestr1.setLocation(170,460);
		hitstr2.setLocation(320,400);
		pfstr2.setLocation(320,430);
		migratestr2.setLocation(320,460);
		pfrate1.setSize(140,20);
		pfrate1.setLocation(170,520);
		pfrate2.setSize(80,20);
		pfrate2.setLocation(320,520);
		runtime.setSize(140,20);
		runtime.setLocation(420,520);
		runtime2.setSize(80,20);
		runtime2.setLocation(500,520);
		
		scroll.setViewportView(panel);
		scroll.setBounds(10,100,600,250);
		panel.setBounds(10,400,150,150);
		panel2.setBounds(10,400,150,300);
		
		add(random);
		add(run);
		add(str1);
		add(str11);
		add(str2);
		add(str22);
		add(scroll);
		add(panel2);
		add(hitstr1);
		add(pfstr1);
		add(migratestr1);
		add(hitstr2);
		add(pfstr2);
		add(migratestr2);
		add(pfrate1);
		add(pfrate2);
		add(runtime);
		add(runtime2);
			
		setSize(700,700);
		setVisible(true);
	}
	void init() {
		hitcnt=0;
		pagefault=0;
		migrate=0;
		framenum=0;
		ordernum=0;	
		targetstr=null;
		frame=null;	
		result.clear();
		rate.clear();
		time=0;
	}
	public static void main(String[] args) {
		new LFU();     //70120304230321201701
	} 
}
