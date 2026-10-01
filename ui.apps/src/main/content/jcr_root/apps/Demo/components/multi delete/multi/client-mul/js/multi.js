(function ($, Coral) {
    "use strict";

    var MAX_ITEMS = 4;

    $(document).on("dialog-ready", function () {

        $("coral-multifield").each(function () {

            var multifield = this;

            multifield.on("coral-collection:add", function (e) {

                var items = multifield.items.getAll();

                if (items.length > MAX_ITEMS) {

                    // Remove the newly added item
                    multifield.items.remove(items[items.length - 1]);

                    // Show browser popup (for testing)
                    alert("You can add only 4 items.");

                    // Prevent further processing
                    e.preventDefault();
                    return false;
                }
            });

        });

    });

})(Granite.$, Coral);