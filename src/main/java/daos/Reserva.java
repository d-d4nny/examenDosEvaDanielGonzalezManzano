package daos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import jakarta.persistence.*;


/**
 * Clase que representa una Reserva de vajillas en la base de datos.
 * Contiene métodos para operaciones CRUD sobre reservas.
 * También está mapeada como entidad en la base de datos.
 * 
 * @author [daniel]
 * @version 1.0
 * @since [14/12/23]
 */
@Entity
@Table(name = "reservas", schema = "esqExaDos" )
public class Reserva {
	
	@Id
	@Column(name = "idReservas", nullable = false)
	private int id;

	@Column(name = "fchReserva")
	private Calendar fecha;

	@ManyToMany(mappedBy = "listaReservas")
	private List<Vajilla> listaVajillas;
	
	
	public Reserva() {
		
	}

	/**
     * Constructor que inicializa una Reserva con una fecha dada.
     *
     * @param fecha Fecha de la reserva
     */
	public Reserva(Calendar fecha) {
		super();
		this.fecha = fecha;
		this.listaVajillas = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public List<Vajilla> getListaVajillas() {
		return listaVajillas;
	}

	public void setListaVajillas(List<Vajilla> listaVajillas) {
		this.listaVajillas = listaVajillas;
	}

	
	//Metodos crud
	
	/**
     * Muestra todos los registros de reservas presentes en la base de datos.
     *
     * @param emf     EntityManagerFactory para acceder a la base de datos
     * @param reserva Objeto Reserva (no utilizado)
     */
	public static void mostrarTodos(EntityManagerFactory emf, Reserva reserva) {
		EntityManager em = emf.createEntityManager();
		
		List<Reserva> reservas = em.createQuery("SELECT u FROM Reserva u", Reserva.class).getResultList();
    	
    	for (Reserva reserva1 : reservas) {
            detallesReserva(reserva1);
        }
	}
	
	/**
     * Crea una nueva reserva en la base de datos.
     *
     * @param emf     EntityManagerFactory para acceder a la base de datos
     * @param reserva Objeto Reserva a crear
     */
	public static void createReserva(EntityManagerFactory emf, Reserva reserva) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(reserva);
		em.getTransaction().commit();
		em.close();
	}

	/**
     * Busca una reserva por su ID en la base de datos.
     *
     * @param emf EntityManagerFactory para acceder a la base de datos
     * @param id  ID de la reserva a buscar
     * @return Objeto Reserva encontrado por su ID
     */
	public static Reserva selectReservaId(EntityManagerFactory emf, int id) {
		EntityManager em = emf.createEntityManager();
		return em.find(Reserva.class, id);
	}

	/**
     * Actualiza una reserva en la base de datos.
     *
     * @param emf     EntityManagerFactory para acceder a la base de datos
     * @param reserva Objeto Reserva a actualizar
     */
	public static void updateReserva(EntityManagerFactory emf, Reserva reserva) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(reserva);
		em.getTransaction().commit();
		em.close();
	}

	/**
     * Elimina una reserva de la base de datos por su ID.
     *
     * @param emf EntityManagerFactory para acceder a la base de datos
     * @param id  ID de la reserva a eliminar
     */
	public static void deleteReserva(EntityManagerFactory emf, int id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Reserva vajilla = em.find(Reserva.class, id);
		if (vajilla != null) {
			em.remove(vajilla);
		}
		em.getTransaction().commit();
		em.close();
	}
	
	/**
     * Muestra los detalles de una reserva (ID y fecha).
     *
     * @param reserva Reserva de la que se mostrarán los detalles
     */
	private static void detallesReserva(Reserva reserva) {
	    System.out.println("-------------------");
	    System.out.println("ID: " + reserva.getId());
	    System.out.println("Fecha: " + reserva.getFecha());
	}
	
}
