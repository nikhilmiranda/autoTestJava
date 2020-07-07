package lib.constants;

public enum Notification {

        WF_UPDATE("Workflow successfully updated"),
        WF_STARTED("We have initiated the workflow and will start in sometime"),
        PRODUCT_UPDATE("Product updated successfully"),
        PRODUCT_UPDATE_NOT_MODIFIED("No modified data found."),
        PRODUCT_GRP_CREATED_ADDED_PDP("product group has been created and products have been successfully added to it!"),
        PRODUCT_GRP_CREATED_PDP("Products successfully added to"),//+ productGrpName
        TASK_CREATION("Task created successfully"),
        CATEGORY_PRODUCT_UPDATE("Category for 1 product(s) updated successfully"),
        ROLE_DETAILS_UPDATE("Role details successfully updated"),
        PRODUCT_UPDATE_PDP("Product updated successfully!"),
        MEMBER_DETAILS_UPDATE("Member details successfully updated"),
        SYSTEM_PERMISSION_UPDATE("System permission details for this role successfully updated");

        private String desc;

        Notification(String desc) {
            this.desc = desc;
        }
        /**
         * Get the code and description
         * @return the code and description of the status code
         *
         **/
        public String getDesc() {
            return desc;
        }

}
