package airline.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;

/**
 * Gère un type de ressource en fonction de la requête de l'utilisateur.
 * On suppose une fois dans ces méthodes que les objets principaux (url.table et url.row) sont corrects.
 */
public interface ContextManager {
    /**
     * Initialise le manager.
     * @param servletContext Le contexte de la servlet utilisant ce manager.
     */
    public void init(ServletContext servletContext);

    /**
     * Affiche toutes les ressources gérées par ce manager.
     * @param request La requête du client.
     * @param response La réponse à envoyer au client.
     * @return Le dispatcher qui sera utilisé par la servlet.
     */
    public RequestDispatcher show(HttpServletRequest request, HttpServletResponse response);

    /**
     * Ajoute une ressource à ce manager.
     * @param request La requête du client.
     * @param response La réponse à envoyer au client.
     * @return Le dispatcher qui sera utilisé par la servlet.
     */
    public RequestDispatcher add(HttpServletRequest request, HttpServletResponse response);

    /**
     * Modifie une ressource gérée par ce manager.
     * @param request La requête du client.
     * @param response La réponse à envoyer au client.
     * @return Le dispatcher qui sera utilisé par la servlet.
     */
    public RequestDispatcher edit(HttpServletRequest request, HttpServletResponse response);

    /**
     * Supprime une ressource gérée par ce manager.
     * @param request La requête du client.
     * @param response La réponse à envoyer au client.
     * @return Le dispatcher qui sera utilisé par la servlet.
     */
    public RequestDispatcher delete(HttpServletRequest request, HttpServletResponse response);
}
