package command;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InputMediaPhoto;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMediaGroup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import dao.DataDAO;
import dao.HTMLDao;
import entity.Result;
import entity.ResultImage;
import entity.SearchResult;

import java.net.URL;

public class MessageService implements Service{
    @Override
    public BaseRequest processData(Update update) {
        Message message = update.message();
        BaseRequest request = null;
        if (message != null) {
            long chatId = message.chat().id();
            if (message.text() == null || message.text().equals("/start")) {
                request = new SendMessage(chatId, "Привет! Напиши артикул, например 1234-5843, и я пришлю картинку!");
            } else {
                try {
                    DataDAO dao = new HTMLDao(new URL("https://cash.sharik.ru/api/rest/v1/products_autocomplete/?page_size=30&format=json&search=" + message.text()));
                    SearchResult searchResult = dao.getData();
                    if (searchResult != null) {
                        if (searchResult.getCount() == 0) {
                            request = new SendMessage(chatId, "Я не смог ничего найти по запросу: \"" + message.text() + "\"");
                        } else {
                            for (Result result : searchResult.getResults()) {
                                String caption = result.getName() + "\n" +
                                        result.getCountry_name() + "\n" +
                                        result.getCode();
                                if (result.getImages() != null) {
                                    if (result.getImages().size() == 1) {
                                        request = new SendPhoto(chatId, result.getImages().get(0).getImage()).caption(caption);
                                    } else {
                                        InputMediaPhoto[] inputMediaPhotos = new InputMediaPhoto[result.getImages().size()];
                                        for (int i = 0; i < result.getImages().size(); i++) {
                                            inputMediaPhotos[i] = new InputMediaPhoto(result.getImages().get(i).getImage()).caption(caption);
                                        }
                                        request = new SendMediaGroup(chatId, inputMediaPhotos);
                                    }
                                }
                            }
                        }
                    } else {
                        request = new SendMessage(chatId, "Произошла ошибка 1. Не удалось загрузить данные с сервера.");
                    }
                } catch (Exception e) {
                    request = new SendMessage(chatId, "Произошла ошибка. Не удалось загрузить данные с сервера.");
                }
            }
        }
        return request;
    }
}
