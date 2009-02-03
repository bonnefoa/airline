$(function() {

    function updateRows(xml) {

    }

    function updateFields(xml) {

    }

    function showError() {

    }

    $("#from").change(function() {
        $.get("...", {
            success: updateFields,
            error: showError
        });
    });

    $("#update").change(function() {
        $.get("...", {
            success: updateRows,
            error: showError
        });
    });


});