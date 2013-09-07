
public class Subset {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int k = Integer.parseInt(args[0]);
		RandomizedQueue<String> q = new RandomizedQueue<String>();
		
		String s;
		while ((s = StdIn.readString()) != null)
			q.enqueue(s);
		
		for (String str : q)
			StdOut.println(str);
		
		for (int i = 0; i < k; i++)
			StdOut.println(q.dequeue());
	}
}
