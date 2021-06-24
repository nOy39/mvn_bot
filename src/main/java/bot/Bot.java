package bot;

import bot.controllers.MessageController;
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

public class Bot extends TelegramLongPollingBot {

    MessageController mc = new MessageController();
    @Override
    public String getBotUsername() {
        return "${bot.bot_name}";
    }

    @Override
    public String getBotToken() {
        return new TOKEN().getTOKEN();
    }

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage message = new SendMessage();
        //TODO
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

    private String getPath(Update update) {
        String staticPath = "./data/userDoc/";
        String userIdFolder = update.getMessage().getChat().getId() + "_"
                            + update.getMessage().getChat().getUserName() + "/";
        String dateSubfolder = getNow() + "/";
        return staticPath + userIdFolder + dateSubfolder;
    }

    private String getNow() {
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy", Locale.ENGLISH);
        return formatter.format(ldt);
    }

    private String getFilePath(Document document) throws TelegramApiException {
        GetFile getFile = new GetFile();
        getFile.setFileId(document.getFileId());
        File file = execute(getFile);
        return file.getFilePath();
    }

    private void sendMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
