package command;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import dao.DataDAO;
import dao.HTMLDao;

import java.net.URL;

public class MessageService implements Service{
    @Override
    public BaseRequest processData(Update update) {
        Message message = update.message();
        BaseRequest request = null;
        if (message != null) {
            long chatId = message.chat().id();
            if (message.text() == null || message.text().equals("/start")) {
                request = new SendMessage(chatId, "Привет JavaRush");
            } else {
                request = new SendMessage(chatId, "Обрабатываем");

                DataDAO dao = null;

                String webPage = "https://cash.sharik.ru/production/search/?search=1206-1230";




                try {
                   // dao = new HTMLDao(new URL("https://cash.sharik.ru/production/search/?search=" + message.text()));
                    dao = new HTMLDao(new URL("https://cash.sharik.ru/price_list/?category&search=" + message.text()));
                    dao.getData();
                } catch (Exception e) {
                    request = new SendMessage(chatId, "Произошла ошибка. Не удалось загрузить данные с сервера.");
                }
            }
        }
        return request;
    }
}
