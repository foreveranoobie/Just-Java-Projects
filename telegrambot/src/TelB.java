import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiValidationException;

public class TelB extends TelegramLongPollingBot {
	@Override
	public void onUpdateReceived(Update update) {
		KeyboardButton button = new KeyboardButton();
		button.setText("Lol");
		if (update.hasMessage() && update.getMessage().hasText()) {
			String text = "lol";
			if (update.getMessage().getText().equals("/info")) {
				text = "hi!";
			} else if (update.getMessage().getText().equals("/but")) {

			}
			SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
					.setChatId(update.getMessage().getChatId()).setText(text);
			try {
				execute(message); // Call method to send the message
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String getBotUsername() {
		return "golfobot";
	}

	@Override
	public String getBotToken() {
		return "739965505:AAH9x5UgB-FffqUv0x_2YttF5SNPcabAoUI";
	}
}