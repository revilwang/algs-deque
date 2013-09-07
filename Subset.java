
public class Subset {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> q = new RandomizedQueue<String>();

        while (!StdIn.isEmpty())
            q.enqueue(StdIn.readString());

        int size = q.size();
        for (int i = 0; i < size - k; i++)
            q.dequeue();

        for (String str : q)
            StdOut.println(str);
    }
}
