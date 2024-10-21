package com.excursions.exploreWithUs.ExploreWithUs.constants;

public class ExcursionConstants {
    //GET ALL
    public static final String EXCURSION_GET_ALL_SUMMERY = "Get all excursions with pagination";
    public static final String EXCURSION_GET_ALL_200 = "Successfully retrieved excursions";
    public static final String EXCURSION_GET_ALL_500 = "Internal server error";

    //GET BY ID
    public static final String EXCURSION_GET_BY_ID_SUMMARY = "Get excursion by ID";
    public static final String EXCURSION_GET_BY_ID_DESCRIPTION = "Retrieve an excursion's details using its unique identifier.";
    public static final String EXCURSION_GET_BY_ID_200 = "Successfully retrieved excursion";
    public static final String EXCURSION_GET_BY_ID_404 = "Excursion not found with the given ID";
    public static final String EXCURSION_GET_BY_ID_500 = "Internal server error";
    public static final String EXCURSION_GET_BY_ID_ID_PARAM = "The ID of the excursion to be retrieved";

    //POST (CREATE NEW EXCURSION)
    public static final String EXCURSION_POST_CREATE_SUMMERY = "Create a new excursion";
    public static final String EXCURSION_POST_CREATE_DESCRIPTION = "Creates a new excursion based on the provided details.";
    public static final String EXCURSION_POST_CREATE_201 = "Successfully created a new excursion. The created excursion is returned in the response body.";
    public static final String EXCURSION_POST_CREATE_400 = "Invalid input provided. This occurs when the input data is not valid, e.g., missing required fields.";
    public static final String EXCURSION_POST_CREATE_500 = "Internal server error. This may happen if there is a failure in the service or database.";
    public static final String EXCURSION_POST_CREATE_REQUEST_BODY = "Details of the excursion to be created";

    //PUT (UPDATE EXCURSION)
    public static final String EXCURSION_PUT_UPDATE_SUMMERY = "Update an excursion by ID";
    public static final String EXCURSION_PUT_UPDATE_DESCRIPTION = "Updates the excursion with the specified ID.";
    public static final String EXCURSION_PUT_UPDATE_200 = "Successfully updated the excursion.";
    public static final String EXCURSION_PUT_UPDATE_400 = "Invalid input provided. This occurs when the input data is not valid, e.g., missing required fields.";
    public static final String EXCURSION_PUT_UPDATE_404 = "Excursion not found with the given ID.";
    public static final String EXCURSION_PUT_UPDATE_500 = "Internal server error.";
    public static final String EXCURSION_PUT_UPDATE_ID_PARAM = "ID of the excursion to be updated";
    public static final String EXCURSION_PUT_UPDATE_REQUEST_BODY = "Details of the excursion to update";

    // DELETE EXCURSION
    public static final String EXCURSION_DELETE_SUMMERY = "Delete excursion by ID";
    public static final String EXCURSION_DELETE_DESCRIPTION = "Deletes an excursion with the specified ID. If the excursion is successfully deleted, a 204 No Content response is returned.";
    public static final String EXCURSION_DELETE_204 = "Successfully deleted the excursion.";
    public static final String EXCURSION_DELETE_404 = "Excursion not found with the given ID. This occurs when no excursion matches the provided ID.";
    public static final String EXCURSION_DELETE_500 = "Internal server error. This may happen if there is a failure in the service or database.";
    public static final String EXCURSION_DELETE_ID_PARAM = "ID of the excursion to be deleted. Must be a valid excursion ID.";

}
