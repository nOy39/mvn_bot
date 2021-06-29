package bot.bot;

import bot.controllers.MessageController;
import config.TOKEN;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.objects.File;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 */
public class Bot extends TelegramLongPollingBot {
    /**
     *
     */
    //todo https://ru.stackoverflow.com/questions/950294/%D0%9E%D1%82%D0%BF%D1%80%D0%B0%D0%B2%D0%BA%D0%B0-%D1%81%D0%BE%D0%BE%D0%B1%D1%89%D0%B5%D0%BD%D0%B8%D0%B9-telegram-bot
    MessageController mc = new MessageController();
    @Override
    public String getBotUsername() {
        return "${bot.bot_name}";
    }

    /**
     *
     * @return
     */
    @Override
    public String getBotToken() {
        return new TOKEN().getTOKEN();
    }


    /**
     *
     * @param update
     */
    @Override
    public void onUpdateReceived(Update update) {
        SendMessage message = new SendMessage();
        if (update.hasMessage()) {
            if (update.hasMessage())
            mc.receiveMessage(update);
        }
        if (update.hasMessage() && update.getMessage().hasDocument() && update.getMessage().getAnimation() == null) {
            Document document = update.getMessage().getDocument();
            try {
                String uploadedFileId = document.getFileId();
                GetFile uploadedFile = new GetFile();
                uploadedFile.setFileId(uploadedFileId);

                File fileToSave = execute(uploadedFile);
                downloadFile(fileToSave, new java.io.File(getPath(update) + document.getFileName()));
                System.out.println(fileToSave.hashCode());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            message.setText("File uploaded");
            message.setChatId(update.getMessage().getChatId().toString());
            sendMessage(message);
        }
        if (update.hasMessage() && update.getMessage().hasText()) {

            message.setChatId(update.getMessage().getChatId().toString());
            message.setText("Echo: " + update.getMessage().getText());
            sendMessage(message);
        }
    }

    /**
     *
     * @param update
     * @return
     */
    private String getPath(Update update) {
        String staticPath = "./data/userDoc/";
        String chatID = update.getMessage().getChatId().toString() + "/";
        String userIdFolder = update.getMessage().getFrom().getId().toString() + "_"
                            + update.getMessage().getFrom().getUserName() + "/";
        String dateSubfolder = getCurrentDate() + "/";
        return staticPath + chatID + userIdFolder + dateSubfolder;
    }

    /**
     *
     * @return
     */
    private String getCurrentDate() {
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy", Locale.ENGLISH);
        return formatter.format(ldt);
    }

    /**
     *
     * @param document
     * @return
     * @throws TelegramApiException
     */
    private String getFilePath(Document document) throws TelegramApiException {
        GetFile getFile = new GetFile();
        getFile.setFileId(document.getFileId());
        File file = execute(getFile);
        return file.getFilePath();
    }

    /**
     *TODO Необходимо вынести клавиатуру в отдельный "Интерфейс"
     * @param message
     */
    private void sendMessage(SendMessage message) {
        message.setText("Custom message text");
        new BotService().makeKeyboard(message);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
