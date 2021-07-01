package bot.bot;

import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class BotService{

    /**
     * Adding keyboardrow
     * @param message
     * @return
     */
    @NotNull
    public SendMessage makeKeyboard(SendMessage message) {
        // Create ReplyKeyboardMarkup object
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        // Create the keyboard (list of keyboard rows)
        List<KeyboardRow> keyboard = new ArrayList<>();
        // Create a keyboard row
        KeyboardRow row = new KeyboardRow();
        // Set each button, you can also use KeyboardButton objects if you need something else than text
        row.add("Row 1 Button 1");
        row.add("Row 1 Button 2");
        row.add("Row 1 Button 3");
        // Add the first row to the keyboard
        keyboard.add(row);
        // Create another keyboard row
        row = new KeyboardRow();
        // Set each button for the second line
        row.add("Row 2 Button 1");
        row.add("Row 2 Button 2");
        row.add("Row 2 Button 3");
        // Add the second row to the keyboard
        keyboard.add(row);
        // Set the keyboard to the markup
        keyboardMarkup.setKeyboard(keyboard);
        // Add it to the message
        message.setReplyMarkup(keyboardMarkup);

        return message;
    }

    /**
     *
     * @param update
     */
    public void checkingMessage(Update update) {
        System.out.println(update.getMessage().getChatId());
        if (update.hasMessage() && update.getMessage().hasText()) {
            update.toString();
        }
    }

    //        if (update.hasMessage() && update.getMessage().hasDocument() && update.getMessage().getAnimation() == null) {
//            Document document = update.getMessage().getDocument();
//            try {
//                String uploadedFileId = document.getFileId();
//                GetFile uploadedFile = new GetFile();
//                uploadedFile.setFileId(uploadedFileId);
//
//                File fileToSave = execute(uploadedFile);
//                downloadFile(fileToSave, new java.io.File(getPath(update) + document.getFileName()));
//                System.out.println(fileToSave.hashCode());
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//
//            message.setText("File uploaded");
//            message.setChatId(update.getMessage().getChatId().toString());
//            sendMessage(message);
//        }
//        if (update.hasMessage() && update.getMessage().hasText()) {
//
//            message.setChatId(update.getMessage().getChatId().toString());
//            message.setText("Echo: " + update.getMessage().getText());
//            sendMessage(message);
//        }
}
