package dao;

/**
 *
 * @author cberge
 */

public class DaoFactory {
 private static CollaborateurDao collaborateurDao;
 private static ResponsableActiviteDao responsableActiviteDao;
    
    private DaoFactory(){
}
    
        public static CollaborateurDao getCollaborateurDao(){
            if (collaborateurDao == null) {
                collaborateurDao = new CollaborateurDao();
            }
            return collaborateurDao;
        }

    
    public static ResponsableActiviteDao ResponsableActiviteDao(){
        if(responsableActiviteDao == null){
            responsableActiviteDao = new ResponsableActiviteDao();
        }
        return responsableActiviteDao;
    }

}
