/**
 * Copyright (C) 2009 Anthonin Bonnefoy and David Duponchel
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *         http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
    $("form").change(function(e) {
        if ($(e.target).is(":input[name=primary]")) {
            $(":input[name=primary]").val("off");
            $(e.target).val("on");
        }
    });

    // validation de champs
    $("form").submit(function() {
        if ($("input[name=tableName]").size() != 0 && $.trim($("input[name=tableName]").val()) == "") {
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
            if ($("table tbody tr:has(:input)").size() > 1) {
                $(e.target).parents("tr").remove();
            }
        }
    });

});