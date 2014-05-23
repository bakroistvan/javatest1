package hu.bakro.test1;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

class Main {
	public static void main(String[] args) {
		try {
			JFrame window =  new JFrame();
			String title = "Tili-toli";
			
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			if(args.length > 3) {
				throw new Exception("Rossz argumentum");
			}
			
			if(args.length == 0) {
				window.setContentPane(new ToliGui("standalone", 3, 3));
				title += " - Standalone";
			} else if(args.length == 1) {
				window.setContentPane(new ToliGui(args[0], 3, 3));
				title +=  " - " + (args[0].contentEquals("master") ? "MASTER" : "SLAVE");
			} else if(args.length == 2) {
				window.setContentPane(new ToliGui("standalone", Integer.parseInt(args[0]), Integer.parseInt(args[1])));
				title += " - Standalone";
			} else if(args.length == 3) {
				window.setContentPane(new ToliGui(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2])));
				title +=  " - " + (args[0].contentEquals("master") ? "MASTER" : "SLAVE");
			}
			window.setTitle(title);
			
			window.pack();  // finalize layout
			window.show();  // make window visible
			window.setResizable(false);
	    } catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.toString(), "Hiba", JOptionPane.INFORMATION_MESSAGE);
	    }
	}
}