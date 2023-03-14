import bot.Bot;

public class BotStarter {
    public static void main(String[] args) {
        new Bot().serve();
        System.out.println("Telegram Bot started.");
    }
}
