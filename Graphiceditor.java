import java.util.*;    //util�� ���Ե� Ŭ�������� ����� ������ �����´�.
abstract class Shape   //�߻�Ŭ������ Shape�̴� 
{   //�߻�Ŭ���� ����
	abstract String get();   //�߻�޼ҵ带 �����Ͽ� �������� Ư¡�� ���ǰ��Ѵ�.
}   //�߻�Ŭ���� ��
class Line extends Shape     //Shape�� ��ӹ޴� Line Ŭ����
{    //LineŬ���� ����
	private String name1;    //Line�� �̸��� ����޴� �����̴�    
	public Line()  //Line�� �⺻������
	{   //������ ����
		name1="Line";    //������ڸ��� ������ Line�� �̸� "Line"�� �����Ѵ� 
	}   //������ ��
	String get()     //�ڽ��� �̸��� ��ȯ�ϴ� �޼ҵ�
	{   //get�޼ҵ� ����
		return name1;    //�ڽ��� �̸��� ��ȯ�Ѵ�.
	}   //get�޼ҵ� ��
}   //Line Ŭ������ ��
class Rect extends Shape    //Shape�� ��ӹ޴� RectŬ����
{  //RectŬ������ ����
	private String name2;   //Rect�� �̸��� ����޴� �����̴�
	public Rect()     //Rect�� �⺻������
	{  //������ ����
		name2="Rect";    //������ڸ��� ������ Rect�� �̸� "Rect"�� �����Ѵ�
	}   //������ ��	
	String get()    //�ڽ��� �̸��� ��ȯ�ϴ� �޼ҵ�
	{   //�޼ҵ� ����
		return name2;    //�ڽ��� �̸��� ��ȯ�Ѵ�.
	}   //�޼ҵ� ��
}  //RectŬ������ ��
/*�������� ���� �� �� ó��*/
class Circle extends Shape      //Shape�� ��ӹ޴� Circle Ŭ����
{  //Ciccle�� ����
	private String name3;   //Circle�� �̸��� ����޴� �����̴�
	public Circle()         //Circle�� �⺻������
	{   //������ ����
		name3="Circle";   //������ڸ��� ������ Circle�� �̸� "Circle"�� �����Ѵ�
	}   //������ ��	
	String get()   //�ڽ��� �̸��� ��ȯ�ϴ� �޼ҵ�
	{   //�޼ҵ� ����
		return name3;   //�ڽ��� �̸��� ��ȯ�Ѵ�.
	}   //�޼ҵ� ��
}  //Circle�� ��
public class Graphiceditor {            //����Ŭ����
	public static void main(String[] args) {       //���θ޼ҵ�
		Scanner std=new Scanner(System.in);    //����std�� ��ĳ��Ŭ������ �����Ѵ�.
		Vector<Shape> m=new Vector<Shape>();   //������ ��ü�� �������ش�.
		/*�������� ���� �� �� ó��*/
		System.out.println("�׷��� ������ beauty�� �����մϴ�.");     //���α׷� ������ �˸���.
		while(true)       //���α׷� ���Ḧ �����Ҷ� ���� ���� �ݺ��Ѵ�.
		{   //�ݺ��� ����
			System.out.print("����(1) ����(2) ��κ���(3) ����(4)>>");    //ó�� �����ϸ� �ɼǼ����� �϶�� �˸���.
			int num=std.nextInt();   //�޴� ���� ����
			/*�������� ���� �� �� ó��*/
			switch(num)     //�޴� ���ú����� ����ġ ���� �����Ų��
			{   //����ġ �� ����
			case 1:   //���Լ���
				System.out.print("Line(1) Rect(2) Circle(3)>>");   //��� ������ �Է��Ұ��� �����϶�� �˸���.
				int num2=std.nextInt();    //� ������ �����ϴ����� ���� ����
				/*�������� ���� �� �� ó��*/
				if(num2==1)    //Line�� �����ҽ�
				{  //if�� ����
					Shape a=new Line();   //��ü�� ����� ��ĳ���� ��Ų�� (�������� ����)
					m.add(a);       //���Ϳ� ��ĳ���� �� ��ü�� �����Ѵ�.
				}  //if�� ��
				else if(num2==2)   //Rect�� �����ҽ�
				{  //else if�� ����
					Shape a=new Rect();  //��ü�� ����� ��ĳ���� ��Ų�� (�������� ����)
					m.add(a);      //���Ϳ� ��ĳ���� �� ��ü�� �����Ѵ�.
				}   //else if�� ��
				else if(num2==3)    //Circle�� ������ ��
				{  //else if�� ��
					Shape a=new Circle();  //��ü�� ����� ��ĳ���� ��Ų�� (�������� ����)
					m.add(a);      //���Ϳ� ��ĳ���� �� ��ü�� �����Ѵ�.
				}  //else if�� ��
				break;     //����ġ ���� ������Ű�� �ٽ� �ݺ����� �����Ѵ�
			case 2:       //����
				System.out.print("������ ������ ��ġ>>");     //�����ġ�� ������ �� �����϶�� �˸���.
				int num3=std.nextInt();      //������ ������ ��ġ���� ����
				try    // ������ ����� ������ �״�� �����ϸ� ������, ������ ����� ���� ��ġ�� �����ϸ� ���� �޼ҵ带 �����Ű���ʰ�, catch�� �˸��� ����Ѵ�.
				{  //try�� ����
					m.remove(num3);  //���Ϳ��� num3��ġ�� �����͸� ����
				}  //try�� ��
				catch(ArrayIndexOutOfBoundsException e)   //����(�迭���� ���)�� ��ٸ� �˸��� �˸���
				{    //catch�� ����
					System.out.println("������ �� �����ϴ�.");   //������ ������ �˸���.
				}    //catch�� �� 
				break;    //����ġ ���� ������Ű�� �ٽ� �ݺ����� �����Ѵ�
			case 3:       //������
				Iterator<Shape> it=m.iterator();     //Iterator�� ������ ������ �����̰� �ϰԲ� �����Ѵ�.
				/*�������� ���� �� �� ó��*/
				while(it.hasNext())      //it(������ ����)�� �������� ���������� �ݺ�
				{   //�ݺ��� ����
					Shape b=it.next();     //b���ٰ� �ش� it�� ��ü���� ��ȯ�Ѵ�.
					System.out.println(b.get());    //��ĳ���� �� b�� �̿��� �������� Ư¡�� ��� get()�޼ҵ带 ������� �ٷ½�Ų��.
				}  //�ݺ��� ��
				break;   //����ġ ���� ������Ű�� �ٽ� �ݺ����� �����Ѵ�
			case 4:      //����
				System.out.println("beauty�� �����մϴ�.");   //���α׷��� ������� �˸���.
				System.exit(0);    //���α׷� ����
			}  //����ġ �� ��
		}  //�ݺ��� ��
	} //���� �޼ҵ��� ��
}     //���� Ŭ������ ��
