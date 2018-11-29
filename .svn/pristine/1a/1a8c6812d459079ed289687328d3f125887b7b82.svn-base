package com.aotain.common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
/**
 * This class is a Singleton that provides access to one or many
 * connection pools defined in a Property file. A client gets
 * access to the single instance through the static getInstance()
 * method and can then check-out and check-in connections from a pool.
 * When the client shuts down it should call the release() method
 * to close all open connections and do other clean up.
 */
public class DBConnectionManager {
    static private DBConnectionManager instance; // The single instance
    static private int clients;
    private String logFile;
    private Vector drivers = new Vector();
    private Hashtable pools = new Hashtable();
  /**
 * Returns the single instance, creating one if it's the
 * first time this method is called.
 *
 * @return DBConnectionManager The single instance.
 */
    static synchronized public DBConnectionManager getInstance() {
        if (instance == null) {
            instance = new DBConnectionManager();
        }
        clients++;
        //System.out.println(clients);
        return instance;
    }
    public int getAccessNum(){
        return clients;
    }
    public String getLogFile(){
        return logFile;
    }
    public int getUsedConNum(String poolName){
        DBConnectionPool pool = (DBConnectionPool) pools.get(poolName);
        int size = 0;
        if (pool != null) {
            size = pool.getUsedConNum();
        }
        pool=null;
        return size;
    }
    public int getFreeConNum(String poolName){
        DBConnectionPool pool = (DBConnectionPool) pools.get(poolName);
        int size = 0;
        if (pool != null) {
            size = pool.getFreeConNum();
        }
        pool=null;
        return size;
    }
    public String[] getPoolName(){
        Enumeration names = pools.keys();
        String[] nameList = new String[pools.size()];
        int i=0;
        while(names.hasMoreElements()){
            nameList[i++] = (String)names.nextElement();
        }
        return nameList;
    }
/**
 * A private constructor since this is a Singleton
 */
    private DBConnectionManager() {
        init();
    }

/**
 * Returns a connection to the named pool.
 *
 * @param name The pool name as defined in the properties file
 * @param con The Connection
 */
    public void freeConnection(String name, Connection con) {
        DBConnectionPool pool = (DBConnectionPool) pools.get(name);
        if (pool != null) {
            pool.freeConnection(con);
        }
        pool=null;
    }

/**
 * Returns an open connection. If no one is available, and the max
 * number of connections has not been reached, a new connection is
 * created.
 *
 * @param name The pool name as defined in the properties file
 * @return Connection The connection or null
 */
    public java.sql.Connection getConnection(String name) {
       DBConnectionPool pool = (DBConnectionPool) pools.get(name);
        if (pool != null) {
            return pool.getConnection();
        }
        pool=null;
        return null;
    }

/**
 * Returns an open connection. If no one is available, and the max
 * number of connections has not been reached, a new connection is
 * created. If the max number has been reached, waits until one
 * is available or the specified time has elapsed.
 *
 * @param name The pool name as defined in the properties file
 * @param time The number of milliseconds to wait
 * @return Connection The connection or null
 */
    public java.sql.Connection getConnection(String name, long time) {

        DBConnectionPool pool = (DBConnectionPool) pools.get(name);
        if (pool != null) {
            return pool.getConnection(time);
        }
        pool=null;
        return null;
    }

/**
 * Closes all open connections and deregisters all drivers.
 */
    public synchronized void release() {
            // Wait until called by the last client
      if (--clients != 0) {
             return;
      }
      Enumeration allPools = pools.elements();
      while (allPools.hasMoreElements()) {
          DBConnectionPool pool = (DBConnectionPool) allPools.nextElement();
          pool.release();
      }
      Enumeration allDrivers = drivers.elements();
      while (allDrivers.hasMoreElements()) {
          Driver driver = (Driver) allDrivers.nextElement();
          try {
              DriverManager.deregisterDriver(driver);
              System.out.println("Deregistered JDBC driver " + driver.getClass().getName());
          }
          catch (SQLException e) {
        	  System.out.println("Can't deregister JDBC driver: " + driver.getClass().getName()+" Exception:"+e.getMessage());
          }
      }
  }

/**
 * Creates instances of DBConnectionPool based on the properties.
 * A DBConnectionPool can be defined with the following properties:
 * <PRE>
 * &lt;poolname&gt;.url The JDBC URL for the database
 * &lt;poolname&gt;.user  A database user (optional)
 * &lt;poolname&gt;.passwordA database user password (if user specified)
 * &lt;poolname&gt;.maxconn The maximal number of connections (optional)
 * </PRE>
 *
 * @param props The connection pool properties
 */
    private void createPools(Properties props) {
        Enumeration propNames = props.propertyNames();
        String poolName = "dams";
        String url = props.getProperty(poolName + ".url");
        String user = props.getProperty(poolName + ".user");
        String password = props.getProperty(poolName + ".pwd");
        String maxconn = props.getProperty(poolName + ".maxconn", "3");
        String minconn = props.getProperty(poolName + ".minconn","1");
        String defconn = props.getProperty(poolName + ".defconn","2");
        int max,min,def;
        try {
            max = Integer.valueOf(maxconn.trim()).intValue();
            min = Integer.valueOf(minconn.trim()).intValue();
            def = Integer.valueOf(defconn.trim()).intValue();
        }
        catch (Exception e) {
        	System.out.println("Invalid maxconn value " + maxconn + " for " + poolName);
        	System.out.println("Invalid minconn value " + minconn + " for " + poolName);
        	System.out.println("Invalid minconn value " + defconn + " for " + poolName);
            max = 3;
            min=1;
            def=2;
        }
        DBConnectionPool pool = new DBConnectionPool(poolName, url.trim(), user, password, max,min,def);
        pools.put(poolName, pool);
        System.out.println("Initialized pool " + poolName);
  }

/**
 * Loads properties and initializes the instance with its values.
 */
    private void init() {
        FileInputStream is=null;
        String path = this.getClass().getResource("/").getPath() + "db.properties";
        if( System.getProperty("os.name").toLowerCase().indexOf("window")>=0)
			path = path.replaceFirst("/", "").replace("%20", " ");
        try{
            is= new FileInputStream(path);
        }
        catch(FileNotFoundException e){
        	System.out.println("File db.properties not found");
        }
        Properties dbProps = new Properties();
        try {
            dbProps.load(is);
        }
        catch (Exception e) {
            System.err.println("Can't read the properties file. " +
            "Make sure db.properties is in the CLASSPATH");
            return;
        }
        loadDrivers(dbProps);
        createPools(dbProps);
  }

/**
 * Loads and registers all JDBC drivers. This is done by the
 * DBConnectionManager, as opposed to the DBConnectionPool,
 * since many pools may share the same driver.
 *
 * @param props The connection pool properties
 */
    private void loadDrivers(Properties props) {
      String driverClasses = props.getProperty("dams.driver");
      StringTokenizer st = new StringTokenizer(driverClasses);
      while (st.hasMoreElements()) {
          String driverClassName = st.nextToken().trim();
           try {
              Driver driver = (Driver)
                Class.forName(driverClassName).newInstance();
                DriverManager.registerDriver(driver);
                drivers.addElement(driver);
                System.out.println("Registered JDBC driver " + driverClassName);
            }
            catch (Exception e) {
            	System.out.println("Can't register JDBC driver: " +
                  driverClassName + ", Exception: " + e);
            }
      }
    }

/**
 * This inner class represents a connection pool. It creates new
 * connections on demand, up to a max number if specified.
 * It also makes sure a connection is still open before it is
 * returned to a client.
 */
    class DBConnectionPool {
        private int checkedOut;                          //
        private Vector freeConnections = new Vector();  //
        private int maxConn;                            //
        private int minConn;                            //
        private int defaultConn;                        //
        private String name;                            //
        private String password;                        //
        private String URL;                             //
        private String user;                           //

        public int getUsedConNum(){
            return checkedOut;
        }
        public int getFreeConNum(){
            return freeConnections.size();
        }
/**
 * Creates new connection pool.
 *
 * @param name The pool name
 * @param URL The JDBC URL for the database
 * @param user The database user, or null
 * @param password The database user password, or null
 * @param maxConn The maximal number of connections, or 0
 * for no limit
 */
    public DBConnectionPool(String name, String URL, String user, String password,
        int maxConn,int minConn,int defaultConn) {
          this.name        = name;
          this.URL         = URL;
          this.user        = user;
          this.password    = password;
          this.maxConn     = maxConn;
          this.minConn     = minConn;
          this.defaultConn =defaultConn;
          initConnections();
    }
/**
 * Checks in a connection to the pool. Notify other Threads that
 * may be waiting for a connection.
 *
 * @param con The connection to check in
 */
      public synchronized void freeConnection(Connection con) {
  // Put the connection at the end of the Vector
            int free=freeConnections.size();
            if(con==null||((checkedOut + free > maxConn) && (free >=minConn)) ) {
                try{
                    if(con!=null){
                        con.close();
                        System.out.println("Closed connection for pool " + name);
                    }
                    else
                    	System.out.println("Thread Name :"+Thread.currentThread().getName()+" Closed Connecion is null " + name);
                }catch(SQLException e){
                	System.out.println("Can't close connection for pool " + name+" Exception :"+e);
                }
            } else {
                Statement stmt=null;
                try{
                        if(!con.getAutoCommit())
                                con.setAutoCommit(true);
                        stmt=con.createStatement();
                        stmt.close();
                        freeConnections.addElement(con);
                }catch(Exception e){
                        if(stmt!=null){
                            try{
                                 stmt.close();
                                 con.close();
                            }
                            catch(SQLException ex){}
                         }
                }
            }
            checkedOut--;
            notifyAll();
        }

/**
 * Checks out a connection from the pool. If no free connection
 * is available, a new connection is created unless the max
 * number of connections has been reached. If a free connection
 * has been closed by the database, it's removed from the pool
 * and this method is called again recursively.
 */
    public synchronized java.sql.Connection getConnection() {
        java.sql.Connection con = null;
        if (freeConnections.size() > 0) {
                    con = (java.sql.Connection) freeConnections.firstElement();
                    freeConnections.removeElementAt(0);
                    Statement stmt=null;
                    try {

                        if (con==null||con.isClosed()) {
                        	System.out.println("Removed bad connection from 1 in:" + Thread.currentThread().getName());

                        con = getConnection();
                        }else{
                            stmt=con.createStatement();
                            stmt.close();
                        }
                    }
                    catch (SQLException e) {
                    	System.out.println("Removed bad connection from 2 in:" +Thread.currentThread().getName());
                        if(stmt!=null){
                            try{
                                 stmt.close();
                                 con.close();
                            }
                            catch(SQLException ex){}
                         }
                         con = getConnection();
                     }
          }
          else if (maxConn == 0 || checkedOut < maxConn) {         //

              con = newConnection();
          }
          if (con != null) {
            checkedOut++;
          }
        return con;
    }

/**
 * Checks out a connection from the pool. If no free connection
 * is available, a new connection is created unless the max
 * number of connections has been reached. If a free connection
 * has been closed by the database, it's removed from the pool
 * and this method is called again recursively.
 * <P>
 * If no connection is available and the max number has been
 * reached, this method waits the specified time for one to be
 * checked in.
 *
 * @param timeout The timeout value in milliseconds
 */
    public synchronized java.sql.Connection getConnection(long timeout) {
        long startTime = new Date().getTime();
        java.sql.Connection con;
        while ((con = getConnection()) == null) {
            try {
                wait(timeout);                                         //
            }
            catch (InterruptedException e) {}
            if ((new Date().getTime() - startTime) >= timeout) {
  // Timeout has expired
                  return null;
              }
        }
        return con;
    }

/**
 * Closes all available connections.
 */
      public synchronized void release() {
           Enumeration allConnections = freeConnections.elements();
           while (allConnections.hasMoreElements()) {
              java.sql.Connection con = (java.sql.Connection) allConnections.nextElement();
              try {
                  con.close();
                  System.out.println("Closed connection for pool " + name);
              }
              catch (SQLException e) {
            	  System.out.println( "Can't close connection for pool " + name+" Exception :"+e);
              }
            }
            freeConnections.removeAllElements();
      }

/**
 * Creates a new connection, using a userid and password
 * if specified.
 */
      private java.sql.Connection newConnection()  {
          java.sql.Connection con = null;
          try {
              if (user == null) {
                  con = DriverManager.getConnection(URL);
              }
              else {
                  con = DriverManager.getConnection(URL,user,password);
              }
              System.out.println("Created a new connection in pool " + name);
          }
          catch (SQLException e) {
        	  System.out.println( "Can't create a new connection for " + URL+" Exception :"+e);
               return null;
          }
          return con;
      }

      private void initConnections(){
          java.sql.Connection con = null;
          for(int i=0;i<defaultConn;i++){
              con=newConnection();
              freeConnections.addElement(con);
          }
      }
  }
}
