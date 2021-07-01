package bot.bot;

import config.TOKEN;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.objects.File;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 */
public class Bot extends TelegramLongPollingBot {


    SendMessage message;

    /**
     * Get Bot name from config TOKEN.class
     *
     * @return BOT_USERNAME
     */
    @Override
    public String getBotUsername() {
        return new TOKEN().getBOT_USERNAME();
    }

    /**
     * Get Bot token from config TOKEN.class
     *
     * @return BOT_TOKEN
     */
    @Override
    public String getBotToken() {
        return new TOKEN().getTOKEN();
    }

    /**
     * @param update
     */
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            if (update.getMessage().getEntities() != null) {
                String botCommand = update.getMessage().getText();
                message = new BotCommand().commandExecute(botCommand);
                sendMessage(message, update);
            }
        } else if (update.hasCallbackQuery()) {
            message = new SendMessage();
            sendMessage(message, update);
        }
    }

    /**
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
     * @return
     */
    private String getCurrentDate() {
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy", Locale.ENGLISH);
        return formatter.format(ldt);
    }

    /**
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
     * @param message
     * @param update
     */
    private void sendMessage(SendMessage message, Update update) {
        if (update.hasMessage()) {
            message.setChatId(update.getMessage().getChatId().toString());
        } else if (!update.hasMessage() && update.hasCallbackQuery()) {
            message.setChatId(update.getCallbackQuery().getMessage().getChatId().toString());
            message.setText("You pressed: " + update.getCallbackQuery().getData());
        }
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
