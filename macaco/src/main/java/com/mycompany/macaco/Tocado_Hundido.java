/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.macaco;

/**
 *
 * @author DPDAN
 */
import java.util.*;
public class Tocado_Hundido {

	public static void main(String[] args) {
		
		String nuevapartida;
		int contadorPartidas = 0;
		do {//Do while para preguntar al final si se desea jugar de nuevo
			
		//Declaracion de variables que se utilizaran en el codigo.
		Scanner lector= new Scanner(System.in);
		int respuesta;
		int numeroDisparos=0;
		int numerosBarcosHundidos=0;
		int tablero [][]= new int [5][5];
		int contDisparos=0;
	    int partida=0;
		
		
		// Uso de un do-while para contemplar los posibles fallos al elegir modo
		do {
		System.out.println("Â¡BIENVENIDO AL JUEGO DE TOCADO Y HUNDIDO!");
		System.out.println("Hay 2 modos de juego, Â¿a cuÃ¡l quieres jugar?:");
		System.out.println("1 <= Modo bÃ¡sico");
		System.out.println("2 <= Modo avanzado");
		respuesta= lector.nextInt();
		
		//MODO BASICO
		if(respuesta==1) {
			partida++;
			System.out.println("Â¡Bienvenido al modo bÃ¡sico!\nEn este modo jugarÃ¡s en un tablero 5x5 cuyas coordenadas son nÃºmeros y letras.\nAl introducir la coordenada dispararÃ¡s a la posiciÃ³n seleccionada.\nSi fallas, sumarÃ¡s un disparo y se te indicarÃ¡ que has disparado al agua.\nSi das a un barco, se marcarÃ¡ con una X.\nMuy atento porque hay 4 barcos distintos:\n- Un barco horizontal de 3 casillas\n- Un barco vertical de 3 casillas\n- Un barco horizontal de 2 casillas\n- Un barco verical de 2 casillas\nÂ¡SUERTE!");

			//CREACION BARCOS
			//USO DE DO-WHILE PARA QUE NO SE SOLAPEN.
			int i = (int) (Math.random()*3);
			int j = (int) (Math.random()*5);
			tablero [i][j] = 1;
			tablero [i+1][j] = 1;
			tablero [i+2][j] = 1;
			
			do {
			i = (int) (Math.random()*5);
			j = (int) (Math.random()*3);
			}
			while (tablero [i][j] == 1 || tablero [i][j+1] == 1 || tablero [i][j+2] == 1);
			tablero [i][j] = 2;
			tablero [i][j+1] = 2;
			tablero [i][j+2] = 2;
			
			do {
			i = (int) (Math.random()*4);
			j = (int) (Math.random()*5);
			}
			while (tablero [i][j] == 1 || tablero [i+1][j] == 1 || tablero [i][j] == 2 || tablero [i+1][j] == 2);
			tablero [i][j] = 3;
			tablero [i+1][j] = 3;
			
			do {
			i = (int) (Math.random()*5);
			j = (int) (Math.random()*4);
			}
			while (tablero [i][j] == 1 || tablero [i][j+1] == 1 || tablero [i][j] == 2 || tablero [i][j+1] == 2 || tablero [i][j] == 3 || tablero [i][j+1] == 3);
			tablero [i][j] = 4;
			tablero [i][j+1] = 4;
			
			
			
			//TABLERO DE DISPAROS (rellenado de "*")
			String disparos[][]=new String[5][5];
			for (int x = 0; x<disparos.length; x++) {
				for (int y = 0; y<disparos.length; y++) {
					disparos[x][y]="*";
				}
			}
			
			//Muestra el tablero
			crearTablero(disparos);
			
			//Disparos por coordenadas
			Scanner input=new Scanner(System.in);
			
			//DECLARACION VARIABLES.
			int DisparoBarco1 = 0;
			int DisparoBarco2 = 0;
			int DisparoBarco3 = 0;
			int DisparoBarco4 = 0;
			int penalizacion=0;
			
			//PETICION DE INGRESO DE COORDENADAS, COMPEMPLANDO QUE SE CUMPLA LA SINTAXIS CORRECTA
			do {
				System.out.println("Ingrese una coordenada (nÃºmero, letra):");
				String coordenada=input.nextLine();
				while(!coordenada.equals("0A")&!coordenada.equals("1A")&!coordenada.equals("2A")&!coordenada.equals("3A")&!coordenada.equals("4A")&!coordenada.equals("0B")&!coordenada.equals("1B")&!coordenada.equals("2B")&!coordenada.equals("3B")&!coordenada.equals("4B")&!coordenada.equals("0C")&!coordenada.equals("1C")&!coordenada.equals("2C")&!coordenada.equals("3C")&!coordenada.equals("4C")&!coordenada.equals("0D")&!coordenada.equals("1D")&!coordenada.equals("2D")&!coordenada.equals("3D")&!coordenada.equals("4D")&!coordenada.equals("0E")&!coordenada.equals("1E")&!coordenada.equals("2E")&!coordenada.equals("3E")&!coordenada.equals("4E")){
					System.out.println("Incorrecto, vuelva a ingresar una coordenada (fila, columna):");
					//PENALIZACION AL INGRESAR MAL LAS COORDENADAS.
					System.out.println("Esto conlleva una penalizaciÃ³n que cuenta como un disparo mÃ¡s.");
					penalizacion++;
					coordenada=input.nextLine();
				}
				
				//DISTINTAS POSIBILIDADES AL INSERTAR LAS COORDENADAS CORRECTAS
				if(coordenada.equals("0A")) {
					if(disparos[0][0]!="*") {
						System.out.println("Incorrecto, ya has disparado en esta coordenada");
						//PENALIZACION AL REPETIR LAS COORDENADAS.
						System.out.println("Esto conlleva una penalización que cuenta como un disparo mas.");
						penalizacion++;
					}
					else if (tablero[0][0]==1||tablero[0][0]==2||tablero[0][0]==3||tablero[0][0]==4) {
						System.out.println("Â¡Has dado a un barco!");
						disparos[0][0]="X";   //Se cambia el caracter de la coordenada insertada.
						numeroDisparos++;		//aumenta el numero de disparos
						//Dependiendo del barco asignado que haya se aumenta el valor de DisparoBarcoX y cuando llega a la longitud del barco se aumenta 1 al numero de barcos hundidos
						if(tablero[0][0]==1) {
							DisparoBarco1++;
							if (DisparoBarco1==3)
								numerosBarcosHundidos++;
								System.out.println("¡hundido!");
						}
						if(tablero[0][0]==2) {
							DisparoBarco2++;
							if (DisparoBarco2==3)
								numerosBarcosHundidos++;
						}
						if(tablero[0][0]==3) {
							DisparoBarco3++;
							if (DisparoBarco3==2)
								numerosBarcosHundidos++;
						}
						if(tablero[0][0]==4) {
							DisparoBarco4++;
							if (DisparoBarco4==2)
								numerosBarcosHundidos++;
						}
					}
					else {
						System.out.println("Agua.");
						disparos[0][0]="~";
						numeroDisparos++;
					}
				}
				
				if(coordenada.equals("1A")) {
					if(disparos[1][0]!="*") {
						System.out.println("Incorrecto, ya has disparado en esta coordenada");
						System.out.println("Esto conlleva una penalización que cuenta como un disparo mas.");
						penalizacion++;
					}
					else if (tablero[1][0]==1||tablero[1][0]==2||tablero[1][0]==3||tablero[1][0]==4) {
						System.out.println("Â¡Has dado a un barco!");
						disparos[1][0]="X";
						numeroDisparos++;
						if(tablero[1][0]==1) {
							DisparoBarco1++;
							if (DisparoBarco1==3)
								numerosBarcosHundidos++;
						}
						if(tablero[1][0]==2) {
							DisparoBarco2++;
							if (DisparoBarco2==3)
								numerosBarcosHundidos++;
						}
						if(tablero[1][0]==3) {
							DisparoBarco3++;
							if (DisparoBarco3==2)
								numerosBarcosHundidos++;
						}
						if(tablero[1][0]==4) {
							DisparoBarco4++;
							if (DisparoBarco4==2)
								numerosBarcosHundidos++;
						}
					}
					else {
						System.out.println("Agua.");
						disparos[1][0]="~";
						numeroDisparos++;
					}
				}
				if(coordenada.equals("2A")) {
					if(disparos[2][0]!="*") {
						System.out.println("Incorrecto, ya has disparado en esta coordenada");
						System.out.println("Esto conlleva una penalización que cuenta como un disparo mas.");
						penalizacion++;
					}
					else if (tablero[2][0]==1||tablero[2][0]==2||tablero[2][0]==3||tablero[2][0]==4) {
						System.out.println("Â¡Has dado a un barco!");
						disparos[2][0]="X";
						numeroDisparos++;
						if(tablero[2][0]==1) {
							DisparoBarco1++;
							if (DisparoBarco1==3)
								numerosBarcosHundidos++;
						}
						if(tablero[2][0]==2) {
							DisparoBarco2++;
							if (DisparoBarco2==3)
								numerosBarcosHundidos++;
						}
						if(tablero[2][0]==3) {
							DisparoBarco3++;
							if (DisparoBarco3==2)
								numerosBarcosHundidos++;
						}
						if(tablero[2][0]==4) {
							DisparoBarco4++;
							if (DisparoBarco4==2)
								numerosBarcosHundidos++;
						}
					}
					else {
						System.out.println("Agua.");
						disparos[2][0]="~";
						numeroDisparos++;
					}
				}
				if(coordenada.equals("3A")) {
					if(disparos[3][0]!="*") {
						System.out.println("Incorrecto, ya has disparado en esta coordenada");
						System.out.println("Esto conlleva una penalización que cuenta como un disparo mas.");
						penalizacion++;
					}
						else if (tablero[3][0]==1||tablero[3][0]==2||tablero[3][0]==3||tablero[3][0]==4) {
						System.out.println("Â¡Has dado a un barco!");
						disparos[3][0]="X";
						numeroDisparos++;
						if(tablero[3][0]==1) {
							DisparoBarco1++;
							if (DisparoBarco1==3)
								numerosBarcosHundidos++;
						}
						if(tablero[3][0]==2) {
							DisparoBarco2++;
							if (DisparoBarco2==3)
								numerosBarcosHundidos++;
						}
						if(tablero[3][0]==3) {
							DisparoBarco3++;
							if (DisparoBarco3==2)
								numerosBarcosHundidos++;
						}
						if(tablero[3][0]==4) {
							DisparoBarco4++;
							if (DisparoBarco4==2)
								numerosBarcosHundidos++;
						}
					}
					else {
						System.out.println("Agua.");
						disparos[3][0]="~";
						numeroDisparos++;
					}
				}
				if(coordenada.equals("4A")) {
					if(disparos[4][0]!="*") {
						System.out.println("Incorrecto, ya has disparado en esta coordenada");
						System.out.println("Esto conlleva una penalización que cuenta como un disparo mas.");
						penalizacion++;
					}
					else if (tablero[4][0]==1||tablero[4][0]==2||tablero[4][0]==3||tablero[4][0]==4) {
						System.out.println("Â¡Has dado a un barco!");
						disparos[4][0]="X";
						numeroDisparos++;
						if(tablero[4][0]==1) {
							DisparoBarco1++;
							if (DisparoBarco1==3)
								numerosBarcosHundidos++;
						}
						if(tablero[4][0]==2) {
							DisparoBarco2++;
							if (DisparoBarco2==3)
								numerosBarcosHundidos++;
						}
						if(tablero[4][0]==3) {
							DisparoBarco3++;
							if (DisparoBarco3==2)
								numerosBarcosHundidos++;
						}
						if(tablero[4][0]==4) {
							DisparoBarco4++;
							if (DisparoBarco4==2)
								numerosBarcosHundidos++;
						}
					}
					else {
						System.out.println("Agua.");
						disparos[4][0]="~";
						numeroDisparos++;
					}
				}
				if(coordenada.equals("0B")) {
					if(disparos[0][1]!="*") {
						System.out.println("Incorrecto, ya has disparado en esta coordenada");
						System.out.println("Esto conlleva una penalización que cuenta como un disparo mas.");
						penalizacion++;
					}
						else if (tablero[0][1]==1||tablero[0][1]==2||tablero[0][1]==3||tablero[0][1]==4) {
						System.out.println("Â¡Has dado a un barco!");
						disparos[0][1]="X";
						numeroDisparos++;
						if(tablero[0][1]==1) {
							DisparoBarco1++;
							if (DisparoBarco1==3)
								numerosBarcosHundidos++;
						}
						if(tablero[0][1]==2) {
							DisparoBarco2++;
							if (DisparoBarco2==3)
								numerosBarcosHundidos++;
						}
						if(tablero[0][1]==3) {
							DisparoBarco3++;
							if (DisparoBarco3==2)
								numerosBarcosHundidos++;
						}
						if(tablero[0][1]==4) {
							DisparoBarco4++;
							if (DisparoBarco4==2)
								numerosBarcosHundidos++;
						}
					}
					else {
						System.out.println("Agua.");
						disparos[0][1]="~";
						numeroDisparos++;
					}
				}
				if(coordenada.equals("1B")) {
					if(disparos[1][1]!="*") {
						System.out.println("Incorrecto, ya has disparado en esta coordenada");
						System.out.println("Esto conlleva una penalización que cuenta como un disparo mas.");
						penalizacion++;
					}
						else if (tablero[1][1]==1||tablero[1][1]==2||tablero[1][1]==3||tablero[1][1]==4) {
						System.out.println("Â¡Has dado a un barco!");
						disparos[1][1]="X";
						numeroDisparos++;
						if(tablero[1][1]==1) {
							DisparoBarco1++;
							if (DisparoBarco1==3)
								numerosBarcosHundidos++;
						}
						if(tablero[1][1]==2) {
							DisparoBarco2++;
							if (DisparoBarco2==3)
								numerosBarcosHundidos++;
						}
						if(tablero[1][1]==3) {
							DisparoBarco3++;
							if (DisparoBarco3==2)
								numerosBarcosHundidos++;
						}
						if(tablero[1][1]==4) {
							DisparoBarco4++;
							if (DisparoBarco4==2)
								numerosBarcosHundidos++;
						}
					}
					else {
						System.out.println("Agua.");
						disparos[1][1]="~";
						numeroDisparos++;
					}
				}
				if(coordenada.equals("2B")) {
					if(disparos[2][1]!="*") {
						System.out.println("Incorrecto, ya has disparado en esta coordenada");
						System.out.println("Esto conlleva una penalización que cuenta como un disparo mas.");
						penalizacion++;
					}
						else if (tablero[2][1]==1||tablero[2][1]==2||tablero[2][1]==3||tablero[2][1]==4) {
						System.out.println("Â¡Has dado a un barco!");
						disparos[2][1]="X";
						numeroDisparos++;
						if(tablero[2][1]==1) {
							DisparoBarco1++;
							if (DisparoBarco1==3)
								numerosBarcosHundidos++;
						}
						if(tablero[2][1]==2) {
							DisparoBarco2++;
							if (DisparoBarco2==3)
								numerosBarcosHundidos++;
						}
						if(tablero[2][1]==3) {
							DisparoBarco3++;
							if (DisparoBarco3==2)
								numerosBarcosHundidos++;
						}
						if(tablero[2][1]==4) {
							DisparoBarco4++;
							if (DisparoBarco4==2)
								numerosBarcosHundidos++;
						}
					}
					else {
						System.out.println("Agua.");
						disparos[2][1]="~";
						numeroDisparos++;
					}
				}
				if(coordenada.equals("3B")) {
					if(disparos[3][1]!="*") {
						System.out.println("Incorrecto, ya has disparado en esta coordenada");
						System.out.println("Esto conlleva una penalización que cuenta como un disparo mas.");
						penalizacion++;
					}
						else if (tablero[3][1]==1||tablero[3][1]==2||tablero[3][1]==3||tablero[3][1]==4) {
						System.out.println("Â¡Has dado a un barco!");
						disparos[3][1]="X";
						numeroDisparos++;
						if(tablero[3][1]==1) {
							DisparoBarco1++;
							if (DisparoBarco1==3)
								numerosBarcosHundidos++;
						}
						if(tablero[3][1]==2) {
							DisparoBarco2++;
							if (DisparoBarco2==3)
								numerosBarcosHundidos++;
						}
						if(tablero[3][1]==3) {
							DisparoBarco3++;
							if (DisparoBarco3==2)
								numerosBarcosHundidos++;
						}
						if(tablero[3][1]==4) {
							DisparoBarco4++;
							if (DisparoBarco4==2)
								numerosBarcosHundidos++;
						}
					}
					else {
						System.out.println("Agua.");
						disparos[3][1]="~";
						numeroDisparos++;
					}
				}
				if(coordenada.equals("4B")) {
					if(disparos[4][1]!="*") {
						System.out.println("Incorrecto, ya has disparado en esta coordenada");
						System.out.println("Esto conlleva una penalización que cuenta como un disparo mas.");
						penalizacion++;
					}
						else if (tablero[4][1]==1||tablero[4][1]==2||tablero[4][1]==3||tablero[4][1]==4) {
						System.out.println("Â¡Has dado a un barco!");
						disparos[4][1]="X";
						numeroDisparos++;
						if(tablero[4][1]==1) {
							DisparoBarco1++;
							if (DisparoBarco1==3)
								numerosBarcosHundidos++;
						}
						if(tablero[4][1]==2) {
							DisparoBarco2++;
							if (DisparoBarco2==3)
								numerosBarcosHundidos++;
						}
						if(tablero[4][1]==3) {
							DisparoBarco3++;
							if (DisparoBarco3==2)
								numerosBarcosHundidos++;
						}
						if(tablero[4][1]==4) {
							DisparoBarco4++;
							if (DisparoBarco4==2)
								numerosBarcosHundidos++;
						}
					}
					else {
						System.out.println("Agua.");
						disparos[4][1]="~";
						numeroDisparos++;
					}
				}
				if(coordenada.equals("0C")) {
					if(disparos[0][2]!="*") {
						System.out.println("Incorrecto, ya has disparado en esta coordenada");
						System.out.println("Esto conlleva una penalización que cuenta como un disparo mas.");
						penalizacion++;
					}
						else if (tablero[0][2]==1||tablero[0][2]==2||tablero[0][2]==3||tablero[0][2]==4) {
						System.out.println("Â¡Has dado a un barco!");
						disparos[0][2]="X";
						numeroDisparos++;
						if(tablero[0][2]==1) {
							DisparoBarco1++;
							
							if (DisparoBarco1==3)
								numerosBarcosHundidos++;
						}
						if(tablero[0][2]==2) {
							DisparoBarco2++;
							
							if (DisparoBarco2==3)
								numerosBarcosHundidos++;
						}
						if(tablero[0][2]==3) {
							DisparoBarco3++;
							
							if (DisparoBarco3==2)
								numerosBarcosHundidos++;
						}
						if(tablero[0][2]==4) {
							DisparoBarco4++;
							
							if (DisparoBarco4==2)
								numerosBarcosHundidos++;
						}
					}
					else {
						System.out.println("Agua.");
						disparos[0][2]="~";
						numeroDisparos++;
						
					}
				}
				if(coordenada.equals("1C")) {
					if(disparos[1][2]!="*") {
						System.out.println("Incorrecto, ya has disparado en esta coordenada");
						System.out.println("Esto conlleva una penalización que cuenta como un disparo mas.");
						penalizacion++;
					}
						else if (tablero[1][2]==1||tablero[1][2]==2||tablero[1][2]==3||tablero[1][2]==4) {
						System.out.println("Â¡Has dado a un barco!");
						disparos[1][2]="X";
						numeroDisparos++;
						if(tablero[1][2]==1) {
							DisparoBarco1++;
							
							if (DisparoBarco1==3)
								numerosBarcosHundidos++;
						}
						if(tablero[1][2]==2) {
							DisparoBarco2++;
							
							if (DisparoBarco2==3)
								numerosBarcosHundidos++;
						}
						if(tablero[1][2]==3) {
							DisparoBarco3++;
							
							if (DisparoBarco3==2)
								numerosBarcosHundidos++;
						}
						if(tablero[1][2]==4) {
							DisparoBarco4++;
							
							if (DisparoBarco4==2)
								numerosBarcosHundidos++;
						}
					}
					else {
						System.out.println("Agua.");
						disparos[1][2]="~";
						numeroDisparos++;
						
					}
				}
				if(coordenada.equals("2C")) {
					if(disparos[2][2]!="*") {
						System.out.println("Incorrecto, ya has disparado en esta coordenada");
						System.out.println("Esto conlleva una penalización que cuenta como un disparo mas.");
						penalizacion++;
					}
						else if (tablero[2][2]==1||tablero[2][2]==2||tablero[2][2]==3||tablero[2][2]==4) {
						System.out.println("Â¡Has dado a un barco!");
						disparos[2][2]="X";
						numeroDisparos++;
						if(tablero[2][2]==1) {
							DisparoBarco1++;
							
							if (DisparoBarco1==3)
								numerosBarcosHundidos++;
						}
						if(tablero[2][2]==2) {
							DisparoBarco2++;
							
							if (DisparoBarco2==3)
								numerosBarcosHundidos++;
						}
						if(tablero[2][2]==3) {
							DisparoBarco3++;
							
							if (DisparoBarco3==2)
								numerosBarcosHundidos++;
						}
						if(tablero[2][2]==4) {
							DisparoBarco4++;
							
							if (DisparoBarco4==2)
								numerosBarcosHundidos++;
						}
					}
					else {
						System.out.println("Agua.");
						disparos[2][2]="~";
						numeroDisparos++;
						
					}
				}
				if(coordenada.equals("3C")) {
					if(disparos[3][2]!="*") {
						System.out.println("Incorrecto, ya has disparado en esta coordenada");
						System.out.println("Esto conlleva una penalización que cuenta como un disparo mas.");
						penalizacion++;
					}
						else if (tablero[3][2]==1||tablero[3][2]==2||tablero[3][2]==3||tablero[3][2]==4) {
						System.out.println("Â¡Has dado a un barco!");
						disparos[3][2]="X";
						numeroDisparos++;
						if(tablero[3][2]==1) {
							DisparoBarco1++;
							
							if (DisparoBarco1==3)
								numerosBarcosHundidos++;
						}
						if(tablero[3][2]==2) {
							DisparoBarco2++;
							
							if (DisparoBarco2==3)
								numerosBarcosHundidos++;
						}
						if(tablero[3][2]==3) {
							DisparoBarco3++;
							
							if (DisparoBarco3==2)
								numerosBarcosHundidos++;
						}
						if(tablero[3][2]==4) {
							DisparoBarco4++;
							
							if (DisparoBarco4==2)
								numerosBarcosHundidos++;
						}
					}
					else {
						System.out.println("Agua.");
						disparos[3][2]="~";
						numeroDisparos++;
						
					}
				}
				if(coordenada.equals("4C")) {
					if(disparos[4][2]!="*") {
						System.out.println("Incorrecto, ya has disparado en esta coordenada");
						System.out.println("Esto conlleva una penalización que cuenta como un disparo mas.");
						penalizacion++;
					}
						else if (tablero[4][2]==1||tablero[4][2]==2||tablero[4][2]==3||tablero[4][2]==4) {
						System.out.println("Â¡Has dado a un barco!");
						disparos[4][2]="X";
						numeroDisparos++;
						if(tablero[4][2]==1) {
							DisparoBarco1++;
							
							if (DisparoBarco1==3)
								numerosBarcosHundidos++;
						}
						if(tablero[4][2]==2) {
							DisparoBarco2++;
							
							if (DisparoBarco2==3)
								numerosBarcosHundidos++;
						}
						if(tablero[4][2]==3) {
							DisparoBarco3++;
							
							if (DisparoBarco3==2)
								numerosBarcosHundidos++;
						}
						if(tablero[4][2]==4) {
							DisparoBarco4++;
							
							if (DisparoBarco4==2)
								numerosBarcosHundidos++;
						}
					}
					else {
						System.out.println("Agua.");
						disparos[4][2]="~";
						numeroDisparos++;
						
					}
				}
				if(coordenada.equals("0D")) {
					if(disparos[0][3]!="*") {
						System.out.println("Incorrecto, ya has disparado en esta coordenada");
						System.out.println("Esto conlleva una penalización que cuenta como un disparo mas.");
						penalizacion++;
					}
						else if (tablero[0][3]==1||tablero[0][3]==2||tablero[0][3]==3||tablero[0][3]==4) {
						System.out.println("Â¡Has dado a un barco!");
						disparos[0][3]="X";
						numeroDisparos++;
						if(tablero[0][3]==1) {
							DisparoBarco1++;
							
							if (DisparoBarco1==3)
								numerosBarcosHundidos++;
						}
						if(tablero[0][3]==2) {
							DisparoBarco2++;
							
							if (DisparoBarco2==3)
								numerosBarcosHundidos++;
						}
						if(tablero[0][3]==3) {
							DisparoBarco3++;
							
							if (DisparoBarco3==2)
								numerosBarcosHundidos++;
						}
						if(tablero[0][3]==4) {
							DisparoBarco4++;
							
							if (DisparoBarco4==2)
								numerosBarcosHundidos++;
						}
					}
					if(tablero[0][3]==5)
						System.out.println("Esta coordenada ya la has dicho, intÃ©ntalo de nuevo.");
					else {
						System.out.println("Agua.");
						disparos[0][3]="~";
						numeroDisparos++;
						
					}
				}
				if(coordenada.equals("1D")) {
					if(disparos[1][3]!="*") {
						System.out.println("Incorrecto, ya has disparado en esta coordenada");
						System.out.println("Esto conlleva una penalización que cuenta como un disparo mas.");
						penalizacion++;
					}
						else if (tablero[1][3]==1||tablero[1][3]==2||tablero[1][3]==3||tablero[1][3]==4) {
						System.out.println("Â¡Has dado a un barco!");
						disparos[1][3]="X";
						numeroDisparos++;
						if(tablero[1][3]==1) {
							DisparoBarco1++;
							
							if (DisparoBarco1==3)
								numerosBarcosHundidos++;
						}
						if(tablero[1][3]==2) {
							DisparoBarco2++;
							
							if (DisparoBarco2==3)
								numerosBarcosHundidos++;
						}
						if(tablero[1][3]==3) {
							DisparoBarco3++;
							
							if (DisparoBarco3==2)
								numerosBarcosHundidos++;
						}
						if(tablero[1][3]==4) {
							DisparoBarco4++;
							
							if (DisparoBarco4==2)
								numerosBarcosHundidos++;
						}
					}
					else {
						System.out.println("Agua.");
						disparos[1][3]="~";
						numeroDisparos++;
						
					}
				}
				if(coordenada.equals("2D")) {
					if(disparos[2][3]!="*") {
						System.out.println("Incorrecto, ya has disparado en esta coordenada");
						System.out.println("Esto conlleva una penalización que cuenta como un disparo mas.");
						penalizacion++;
					}
						else if (tablero[2][3]==1||tablero[2][3]==2||tablero[2][3]==3||tablero[2][3]==4) {
						System.out.println("Â¡Has dado a un barco!");
						disparos[2][3]="X";
						numeroDisparos++;
						if(tablero[2][3]==1) {
							DisparoBarco1++;
							
							if (DisparoBarco1==3)
								numerosBarcosHundidos++;
						}
						if(tablero[2][3]==2) {
							DisparoBarco2++;
							
							if (DisparoBarco2==3)
								numerosBarcosHundidos++;
						}
						if(tablero[2][3]==3) {
							DisparoBarco3++;
							
							if (DisparoBarco3==2)
								numerosBarcosHundidos++;
						}
						if(tablero[2][3]==4) {
							DisparoBarco4++;
							
							if (DisparoBarco4==2)
								numerosBarcosHundidos++;
						}
					}
					else {
						System.out.println("Agua.");
						disparos[2][3]="~";
						numeroDisparos++;
						
					}
				}
				if(coordenada.equals("3D")) {
					if(disparos[3][3]!="*") {
						System.out.println("Incorrecto, ya has disparado en esta coordenada");
						System.out.println("Esto conlleva una penalización que cuenta como un disparo mas.");
						penalizacion++;
					}
						else if (tablero[3][3]==1||tablero[3][3]==2||tablero[3][3]==3||tablero[3][3]==4) {
						System.out.println("Â¡Has dado a un barco!");
						disparos[3][3]="X";
						numeroDisparos++;
						if(tablero[3][3]==1) {
							DisparoBarco1++;
							
							if (DisparoBarco1==3)
								numerosBarcosHundidos++;
						}
						if(tablero[3][3]==2) {
							DisparoBarco2++;
							
							if (DisparoBarco2==3)
								numerosBarcosHundidos++;
						}
						if(tablero[3][3]==3) {
							DisparoBarco3++;
							
							if (DisparoBarco3==2)
								numerosBarcosHundidos++;
						}
						if(tablero[3][3]==4) {
							DisparoBarco4++;
							
							if (DisparoBarco4==2)
								numerosBarcosHundidos++;
						}
					}
					else {
						System.out.println("Agua.");
						disparos[3][3]="~";
						numeroDisparos++;
						
					}
				}
				if(coordenada.equals("4D")) {
					if(disparos[4][3]!="*") {
						System.out.println("Incorrecto, ya has disparado en esta coordenada");
						System.out.println("Esto conlleva una penalización que cuenta como un disparo mas.");
						penalizacion++;
					}
						else if (tablero[4][3]==1||tablero[4][3]==2||tablero[4][3]==3||tablero[4][3]==4) {
						System.out.println("Â¡Has dado a un barco!");
						disparos[4][3]="X";
						numeroDisparos++;
						if(tablero[4][3]==1) {
							DisparoBarco1++;
							
							if (DisparoBarco1==3)
								numerosBarcosHundidos++;
						}
						if(tablero[4][3]==2) {
							DisparoBarco2++;
							
							if (DisparoBarco2==3)
								numerosBarcosHundidos++;
						}
						if(tablero[4][3]==3) {
							DisparoBarco3++;
							
							if (DisparoBarco3==2)
								numerosBarcosHundidos++;
						}
						if(tablero[4][3]==4) {
							DisparoBarco4++;
							
							if (DisparoBarco4==2)
								numerosBarcosHundidos++;
						}
					}
					else {
						System.out.println("Agua.");
						disparos[4][3]="~";
						numeroDisparos++;
						
					}
				}
				if(coordenada.equals("0E")) {
					if(disparos[0][4]!="*") {
						System.out.println("Incorrecto, ya has disparado en esta coordenada");
						System.out.println("Esto conlleva una penalización que cuenta como un disparo mas.");
						penalizacion++;
					}
						else if (tablero[0][4]==1||tablero[0][4]==2||tablero[0][4]==3||tablero[0][4]==4) {
						System.out.println("Â¡Has dado a un barco!");
						disparos[0][4]="X";
						numeroDisparos++;
						if(tablero[0][4]==1) {
							DisparoBarco1++;
							
							if (DisparoBarco1==3)
								numerosBarcosHundidos++;
						}
						if(tablero[0][4]==2) {
							DisparoBarco2++;
							
							if (DisparoBarco2==3)
								numerosBarcosHundidos++;
						}
						if(tablero[0][4]==3) {
							DisparoBarco3++;
							
							if (DisparoBarco3==2)
								numerosBarcosHundidos++;
						}
						if(tablero[0][4]==4) {
							DisparoBarco4++;
							
							if (DisparoBarco4==2)
								numerosBarcosHundidos++;
						}
					}
					else {
						System.out.println("Agua.");
						disparos[0][4]="~";
						numeroDisparos++;
						
					}
				}
				if(coordenada.equals("1E")) {
					if(disparos[1][4]!="*") {
						System.out.println("Incorrecto, ya has disparado en esta coordenada");
						System.out.println("Esto conlleva una penalización que cuenta como un disparo mas.");
						penalizacion++;
					}
						else 
					if (tablero[1][4]==1||tablero[1][4]==2||tablero[1][4]==3||tablero[1][4]==4) {
						System.out.println("Â¡Has dado a un barco!");
						disparos[1][4]="X";
						numeroDisparos++;
						if(tablero[1][4]==1) {
							DisparoBarco1++;
							
							if (DisparoBarco1==3)
								numerosBarcosHundidos++;
						}
						if(tablero[1][4]==2) {
							DisparoBarco2++;
							
							if (DisparoBarco2==3)
								numerosBarcosHundidos++;
						}
						if(tablero[1][4]==3) {
							DisparoBarco3++;
							
							if (DisparoBarco3==2)
								numerosBarcosHundidos++;
						}
						if(tablero[1][4]==4) {
							DisparoBarco4++;
							
							if (DisparoBarco4==2)
								numerosBarcosHundidos++;
						}
					}
					else {
						System.out.println("Agua.");
						disparos[1][4]="~";
						numeroDisparos++;
						
					}
				}
				if(coordenada.equals("2E")) {
					if(disparos[2][4]!="*") {
						System.out.println("Incorrecto, ya has disparado en esta coordenada");
						System.out.println("Esto conlleva una penalización que cuenta como un disparo mas.");
						penalizacion++;
					}
						else if (tablero[2][4]==1||tablero[2][4]==2||tablero[2][4]==3||tablero[2][4]==4) {
						System.out.println("Â¡Has dado a un barco!");
						disparos[2][4]="X";
						numeroDisparos++;
						if(tablero[2][4]==1) {
							DisparoBarco1++;
							
							if (DisparoBarco1==3)
								numerosBarcosHundidos++;
						}
						if(tablero[2][4]==2) {
							DisparoBarco2++;
							
							if (DisparoBarco2==3)
								numerosBarcosHundidos++;
						}
						if(tablero[2][4]==3) {
							DisparoBarco3++;
							
							if (DisparoBarco3==2)
								numerosBarcosHundidos++;
						}
						if(tablero[2][4]==4) {
							DisparoBarco4++;
							
							if (DisparoBarco4==2)
								numerosBarcosHundidos++;
						}
					}
					else {
						System.out.println("Agua.");
						disparos[2][4]="~";
						numeroDisparos++;
						
					}
				}
				if(coordenada.equals("3E")) {
					if(disparos[3][4]!="*") {
						System.out.println("Incorrecto, ya has disparado en esta coordenada");
						System.out.println("Esto conlleva una penalización que cuenta como un disparo mas.");
						penalizacion++;
					}
						else if (tablero[3][4]==1||tablero[3][4]==2||tablero[3][4]==3||tablero[3][4]==4) {
						System.out.println("Â¡Has dado a un barco!");
						disparos[3][4]="X";
						numeroDisparos++;
						if(tablero[3][4]==1) {
							DisparoBarco1++;
							
							if (DisparoBarco1==3)
								numerosBarcosHundidos++;
						}
						if(tablero[3][4]==2) {
							DisparoBarco2++;
							
							if (DisparoBarco2==3)
								numerosBarcosHundidos++;
						}
						if(tablero[3][4]==3) {
							DisparoBarco3++;
							
							if (DisparoBarco3==2)
								numerosBarcosHundidos++;
						}
						if(tablero[3][4]==4) {
							DisparoBarco4++;
							
							if (DisparoBarco4==2)
								numerosBarcosHundidos++;
						}
					}
					else {
						System.out.println("Agua.");
						disparos[3][4]="~";
						numeroDisparos++;
						
					}
				}
				if(coordenada.equals("4E")) {
					if(disparos[4][4]!="*") {
						System.out.println("Incorrecto, ya has disparado en esta coordenada");
						System.out.println("Esto conlleva una penalización que cuenta como un disparo mas.");
						penalizacion++;
					}
						else if (tablero[4][4]==1||tablero[4][4]==2||tablero[4][4]==3||tablero[4][4]==4) {
						System.out.println("Â¡Has dado a un barco!");
						disparos[4][4]="X";
						numeroDisparos++;
						if(tablero[4][4]==1) {
							DisparoBarco1++;
							
							if (DisparoBarco1==3)
								numerosBarcosHundidos++;
						}
						if(tablero[4][4]==2) {
							DisparoBarco2++;
							
							if (DisparoBarco2==3)
								numerosBarcosHundidos++;
						}
						if(tablero[4][4]==3) {
							DisparoBarco3++;
							
							if (DisparoBarco3==2)
								numerosBarcosHundidos++;
						}
						if(tablero[4][4]==4) {
							DisparoBarco4++;
							
							if (DisparoBarco4==2)
								numerosBarcosHundidos++;
						}
					}
					else {
						System.out.println("Agua.");
						disparos[4][4]="~";
						numeroDisparos++;
						
					}
				}
				//Declaracion metodo
				crearTablero(disparos);
				
				//Muestra en pantalla el numero de disparos y numero de barcos hundidos
				System.out.println("Numero de disparos: " + numeroDisparos);
				System.out.println("Numero de barcos hundidos: " + numerosBarcosHundidos);
			}
			// este barco no finaliza hasta que la cantidad de barcos hundidos sea >=4
			while(numerosBarcosHundidos<4);
			System.out.println("JUEGO FINALIZADO. Â¡HAS DERRIBADO TODOS LOS BARCOS!");
			System.out.println(partida +  " juego realizado." );
			System.out.println("PenalizaciÃ³n aplicada tras ingresar mal las coordenadas: " + penalizacion);
			int resultado=numeroDisparos+penalizacion;
			System.out.println("NÃºmero final de disparos aplicando la penaliaciÃ³n: " + resultado);
		}
		
		//MODO AVANZADO
		else if(respuesta==2) {
			partida++;
			System.out.println("Bienvenido al modo avanzado\nIngrese las dimensiones del tablero con el que desea jugar (entre 5 y 10)");
			
			Scanner input = new Scanner(System.in);
			int d = input.nextInt();
			while (d<5 || d>10) {
				System.out.println("Valor incorrecto, introduzca un nÃºmero entre 5 y 10:");
				d = input.nextInt();
			}
			int tableroa [][] = new int[d][d];
		
			int i = (int) (Math.random()*(d-2));
			int j = (int) (Math.random()*(d));
			tableroa [i][j] = 1;
			tableroa [i+1][j] = 1;
			tableroa [i+2][j] = 1;
			
			do {
				i = (int) (Math.random()*(d));
				j = (int) (Math.random()*(d-2));
				}
				while (tableroa [i][j] == 1 || tableroa [i][j+1] == 1 || tableroa [i][j+2] == 1);
				tableroa [i][j] = 2;
				tableroa [i][j+1] = 2;
				tableroa [i][j+2] = 2;
				
			do {
			i = (int) (Math.random()*(d-1));
			j = (int) (Math.random()*(d));
			}
			while (tableroa [i][j] == 1 || tableroa [i+1][j] == 1 || tableroa [i][j] == 2 || tableroa [i+1][j] == 2);
			tableroa [i][j] = 3;
			tableroa [i+1][j] = 3;
			
			do {
			i = (int) (Math.random()*(d));
			j = (int) (Math.random()*(d-1));
			}
			while (tableroa [i][j] == 1 || tableroa [i][j+1] == 1 || tableroa [i][j] == 2 || tableroa [i][j+1] == 2 || tableroa [i][j] == 3 || tableroa [i][j+1] == 3);
			tableroa [i][j] = 4;
			tableroa [i][j+1] = 4;
			
			//TABLERO DE DISPAROS AVANZADO
			String disparosa[][]=new String[d][d];
			for (int x = 0; x<disparosa.length; x++) {
				for (int y = 0; y<disparosa.length; y++) {
					disparosa[x][y]="*";
				}
			}
			
			crearTablero(disparosa);
			for(int p=0; p<i; p++) {
				for(int q=0; q<j;q++) {
					//System.out.print(tablero[i][j] + " \t");
				}
				//System.out.println();
			}
			System.out.println("NÃºmero de disparos: " + numeroDisparos);
			System.out.println("NÃºmero de barcos hundidos: " + numerosBarcosHundidos);
		
		
		
		}
		
		else {
			System.out.println("OpciÃ³n incorrecte vuelva a introducir una opciÃ³n vÃ¡lida:");
		} 
		}
		while(respuesta!=1 && respuesta!=2);
		
		do {
			System.out.println("Â¿Desea jugar otra partida?\n1 <= Â¡SÃ­! Â¡Quiero jugar de nuevo!\n2 <= No, ya me he cansado.");
			Scanner input2 = new Scanner(System.in);
			nuevapartida = input2.nextLine();
			if(nuevapartida.equals("1")) {
				contadorPartidas++;
				System.out.println("NÃºmero de partidas jugadas = "+contadorPartidas+".");
			}
			if(nuevapartida.equals("2")) {
				contadorPartidas++;
				System.out.println("NÃºmero de partidas jugadas = "+contadorPartidas+".");
			}
			if(!nuevapartida.equals("1")&&!nuevapartida.equals("2"))
				System.out.println("No te hemos entendido.");
		}
		while(!nuevapartida.equals("1") && !nuevapartida.equals("2"));
	    }
	    while(nuevapartida.equals("1"));
	}
	
	public static void crearTablero(String [][]tablero) {
	    int numero = 0;
	    System.out.println("    A  B  C  D  E");
	    System.out.println("   ---------------");
	    for(int i = 0; i < tablero.length; i++) {
	        System.out.print(numero + " |");
	        numero++;
	        for(int j = 0; j < tablero[i].length; j++) {
	            System.out.print(" " + tablero [i][j] + " ");
	        }
	        System.out.print("|");
	        System.out.println(" ");
	    }
	    System.out.println("   ---------------");
	}

}

