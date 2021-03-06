package sust.bookshelves.session;

import java.util.*;
import sust.bookshelves.*;
import sust.bookshelves.servicelocator.LocatableService;
import com.finalist.util.log.*;
import sust.bookshelves.exception.GenericBusinessException;
//import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.finalist.util.log.*;
import sust.bookshelves.entity.*;

/**
 * The <code>StatusLikeService</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  Nahid and Afjal  sust
 * @version $Revision: 1.19 $, $Date: 2006/04/29 12:39:09 $
 *
 */
public class StatusLikeService implements IStatusLikeService , LocatableService {
   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(StatusLikeService.class);


   /**
    * Default implementation for the Locatable Service interface.
    */
    public void init() {
    }

   /**
    * Default implementation for the Locatable Service interface.
    */
    public void destroy() {
    }

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/



   /**
    * Adds a new statuslike to the database.
    *
    * @param model a data object
    * @return Statuslike a data object with the primary key
    */
   public sust.bookshelves.entity.Statuslike addStatuslike(sust.bookshelves.entity.Statuslike model) throws GenericBusinessException {
      sust.bookshelves.hibernatehelper.HibernateQueryHelper hibernateTemplate = new sust.bookshelves.hibernatehelper.HibernateQueryHelper();
      try {
         hibernateTemplate.save(model);
         return getStatuslike(model.getPrimaryKey());
      } finally {
         log.debug("finished addStatuslike(sust.bookshelves.entity.Statuslike model)");
      }
   }

   /**
    * Stores the <code>Statuslike</code> in the database.
    *
    * @param model the data model to store
    */
   public void saveStatuslike(sust.bookshelves.entity.Statuslike model) throws GenericBusinessException {
      // We have to create an object:
      sust.bookshelves.hibernatehelper.HibernateQueryHelper hibernateTemplate = new sust.bookshelves.hibernatehelper.HibernateQueryHelper();
      try {
         // Now update the data.
         hibernateTemplate.update(model);
      } finally {
         log.debug("finished saveStatuslike(sust.bookshelves.entity.Statuslike model)");
      }
   }

   /**
    * Removes a statuslike.
    *
    * @param id the unique reference for the statuslike
    */
   public void deleteStatuslike(java.lang.Integer id) throws GenericBusinessException {
      sust.bookshelves.hibernatehelper.HibernateQueryHelper hibernateTemplate = new sust.bookshelves.hibernatehelper.HibernateQueryHelper();
      try {
         // First get the data.
         Statuslike bean = (Statuslike) hibernateTemplate.get(Statuslike.class, id);
         hibernateTemplate.delete(bean);
      } finally {
         log.debug("finished deleteStatuslike(java.lang.Integer id)");
      }
   }

   /**
    * Retrieves a data object from the database by its primary key.
    *
    * @param id the unique reference
    * @return Statuslike the data object
    */
   public sust.bookshelves.entity.Statuslike getStatuslike(java.lang.Integer id) throws GenericBusinessException {
      sust.bookshelves.hibernatehelper.HibernateQueryHelper hibernateTemplate = new sust.bookshelves.hibernatehelper.HibernateQueryHelper();
      try {
      Statuslike bean = (Statuslike) hibernateTemplate.get(Statuslike.class, id);
      return bean;
      } finally {
         log.debug("finished getStatuslike(java.lang.Integer id)");
      }
   }

   /**
    * Returns a list of all statuslike instances.
    *
    * @return a list of Statuslike objects.
    */
   public List getStatuslikeList() throws GenericBusinessException {
      sust.bookshelves.hibernatehelper.HibernateQueryHelper hibernateTemplate = new sust.bookshelves.hibernatehelper.HibernateQueryHelper();
      try {

      String queryString = "from " + Statuslike.class.getName() + " e";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.statuslikeId";
      queryString += orderByPart;
      Query query = hibernateTemplate.createQuery(queryString);
      List list = hibernateTemplate.list(query);

      return list;
      } finally {
         log.debug("finished getStatuslikeList");
      }
   }

   /**
    * Returns a subset of all statuslike instances.
    *
    * @param startIndex the start index within the result set (1 = first record);
    * any zero/negative values are regarded as 1, and any values greater than or equal to
    * the total number of statuslike instances will simply return an empty set.
    * @param endIndex the end index within the result set (<code>getStatuslikeListSize()</code> = last record),
    * any values greater than or equal to the total number of statuslike instances will cause
    * the full set to be returned.
    * @return a list of Statuslike objects, of size <code>(endIndex - startIndex)</code>.
    */
   public List getStatuslikeList(int startIndex, int endIndex) throws GenericBusinessException {
      if (startIndex < 1) {
         startIndex = 1;
      }
      if ( (endIndex - startIndex) < 0) {
         // Just return an empty list.
         return new ArrayList();
      }
      sust.bookshelves.hibernatehelper.HibernateQueryHelper hibernateTemplate = new sust.bookshelves.hibernatehelper.HibernateQueryHelper();
      try {
         String queryString = "from " + Statuslike.class.getName() + " e";
         // Add a an order by on all primary keys to assure reproducable results.
         String orderByPart = "";
         orderByPart += " order by e.statuslikeId";
         queryString += orderByPart;
         Query query = hibernateTemplate.createQuery(queryString);
         query.setFirstResult(startIndex - 1);
         query.setMaxResults(endIndex - startIndex + 1);
         List list = hibernateTemplate.list(query);
         return list;
      } finally {
         log.debug("finished getStatuslikeList(int startIndex, int endIndex)");
      }
   }

   /**
    * Obtains the total number of statuslike objects in the database.
    *
    * @return an integer value.
    */
   public int getStatuslikeListSize() throws GenericBusinessException {
      sust.bookshelves.hibernatehelper.HibernateQueryHelper hibernateTemplate = new sust.bookshelves.hibernatehelper.HibernateQueryHelper();
      try {
         String queryString = "select count(*) from " + Statuslike.class.getName();
         Query query = hibernateTemplate.createQuery(queryString);
         List list = hibernateTemplate.list(query);
         Integer countResult = (Integer) list.get(0);
         return countResult.intValue();
      } finally {
         log.debug("finished getStatuslikeListSize()");
      }
   }

    /**
     *
     * Retrieves a list of data object for the specified statuslikeId field.
     * To use a wildcard search, use a % in the query.
     *
     * @param statuslikeId the field
     * @return List of Statuslike data objects, empty list in case no results were found.
     */
    public java.util.List findStatuslikeByStatuslikeId(java.lang.Integer statuslikeId) throws GenericBusinessException {
      sust.bookshelves.hibernatehelper.HibernateQueryHelper hibernateTemplate = new sust.bookshelves.hibernatehelper.HibernateQueryHelper();
      try {
         String queryString = "from " + Statuslike.class.getName() + " e where e.statuslikeId like :statuslikeId ";
         // Add a an order by on all primary keys to assure reproducable results.
         String orderByPart = "";
         orderByPart += " order by e.statuslikeId";
         queryString += orderByPart;
         Query query = hibernateTemplate.createQuery(queryString);
         hibernateTemplate.setQueryParameter(query, "statuslikeId", statuslikeId);
         List list = hibernateTemplate.list(query);
         return list;
      } finally {
         log.debug("finished findStatuslikeByStatuslikeId(java.lang.Integer statuslikeId)");
      }
    }
    /**
     *
     * Retrieves a list of data object for the specified userId field.
     * To use a wildcard search, use a % in the query.
     *
     * @param userId the field
     * @return List of Statuslike data objects, empty list in case no results were found.
     */
    public java.util.List findStatuslikeByUserId(java.lang.Integer userId) throws GenericBusinessException {
      sust.bookshelves.hibernatehelper.HibernateQueryHelper hibernateTemplate = new sust.bookshelves.hibernatehelper.HibernateQueryHelper();
      try {
         String queryString = "from " + Statuslike.class.getName() + " e where e.userId like :userId ";
         // Add a an order by on all primary keys to assure reproducable results.
         String orderByPart = "";
         orderByPart += " order by e.statuslikeId";
         queryString += orderByPart;
         Query query = hibernateTemplate.createQuery(queryString);
         hibernateTemplate.setQueryParameter(query, "userId", userId);
         List list = hibernateTemplate.list(query);
         return list;
      } finally {
         log.debug("finished findStatuslikeByUserId(java.lang.Integer userId)");
      }
    }
    /**
     *
     * Retrieves a list of data object for the specified statusId field.
     * To use a wildcard search, use a % in the query.
     *
     * @param statusId the field
     * @return List of Statuslike data objects, empty list in case no results were found.
     */
    public java.util.List findStatuslikeByStatusId(java.lang.Integer statusId) throws GenericBusinessException {
      sust.bookshelves.hibernatehelper.HibernateQueryHelper hibernateTemplate = new sust.bookshelves.hibernatehelper.HibernateQueryHelper();
      try {
         String queryString = "from " + Statuslike.class.getName() + " e where e.statusId like :statusId ";
         // Add a an order by on all primary keys to assure reproducable results.
         String orderByPart = "";
         orderByPart += " order by e.statuslikeId";
         queryString += orderByPart;
         Query query = hibernateTemplate.createQuery(queryString);
         hibernateTemplate.setQueryParameter(query, "statusId", statusId);
         List list = hibernateTemplate.list(query);
         return list;
      } finally {
         log.debug("finished findStatuslikeByStatusId(java.lang.Integer statusId)");
      }
    }


}
