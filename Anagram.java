import java.util.*;
import java.io.*;

class Node{
    String key;
    ArrayList<String> val;
    Node(String key,ArrayList<String> val){
        this.key=key;
        this.val=val;
    }
}
class Map{
    int size=49999;
    Node arr[];
    Map(){
        arr=new Node[size];
        for(int i=0;i<size;i++)
            arr[i]=null;
    }
    int hash(String key){
        int r=0;
        for(int i=0;i<key.length();i++){
            //r+=(key.charAt(i)*(int)Math.pow(256,i))%size;
                        //r=(r*37+key.charAt(i))%size;
        	r=(key.charAt(i)+256*r)%size;
        }
        return r;
    }
    void put(String key,ArrayList<String> val){
        int h=hash(key);
        //arr[h].key=key;
        //arr[h].val=val;
        arr[h]=new Node(key,val);
    }
    ArrayList<String> get(String key){
        Node a=arr[hash(key)];
        if(a==null) return null;
        ArrayList<String> al=a.val;
        return al;
    }
    boolean containsKey(String key){
        ArrayList<String> al=get(key);
        if(al==null) return false;
        return true;
    }
}
public class Anagram{
	public static void main(String[] args) throws FileNotFoundException{
		Scanner sc=new Scanner(new File(args[0]));
		long start1=System.currentTimeMillis();
		int n=sc.nextInt();
		Map map=new Map();
		//Map map=new Map();
		for(int i=0;i<n;i++){
			String s=sc.next();
			char []char_arr=s.toCharArray();
			Arrays.sort(char_arr);
			String s1=new String(char_arr);
			if(map.containsKey(s1)){
				ArrayList al=map.get(s1);
				al.add(s);
				map.put(s1,al);
			}
			else{
				ArrayList<String> al=new ArrayList();
				al.add(s);
				map.put(s1,al);
			}
		}
		Scanner scn=new Scanner(new File(args[1]));
		int n1=scn.nextInt();
		for(int i=0;i<n1;i++){
			//TreeSet ts=new TreeSet();
			Set<String> ts=new HashSet<>();
			long start=System.currentTimeMillis();
	
			String s2=scn.next();
			if(s2.length()>12){
				System.out.println(-1);
				continue;
			}
			int len=s2.length();
			Set<String> set=new HashSet<>();
			if(len>=3 && len<6){
				ArrayList<String> al1=findOne(map,s2);
				if(al1!=null)
					for(int x=0;x<al1.size();x++){
						set.add(al1.get(x));
					}
			}
			else if(len>=6 && len<9){
				ArrayList<String> al1=findOne(map,s2);
				if(al1!=null)
					for(int x=0;x<al1.size();x++){
						set.add(al1.get(x));
					}
				ArrayList<String> al2=findTwoNew(map,s2,set);
			}
			else if(len>=9){
				ArrayList<String> al1=findOne(map,s2);
				if(al1!=null)
					for(int x=0;x<al1.size();x++){
						set.add(al1.get(x));
					}
				ArrayList<String> al2=findTwoNew(map,s2,set);
				ArrayList<String> al3=findthreeNew(map,s2,set,al2);
				print2(map,al3,set);
			}
			ArrayList<String> result=new ArrayList<>();

			result.addAll(set);
			Collections.sort(result);
			for(int x=0;x<result.size();x++){
				System.out.println(result.get(x));
			}
			System.out.println(-1);	
			long end=System.currentTimeMillis();
			//System.out.println(end-start);

		}
			long end1=System.currentTimeMillis();
			//System.out.println(end1-start1);
			//System.out.println(map.size());
	}
	static void print(ArrayList<String> al,Set ts){
		if(al==null) return;
		for(int i=0;i<al.size();i++){
			//System.out.println(al.get(i));
			ts.add(al.get(i));
		}

	}
	static void print2(Map map,ArrayList<String> al,Set ts){
		if(al==null) return;
		for(int g=0;g<al.size();g++){
			String s[]=al.get(g).split(" ");
			char [] arr1=s[0].toCharArray();
			Arrays.sort(arr1);
			String s1=new String(arr1);

			char [] arr2=s[1].toCharArray();
			Arrays.sort(arr2);
			String s2=new String(arr2);

			char [] arr3=s[2].toCharArray();
			Arrays.sort(arr3);
			String s3=new String(arr3);

			ArrayList<String> al1=map.get(s1);
			ArrayList<String> al2=map.get(s2);
			ArrayList<String> al3=map.get(s3);
			//System.out.println(al1+" "+al2+" "+al3);
			if(al1==null || al2==null || al3==null) continue;
			for(int l=0;l<al1.size();l++){
				for(int m=0;m<al2.size();m++){
					for(int n=0;n<al3.size();n++){
						ts.add(al1.get(l)+" "+al2.get(m)+" "+al3.get(n));
						ts.add(al1.get(l)+" "+al3.get(n)+" "+al2.get(m));
						ts.add(al2.get(m)+" "+al1.get(l)+" "+al3.get(n));
						ts.add(al2.get(m)+" "+al3.get(n)+" "+al1.get(l));
						ts.add(al3.get(n)+" "+al1.get(l)+" "+al2.get(m));
						ts.add(al3.get(n)+" "+al2.get(m)+" "+al1.get(l));
					}
				}
			}
		}

	}
	static ArrayList<String> findOne(Map map,String s1){
		char [] arr=s1.toCharArray();
		Arrays.sort(arr);
		String s2=new String(arr);
		ArrayList<String> al=map.get(s2);
		return al;
	}
	static ArrayList<String> findTwoNew(Map map,String s1,Set ts){
		char[] arr=s1.toCharArray();
		ArrayList<String> f=new ArrayList<>();
		int n=arr.length;

        String w1="";
        String w2="";
        for (int i = 0; i < (1<<n); i++)
        {
            w1="";
            w2="";
            for (int j = 0; j < n; j++)
                if ((i & (1 << j)) > 0)
                    w1+=arr[j];
                else
                    w2+=arr[j];

			if(w1.length()>2 && w2.length()>2){

				char [] arr1=w1.toCharArray();
				Arrays.sort(arr1);
				String final1=new String(arr1);

				char [] arr2=w2.toCharArray();
				Arrays.sort(arr2);
				String final2=new String(arr2);

				ArrayList<String> al1=map.get(final1);
				ArrayList<String> al2=map.get(final2);
				if(al1==null || al2==null) continue;
				for(int j=0;j<al1.size();j++){
					for(int k=0;k<al2.size();k++){
						//f.add(al1.get(j)+" "+al2.get(k));
						//f.add(al2.get(k)+" "+al1.get(j));
						ts.add(al1.get(j)+" "+al2.get(k));
						ts.add(al2.get(k)+" "+al1.get(j));
					}
				}

			}

        }
		return f;
	}
	static ArrayList<String> findthreeNew(Map map,String s1,Set ts,ArrayList<String> al){
		//if(al==null) return null;
		ArrayList<String> ret=new ArrayList<>();

		char[] arr=s1.toCharArray();
		//ArrayList<String> f=new ArrayList<>();
		int n=arr.length;

        String w1="";
        String w2="";
        for (int i = 0; i < (1<<n); i++)
        {
            w1="";
            w2="";
            for (int j = 0; j < n; j++)
                if ((i & (1 << j)) > 0)
                    w1+=arr[j];
                else
                    w2+=arr[j];

			if(w1.length()>2 && w2.length()>2){

				char[] arr1=w1.toCharArray();
				int n1=arr1.length;	
		        String r1="";
		        String r2="";
		        for (int k = 0; k < (1<<n1); k++)
		        {
		            r1="";
		            r2="";
		            for (int j = 0; j < n1; j++)
		                if ((k & (1 << j)) > 0)
		                    r1+=arr1[j];
		                else
		                    r2+=arr1[j];

		            if(r1.length()>2 && r2.length()>2){
		            	ret.add(r1+" "+r2+" "+w2);
		            }
		        }
		    }
		}
		return ret;
	}
}
