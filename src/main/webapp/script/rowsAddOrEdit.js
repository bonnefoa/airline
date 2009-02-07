$(function() {
    // duplique une ligne
    $("#addField").click(function() {
        var line = $("table tbody tr:last").clone();
        line.find(":input").val("").filter(":checkbox").each(function() {
            this.checked = false;
        });
        $("table tbody").append(line);
        line.find(":input:first").focus();

        return false;
    });

    // une seule clef primaire autorisee
    $("form").click(function(e) {
        if ($(e.target).is(":checkbox")) {
            return $(":checkbox:checked").not(e.target).size() == 0;
        }
    });

});