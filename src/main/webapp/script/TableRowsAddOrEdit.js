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
    $("#addRow").click(function() {
        var line = $("table tbody tr:last").clone();
        line.find(":input").val("");
        $("table tbody").append(line);
        line.find(":input:first").focus();

        return false;
    });

    // datepicker
    $(":input.TIMESTAMP").datepicker({ dateFormat: 'yy-mm-dd' });
    $(":input.DATE").datepicker({ dateFormat: 'yy-mm-dd' });

    // supression de ligne
    $("form").click(function(e) {
        if ($(e.target).is("img.deleteImg")) {
            if ($("table tbody tr:has(:input)").size() > 1) {
                $(e.target).parents("tr").remove();
            }
        }
    });

    // verification
    $("form").submit(function(e) {
        var ok = true;
        $(":input.INTEGER").each(function(){
            ok = ok && parseInt(this.value) != NaN;
        });

        if(!ok) {
            alert("les champs entiers ne doivent contenir que des entiers !");
        }

        return ok;
    });

});