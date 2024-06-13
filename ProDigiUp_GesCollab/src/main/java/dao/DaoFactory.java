package dao;

/**
 *
 * @author cberge
 */
public final class DaoFactory {
    private static ResponsableActiviteDao responsableActiviteDao;
    
    private DaoFactory(){
        
    }
    
    public static ResponsableActiviteDao ResponsableActiviteDao(){
        if(responsableActiviteDao == null){
            responsableActiviteDao = new ResponsableActiviteDao();
        }
        return responsableActiviteDao;
    }
}
