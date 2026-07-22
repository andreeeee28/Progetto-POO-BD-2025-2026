package implementazionePostgresDAO;
import dao.EsempioDAO;
import Database.ConnessioneDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * The type Esempio implementazione postgres dao.
 */
public class EsempioImplementazionePostgresDAO implements EsempioDAO {

	private Connection connection;

    /**
     * Instantiates a new Esempio implementazione postgres dao.
     */
    public EsempioImplementazionePostgresDAO() {
		try {
			connection = ConnessioneDatabase.getInstance().connection;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


}
