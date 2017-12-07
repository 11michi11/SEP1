package controler;

import java.awt.EventQueue;
import view.VIAWindow;

public class VIAControler {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				VIAWindow window = new VIAWindow();
			}
		});
	}
}
