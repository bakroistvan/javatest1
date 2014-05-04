package hu.bakro.test1;

import javax.swing.JFrame;

class hello {
	public static void main(String[] args) {
		JFrame window = new JFrame("Tili-toli játék");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(new ToliGui());
		window.pack();  // finalize layout
		window.show();  // make window visible
		window.setResizable(false);
	}
}