package Utilidades;

import Modelo.Token;
import Modelo.Simbolo;

import java.util.Stack;

public class VerificaCaracteres {

	public static Stack<Token> analiseLexica(String file) throws Throwable {

		Stack<Token> pilha = new Stack<Token>();

		int linha = 1;

		for (int i = 0; i < file.length(); i++) {
			char caracter = ' ';
			char car = ' ';
			Simbolo simbolo;

			StringBuilder builder = new StringBuilder();

			// pega o segundo caracter
			if ((i + 1) != file.length()) {
				caracter = file.charAt(i + 1);
			}

			if (Character.isLetter(file.charAt(i)) || file.charAt(i) == '_') {
				builder.append(file.charAt(i));

				while (Character.isLetterOrDigit(caracter) || caracter == '_') {
					i++;
					builder.append(file.charAt(i));
					caracter = ' ';
					if ((i + 1) != file.length()) {
						caracter = file.charAt(i + 1);
					}

				}

				if (builder.toString().length() > 30) {
					
					throw new Exception("Tamanho de identificador inválido na linha " + linha + ": " + builder.toString() + "'");
				}

				// implementar pilha x token
				if (Reservada(builder.toString())) {
					simbolo = getSimbolo(builder.toString());
				} else {
					simbolo = getSimbolo("IDENTIFICADOR");
				}

				pilha.add(pilha.size(), new Token(simbolo.getCodigo(), simbolo.getSimbolo(), builder.toString(), linha));

			} else if (Character.isDigit(file.charAt(i)) || (file.charAt(i) == '-' && Character.isDigit(caracter))) {
				builder.append(file.charAt(i));
				while (Character.isDigit(caracter)) {
					i++;
					builder.append(file.charAt(i));
					caracter = ' ';
					if ((i + 1) != file.length()) {
						caracter = file.charAt(i + 1);
					}

				}

				if (Character.isLetter(caracter)) {throw new Exception("Caracter inválido na linha " + linha + ": " + caracter);
				
				}

				int numeros = Integer.parseInt(builder.toString());

				if (numeros > 32767 || numeros < -32767) {
					throw new Exception("Valor numérico fora da faixa " + linha);
				}

				simbolo = getSimbolo("INTEIRO");

				pilha.add(pilha.size(), new Token(simbolo.getCodigo(), simbolo.getSimbolo(), builder.toString(), linha));
				
			} else if (!Character.isWhitespace(file.charAt(i))) {
				if (file.charAt(i) == '(' && caracter == '*') {
					boolean comentario = true;
					while (comentario) {
						comentario = !(file.charAt(i) == '*' && caracter == ')');

						if (!comentario)
							break;

						if (BarraN(file.charAt(i))) {
							linha++;
						}

						i++;
						caracter = ' ';
						if ((i + 1) != file.length()) {
							caracter = file.charAt(i + 1);
						}
					}
					i += 2;
				} else if (file.charAt(i) == '\'') {
					// literais delimitados por apóstrofe
					while (caracter != '\'') {
						i++;
						builder.append(file.charAt(i));
						caracter = ' ';
						if ((i + 1) != file.length()) {
							caracter = file.charAt(i + 1);
						}

					}

					if (!builder.toString().equals("")) {
						if (builder.toString().length() > 255) {
							
							throw new Exception("Sequência de caracter <Literais> maior que 255 na linha " + linha + ": " + builder.toString());
						}

						simbolo = getSimbolo("LITERAL");

						pilha.add(pilha.size(), new Token(simbolo.getCodigo(), simbolo.getSimbolo(), builder.toString(), linha));
						
					}
					i++;
				} else {
					builder.append(file.charAt(i));

					if (!Character.isLetterOrDigit(caracter) && !Character.isWhitespace(caracter)) {
						if (Atribuicao(file.charAt(i), caracter) || Comparacao(file.charAt(i), caracter)
								|| DoisPontos(file.charAt(i), caracter)) {
							builder.append(caracter);
							i++;
						}

					}

					simbolo = getSimbolo(builder.toString());

					if (simbolo == null) {
						throw new Exception("Símbolo inconsistente na linha: " + linha + " - " + builder.toString());
					}
					pilha.add(pilha.size(), new Token(simbolo.getCodigo(), simbolo.getSimbolo(), builder.toString(), linha));
				}
			} else if (BarraN(car)) {
				linha++;
			}
		}
		return pilha;
	}

	private static boolean Reservada(String setenca) {
		for (String texto : Reservadas_tokens.RESERVADAS) {
			if (setenca.equals(texto)) {
				return true;
			}
		}

		return false;
	}

	private static Simbolo getSimbolo(String simbolo) {
		for (Simbolo smb : Reservadas_tokens.SIMBOLOS_TERMINAIS) {
			if (simbolo.equals(smb.getSimbolo())) {
				return smb;
			}
		}

		return null;
	}

	private static boolean Atribuicao(char car, char caracter) {
		String aux = String.valueOf(car) + String.valueOf(caracter);
		return aux.equals(":=");
	}

	private static boolean Comparacao(char car, char caracter) {
		String aux = String.valueOf(car) + String.valueOf(caracter);
		return (aux.equals("<>") || aux.equals("<=") || aux.equals(">="));
	}

	private static boolean DoisPontos(char car, char caracter) {
		String aux = String.valueOf(car) + String.valueOf(caracter);
		return aux.equals("..");
	}

	private static boolean BarraN(char car) {
		return String.valueOf(car).equals("\n");
	}
}
