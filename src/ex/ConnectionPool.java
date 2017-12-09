package transactions.connection;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by andrei on 26.11.17.
 */
public class ConnectionPool
{
    protected GenericObjectPool connectionPool;

    public void initConnectionPool( String driver, String dbURL, String dbUser, String dbPswd )
    {
        try
        {
            Class.forName( driver ).newInstance();//создаем новый объект класса драйвера,
            //тем самым инициализируя его

            connectionPool = new GenericObjectPool( null );//создаем GenericObjectPool
            //создаем connection factory ("фабрика соединений" - объект который будет создавать соединения)
            ConnectionFactory connectionFactory = new DriverManagerConnectionFactory( dbURL, dbUser, dbPswd );

            //создаем PoolableConnectionFactory
            new PoolableConnectionFactory( connectionFactory, connectionPool, null, "SELECT 1", false, true );

            new PoolingDataSource( connectionPool );
            connectionPool.setMaxIdle( 20 );//устанавливаем максимальное кол-во простаивающих соединений
            connectionPool.setMaxActive( 300 );//устанавилваем макс. кол-во активных соединений
        }
        catch( Exception ex )
        {
            ex.printStackTrace();
        }
    }

    /**
     * Функция получения connection из пула
     * @return Connection
     */
    public final Connection getConnection()
    {
        try
        {
            if( connectionPool.getMaxActive() <= connectionPool.getNumActive() )
            {
                System.err.println( "Connections limit is over!!!" );
            }

            Connection con = (Connection)connectionPool.borrowObject();
            return con;
        }
        catch( Exception ex )
        {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Функция возвращения connection в пул
     */
    public final void returnConnection( Connection con )
    {
        if( con == null )
        {
            System.err.println( "Returning NULL to pool!!!" );
            return;
        }

        try
        {
            connectionPool.returnObject( con );
        }
        catch( Exception ex )
        {
        }
    }


    public static void main( String[] args )
    {
        //создаем наш объект ConnectionPool
        ConnectionPool pool = new ConnectionPool();
        //единожды инициализируем его указав класс драйвера, URL базы данных, а также логин и пароль к базе данных
        pool.initConnectionPool( "com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1/mydatabase", "test", "test" );

        //получаем connection
        Connection con = pool.getConnection();

        //что нибудь делаем
        PreparedStatement ps = con.prepareStatement( "SELECT * FROM employee WHERE id=?" );
        ps.setInt( 1, 3546 );

        ResultSet rs = ps.executeQuery();
        //получаем следующую строку результата, если она есть. '''В самом начале курсор стоит перед'''
        //'''первой строкой'''. Если строка есть функция next() возвращает true и передвигает курсор
        //на следующую строку
        while( rs.next() )
        {
            System.out.println( rs.getString( "lastname" ) );
        }
        ps.close();

        //возвращаем соединение в пул
        pool.returnConnection( con );
    }
}