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

    private DaoFactory() {
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
