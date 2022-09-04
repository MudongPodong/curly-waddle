import java.awt.*;        //awt�� Ŭ������ ����ϱ����� ����
import java.awt.event.*;  //awt�� �̺�Ʈó���� ���� ����
import javax.swing.*;     //���� ���� Ŭ������ ����ϱ����� ����
import java.util.*;       //����ó�� �� ����ϱ����� ����
public class JSliderTextarea extends JFrame{        //����Ŭ����(��ü)�� JFrame�� ��ӹ޴´�
	private int location=0;     //�����̴��� �������� ����޴� ����
	private int point=0;        //���� ��������(�������ڿ��� �������κ�)
	public JSliderTextarea()    //���� Ŭ������ ������
	{  //����Ŭ���� ������ ����
		setTitle("JAVA Assignment-3 hakbun:21812141");    //â ������ "JAVA Assignment"�� ����
		setDefaultCloseOperation(EXIT_ON_CLOSE);   //'x'(â �ݱ�)�� ������ ���α׷� �����Ű��
		Container c=getContentPane();      //�����̳� ����
		setSize(500,500);                  //â ����� ����500,����500�� ���� 
		c.setLayout(new BorderLayout());   //���̾ƿ� ��ġ�� BorderLayout���� ����
		JTextArea ta=new JTextArea(10,10); //�ؽ�Ʈarea�� ����
		JSlider sl=new JSlider(0,100,0);   //�����̴��� 0~100������ �����ϰ� ������ġ�� 0���� ����
		sl.setPaintLabels(true);           //���̺��� ���̰��Ѵ�
		sl.setPaintTicks(true);            //tick�� ���̰��Ѵ�
		sl.setPaintTrack(true);            //track�� ���̰��Ѵ�
		sl.setMajorTickSpacing(10);        //ū ���� ������ 10���� ����
		sl.setMinorTickSpacing(1);         //���� ���� ������ 1�� ����
		c.add(BorderLayout.NORTH,ta);      //�ؽ�Ʈarea�� ���ʿ� ��ġ
		c.add(BorderLayout.CENTER,sl);     //�����̴��� ���Ϳ� ��ġ
/*�������� ���� �� �� ó��*/			
		Stack<String> stk=new Stack<String>();    //���� ���ڵ��� �����ų ����		
		ta.addKeyListener(new KeyAdapter(){       //�ؽ�Ʈarea�� Ű����� �ԷµǾ������� ���� ����
			public void keyPressed(KeyEvent e)    //Ű�� ������ ������ �Ͼ���Ѵ�
			{    //keyPressed�޼ҵ� ����
				try                               //����ó���� ���� ����( ���ڿ� ũ�⸦ ������� ����)
				{  //try�� ����
					if(ta.getText().length()==100) //���� �ؽ�Ʈarea�� ���ڿ��� ���̰� 100�� �ȴٸ� ������ ���ϰ��Ѵ�
					{ //if������
						ta.setEditable(false);	   //�����Ұ������� ����	
					} //if�� ��		
					if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE)   //���� �齺���̽��� ������ ���ÿ� ���� ���ڸ� �ְ� ����ġ(location)�� ���ҽ�Ų��
					{ //if������				
						stk.push(ta.getText().substring(location-1,location));    //���������ڸ� ���ÿ� �־��ش�
						if(stk.size()==1)        //���� ù ������ �̷������ �� ������ �����س��´�.
						{  //if�� ����
							point=location;      //���ڿ��� �������κ��� �����س��´�
						}  //if�� ��
						location--;  //�� ��ġ ����
						System.out.print(stk.peek()+"�� ���� ");   //�����ߴٰ� �˸�
						System.out.println(stk.size()+", "+location); //���� ����ũ��� �� �����̵� ��ġ�� �˷��ش�			 			
					} //if�� ��
					else    //�� �̿ܿ� Ű����� �ٸ��� �Է¹ް� �ȴٸ� ����ġ�� ������Ų��
					{ //else�� ����
						if(stk.size()>0)                  //���� ������� �ٷ� ������ ���ϰ� �� ���¿��� ���𰡸� Ű����� �ۼ��Ѵٸ� ������ ����ش�
						{  //if�� ����
							stk.clear();                  //������ ����ش�
						}  //if�� ��*/
						if(e.getKeyCode()==KeyEvent.VK_SHIFT)    //���� ����Ʋ ���̾��� ���ڸ� �ִ´ٸ� location���� 2�� �ö󰡰ԵǴµ� �̸� �����ϱ����� �ϳ� ���ش�. 
						{  //if�� ����
							location--;                   //location�� ����
							point--;                      //point�� ����
						}  //if�� ��
						location++;        //�� ��ġ ����
						point=location;    //�� ������ ����ġ�� �����Ѵ�(���� �Է½�)
						System.out.println(stk.size()+", "+location);   //���� ���û������ �� �����̵��� ��ġ�� ����Ѵ�.
					} //else�� ��
					sl.setValue(location);	 //����� location���� �����̵带 �����Ѵ�
				}  //try�� ��
				catch(StringIndexOutOfBoundsException a)  //���� ���ڿ��� ������ ����ٸ� ���� ó��
				{  //catch�� ����
					System.out.println("������ ������ϴ�!");  //������ ����ٰ� ����
				}  //catch�� ��
				catch(EmptyStackException b)    //���� ���ڿ��� ������ ����ٸ� ����ó��
				{  //catch�� ����
					System.out.println("������ ������ϴ�!");  //������ ����ٰ� ����
				}  //catch�� ��
			}  //keyPressed �޼ҵ� ��
		});    //�͸� Ŭ���� ���� ��
/*�������� ���� �� �� ó��*/		
		sl.addMouseMotionListener(new MouseAdapter(){  //�����̴��� ���콺�� �����϶��� ���� ����
			public void mouseDragged(MouseEvent e)     //���콺�� �巡���ϸ� ���ϰ� �Ѵ�
			{  //mouseDragged�޼ҵ� ����
				try {  //����ó���� ���� ����
					int m=sl.getValue();    //�̵���Ų ���簪
					if(m<location)    //�����̴��� �������� �̵���ų��
					{  //if�� ����
						stk.push(ta.getText().substring(location-1,location));  //�����(����������)�� ���ÿ� ����ִ´�
						if(stk.size()==1)        //���� ù ������ �̷������ �� ������ �����س��´�.
						{  //if�� ����
							point=location;      //���ڿ��� �������κ��� �����س��´�
						}  //if�� ��
						String text=ta.getText().substring(0,m);   //���������ڸ� ���ܽ�Ű�� text�� ����
						ta.setText(text);                          //�ؽ�Ʈarea�� ���Ϲ��ڿ�����
						System.out.println(stk.size()+", "+location);   //���� ���û������ �� �����̵��� ��ġ�� ����Ѵ�.     
						location=m;                                //location�� �ٲ� �����̴������� �ٲ���Ѵ�
					}  //if�� ��
					else if(m>location)    //�����̴��� ���������� �̵���ų��
					{  //else if�� ����
						if(m>point)         //���� ù ���������� �Ѿ�� �ȴٸ� �� ��ġ�� �ٽ� ù �����κ����� ���, �����̴� ƽ�� ������ �������� ���´�
						{  //if�� ����
							location=point; //�� ��ġ�� �ٽ� ù�����κ����� ����
							sl.setValue(point); //�����̴� ƽ�� ù�����κ����� ����
						}  //if�� ��
						String txt1=stk.pop();    //���� �ֱٿ� ���� ���ڸ� ���ÿ��� ������ txt1������
						String txt2=ta.getText(); //���� �ؽ�Ʈarea�� ���ڿ��� txt2�� ����
						ta.setText(txt2+txt1);    //�ؽ�Ʈarea�� txt2�� ���� �����ϰ� txt1�� �״������� �����Ų��.							
						System.out.println(stk.size()+", "+location);   //���� ���û������ �� �����̵��� ��ġ�� ����Ѵ�.
						location=m;               //location�� �ٲ� �����̴������� �ٲ���Ѵ�
					}  //else if�� ��
				}   //try�� ��
				catch(StringIndexOutOfBoundsException a){   //���� ���ڿ��� ������ ����ٸ� ���� ó��
					System.out.println("������ ������ϴ�!");  //������ ����ٰ� ����
				}   //catch�� ��
				catch(EmptyStackException b){               //���� ���ڿ��� ������ ����ٸ� ���� ó��
					System.out.println("������ ������ϴ�!");  //������ ����ٰ� ����
				}	//catch�� ��		
			}  //mouseDragged�޼ҵ� ��		    
			});  //�͸�Ŭ���� ���� ��
		setVisible(true);     //â�� ���̰��Ѵ�.
	}    //������ ��
	public static void main(String[] args) {    //���θ޼ҵ� ����
		new JSliderTextarea();                  //��ü ����
	}                                           //���θ޼ҵ� ��                          
}                                               //Ŭ���� ��
