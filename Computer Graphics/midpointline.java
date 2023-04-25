import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.util.Scanner;

import javax.swing.JFrame;



public class midpointline extends JFrame{
	
	private int x0,y0,x1,y1,dx2,dy2;	
	int[] translationx=new int[50];
	int[] translationy=new int[50];
	int cnt=0;
	Scanner std=new Scanner(System.in);
		
	public midpointline(int x0,int y0,int x1,int y1,int dx2,int dy2) {

		if(x0<0) this.x0=(Math.abs(x0)*20+500)-Math.abs(x0)*40;
		else this.x0=x0*20+500;
		if(x1<0) this.x1=(Math.abs(x1)*20+500)-Math.abs(x1)*40;
		else this.x1=x1*20+500;
		if(y0<0) this.y0=(Math.abs(y0)*20+500)-Math.abs(y0)*40;
		else this.y0=y0*20+500;
		if(y1<0) this.y1=(Math.abs(y1)*20+500)-Math.abs(y1)*40;
		else this.y1=y1*20+500;
		
		this.dx2=dx2*20;
		this.dy2=dy2*20;
		
		for(int i=0;i<50;i++) {
			translationx[i]=0;
			translationy[i]=0;
		}
		
		setTitle("Midpoinit Line Algorithm");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container c=getContentPane();
		
		setSize(1000,1000);
		setVisible(true); 
		
	}
	public void paint(Graphics g) {
		g.setColor(Color.black);
		drawmidpoint(g);
	}
	
	private void drawmidpoint(Graphics g) {
		int dx,dy, inCRE,inCRNE,d,x,y;
		Scanner std=new Scanner(System.in);
		
		for(int i=0;i<50;i++) {
			if(i==10) {
				g.drawLine(0, 498, 1000, 498);
				g.drawLine(498, 0, 498, 1000);
				g.drawLine(0, 499, 1000, 499);
				g.drawLine(499, 0, 499, 1000);
				g.drawLine(0, i*20, 1000, i*20);
				g.drawLine(i*20, 0, i*20, 1000);
				g.drawLine(0, 501, 1000, 501);
				g.drawLine(501, 0, 501, 1000);
			}else {
				g.drawLine(0, i*20, 1000, i*20);
				g.drawLine(i*20, 0, i*20, 1000);
			}
		}	
		x=x0;
		y=y0;
		g.fillOval(x-10,(1000-y)-10, 20, 20);
		translationx[cnt]=x-10;
		translationy[cnt++]=(1000-y)-10;
		dx=x1-x0;
		dy=y1-y0;
		if(dx>=0 && dy>=0) { 
			if(dy<dx) {              //기울기 1미만
				d=dy*2-dx;
				inCRE=dy*2;
				inCRNE=(dy-dx)*2;
				while(x<x1) {
					if(d<=0) {
						d+=inCRE;
						x+=20;
					}else {
						d+=inCRNE;
						x+=20;
						y+=20;
					}
					g.fillOval(x-10, (1000-y)-10, 20, 20);
					translationx[cnt]=x-10;
					translationy[cnt++]=(1000-y)-10;
				}
			} else {
				d=dx*2-dy;
				inCRE=dx*2;
				inCRNE=(dx-dy)*2;
				while(y<y1) {
					if(d<=0) {
						d+=inCRE;
						y+=20;
					}else {
						d+=inCRNE;
						x+=20;
						y+=20;
					}
					g.fillOval(x-10, (1000-y)-10, 20, 20);
					translationx[cnt]=x-10;
					translationy[cnt++]=(1000-y)-10;
				}
			}
		} else if(dx<0 && dy<0) {
			if(dy>=dx) {              
				d=dy*2-dx;
				inCRE=dy*2;
				inCRNE=(dy-dx)*2;
				while(x>x1) {
					if(d<=0) {
						d-=inCRE;
						x-=20;
					}else {
						d-=inCRNE;
						x-=20;
						y-=20;
					}
					g.fillOval(x-10, (1000-y)-10, 20, 20);
					translationx[cnt]=x-10;
					translationy[cnt++]=(1000-y)-10;
				}
			} else {
				d=dx*2-dy;
				inCRE=dx*2;
				inCRNE=(dx-dy)*2;
				while(y>y1) {
					if(d<=0) {
						d-=inCRE;
						y-=20;
					}else {
						d-=inCRNE;
						x-=20;
						y-=20;
					}
					g.fillOval(x-10, (1000-y)-10, 20, 20);
					translationx[cnt]=x-10;
					translationy[cnt++]=(1000-y)-10;
				}
			}
		} else if(dx>0 && dy<0) {
			dx=Math.abs(dx);
			dy=Math.abs(dy);
			if(dx>dy) {              //기울기 1이상
				d=dy*2-dx;
				inCRE=dy*2;
				inCRNE=(dy-dx)*2;
				while(x<x1) {
					if(d<=0) {
						d+=inCRE;
						x+=20;
					}else {
						d+=inCRNE;
						x+=20;
						y-=20;
					}
					g.fillOval(x-10, (1000-y)-10, 20, 20);
					translationx[cnt]=x-10;
					translationy[cnt++]=(1000-y)-10;
				}
			} else {
				d=dx*2-dy;
				inCRE=dx*2;
				inCRNE=(dx-dy)*2;
				while(y>y1) {
					if(d<=0) {
						d+=inCRE;
						y-=20;
					}else {
						d+=inCRNE;
						x+=20;
						y-=20;
					}
					g.fillOval(x-10, (1000-y)-10, 20, 20);
					translationx[cnt]=x-10;
					translationy[cnt++]=(1000-y)-10;
				}
			}
		} else if(dx<0 && dy>0) {
			dx=Math.abs(dx);
			dy=Math.abs(dy);
			if(dx>dy) {              //기울기 1이상
				d=dy*2-dx;
				inCRE=dy*2;
				inCRNE=(dy-dx)*2;
				while(x>x1) {
					if(d<=0) {
						d+=inCRE;
						x-=20;
					}else {
						d+=inCRNE;
						x-=20;
						y+=20;
					}
					g.fillOval(x-10, (1000-y)-10, 20, 20);
					translationx[cnt]=x-10;
					translationy[cnt++]=(1000-y)-10;
				}
			} else {
				d=dx*2-dy;
				inCRE=dx*2;
				inCRNE=(dx-dy)*2;
				while(y<y1) {
					if(d<=0) {
						d+=inCRE;
						y+=20;
					}else {
						d+=inCRNE;
						x-=20;
						y+=20;
					}
					g.fillOval(x-10, (1000-y)-10, 20, 20);
					translationx[cnt]=x-10;
					translationy[cnt++]=(1000-y)-10;
				}
			}
		}	
		
		g.setColor(Color.RED);
		
		for(int i=0;i<cnt;i++) {
			g.fillOval(translationx[i]+dx2, translationy[i]-dy2, 20, 20);
		}
	}
	
	public static void main(String[] args) {
		Scanner std=new Scanner(System.in);
		int x0,y0,x1,y1,dx,dy;
		System.out.print("좌표1를 입력하세요():");
		x0=std.nextInt();
		y0=std.nextInt();
		System.out.print("좌표2를 입력하세요():");
		x1=std.nextInt();
		y1=std.nextInt();
		
		
		System.out.print("x를 얼만큼 변경시키고 싶으신가요?: ");
		dx=std.nextInt();
		System.out.print("y를 얼만큼 변경시키고 싶으신가요?: ");
		dy=std.nextInt();
		
		System.out.println(x0+","+y0);
		System.out.println(x1+","+y1);
		
		new midpointline(x0,y0,x1,y1,dx,dy);
	}
}