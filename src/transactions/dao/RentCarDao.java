package transactions.dao;

/**
 * Created by andrei on 26.11.17.
 */
public class RentCarDao {
    private static RentCarDao INSTANCE;

    public static RentCarDao getInstance() {
        if (INSTANCE == null) {
            synchronized (RentCarDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RentCarDao();
                }
            }
        }
        return INSTANCE;
    }
}
