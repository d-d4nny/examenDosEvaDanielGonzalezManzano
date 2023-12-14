package controladores;

import java.util.Calendar;
import java.util.Scanner;

import daos.*;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import utils.Menu;


/**
 * Esta clase representa el controlador principal que gestiona las operaciones de la aplicación.
 * Permite realizar operaciones CRUD en la entidad Vajilla y reservar vajillas.
 *
 * @author [daniel]
 * @version 1.0
 * @since [14/12/23]
 */
public class main {

	/**
     * Método principal que inicia la aplicación y muestra un menú de opciones.
     *
     * @param args Argumentos de línea de comandos (no utilizados)
     */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

		Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            Menu.mostrarMenu();
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            int id, stock;
            
            switch (opcion) {
                case 1:
                	
                	System.out.println("Has seleccionado: Alta de vajilla");

                    System.out.print("Ingrese el nombre: ");
                    String nombre = scanner.nextLine();

                    System.out.print("Ingrese la descripción: ");
                    String descripcion = scanner.nextLine();

                    System.out.print("Ingrese la cantidad: ");
                    int cantidad = scanner.nextInt();
                    scanner.nextLine();

                    Vajilla nuevaVajilla = new Vajilla(nombre+descripcion, nombre, descripcion, cantidad);    

                    Vajilla.createVajilla(emf, nuevaVajilla);
                    break;
                case 2:
                	
                	System.out.println("Has seleccionado: Eliminar vajilla");
                	
                	System.out.print("Ingrese el id: ");
                	id = scanner.nextInt();
                    scanner.nextLine();                 
                    
                    System.out.println("Has seleccionado la siguiente vajilla:");
                    
                    Vajilla.mostrarUno(emf, id);
                    
                    System.out.println("Si de verdad desea borrarla pulse una tecla:");
                    scanner.nextLine(); 
                    Vajilla.deleteVajilla(emf, id);
                    
                    break;
                case 3:
                    
                	System.out.println("Has seleccionado: Retirar stock");
                	
                	System.out.print("Ingrese el id: ");
                	id = scanner.nextInt();
                    scanner.nextLine();   
                    
                    Vajilla vajillaRecuperada = Vajilla.selectVajillaId(emf, id);
                    
                    System.out.print("El stock actual es:" + vajillaRecuperada.getCantidad());
                    System.out.print("Ingrese el stock a retirar: ");
                	stock = scanner.nextInt();
                    scanner.nextLine();  
                    
                    
                	
                	vajillaRecuperada.setCantidad(vajillaRecuperada.getCantidad() - stock);
            		Vajilla.updateVajilla(emf, vajillaRecuperada);
            		
                    break;
                case 4:

                	System.out.println("Has seleccionado: Mostrar stock");
                	
                	Vajilla.mostrarTodos(emf);
                	
                    break;
                case 5:
                    
                	System.out.println("Has seleccionado: Nueva Reserva");                
                	
                	System.out.print("Ingrese el id: ");
                	id = scanner.nextInt();
                    scanner.nextLine();                 
                    
                    System.out.println("Has seleccionado la siguiente vajilla:");
                    
                    Vajilla.mostrarUno(emf, id);
                    
                    Vajilla vajillaReserva = Vajilla.selectVajillaId(emf, id);
                    
                    Reserva nuevaReserva = new Reserva(Calendar.getInstance());
                    
                    nuevaReserva.getListaVajillas().add(vajillaReserva);
                    
                    vajillaReserva.getListaReservas().add(nuevaReserva);
                    
                    Vajilla.createVajilla(emf, vajillaReserva);

                    break;
                case 6:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (opcion != 6);
        
        scanner.close();
	}

}
