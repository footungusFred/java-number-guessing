import java.util.*;

public class NumberGuessing {
    static Random rand = new Random();
    static Scanner sc = new Scanner(System.in);

    static int play(int low, int high, int maxAttempts) {
        int secret = rand.nextInt(high - low + 1) + low;
        System.out.printf("%nGuess a number between %d and %d (%d attempts)%n%n", low, high, maxAttempts);
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            System.out.printf("  Attempt %d/%d: ", attempt, maxAttempts);
            try {
                int guess = Integer.parseInt(sc.nextLine().trim());
                if (guess == secret) {
                    int score = Math.max(100 - (attempt - 1) * 10, 10);
                    System.out.printf("  🎉 Correct! Got it in %d attempt(s). Score: %d%n", attempt, score);
                    return score;
                } else if (guess < secret) {
                    System.out.println("  📈 Too low!" + (secret - guess <= 5 ? " Almost!" : ""));
                } else {
                    System.out.println("  📉 Too high!" + (guess - secret <= 5 ? " Almost!" : ""));
                }
            } catch (NumberFormatException e) { System.out.println("  Invalid number."); attempt--; }
        }
        System.out.println("  ❌ Out of attempts! The number was " + secret);
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("=== Number Guessing Game ===");
        int totalScore = 0, rounds = 0;
        while (true) {
            System.out.print("
Difficulty (easy/medium/hard) or quit: ");
            String level = sc.nextLine().trim().toLowerCase();
            if (level.equals("quit")) break;
            int score;
            switch (level) {
                case "easy":   score = play(1, 50, 10); break;
                case "medium": score = play(1, 100, 7); break;
                case "hard":   score = play(1, 200, 5); break;
                default: System.out.println("Invalid level."); continue;
            }
            totalScore += score; rounds++;
        }
        if (rounds > 0)
            System.out.printf("%n🏆 Game Over! Rounds: %d | Total: %d | Avg: %d%n", rounds, totalScore, totalScore/rounds);
        System.out.println("Thanks for playing!");
    }
}
