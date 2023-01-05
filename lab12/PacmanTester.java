package lab12;

public class PacmanTester {
	public static void main(String[] args) {
		
		Pacman p = new Pacman("src/lab12/mazes/turn.txt", "src/lab12/mazes/poopypants.txt");
		System.out.println(p);
		System.out.println();
		p.solveBFS();
		System.out.println(p);
		
	}
}
