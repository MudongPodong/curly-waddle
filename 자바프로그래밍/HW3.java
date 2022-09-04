
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.StringTokenizer;
public class HW3 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String fileinfo=null;
		int  wordnum=0;
		
		System.out.print("파일 이름, 단어 쌍의 수? ");
		fileinfo=sc.nextLine();
		StringTokenizer filename=new StringTokenizer(fileinfo," ");
		fileinfo=filename.nextToken();
		wordnum=Integer.parseInt(filename.nextToken());
		
		ArrayList<String> stoplist=new ArrayList<String>(); 
		HashMap<String,Integer> counthm=new HashMap<String,Integer>();      
		HashMap<String,Integer> countpair=new HashMap<String,Integer>();  
		
		File file=new File(fileinfo);
		File stopfile=new File("stop.txt");		

		try {
			sc=new Scanner(stopfile);
			while(sc.hasNextLine()) stoplist.add(sc.nextLine());
			stoplist.add("-");
			
			StringBuilder sb=new StringBuilder();
			LinkedHashSet<String> overlayword=new LinkedHashSet<String>();
			
			sc=new Scanner(file);
			while(sc.hasNextLine()) {
				sb.append(sc.nextLine()+" ");
			}
			int cnt=0;
			
			String[] line=sb.toString().toLowerCase().split("[\\.\\!\\?]+[^a-z-]*");
			for(int i=0;i<line.length;i++) {
				String[] word=line[i].split("(\\W+\\-+\\W+)+|[^a-z-]+");
				
				for(int j=0;j<word.length;j++) {    
					if(stoplist.contains(word[j])) continue;
					
					if(counthm.containsKey(word[j]) ) {      
						counthm.replace(word[j], counthm.get(word[j])+1);
					}else {
						counthm.put(word[j], 1);
					}
				}
	
				for(int j=0;j<word.length;j++) overlayword.add(word[j]);
				word=new String[overlayword.size()];
				Iterator iter=overlayword.iterator();
				while(iter.hasNext()) word[cnt++]=(String)iter.next();
				
				Arrays.sort(word);
				for(int j=0;j<word.length-1;j++) {
					if(stoplist.contains(word[j])) continue;
					for(int k=j+1;k<word.length;k++) {
						if(stoplist.contains(word[k])) continue;
						if(word[j].equals(word[k])) continue;     
						if(countpair.containsKey(word[j]+", "+word[k]) ) {      
							countpair.replace(word[j]+", "+word[k], countpair.get(word[j]+", "+word[k])+1);
							
						}else {
							countpair.put(word[j]+", "+word[k], 1);
						}
					}
				}
				overlayword.clear();
				cnt=0;
			}

			List<Entry<String,Integer>> countmapvalue=new ArrayList<Entry<String,Integer>>(counthm.entrySet());                 
			Collections.sort(countmapvalue,(a,b) -> a.getKey().compareTo(b.getKey()));
			Collections.sort(countmapvalue, new Comparator<Entry<String,Integer>>(){
				public int compare(Entry<String,Integer> a,Entry<String,Integer> b) {
					return b.getValue().compareTo(a.getValue());
				}
			});
			
			List<Entry<String,Integer>> countpairvalue=new ArrayList<Entry<String,Integer>>(countpair.entrySet());
			Collections.sort(countpairvalue,(a,b) -> a.getKey().compareTo(b.getKey()));
			Collections.sort(countpairvalue, new Comparator<Entry<String,Integer>>(){
				public int compare(Entry<String,Integer> a,Entry<String,Integer> b) {
					return b.getValue().compareTo(a.getValue());
				}
			});
			
			cnt=0;
			System.out.print("Top-k 문자열: ");
			for(Entry<String,Integer> entry:countmapvalue) {
				if(cnt==wordnum) break;
				System.out.print(entry.getKey()+"("+entry.getValue()+") ");
				cnt++;
			}
			System.out.println();
			cnt=0;
			System.out.print("Top-k 단어쌍: ");
			for(Entry<String,Integer> entry:countpairvalue) {
				if(cnt==wordnum) break;
				System.out.print("["+entry.getKey()+"]("+entry.getValue()+") ");
				cnt++;
			}
		}catch(IOException a) {
			System.out.println("파일 복사에 실패하였습니다");
		}
	}
}
