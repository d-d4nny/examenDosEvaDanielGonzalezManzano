package utils;

/**
 * Clase utilitaria para mostrar un menú en la consola.
 */
public class Menu {

	/**
     * Muestra el menú en la consola con varias opciones numeradas.
     */
	public static void mostrarMenu() {
        System.out.println("\n----- Menú -----");
        System.out.println("1. Alta de vajilla");
        System.out.println("2. Eliminar vajilla");
        System.out.println("3. Modificar stock");
        System.out.println("4. Mostrar stock");
        System.out.println("5. Crear reserva");
        System.out.println("6. Cerrar la aplicación");
        System.out.println("----------------\n");
    }
}
