import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Frame {
	private String a;
	private int order;
	private int order2;
	
	public Frame() {}
	void setstr(String a) {
		this.a=a;
	}
	String getstr() {
		return a;
	}
	
	void setorder(int order) {
		this.order=order;
	}
	int getorder() {
		return order;
	}
	void setorder2(int order2) {
		this.order2=order2;
	}
	int getorder2() {
		return order2;
	}

}
class Result{
	Frame[] a;
	String inputstr;
	String state;
	
	void setframe(Frame[] a) {
		this.a=a;
	}
	void setinput(String inputstr) {
		this.inputstr=inputstr;
	}
	String getinput() {
		return inputstr;
	}
	
	void setstate(String state) {
		this.state=state;
	}
	String getstate() {
		return state;
	}
}

class MyPanel extends JPanel{
	ArrayList<Result> a=new ArrayList<Result>();
	public MyPanel() {}
	public void setArray(ArrayList<Result> a) {
		this.a=a;
	}	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		int interval=10;
		int rectwidth=50,rectheight=50;
		int finish=0;
		
		for(Result i: a) {		
			g.drawString(i.getinput(),interval+20,10);			
			for(int k=0;k<i.a.length;k++) {
				g.drawRect(interval,k*50+10,rectwidth,rectheight);
				g.drawString(i.a[k].getstr(),interval+20,30+50*k);
				finish=k*50+10+rectheight;
			}
			g.drawString(i.getstate(), interval+10, finish+10);
			interval+=50; 
		}
	}
}
class CirclePanel extends JPanel{
	ArrayList<Double> a=new ArrayList<>();
	public CirclePanel() {}
	public void setrate(ArrayList<Double> a) {
		this.a=a;		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.BLACK);
		int start=0;
		int p=0;

		for(Double i:a) {
			int k=(int)Math.round(i);
			g.drawRect(10, start, 50, k*10);
			if(p==0 && i!=0) g.drawString("hit:"+k, 20, start+10);
			if(p==1 && i!=0) g.drawString("P/F:"+k, 20, start+10);
			if(p==2 && i!=0) g.drawString("Migrate:"+k, 10, start+10);
			p++;
			start+=k*10;
		}
	}
}
