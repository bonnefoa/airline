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
    /*
     $("form").click(function(e) {
     if ($(e.target).is(":checkbox")) {
     return $(":checkbox:checked").not(e.target).size() == 0;
     }
     });
     */
    $("form").change(function(e) {
        if ($(e.target).is(":input[name=primary]")) {
            $(":input[name=primary]").val("off");
            $(e.target).val("on");
        }
    });

    // validation de champs
    $("form").submit(function() {
        if ($.trim($("input[name=tableName]").val()) == "") {
            alert("la table doit avoir un nom !");
            return false;
        }

        var ok = true;
        $("form input[name=name]").each(function() {
            ok = ok && ($(this).val().length != 0);
        });
        if (!ok) {
            alert("tous les champs doivent être remplis !");
        }
        return ok;
    });

    // supression de ligne
    $("form").click(function(e) {
        if ($(e.target).is("img.deleteImg")) {
            if ($("table tbody tr").size() > 1) {
                $(e.target).parents("tr").remove();
            }
        }
    });

});