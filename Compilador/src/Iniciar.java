import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import InterfaceGrafica.Grafico;

public class Iniciar {
	
	public static void main (String[] args)
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) 
		{
			e.printStackTrace();
		}
		
		new Grafico().setVisible(true);
	}
}
