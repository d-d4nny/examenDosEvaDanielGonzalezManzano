package daos;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

/**
 * Clase que representa una Vajilla en la base de datos.
 * Contiene métodos para operaciones CRUD sobre vajillas.
 * También está mapeada como entidad en la base de datos.
 * 
 * @author [daniel]
 * @version 1.0
 * @since [14/12/23]
 */
@Entity
@Table(name = "vajillas", schema = "esqExaDos")
public class Vajilla {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idVajilla", nullable = false)
	private int id;
	
	@Column(name = "codigoVajilla")
	private String codigo;
	
	@Column(name = "nombreVajilla")
	private String nombre;
	
	@Column(name = "descripcionVajilla")
	private String descripcion;
	
	@Column(name = "cantidadVajilla")
	private int cantidad;
	
	@JoinTable(name = "Rel_Reserva_Vajilla", 
			joinColumns = @JoinColumn(name = "idVajilla"), 
			inverseJoinColumns = @JoinColumn(name = "idReserva"),
			schema = "esqExaDos")
	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<Reserva> listaReservas;

	
	public Vajilla() {
		
	}


	/**
	 * Constructor de la clase Vajilla que inicializa una instancia de Vajilla con los detalles proporcionados.
	 * 
	 * @param codigo      Código de identificación de la vajilla
	 * @param nombre      Nombre de la vajilla
	 * @param descripcion Descripción de la vajilla
	 * @param cantidad    Cantidad disponible de la vajilla
	 */
	public Vajilla(String codigo, String nombre, String descripcion, int cantidad) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.listaReservas = new ArrayList<>();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public List<Reserva> getListaReservas() {
		return listaReservas;
	}


	public void setListaReservas(List<Reserva> listaReservas) {
		this.listaReservas = listaReservas;
	}
	
	
	//Metodos crud
	
	/**
     * Muestra todos los registros de vajillas presentes en la base de datos.
     *
     * @param emf EntityManagerFactory para acceder a la base de datos
     */
	public static void mostrarTodos(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		
		List<Vajilla> vajillas = em.createQuery("SELECT u FROM Vajilla u", Vajilla.class).getResultList();
    	
    	for (Vajilla vajilla1 : vajillas) {
            detallesVajilla(vajilla1);
        }
	}
	
	/**
     * Muestra los detalles de una vajilla según su ID.
     *
     * @param emf EntityManagerFactory para acceder a la base de datos
     * @param id  ID de la vajilla a mostrar
     */
	public static void mostrarUno(EntityManagerFactory emf, int id) {
	    EntityManager em = emf.createEntityManager();
	    
	    Vajilla vajilla = em.find(Vajilla.class, id);
	    
	    if (vajilla != null) {
	        detallesVajilla(vajilla);
	    } else {
	        System.out.println("No se encontró ninguna vajilla con el ID proporcionado.");
	    }
	}
	
	/**
     * Crea una nueva vajilla en la base de datos.
     *
     * @param emf     EntityManagerFactory para acceder a la base de datos
     * @param vajilla Objeto Vajilla a crear
     */
	public static void createVajilla(EntityManagerFactory emf, Vajilla vajilla) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(vajilla);
		em.getTransaction().commit();
		em.close();
	}

	/**
     * Busca una vajilla por su ID en la base de datos.
     *
     * @param emf EntityManagerFactory para acceder a la base de datos
     * @param id  ID de la vajilla a buscar
     * @return Objeto Vajilla encontrado por su ID
     */
	public static Vajilla selectVajillaId(EntityManagerFactory emf, int id) {
		EntityManager em = emf.createEntityManager();
		return em.find(Vajilla.class, id);
	}

	/**
     * Actualiza una vajilla en la base de datos.
     *
     * @param emf     EntityManagerFactory para acceder a la base de datos
     * @param vajilla Objeto Vajilla a actualizar
     */
	public static void updateVajilla(EntityManagerFactory emf, Vajilla vajilla) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(vajilla);
		em.getTransaction().commit();
		em.close();
	}

	/**
     * Elimina una vajilla de la base de datos por su ID.
     *
     * @param emf EntityManagerFactory para acceder a la base de datos
     * @param id  ID de la vajilla a eliminar
     */
	public static void deleteVajilla(EntityManagerFactory emf, int id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Vajilla reserva = em.find(Vajilla.class, id);
		if (reserva != null) {
			em.remove(reserva);
		}
		em.getTransaction().commit();
		em.close();
	}
	
	/**
     * Muestra los detalles de una vajilla (ID, código, nombre, descripción, cantidad).
     *
     * @param vajilla Vajilla de la que se mostrarán los detalles
     */
	private static void detallesVajilla(Vajilla vajilla) {
	    System.out.println("-------------------");
	    System.out.println("ID: " + vajilla.getId());
	    System.out.println("Codigo: " + vajilla.getCodigo());
	    System.out.println("Nombre: " + vajilla.getNombre());
	    System.out.println("Descripcion: " + vajilla.getDescripcion());
	    System.out.println("Cantidad: " + vajilla.getCantidad());
	}


	@Override
	public String toString() {
		return "Vajilla [id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", cantidad=" + cantidad + ", listaReservas=" + listaReservas + "]";
	}
	
	
}
