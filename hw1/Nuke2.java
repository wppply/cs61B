import java.io.*;

public class Nuke2{
	public static void main(String[] arg) throws Exception {
		BufferedReader keybd = new BufferedReader(new InputStreamReader(System.in));
		String line = keybd.readLine();
		System.out.println(line.substring(0,1)+line.substring(2));
	}

}