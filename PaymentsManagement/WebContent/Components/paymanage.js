$(document).ready(function() {
    if ($('#alertSuccess').text().trim() == "") {
        $('#alertSuccess').hide();
    }

    $('#alertError').hide();
})

// SAVE
$(document).on("click","#btnSave", function(event) {
    // Clear alerts
    $("#alertSuccess").text(""); 
    $("#alertSuccess").hide(); 
    $("#alertError").text(""); 
    $("#alertError").hide();

    // Form validation
    var status = validateItemForm(); 
    if (status != true) 
    { 
        $("#alertError").text(status); 
        $("#alertError").show(); 
        return; 
    } 

    // If valid
    $("#formItem").submit(); 
})

// UPDATE
//to identify the update button we didn't use an id we used a class
$(document).on("click", ".btnUpdate", function(event) 
{ 
    $("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIDUpdate').val()); 
    $("#pAccNo").val($(this).closest("tr").find('td:eq(0)').text()); 
    $("#pCus").val($(this).closest("tr").find('td:eq(1)').text()); 
    $("#pAmount").val($(this).closest("tr").find('td:eq(2)').text()); 
    $("#pDate").val($(this).closest("tr").find('td:eq(3)').text()); 
}); 


// CLIENT-MODEL================================================================ 
function validateItemForm() { 
    // CODE 
    if ($("#pAccNo").val().trim() == "") 
    { 
        return "Insert Item Code."; 
    } 
    
    // NAME 
    if ($("#pCus").val().trim() == "") 
    { 
        return "Insert Item Name."; 
    } 
    
    // PRICE------------------------------- 
    if ($("#pAmount").val().trim() == "") 
    { 
        return "Insert Item Price."; 
    } 
    
    // is numerical value 
    var tmpPrice = $("#pAmount").val().trim(); 
    if (!$.isNumeric(tmpPrice)) 
    { 
        return "Insert a numerical value for Item Price."; 
    } 
    
    // convert to decimal price 
    $("#pAmount").val(parseFloat(tmpPrice).toFixed(2)); 
    
    // DESCRIPTION------------------------ 
    if ($("#pDate").val().trim() == "") 
    { 
        return "Insert Item Description."; 
    } 
    
    return true; 
} 
 