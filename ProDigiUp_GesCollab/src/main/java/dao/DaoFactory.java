package dao;

/**
 * modèle de conception Factory Method, permettant d'instancier des objets DAO
 * (Data Access Object) pour différentes entités de votre système Attributs
 * static Constructeur privée Méthodes de fabrique (get*Dao methods) Utilisation
 * du modèle Singleton
 *
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

    /**
     * Constructeur privé de la classe DaoFactory. Ce constructeur est privé
     * pour empêcher l'instanciation directe de la classe depuis l'extérieur. La
     * classe DaoFactory utilise le pattern Singleton, où l'unique instance est
     * accessible via des méthodes statiques.
     */
    private DaoFactory() {
    }

    /**
     * Retourne une instance unique de la classe PossederDao. Utilise le pattern
     * Singleton pour s'assurer qu'une seule instance de PossederDao existe.
     *
     * @return l'unique instance de PossederDao
     */
    public static PossederDao getPossederDao() {
        if (possederDao == null) {
            possederDao = new PossederDao();
        }
        return possederDao;
    }

    /**
     * Retourne une instance unique de la classe ProposerDao. Utilise le pattern
     * Singleton pour s'assurer qu'une seule instance de ProposerDao existe.
     *
     * @return l'unique instance de ProposerDao
     */
    public static ProposerDao getProposerDao() {
        if (proposerDao == null) {
            proposerDao = new ProposerDao();
        }
        return proposerDao;
    }

    /**
     * Retourne une instance unique de la classe CollaborateurDao. Utilise le
     * pattern Singleton pour s'assurer qu'une seule instance de
     * CollaborateurDao existe.
     *
     * @return l'unique instance de CollaborateurDao
     */
    public static CollaborateurDao getCollaborateurDao() {
        if (collaborateurDao == null) {
            collaborateurDao = new CollaborateurDao();
        }
        return collaborateurDao;
    }

    /**
     * Retourne une instance unique de la classe ResponsableActiviteDao. Utilise
     * le pattern Singleton pour s'assurer qu'une seule instance de
     * ResponsableActiviteDao existe.
     *
     * @return l'unique instance de ResponsableActiviteDao
     */
    public static ResponsableActiviteDao ResponsableActiviteDao() {
        if (responsableActiviteDao == null) {
            responsableActiviteDao = new ResponsableActiviteDao();
        }
        return responsableActiviteDao;
    }

    /**
     * Retourne une instance unique de la classe PartenaireDao. Utilise le
     * pattern Singleton pour garantir qu'une seule instance de PartenaireDao
     * existe.
     *
     * @return l'unique instance de PartenaireDao
     */
    public static PartenaireDao getPartenaireDao() {
        if (partenaireDao == null) {
            partenaireDao = new PartenaireDao();
        }
        return partenaireDao;
    }

    /**
     * Retourne une instance unique de la classe PrestationDao. Utilise le
     * pattern Singleton pour garantir qu'une seule instance de PrestationDao
     * existe.
     *
     * @return l'unique instance de PrestationDao
     */
    public static PrestationDao getPrestationDao() {
        if (prestationDao == null) {
            prestationDao = new PrestationDao();
        }
        return prestationDao;
    }
    
    

}
