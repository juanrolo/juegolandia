/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package conecta4;

/**
 *
 * @author sistemas inteligentes
 */
public class JugadorMaquina extends Jugador {

	// Profundidad hasta la que se va a desarrollar el √°rbol de juego
	public final static int NIVEL_DEFECTO = 4;

	// Constructor
	public JugadorMaquina(int jugador) {
		super(jugador);
	}

	// Funci√≥n que se ejecuta en el thread
	public void run() {
		// Llama a la funci√≥n Minimax que implementa el algoritmo para calcular
		// la jugada
		minimax();

		// No borrar esta l√≠nea!!
		isDone(true);
	}

	/**
	 * Se debe determinar la mejor jugada mediante Minimax. El tablero de juego
	 * se encuentra en la variable m_tablero. Al final de la funci√≥n de la
	 * variable m_columna debe contener la tirada.
	 * 
	 * @return
	 */
	public void minimax() {/*
							 * 
							 * //El siguiente c√≥digo genera una jugada
							 * aleatoria //SE DEBE SUSTITUIR ESTE C√ìDIGO POR EL
							 * DEL ALGORITMO Minimax boolean buenaTirada =
							 * false; int columna = -1;
							 * 
							 * columna = (int)
							 * (Math.random()*m_tablero.numColumnas());
							 * 
							 * while(!buenaTirada) {
							 * if(m_tablero.comprobarColumna(columna)!=-1) {
							 * buenaTirada = true; m_columna = columna;
							 * 
							 * } else columna = (int)
							 * (Math.random()*m_tablero.numColumnas());
							 * 
							 * }
							 */

		/**
		 * 0 est√° vac√≠a 1 pertenece al jugador 1 2 pertenece al jugador 2 ya
		 * dispongo del tablero inicial
		 **/

		tableroAux actual = new tableroAux(m_tablero.numColumnas(), m_tablero.numFilas());
		// copio el estado actual del tablero a mi tablero actual
		for (int i = 0; i < m_tablero.numFilas(); ++i) {
			for (int j = 0; j < m_tablero.numColumnas(); ++j) {
				actual.insertarTablero(i, j, m_tablero.obtenerCasilla(i, j));
				// System.out.print(m_tablero.obtenerCasilla(i, j));
			}
			// System.out.println("");
		}
		// System.out.println("");
		// ya tengo guardado el tablero actual en actual
		// actual.insertarPiezaJuego(2, 2);
		// System.out.println("jugador actual"+this.m_jugador);
		nodo a = new nodo(true, this.m_jugador, actual);

		// System.out.println(actual.getColumnas());
		// System.out.println(actual.getFilas());
		long time_start, time_end;
		time_start = System.currentTimeMillis();
		m_columna = a.extenderArbol();
		time_end = System.currentTimeMillis();
		System.out.println("Ha tardado: " + (time_end - time_start) + " milisegundos");

		// actual.insertarPiezaJuego(0, 1);
		// actual.printTablero();

	}

	private int auxMiniMax() {

		return 0;
	}

}

class nodo {
	private boolean minMax; // Min false Max true2
	private int valor;
	private int jugador;
	private int nivel;
	private int opcion;
	private tableroAux tablero;
	private nodo padre;
	private int jugadorInical;

	public nodo(nodo aux) {
		this.jugador = aux.jugador;
		this.minMax = aux.minMax;
		this.nivel = aux.nivel;
		this.opcion = aux.opcion;
		this.padre = aux.padre;
		this.tablero = aux.tablero;
		this.valor = aux.valor;
		this.jugadorInical = aux.jugadorInical;
	}

	public nodo() {

	}

	public nodo(boolean tipo, int jugador, tableroAux tablero) {
		this.minMax = tipo;
		// this.padre = null;
		this.valor = 0;
		this.padre = null;
		this.jugador = jugador;
		this.jugadorInical = jugador;
		this.tablero = tablero;
		this.opcion = 0;
		this.nivel = 0;
	}

	public nodo(boolean tipo, nodo padre, int jugadorInicial, int jugador, int nivel, int opcion, tableroAux tablero) {
		this.minMax = tipo;
		this.padre = padre;
		this.valor = 0;
		this.jugador = jugador;
		this.jugadorInical = jugadorInicial;
		this.nivel = nivel;
		this.opcion = opcion;
		this.tablero = new tableroAux(tablero);
		this.tablero.insertarPiezaJuego(this.opcion, this.jugador);

	}

	private Boolean nodoContrario() {
		if (this.minMax) {
			return false;
		} else {
			return true;
		}
	}

	private int jugadorContrario() {
		if (this.jugador == 1) {
			return 2;
		} else if (this.jugador == 2) {
			return 1;
		}
		return 0;
	}

	private int calcularJugada() {
		int columna = this.padre.opcion;
		// System.out.println("columna padre: "+columna);
		int contrario = 0;
		int sub[][];
		int valorBruto = this.tablero.devolverValorCasilla(!this.minMax, columna);
		if (this.jugadorInical == 1) {
			contrario = 2;
		} else if (this.jugadorInical == 2) {
			contrario = 1;
		}
		// System.out.println("columna:"+columna);
		// System.out.println("fila de
		// juego:"+tablero.devolverFilaDeJuego(columna));
		sub = this.tablero.subMatrix(this.tablero.devolverFilaDeJuego(columna, this.jugadorInical), columna);
		// System.out.println(this.tablero.devolverFilaDeJuego(columna,this.jugador));
		if (this.tablero.devolverFilaDeJuego(columna, this.jugadorInical) == 5) {
			if (!this.minMax) {
				valorBruto = -100000000;
			} else {
				valorBruto = +100000000;
			}

		} else {
			for (int i = 0; i < 3; ++i) {
				// System.out.println();
				for (int j = 0; j < 3; ++j) {
					// System.out.print(sub[i][j]+" ");
					// System.out.println("jugador
					// contrario"+this.jugadorContrario());
					if (sub[i][j] == contrario) {
						valorBruto = valorBruto + 30;
						// he bloqueado a un jugador
					}
					if (sub[i][j] == this.jugadorInical) {
						valorBruto = valorBruto + 20;
						// continuo con mi jugada
					}
				}
			}
			// System.out.println();
			int altura = this.tablero.devolverFilaDeJuego(columna, this.jugadorInical);
			// compruebo si bloqueo
			// miro si la altura es mayor que dos para comprobar en vertical
			// vertical
			// if(altura > 1){
			if (this.tablero.returnPieza(altura - 1, this.opcion) == contrario) {
				// tengo una ficha bloqueada
				if (this.tablero.returnPieza(altura - 2, this.opcion) == contrario) {
					// System.out.println("entro en bloquear segundo nivel");
					// System.out.println("["+(altura-2)+"]"+"["+this.opcion+"]");
					valorBruto = valorBruto + 100;
					if (this.tablero.returnPieza(altura - 3, this.opcion) == contrario) {
						// he bloqueado un posible cuatro en raya
						// System.out.println("entro en bloquear 4 en raya");
						valorBruto = valorBruto + 1000;

					}
				}
			}
			// }

			// horizontal
			// miro izquierda
			if (this.tablero.returnPieza(altura, this.opcion - 1) == contrario) {
				if (this.tablero.returnPieza(altura, this.opcion - 2) == contrario) {
					// System.out.println("entro a bloquear izquierda 2");
					valorBruto = valorBruto + 100;
					if (this.tablero.returnPieza(altura, this.opcion - 3) == contrario) {
						// System.out.println("entro a bloquear tres en raya
						// izquierda");
						valorBruto = valorBruto + 10000;
					}
				}
			}
			// derecha
			if (this.tablero.returnPieza(altura, this.opcion + 1) == contrario) {
				if (this.tablero.returnPieza(altura, this.opcion + 2) == contrario) {
					// System.out.println("entro a bloquear derecha 2");
					valorBruto = valorBruto + 100;
					if (this.tablero.returnPieza(altura, this.opcion + 3) == contrario) {
						// System.out.println("entro a bloquear tres en raya
						// derecha");
						valorBruto = valorBruto + 10000;
					}
				}
			}

			// calcula la sub matriz ahora que mira si hay un cuatro en raya
			/*
			 * if(this.jugador == this.tablero.cuatroEnRaya()){
			 * 
			 * valorBruto = valorBruto +1000; } if(this.jugadorContrario() ==
			 * this.tablero.cuatroEnRaya()){ valorBruto = valorBruto -1000; }
			 */
			if (this.tablero.cuatroEnRaya() == this.jugadorInical) {
				valorBruto = valorBruto + 10000;
			} else if (this.tablero.cuatroEnRaya() == contrario) {
				valorBruto = valorBruto - 10000;
			}

		}

		return valorBruto;
	}

	public int extenderArbol() {

		/*
		 * System.out.println(this.jugador); System.out.println(this.minMax);
		 * System.out.println(this.nivel); System.out.println(this.opcion);
		 * System.out.println(this.valor); this.tablero.printTablero();
		 * System.out.println(""); System.out.println("");
		 * System.out.println("");
		 */

		// this.jugador = this.jugadorContrario();
		// this.auxExtenderArbol();
		// aqui maximizo
		valor = -2000000;
		// System.out.println("Estoy en el nodo inicial");
		for (int j = 0; j < 7; ++j) {
			// System.out.println(j);

			nodo a = new nodo(false, this, this.jugadorInical, this.jugador, this.nivel + 1, j, this.tablero);
			a.auxExtenderArbol();

			// System.out.println("opcion bucle:"+j+":opcion en a:"+a.opcion);
			if (valor < a.valor) {
				valor = a.valor;
				this.valor = a.valor;
				this.opcion = a.opcion;
			}
			// System.out.println("valor devuelto por un nodo "+this.minMax+" :
			// "+this.valor);
		}

		// System.out.println("valor del nodo inicial"+this.valor);

		return this.opcion;
	}

	private void auxExtenderArbol() {
		int valor;
		if (this.nivel == JugadorMaquina.NIVEL_DEFECTO) {
			// System.out.println("Hoja: "+this.minMax);
			this.valor = this.calcularJugada();
			// System.out.println("hoja con valor: "+this.valor);
			// this.tablero.printTablero();
			// System.out.println("solucion valor nodo:"+this.valor);
		} else {
			if (this.minMax) {
				// aqui maximizo
				valor = -2000000;
				// System.out.println("Entro en nodo max");
				for (int j = 0; j < 7; ++j) {
					// System.out.println(j);

					nodo a = new nodo(false, this, this.jugadorInical, this.jugadorContrario(), this.nivel + 1, j,
							this.tablero);
					a.auxExtenderArbol();

					// System.out.println("opcion bucle:"+j+":opcion en
					// a:"+a.opcion);
					if (valor < a.valor) {
						valor = a.valor;
						this.valor = a.valor;
						// this.opcion = a.opcion;
					}
					// System.out.println("valor devuelto por un nodo
					// "+this.minMax+" : "+this.valor);
				}

			} else {
				// aqui minimizo
				valor = 2000000;
				// System.out.println("Entro en nodo min");
				for (int j = 0; j < 7; ++j) {

					// System.out.println(j);
					nodo a = new nodo(true, this, this.jugadorInical, this.jugadorContrario(), this.nivel + 1, j,
							this.tablero);
					a.auxExtenderArbol();
					// System.out.println("valor devuelto por un nodo
					// "+aux.minMax+" : "+aux.valor);
					// System.out.println("opcion bucle:"+j+":opcion en
					// a:"+a.opcion);
					// System.out.println(a.valor);
					if (valor > a.valor) {
						valor = a.valor;
						this.valor = a.valor;
						// this.opcion = a.opcion;
					}
					// System.out.println("valor devuelto por un nodo
					// "+this.minMax+" : "+this.valor);

				}

			}

		}
	}
}

class tableroAux {
	private int filas;
	private int columnas;
	private int matrix[][];

	public tableroAux(int nColumnas, int nFilas) {
		this.filas = nFilas;
		this.columnas = nColumnas;
		matrix = new int[filas][columnas];
	}

	public tableroAux(tableroAux t) {
		this.filas = t.filas;
		this.columnas = t.columnas;
		this.matrix = new int[filas][columnas];
		for (int i = 0; i < filas; ++i) {
			for (int j = 0; j < columnas; ++j) {
				this.matrix[i][j] = t.matrix[i][j];
			}
		}
	}

	public int getFilas() {
		return this.filas;
	}

	public int getColumnas() {
		return this.columnas;
	}

	public void insertarTablero(int fila, int columna, int valor) {

		this.matrix[fila][columna] = valor;
	}

	public int returnPieza(int fila, int columna) {
		if (fila < 0 || columna < 0) {
			return -1;
		} else if (fila > 5 || columna > 6) {
			return -1;
		} else {
			return this.matrix[fila][columna];
		}
	}

	public int devolverFilaDeJuego(int columna, int jugador) {
		int fila = -1;
		// arreglar cuando llega a final de tablero
		// int i = this.filas-1;
		for (int i = 0; i < this.filas; i++) {
			if (this.matrix[i][columna] == jugador) {
				fila = i;
			}
		}
		// System.out.println("fila de juego: "+fila);
		/*
		 * while(this.matrix[i][columna] != jugador && i != 0){
		 * System.out.println("valor de i: "+i+" Valor tablero "+this.matrix[i][
		 * columna]); --i; } if(i != -1){ fila = i; }
		 */
		/*
		 * for(int i = this.filas-1; i > 0 ; --i){ if(this.matrix[i][columna] ==
		 * jugador){ fila = i; } }
		 */
		return fila;
	}

	public int devolverValorCasilla(boolean minMax, int columna) {
		int valor = 0;
		if (columna == 0 || columna == 6) {
			valor = 10;
		} else if (columna == 1 || columna == 5) {
			valor = 20;
		} else if (columna == 2 || columna == 4) {
			valor = 30;
		} else if (columna == 3) {
			valor = 40;
		}
		// revisar
		/*
		 * if (!minMax){ valor = -1 * valor; }
		 */

		return valor;
	}

	public boolean insertarPiezaJuego(int columna, int valor) {
		boolean hueco = true;
		// arreglar cuando llega a final de tablero
		for (int i = 0; i < this.filas && hueco; ++i) {
			if (this.matrix[i][columna] == 0) {
				this.matrix[i][columna] = valor;
				hueco = false;
			}
		}
		return !hueco;
	}

	public int[][] subMatrix(int fila, int columna) {
		int sub[][] = new int[3][3];

		int newFila = fila - 1;
		int newColumna = columna - 1;
		// System.out.println("newFila"+newFila);
		// System.out.println("newColumna"+newColumna);
		for (int i = 0; i < 3; ++i) {
			// System.out.println();
			for (int j = 0; j < 3; ++j) {

				if (newFila < 0 || newFila > 5) {
					sub[i][j] = -1;
					// System.out.println("entro por fila fuera de limite");
				} else if (newColumna < 0 || newColumna > 6) {
					// System.out.println("entro columna fuera de limite");
					sub[i][j] = -1;
				} else {
					// System.out.println("entro por dentro de rango");
					sub[i][j] = this.returnPieza(newFila, newColumna);
				}

				// System.out.println( sub[i][j]);
				// this.printTablero();
				// System.out.println(this.returnPieza(newFila, newColumna));
				++newColumna;

			}
			newColumna = columna - 1;
			++newFila;
		}

		return sub;
	}

	public int cuatroEnRaya() {
		// Casilla por la que busca
		int casilla;
		// Jugador que a hecho el 4 en raya
		int ganador = 0;

		// Indica que ha encontrado 4 en raya
		boolean fin = false;

		int i = 0;
		int j = 0;

		while ((!fin) && (i < this.filas)) {
			j = 0;
			while ((!fin) && (j < this.columnas)) {
				// Para cada casilla del tablero
				casilla = this.matrix[i][j];
				// si la casilla no est· vacÌa comprueba sÌ pertenece a
				// un 4 en raya
				if (casilla != 0) {
					// Realiza una b˙squeda en horizontal
					if (j + 3 < this.columnas)
						if ((this.matrix[i][j + 1] == casilla) && (this.matrix[i][j + 2] == casilla)
								&& (this.matrix[i][j + 3] == casilla)) {
							ganador = casilla;
							fin = true;
						}
					// Realiza una b˙squeda en vertical
					if (i + 3 < this.filas)
						if ((this.matrix[i + 1][j] == casilla) && (this.matrix[i + 2][j] == casilla)
								&& (this.matrix[i + 3][j] == casilla)) {
							ganador = casilla;
							fin = true;
						}
					// Realiza una b˙squeda diagonal. Debe buscar en las 2
					// diagonales a partir de una casilla
					if (i + 3 < this.filas) {
						// Realiza una b˙squeda en la primera diagonal
						if (j - 3 >= 0) {
							if ((this.matrix[i + 1][j - 1] == casilla) && (this.matrix[i + 2][j - 2] == casilla)
									&& (this.matrix[i + 3][j - 3] == casilla)) {
								ganador = casilla;
								fin = true;
							}
						}
						// Realiza una b˙squeda en la segunda diagonal
						if (j + 3 < this.columnas) {
							if ((this.matrix[i + 1][j + 1] == casilla) && (this.matrix[i + 2][j + 2] == casilla)
									&& (this.matrix[i + 3][j + 3] == casilla)) {
								ganador = casilla;
								fin = true;
							}
						}
					}
				}
				j++;
			}
			i++;
		}
		return ganador;
	}

	public void printTablero() {
		for (int i = 0; i < this.filas; ++i) {
			for (int j = 0; j < this.columnas; ++j) {
				System.out.print(this.matrix[i][j]);
			}
			System.out.println("");
		}
		System.out.println("");
	}

}
