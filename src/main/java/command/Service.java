package command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;

public interface Service {
    BaseRequest processData(Update update);
}
