package hu.bakro.test1;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

class hello {
	public static void main(String[] args) {
		try {
			JFrame window = new JFrame("Tili-toli játék");
			
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			if(args.length == 2) {
				window.setContentPane(new ToliGui(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
			} else {
				window.setContentPane(new ToliGui(3, 3));
			}
			
			
			window.pack();  // finalize layout
			window.show();  // make window visible
			window.setResizable(false);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.toString(), "Hiba", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}