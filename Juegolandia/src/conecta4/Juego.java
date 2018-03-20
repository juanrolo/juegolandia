/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package conecta4;

/**
 *
 * @author sistemas inteligentes
 */
public class Juego {

    //Tablero de juego.
    public Tablero m_tablero;

    //Para saber si estamos jugando.
    public boolean m_jugando = false;

    //Mensaje que se mostrará en el juego:
    //1. Turno del jugador X.
    //2. Ha ganado el jugador X.
    //3. Empate.
    //4. Error. Colocación de ficha incorrecta.
    public int m_mensaje;

    //Para saber a quién le toca el turno, 1 jugador1, 2 jugador2 
    private int m_turno;

    //Tiempo máximo de respuesta del ordenador
    private int m_tiempoMaximo;

    //Los jugadores máquina
    private JugadorMaquina m_maquina1, m_maquina2;

    //Indica el modo de juego.
    //1. Humano contra humano.
    //2. Humano contra máquina.
    //3. Máquina contra máquina.
    private int m_modoJuego;


    /**
     * Constructor del juego.
     * Crea el tablero, especifica el turno de juego (si empieza a jugar el jugador 1 o 2), si los jugadores son humanos o la máquina.
     */
    public Juego(int anchura, int altura, int tiempoMaximo, int modoJ, int turnoJugador)
    {
        m_tablero = new Tablero(anchura, altura);
        m_tiempoMaximo = tiempoMaximo;
        m_turno = turnoJugador;
        m_modoJuego = modoJ;
        m_jugando = false;
    }

    /*************************************************************
    //Funciones de acceso a las variables de juego
    *************************************************************/
    //Devuelve si estamos jugando
    public boolean getJugando()
    {
        return m_jugando;
    }

    //Devuelve a quién le toca turno
    public int getTurno()
    {
        return m_turno;
    }

    //Devuelve el modo de juego
    public int getModo()
    {
        return m_modoJuego;
    }


    /**
     * Inicializa el juego. Guarda el modo de juego y crea los jugadores máquina.
     * @param modoJ 
     */
    public void setJuego(int modoJ)
    {
        //Guarda el modo de juego
        m_modoJuego = modoJ;

        //Si el modo de juego es Humano contra máquina, crea una máquina que jugará como jugador 2.
        if(m_modoJuego == 2)
           m_maquina1 = new JugadorMaquina(2);

        //Si el modo de juego es Maquina contra máquina, crea 2 máquinas, una juega como jugador 1 y otra como 2.
        //Para esto se utiliza el parámetro.
        if(m_modoJuego == 3)
        {
            m_maquina1 = new JugadorMaquina(1);
            m_maquina2 = new JugadorMaquina(2);
        }

        //Indica que ya empieza el juego.
        m_jugando = true;
        //Indica que se muestre el mensaje de turno.
        m_mensaje = 1;
    }


    /**
     * Controla el juego de los jugadores máquina.
     */
    public void controlJuego()
    {
        int resultado;

            //Si el modo de juego es humano contra máquina.
            if(m_modoJuego == 2)
            {
                //Si le toca el turno a la máquina.
                if(m_turno==2)
                {
                    //Realiza la jugada la máquina.
                    resultado = jugadaMaquina(m_maquina1);
                    //Si el juego no ha terminado cambia de turno.
                    if(m_jugando)
                        cambiaTurno();
                }
            }

            //Si el modo de juego es máquina contra máquina.
            if(m_modoJuego == 3)
            {
                    //Si le toca el turno a la máquina que juega como jugador 1.
                    if(m_turno==1)
                    {
                        resultado = jugadaMaquina(m_maquina1);
                    }
                    
                    //Si le toca el turno a la máquina que juega como jugador 2.
                    if(m_turno==2)
                    {
                        resultado = jugadaMaquina(m_maquina2);
                    }

                    if(m_jugando)
                            cambiaTurno();
            }
    }

    /**
     * El jugador humano realiza una tirada si es su turno, y se puede colocar la ficha en la columna especificada.
     * Devuelve 0 si todo ha ido correctamente.
     * @param columna Indica la columna donde introduce la ficha
     * @param jugador Indica si el humano juega como jugador 1 o como jugador 2
     * @return
     */
    public int jugadaHumano(int columna, int jugador)
    {
        int resultado = 0;

        //Si le toca su turno.
        if (m_turno == jugador)
        {
            //Introduce la ficha en el tablero.
             resultado = m_tablero.ponerFicha(columna, jugador);

             //Si la colocación de la ficha ha sido correcta.
             if(resultado == 0)
             {
                 //Comprueba si ha ganado el jugador.
                 if(m_tablero.cuatroEnRaya()!= 0)
                 {
                     m_mensaje = 2;
                     m_jugando = false;
                 }
                 
                 //Comprueba si hay empate.
                 if(m_tablero.tableroLleno())
                 {
                     m_mensaje = 3;                   
                     m_jugando = false;
                 }
             }
             else //Si la ficha se coloca incorrectamente el juego termina.
                