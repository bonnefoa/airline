$(function(){
    $(":submit").remove();
    $(":select").change(function(){$("form").submit();});
});