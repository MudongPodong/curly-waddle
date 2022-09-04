import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.StringTokenizer;

public class HW2 {

	private static String original;
	
	public static void copyarr(LinkedHashSet<String> a,StringTokenizer b) {
		a.clear();
		while(b.hasMoreTokens()) {
			a.add(b.nextToken());
		}	
	}
	public static void main(String[] args) {
		String file=new String();
		String x=new String();
		int n=0;  
		int m=0;   
		
		Scanner std=new Scanner(System.in);
		
		System.out.print("파일 이름, 사용자이름, 사용자 수, 항목 수? ");
		String fileinfo1=std.nextLine();
		StringTokenizer fileinfo2=new StringTokenizer(fileinfo1," ");
		
		for(int i=0;i<4;i++) {
			if(i==0) {			
				file=fileinfo2.nextToken();
			}else if(i==1){
				x=fileinfo2.nextToken();
			}else if(i==2){	
				n=Integer.parseInt(fileinfo2.nextToken());
			} else {
				m=Integer.parseInt(fileinfo2.nextToken());
			}
		}
		File f=new File(file);
		StringTokenizer origindata=null;
		StringTokenizer data;		
		String str=null;

		LinkedHashSet<String> user1=new LinkedHashSet<String>();
		LinkedHashSet<String> user2=new LinkedHashSet<String>();
		HashMap<Integer,Double> hm=new HashMap<Integer,Double>();   
		List<Entry<Integer,Double>> sortarr;
		int[] countsimilar=new int[10000];  
		
		for(int i=0;i<10000;i++) {
			countsimilar[i]=0;
		}
		try {
			FileReader fr=new FileReader(f);
			BufferedReader reader = new BufferedReader(fr);
			 
			while((str=reader.readLine())!=null) {
				origindata=new StringTokenizer(str," ");
				
				if(origindata.nextToken().equals(x)) {
					origindata=new StringTokenizer(str," ");
					while(origindata.hasMoreTokens()) {
						user1.add(origindata.nextToken());
					}
					user1.remove(x);
					original=str;
					origindata=new StringTokenizer(str," ");
					break;
				}
			}
			fr=new FileReader(f);
			reader=new BufferedReader(fr);

			while((str=reader.readLine())!=null) {
				data=new StringTokenizer(str," ");
				double a=0,b=0;
				String compareuser=null;
				
				compareuser=data.nextToken();    
				if(!compareuser.equals(x)) {
					data=new StringTokenizer(str," ");
					
					while(data.hasMoreTokens()) {
						user2.add(data.nextToken());					
					}
					user1.retainAll(user2);
					a=user1.size();	
					
					copyarr(user1,origindata);
					user1.remove(x);
					origindata=new StringTokenizer(original," ");		
					
					user1.addAll(user2);
					b=user1.size();	
					
					copyarr(user1,origindata);
					user1.remove(x);
					origindata=new StringTokenizer(original," ");
					
					hm.put(Integer.parseInt(compareuser.replaceAll("u","")),a/b);
					user2.clear();
				}
			}
			
			sortarr=new ArrayList<Entry<Integer,Double>>(hm.entrySet());
			Collections.sort(sortarr,new Comparator<Entry<Integer,Double>>(){
				public int compare(Entry<Integer,Double> a, Entry<Integer,Double> b) {
					return b.getValue().compareTo(a.getValue());
				}
			});
			int cnt2=0;
			for(Entry<Integer,Double> arr:sortarr) {
				if(cnt2==n) {
					break;
				}
				fr=new FileReader(f);
				reader=new BufferedReader(fr);
			
				while((str=reader.readLine())!=null) {
					data=new StringTokenizer(str," ");
					double a=0,b=0;
					int cnt=0;
					String compareuser=null;

					if(data.nextToken().equals("u"+Integer.toString(arr.getKey()))) {
						data=new StringTokenizer(str," ");

						while(data.hasMoreTokens()) {
							if(cnt==0) {
								compareuser=data.nextToken();
								cnt++;
							}else {
								user2.add(data.nextToken());	
							}
						}			
						user2.removeAll(user1);
						Iterator<String> iter=user2.iterator();
						while(iter.hasNext()) {
							countsimilar[Integer.parseInt(iter.next())]++;
						}
						user2.clear();
						continue;
					}
				}
				cnt2++;
			}
			System.out.println("");
			System.out.print("결과:");
			for(int i=0;i<m;i++) {
				int max=0;
				int index=0;
				for(int j=0;j<10000;j++) {
					if(max<countsimilar[j]) {
						max=countsimilar[j];
						index=j;
					}
				}
				System.out.print(index+"("+max+")"+" ");
				countsimilar[index]=0;
			}
		}catch(IOException e) {
			System.out.println("파일복사 오류");
		}
	}
}
