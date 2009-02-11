/**
 * Copyright (C) 2009 Anthonin Bonnefoy and David Duponchel
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *         http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package airline.tables;

import airline.servlet.enumeration.MessageError;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Un gestionnaire d'action sur une entite.
 * Il sera utilise pour verifier son contexte, et appliquer les methodes HTTP demandees.
 */
public interface ActionHandler {

    /**
     * Initialise l'objet.
     * @param servletContext Le ServletContext.
     */
    void init(ServletContext servletContext);

    /**
     * Verifie que le contexte courant est coherent avec l'action demandee.
     * @param request La requete HTTP.
     * @return Un type d'erreur, null si tout s'est bien deroule.
     */
    MessageError checkContext(ServletRequest request);

    /**
     * Applique la methode HTTP GET sur l'element.
     * @param request La requete HTTP.
     * @param response La reponse HTTP.
     * @return Le dispatcher a utiliser si l'action aboutit effectivement.
     */
    RequestDispatcher get(HttpServletRequest request, HttpServletResponse response);

    /**
     * Applique la methode HTTP POST sur l'element.
     * @param request La requete HTTP.
     * @param response La reponse HTTP.
     * @return Le dispatcher a utiliser si l'action aboutit effectivement.
     */
    RequestDispatcher post(HttpServletRequest request, HttpServletResponse response);
}
