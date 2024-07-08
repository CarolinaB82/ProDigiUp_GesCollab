package dao;

/**
 *
 * @author cberge
 */
public class DaoFactory {

    private static CollaborateurDao collaborateurDao;
    private static ResponsableActiviteDao responsableActiviteDao;
    private static PartenaireDao partenaireDao;
    private static PrestationDao prestationDao;
    private static ProposerDao proposerDao;
    private static PossederDao possederDao;

    

    

    private DaoFactory() {
    }
    public static PossederDao getPossederDao(){
        if (possederDao == null){
            possederDao = new PossederDao();
        }
        return possederDao;
    }
    
    
    public static ProposerDao getProposerDao(){
        if (proposerDao == null){
            proposerDao = new ProposerDao();
        }
        return proposerDao;
    }
    
    public static CollaborateurDao getCollaborateurDao() {
        if (collaborateurDao == null) {
            collaborateurDao = new CollaborateurDao();
        }
        return collaborateurDao;
    }

    public static ResponsableActiviteDao ResponsableActiviteDao() {
        if (responsableActiviteDao == null) {
            responsableActiviteDao = new ResponsableActiviteDao();
        }
        return responsableActiviteDao;
    }

    public static PartenaireDao getPartenaireDao() {
        if (partenaireDao == null) {
            partenaireDao = new PartenaireDao();
        }
        return partenaireDao;
    }

    public static PrestationDao getPrestationDao() {
        if (prestationDao == null) {
            prestationDao = new PrestationDao();
        }
        return prestationDao;
    }

}
