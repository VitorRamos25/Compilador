package InterfaceGrafica;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import Componentes.Componentes;
import Modelo.Token;
import Utilidades.VerificaCaracteres;



public class Grafico extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private JPanel telaCima;
	private JPanel telaTexto;
	private JPanel telaConsole;
	private JPanel telaTabelas;
	
	private JButton botaoAbrir;
	private JButton botaoSalvar;
	private JButton botaoNovo;
	private JButton botaoParar;
	private JButton botaoDebugar;
	
	private JTextArea arqEditor;
	private JTextArea arqConsole;
	private JTextArea telaEditor;
	private JTextArea textoConsole;
	
	private JTable telaLexica;
	private JTable telaSintatica;
	
	private JLabel pilhaLexica;
	private JLabel pilhaSintatica;
	
	private DefaultTableModel tabelaLexia = new DefaultTableModel();
	private DefaultTableModel tabelaSintatica = new DefaultTableModel();
	
	private Stack<Token> pilhaLex = new Stack<Token>();
	
	public Grafico() 
	{
		super("COMPILADOR");
		this.setSize(900, 700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		
		iniciaComponentes();
		
	}
		
	
	private void iniciaComponentes()
	{
		
		telaCima = new JPanel();
		telaCima.setBounds(2, 2, 880 ,45);
		telaCima.setLayout(null);
		telaCima.setVisible(true);
		telaCima.setBorder(BorderFactory.createRaisedSoftBevelBorder());		
		
		this.getContentPane().add(telaCima);
		
		ImageIcon iconeRodar = new ImageIcon("src/InterfaceGrafica/Imagens/1file.png");
		botaoNovo = new JButton(iconeRodar);
		botaoNovo.setBounds(10, 5, 35, 35);
		botaoNovo.setFocusable(false);
		botaoNovo.setToolTipText("Arquivo");
		telaCima.add(botaoNovo);
		
		ImageIcon iconePasta = new ImageIcon("src/InterfaceGrafica/Imagens/2folder.png");
		botaoAbrir = new JButton(iconePasta);
		botaoAbrir.setBounds(45, 5, 35, 35);
		botaoAbrir.setFocusable(false);
		botaoAbrir.setToolTipText("Abrir");
		telaCima.add(botaoAbrir);
		
		ImageIcon iconeSalvar = new ImageIcon("src/InterfaceGrafica/Imagens/3save.png");
		botaoSalvar = new JButton(iconeSalvar);
		botaoSalvar.setBounds(80, 5, 35, 35);
		botaoSalvar.setFocusable(false);
		botaoSalvar.setToolTipText("Salvar");
		telaCima.add(botaoSalvar);
		
		ImageIcon iconeParar = new ImageIcon("src/InterfaceGrafica/Imagens/4stop.png");
		botaoParar = new JButton(iconeParar);
		botaoParar.setBounds(115, 5, 35, 35);
		botaoParar.setFocusable(false);
		botaoParar.setToolTipText("Parar");
		telaCima.add(botaoParar);
		
		ImageIcon iconeDebug = new ImageIcon("src/InterfaceGrafica/Imagens/5bug.png");
		botaoDebugar = new JButton(iconeDebug);
		botaoDebugar.setBounds(150, 5, 35, 35);
		botaoDebugar.setFocusable(false);
		botaoDebugar.setToolTipText("Debugar");
		botaoDebugar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					debugar();
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		telaCima.add(botaoDebugar);
		
		telaTexto = new JPanel();
		telaTexto.setBounds(2, 50, 625, 470);
		telaTexto.setVisible(true);
		telaTexto.setLayout(null);
		telaTexto.setBackground(new Color(255, 255, 255));
		telaTexto.setBorder(BorderFactory.createRaisedBevelBorder());
		
		telaEditor = new JTextArea();
		JScrollPane rolagemEditor = new JScrollPane(telaEditor);
		Componentes linhaNumeros = new Componentes(telaEditor);
		rolagemEditor.setRowHeaderView(linhaNumeros	);
		rolagemEditor.setVisible(true);
		rolagemEditor.setBounds(2, 0, 621, 469);
		telaTexto.add(rolagemEditor);
		
		this.getContentPane().add(telaTexto);
		
		telaTabelas = new JPanel();
		telaTabelas.setBounds(630, 50, 252, 470);
		telaTabelas.setLayout(null);
		telaTabelas.setVisible(true);
		telaTabelas.setBorder(BorderFactory.createRaisedBevelBorder());
		
		telaLexica = new JTable(tabelaLexia);
		tabelaLexia.addColumn("Código");
		tabelaLexia.addColumn("Palavra");
		
		pilhaLexica = new JLabel("PILHA LÉXICA");
		pilhaLexica.setBounds(2, 2, 249, 22);
		pilhaLexica.setBorder(BorderFactory.createRaisedBevelBorder());
		pilhaLexica.setFont(new Font("Calibri", Font.BOLD, 12));
		pilhaLexica.setHorizontalAlignment(JLabel.CENTER);
		telaTabelas.add(pilhaLexica);
		
		JScrollPane rolagemTabelaLexica = new JScrollPane(telaLexica);
		rolagemTabelaLexica.setBounds(2, 27, 249, 210);
		rolagemTabelaLexica.setVisible(true);
		telaTabelas.add(rolagemTabelaLexica);
		
		telaSintatica = new JTable(tabelaSintatica);
		tabelaSintatica.addColumn("Código");
		tabelaSintatica.addColumn("Palavra");
		
		pilhaSintatica = new JLabel("PILHA SINTÁTICA");
		pilhaSintatica.setBounds(2, 240, 249, 22);
		pilhaSintatica.setBorder(BorderFactory.createRaisedBevelBorder());
		pilhaSintatica.setFont(new Font("Calibri", Font.BOLD, 12));
		pilhaSintatica.setHorizontalAlignment(JLabel.CENTER);
		telaTabelas.add(pilhaSintatica);
		
		JScrollPane rolagemTabelaSintatica = new JScrollPane(telaSintatica);
		rolagemTabelaSintatica.setBounds(2, 263, 249, 206);
		rolagemTabelaSintatica.setVisible(true);
		telaTabelas.add(rolagemTabelaSintatica);
		
		this.getContentPane().add(telaTabelas);
		
		
		telaConsole = new JPanel();
		telaConsole.setBounds(2, 519, 880, 140);
		telaConsole.setLayout(null);
		telaConsole.setVisible(true);
		telaConsole.setBorder(BorderFactory.createRaisedBevelBorder());
		
		textoConsole = new JTextArea();
		textoConsole.setEditable(false);
		
		JScrollPane rolagemConsole = new JScrollPane(textoConsole);
		rolagemConsole.setBounds(2, 2, 880, 140);
		rolagemConsole.setVisible(true);
		telaConsole.add(rolagemConsole);
		
		this.getContentPane().add(telaConsole);
		
		}

	
	private void setarDebugOff()
	{
		botaoDebugar.setEnabled(true);
		telaEditor.setEditable(true);
	}
	
	public String pegarTextoArea()
	{
		return telaEditor.getText();
	}
	
	public void pegarTextoArea(String Texto)
	{
		telaEditor.setText(Texto);
	}
	
		
	public String pegarTexto()
	{
		return textoConsole.getText();
	}
	
	public void pegarTexto(String Texto)
	{
		textoConsole.setText(Texto);
	}
	
	private void debugar() throws Throwable
	{
		String conteudo = pegarTextoArea();
				
		try
		{
			AdicionarConsole("Inicia análise Lexica...");
			pilhaLex = VerificaCaracteres.analiseLexica(conteudo);
			AdicionarConsole("Fim análise Lexica com sucesso.");
			
			tabelaLexia.setNumRows(0);
			for(Token t : pilhaLex)
			{
				tabelaLexia.addRow(new Object[] {t.getCodigo(), t.getValor()});
			}
		}
		catch(Exception e)
		{
			AdicionarConsole(e.getMessage());
			parar();
		}
		
	}
	
	private void parar()
	{
		setarDebugOff();
		
		pilhaLex = null;
		pilhaLex = new Stack<Token>();
		tabelaLexia.setNumRows(0);
		
	}
	
	private void AdicionarConsole(String Texto)
	{
		if(textoConsole.getText().trim().equals(""))
		{
			textoConsole.setText(Texto);
		}
		else
		{
			textoConsole.setText(textoConsole.getText() + "\n" + Texto);
		}
	}

}
