package bot.controllers;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MessageController {
    FileReceiverController fc = new FileReceiverController();


    public void receiveMessage(Update update) {
//        if (update.getMessage().hasDocument()) {
//            try {
//           //     fc.downloadFile(update.getMessage());
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
