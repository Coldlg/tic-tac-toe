public class Main{
	public static void main(String[] args) {
		Engine engine = new Engine();
		System.out.println(engine.getBoard());
		engine.resetGame();
		System.out.println(engine.getBoard());
	}
}