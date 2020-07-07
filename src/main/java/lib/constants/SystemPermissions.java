package lib.constants;

public enum SystemPermissions {

    ORGANIZATION_SETUP("Organization setup"),
    IMPORT("Import"),
    PROPERTY_DEFINITION("Property Definitions"),
    PRODUCT_MANAGEMENT("Product Management"),
    NETWORK("Network"),
    TASK("Task"),
    CATALOG("Catalog"),
    CATEGORY("Category"),
    ASSETS("Assets"),
    WORKFLOW("Workflow"),

  //  -------------------------------------

    MANAGE("Manage"),
    READ_ONLY("Read Only"),
    HIDE("Hide");

    private String desc;

    SystemPermissions(String desc) {
        this.desc = desc;
    }
    /**
     * Get the code and description
     * @return the code and description of the status code
     *
     **/
    public String getDesc() { return desc; }
}
