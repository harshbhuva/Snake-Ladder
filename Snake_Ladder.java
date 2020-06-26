//import java.io.BufferedReader;
//import java.io.InputStreamReader;
import java.util.Random;

class Board
{
	Random ra;
	//BufferedReader br;
	Board()
	{
		ra=new Random();
	}
	synchronized int throwDice(String pna,int score)
	{
		System.out.println("Previous score for "+pna+" is "+score);
		int dice=ra.nextInt(6);
		dice++;
		return dice;
	}
}

class Player implements Runnable
{
	Thread th;
	String pna;
	Board b;
	int dice,score;
	
	Player(String pna,Board b)
	{
		th=new Thread(this);
		this.b=b;
		this.pna=pna;
		dice=0;
		score=0;
		th.start();			
	}
	
	public void run()
	{
		while(true)
		{
			dice=b.throwDice(pna, score);
			System.out.println("Dice= "+dice);
			score=score+dice;
			if(score==50)
			{
				System.out.println(pna+" has won the game");
				System.exit(0);
			}
			else if(score>50)
			{
				score=score-dice;
			}
			else if(score==13 || score==25 || score==32 || score==39)
			{
				System.out.println(pna+" has got ladder");
				score+=5;
			}
			else if(score==9 || score==19 || score==28 || score==38 || score==49)
			{
				System.out.println(pna+" has got bitten by snake");
				score-=8;
			}
			System.out.println("Updated score of "+pna+" is "+score+"\n\n");
			try {  th.sleep(5000); }
			catch(Exception e)  {  }
		}
	}
	
}
public class Snake_Ladder 
{
	public static void  main(String[] args)
	{
		Board b=new Board();
		Player p1=new Player("Harshal",b);
		Player p2=new Player("Prashant",b);
	}

}
