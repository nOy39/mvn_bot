package bot.bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class BotCommand {

    /**
     * Method for extracting a command from a string.
     *
     * @param botComm - String value. The value of this variable comes from a message from the bot.
     * @param message - SendMessage value (TelegramAPI). The object is passed from the method above.
     *                To fill in the fields of the SendMessage object before sending a response by the bot.
     * @return
     */
    public SendMessage commandExecute(String botComm) {
        SendMessage message = new SendMessage();
        int charEnd = botComm.indexOf('@');
        if (Character.compare(botComm.charAt(0), '/') == 0) {
            try {
                BotCommandEnum botCommand = BotCommandEnum.valueOf(
                        charEnd >= 0 ?
                                botComm.substring(1, charEnd).toUpperCase() :
                                botComm.substring(1).toUpperCase());

                switch (botCommand) {
                    case SETTING:
                        settingMessage(message);
                        break;
                    case INFO:
                        infoMessage(message);
                        break;
                    case REMIND:
                        remindMessage(message);
                        break;
                    default: {
                        message.setText("Unknown command for bot");
                    }
                }
                return message;
            } catch (IllegalArgumentException e) {
                System.err.println(e);
                message.setText("Unknown command for bot");
                return message;
            }
        }
        return null;
    }

    private SendMessage infoMessage(SendMessage message) {
        message.setText("\uD83D\uDD95");
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button1.setText("Info");
        button2.setText("О Боте");
        button1.setCallbackData("repeatFuck");
        button2.setCallbackData("about");
        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(button1);
        row.add(button2);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(row);
        keyboardMarkup.setKeyboard(rowList);
        message.setReplyMarkup(keyboardMarkup);
        return message;
    }

    private SendMessage remindMessage(SendMessage message) {
        message.setText("\uD83D\uDD95");
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        InlineKeyboardButton button3 = new InlineKeyboardButton();
        InlineKeyboardButton button4 = new InlineKeyboardButton();
        button1.setText("Remind today");
        button2.setText("Remind after time");
        button3.setText("Remind another day");
        button4.setText("Show my reminder");
        button1.setCallbackData("remindToday");
        button2.setCallbackData("remindAfterTime");
        button3.setCallbackData("remindAnotherTime");
        button4.setCallbackData("showReminder");
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row1.add(button1);
        row1.add(button2);
        row1.add(button3);
        row2.add(button4);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(row1);
        rowList.add(row2);
        keyboardMarkup.setKeyboard(rowList);
        message.setReplyMarkup(keyboardMarkup);
        return message;
    }

    private SendMessage settingMessage(SendMessage message) {
        message.setText("\uD83D\uDD95");
//        // Create ReplyKeyboardMarkup object
//        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
//        // Create the keyboard (list of keyboard rows)
//        List<KeyboardRow> keyboard = new ArrayList<>();
//        // Create a keyboard row
//        KeyboardRow row = new KeyboardRow();
//        // Set each button, you can also use KeyboardButton objects if you need something else than text
//        row.add("Row 1 Button 1");
//
//        // Add the first row to the keyboard
//        keyboard.add(row);
//        // Create another keyboard row
//        row = new KeyboardRow();
//        // Set each button for the second line
//        row.add("Row 2 Button 1");
//
//        // Add the second row to the keyboard
//        keyboard.add(row);
//        // Set the keyboard to the markup
//        keyboardMarkup.setKeyboard(keyboard);
//        // Add it to the message
//        message.setReplyMarkup(keyboardMarkup);
        return message;
    }
}
