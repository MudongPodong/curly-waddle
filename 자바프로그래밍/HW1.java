
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

abstract class AbstractSort{
	
	public static void sort(Comparable[] a) {};
	
	protected static boolean less(Comparable v,Comparable w) {
		if(v!=null) return v.compareTo(w)<0;
		
		return false;
	}
	protected static void exch(Comparable[] a,int i,int j) {
		Comparable t=a[i];
		a[i]=a[j];
		a[j]=t;
	}
	protected static void show(Comparable[] a) {
		for(int j=0;j<a.length;j++) {
			if(j==10) {
				System.out.println("");
			}
			System.out.println(a[j]+" ");
		}
	}
	protected static boolean isSorted(Comparable[] a) {
		for(int i=1;i<a.length;i++) {
			if(less(a[i],a[i-1])) {
				return false;
			}
		}
		return true;
	}
}

class Selection extends AbstractSort{
	
	public static void sort(Comparable[] a) {
		int N=a.length;
		for(int i=0;i<N-1;i++) {
			int min=i;
			for(int j=i+1;j<N;j++) {
				if(less(a[j],a[min])) {
					min=j;
				}
			}
			exch(a,i,min);
		}
	}
}

class Insertion extends AbstractSort{
	public static void sort(Comparable[] a) {
		int N=a.length;
		for(int i=1;i<N;i++) {
			for(int j=i;j>0 && less(a[j],a[j-1]);j--) {
				exch(a,j,j-1);
			}
		}
	}
}



 class Shell extends AbstractSort {
public static void sort(Comparable[] a) {
	int N = a.length;
	int h = 1;
	while (h < N/3) h = 3*h + 1;
	while (h >= 1) { 
	for (int i = h; i < N; i++)
		for (int j = i; j >= h && less(a[j], a[j-h]); j -= h)
			exch(a, j, j-h); 
		h /= 3;
		}
	}
 }
class TopDownMerge extends AbstractSort{
	private static void merge(Comparable[] a,Comparable[] aux,int lo,int mid,int hi) {
		for(int k=lo;k<=hi;k++) {
			aux[k]=a[k];
		}
		
		int i=lo,j=mid+1;
		for(int k=lo;k<=hi;k++) {
			if(i>mid)     a[k]=aux[j++];
			else if(j>hi) a[k]=aux[i++];
			else if(less(aux[j],aux[i])) a[k]=aux[j++];
			else a[k]=aux[i++];
		}
	}
	
	public static void sort(Comparable[] a) {
		Comparable[] aux=new Comparable[a.length];
		sort(a,aux,0,a.length-1);
	}
	
	private static void sort(Comparable[] a,Comparable[] aux,int lo,int hi) {
		if(hi<=lo) return;
		int mid=lo+(hi-lo)/2;
		sort(a,aux,lo,mid);
		sort(a,aux,mid+1,hi);
		merge(a,aux,lo,mid,hi);
	}
}

class BottomUpMerge extends AbstractSort{
	private static void merge(Comparable[] in,Comparable[] out,int lo,int mid,int hi) {
		int i=lo,j=mid+1;
		for(int k=lo;k<=hi;k++) {
			if(i>mid)     out[k]=in[j++];
			else if(j>hi) out[k]=in[i++];
			else if(less(in[j],in[i])) out[k]=in[j++];
			else    out[k]=in[i++];
		}
	}
	
	public static void sort(Comparable[] a) {
		Comparable[] src=a, dst=new Comparable[a.length],tmp;
		int N=a.length;
		for(int n=1;n<N;n*=2) {
			for(int i=0;i<N;i+=2*n) {
				merge(src,dst,i,i+n-1,Math.min(i+2*n-1, N-1));
			}
			tmp=src;src=dst;dst=tmp;
		}
		if(src!=a) System.arraycopy(src, 0, a, 0, N);
	}
}

public class HW1 {

	public static void main(String[] args) {
	Scanner std=new Scanner(System.in);
	System.out.println("입력 파일 이름?");
	String filename=std.nextLine();
	
	File f=new File(filename);
	
	
	String[] strarr;
	String[] cpyarr=new String[100000];

	
	String word="";
	int c;
	int cnt=0;
	int wordcnt=0;
	int i=0;
	
	long beforetime;
	long aftertime;
	
	try {
		FileReader fr=new FileReader(f);
		
		while((c=fr.read())!=-1) {	
			
			
			if(c>=65&&c<=122) {
				wordcnt++;
				word=word+(char)c;
			}
			
			if(c==32||c==10) {                  
				if(wordcnt>=1) {
					cnt++;
					cpyarr[i++]=word;
					wordcnt=0;
					word="";
				}
			}
		}
		
		strarr=new String[cnt];
		for(int k=0;k<cnt;k++) {
			strarr[k]=cpyarr[k];
		}
		
		System.out.println("1.단어의 수 ="+cnt);
		
		
		beforetime=System.currentTimeMillis();
		Selection.sort(strarr);
		aftertime=System.currentTimeMillis();
		System.out.print("2. 선택정렬: ");
		if(Selection.isSorted(strarr)) {
			System.out.print("정렬여부= true, ");
		}
		else {
			System.out.print("정렬여부= false, ");
		}
		System.out.println("소요시간 = "+(aftertime-beforetime)+"ms");

		for(int k=0;k<strarr.length;k++) {
			strarr[k]=cpyarr[k];
		}
		
		System.out.print("3. 삽입정렬: ");
		beforetime=System.currentTimeMillis();
		Insertion.sort(strarr);
		aftertime=System.currentTimeMillis();
		if(Insertion.isSorted(strarr)) {
			System.out.print("정렬여부= true, ");
		}
		else {
			System.out.print("정렬여부= false, ");
		}
		System.out.println("소요시간 = "+(aftertime-beforetime)+"ms");
		
		
		for(int k=0;k<strarr.length;k++) {
			strarr[k]=cpyarr[k];
		}
		
		System.out.print("4. Shell정렬: ");
		beforetime=System.currentTimeMillis();
		Shell.sort(strarr);
		aftertime=System.currentTimeMillis();
		if(Shell.isSorted(strarr)) {
			System.out.print("정렬여부= true, ");
		}
		else {
			System.out.print("정렬여부= false, ");
		}
		System.out.println("소요시간 = "+(aftertime-beforetime)+"ms");
		
		
		for(int k=0;k<strarr.length;k++) {
			strarr[k]=cpyarr[k];
		}
		
		
		System.out.print("5. Top Down 합병정렬: ");
		beforetime=System.currentTimeMillis();
		TopDownMerge.sort(strarr);
		aftertime=System.currentTimeMillis();
		if(TopDownMerge.isSorted(strarr)) {
			System.out.print("정렬여부= true, ");
		}
		else {
			System.out.print("정렬여부= false, ");
		}
		System.out.println("소요시간 = "+(aftertime-beforetime)+"ms");
		
		for(int k=0;k<strarr.length;k++) {
			strarr[k]=cpyarr[k];
		}
		
		
		System.out.print("6. Bottom Up 합병정렬: ");
		beforetime=System.currentTimeMillis();
		BottomUpMerge.sort(strarr);
		aftertime=System.currentTimeMillis();
		if(BottomUpMerge.isSorted(strarr)) {
			System.out.print("정렬여부= true, ");
		}
		else {
			System.out.print("정렬여부= false, ");
		}
		System.out.println("소요시간 = "+(aftertime-beforetime)+"ms");
	
	}catch(IOException e){
		System.out.print("파일복사 오류");
	}
	
 }
}
