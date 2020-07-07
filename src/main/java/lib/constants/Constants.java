package lib.constants;

public enum Constants {

        PRODUCT_MODIFICATION_IMPORT_LOG("Product was modified via import"),
        PRODUCT_MODIFICATION_ASSET_LOG("Product was modified by asset association"),
        PRODUCT_MODIFICATION_EDIT_LOG("Product was modified"),
        NO_RESULTS_FOUND("No results found");

         private String desc;

         Constants(String desc) {
            this.desc = desc;
        }
         /**
         * Get the code and description
         * @return the code and description of the status code
         *
         **/
          public String getDesc() { return desc; }

}


