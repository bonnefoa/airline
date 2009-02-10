package airline.criteria.model;

/**
 * Interface for request.
 */
public interface IRequest {
    /**
     * Build a query according to the state of the request.
     *
     * @return Sql query corresponding to the request
     */
    String buildQuery();
}
