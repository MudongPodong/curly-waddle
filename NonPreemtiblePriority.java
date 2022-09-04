
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class NonPreemtiblePriority extends JFrame {
	JButton b1,b2;
	JTable jt1,jt2;
	JPanel p;
	JLabel printavg=new JLabel();
	JLabel printruntime=new JLabel();
	JLabel printswitiching=new JLabel();
	MyPanel5 panel=new MyPanel5();

	int PresentTime=0;

	public NonPreemtiblePriority() {
		setTitle("NonPreemtiblePriority-21812141");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container c=getContentPane();
		setLayout(null);
		b1=new JButton("Open File");
		b2=new JButton("Run");

		String header1[]= new String[]{"Process","ArriveTime","BurstTime","Priority"};
		String header2[]=new String[] {"Process","BurstTime","ResponseTime","TurnaroundTime","WaitingTime"};
 
		ArrayList<Process> pa=new ArrayList<Process>();
		 DefaultTableModel model1=new DefaultTableModel(header1,0);
		 jt1=new JTable(model1);
		 JScrollPane sp=new JScrollPane(jt1);
		 sp.setBounds(10,10,300,100);

		 DefaultTableModel model2=new DefaultTableModel(header2,0);
		 jt2=new JTable(model2);
		 JScrollPane sp2=new JScrollPane(jt2);
		 sp2.setBounds(10,120,450,100);
 
		 b1.addActionListener(new ActionListener() {	 
	            @Override
	            public void actionPerformed(ActionEvent e) {     	
	        		try {
	        			JFileChooser fc=new JFileChooser();
		        		int returnval=fc.showOpenDialog(fc);
		        		String filepath=fc.getSelectedFile().getPath();
		        		File f=new File(filepath);

		        		String str=null;
		        		StringTokenizer st=null;
		        		FileReader fr=new FileReader(f);
		        		BufferedReader reader=new BufferedReader(fr);
 
		        		while((str=reader.readLine())!=null) {
		        			st=new StringTokenizer(str," ");
		        			st.nextToken();
		        			Process a=new Process();

		        			int cnt=0;
		        			while(st.hasMoreTokens()) {
		        				if(cnt==0) a.setProcessID(Integer.parseInt(st.nextToken()));
		        				else if(cnt==1) a.setArrivetime(Integer.parseInt(st.nextToken()));
		        				else if(cnt==2) a.setBursttime(Integer.parseInt(st.nextToken()));
		        				else if(cnt==3) a.setPriority(Integer.parseInt(st.nextToken()));
		        				cnt++;
		        			}
		        			pa.add(a);
		        			String[] rows=new String[4];
			        		rows[0]=Integer.toString(a.getProcessID());
			        		rows[1]=Integer.toString(a.getArrivetime());
			        		rows[2]=Integer.toString(a.getBursttime());
			        		rows[3]=Integer.toString(a.getPriority());
			        		model1.addRow(rows);
		        		}	
	        		} catch(IOException k) {
	        			System.out.println("파일을 제대로 불러오지 못했습니다");
	        		} catch (NullPointerException j){
	        			System.out.println("아무파일도 선택하지 않았습니다");
	        		}
	            }
	        });

		 b2.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 PriorityQueue<Process> queue=new PriorityQueue<>(new Comparator<Process>() {
					 public int compare(Process ob1,Process ob2) {
						 return ob1.getPriority()<=ob2.getPriority() ? 1: -1;   //우선순위 내림차순
					 }
				 });
				 int min=pa.get(0).getArrivetime();
				 int cnt=0,removeidx=0;
				 double avg=0;
				 Process k=new Process();
				 Result r=null;

				 ArrayList<Result> result=new ArrayList<Result>();
				 ArrayList<Integer> ganttorder=new ArrayList<Integer>();

				 for(Process i: pa) {
					 if(min>i.getArrivetime()) {
						 min=i.getArrivetime();	
						 k.setProcessID(i.getProcessID());
						 k.setArrivetime(i.getArrivetime());
						 k.setBursttime(i.getBursttime());
						 k.setPriority(i.getPriority());
						 removeidx=pa.indexOf(i);
						 cnt++;
					 }
				 }
				 if(cnt==0) {
					 k.setProcessID(pa.get(0).getProcessID());
					 k.setArrivetime(pa.get(0).getArrivetime());
					 k.setBursttime(pa.get(0).getBursttime());
					 k.setPriority(pa.get(0).getPriority());
				 }
				 pa.remove(removeidx);
				 queue.add(k);
				 PresentTime+=queue.peek().getArrivetime();
 
				 cnt=0;
				 int pacount=0;
				 int emptycheck=0;
				 
				 while(!queue.isEmpty()|| pacount!=pa.size()) {
					 Process a=queue.remove();
					 for(int i=1;i<=a.getBursttime();i++) {   
						 if(i==1) a.setStartP(PresentTime);
						 for(Process p:pa) {          
							 if(PresentTime==p.getArrivetime()) {
								 pacount++;
								 queue.add(p);
							 }
						 }
						 PresentTime++;
					 }					 
					 if(queue.isEmpty()) PresentTime++;
					 if(queue.isEmpty() && pacount==pa.size()) PresentTime--;					 
					 
					 r=new Result();
					 r.setStartP(PresentTime);
					 r.setProcessID(a.getProcessID());
					 r.setArriveTime(a.getArrivetime());
					 r.setBursttime(a.getBursttime());
					 r.setResponsetime(a.getStartP()-a.getArrivetime());
					 r.setWaitingtime(a.getStartP()-a.getArrivetime());
					 r.setTurnaroundtime(a.getBursttime()+a.getStartP()-a.getArrivetime()); //실행시간+대기시간(시작시간-도착시간)
					 r.setStartP(r.getWaitingtime()+r.getArriveTime());

					 result.add(r);
					 avg+=r.getWaitingtime();
					 cnt++;
				 }

				 printswitiching.setText("스위칭 횟수:"+Integer.toString(cnt-1));
				 
				 cnt=0;
				 String[] rows=new String[5];
				 for(Result i:result) {
					 rows[0]=Integer.toString(i.getProcessID());
					 rows[1]=Integer.toString(i.getBursttime());
					 rows[2]=Integer.toString(i.getResponsetime());
					 rows[3]=Integer.toString(i.getTurnaroundtime());
					 rows[4]=Integer.toString(i.getWaitingtime());
					 
					 ganttorder.add(i.getBursttime());
					 model2.addRow(rows);
					 cnt++;
				 }
				 avg/=cnt;
				printavg.setText("평균대기시간:"+Double.toString(avg));
				printruntime.setText("전체 실행시간:"+Integer.toString(PresentTime));

				panel.setArray(result);
				panel.removeAll();
				panel.repaint();	
			 }
		 });

		panel.setLayout(null);
		panel.setBounds(10,285,1000,110);
	    printavg.setSize(200,40);
		printavg.setLocation(10,220); 
		printruntime.setSize(100,40);
		printruntime.setLocation(10,235);
		printswitiching.setSize(100,40);
		printswitiching.setLocation(10,250);
		b1.setSize(100,60);
		b2.setSize(100,60);
		b1.setLocation(30,800);
		b2.setLocation(140,800);
		add(b1);
		add(b2);
		add(sp);
		add(sp2);
		add(printavg);
		add(printruntime);
		add(printswitiching);
		add(panel);
		setSize(1000,1000);
		setVisible(true);
	}
	public static void main(String[] args) {
		new NonPreemtiblePriority();
	}
}

class MyPanel5 extends JPanel{
	int count=0;
	ArrayList<Result> a=new ArrayList<Result>();
	private LineBorder bb=new LineBorder(Color.black,1,true);
	public MyPanel5() {}
	public void setArray(ArrayList<Result> a) {
		this.a=a;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		int finish=0;
		int rememberfinalburst=0;

		for(Result i: a) {
			
			JLabel process=new JLabel("p"+Integer.toString(i.getProcessID()));
			process.setBounds(finish*10+10,0,60,50);
			add(process);
			
			JLabel burstprocess=new JLabel(Integer.toString(i.getBursttime())+"초");
			burstprocess.setBounds(finish*10+10,10,60,50);
			add(burstprocess);
			
			JLabel runtime=new JLabel(Integer.toString(i.getWaitingtime()+i.getArriveTime()));
			runtime.setBounds(finish*10+5,35,60,50);
			add(runtime);
			
			JLabel waitingtime=new JLabel("wait:"+Integer.toString(i.getWaitingtime()));
			waitingtime.setBounds(finish*10,45,60,50);
			add(waitingtime);

			g.drawRect(finish*10+10, 0, i.getBursttime()*10, 50);
			finish+=i.getBursttime();
			rememberfinalburst=i.getBursttime();
		}
		JLabel lb1=new JLabel("<---wait");
		lb1.setBounds(finish*10+30,45,60,50);
		add(lb1);
		
		JLabel lb2=new JLabel("<---starttime");
		lb2.setBounds(finish*10+30,35,80,50);
		add(lb2);
	}
}