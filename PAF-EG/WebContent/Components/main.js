/**
 * 
 */
$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});




//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateUserForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }

var type = ($("#hididSave").val() == "") ? "POST" : "PUT";


$.ajax(
    {
     url : "BillAPI",
     type : type,
     data : $("#formUser").serialize(),
     dataType : "text",
     complete : function(response, status)
     {
     onUserSaveComplete(response.responseText, status);
     }
    });

});
function onUserSaveComplete(response, status)
{
if (status == "success")
 {
  var resultSet = JSON.parse(response);
  if (resultSet.status.trim() == "success")
  {
    $("#alertSuccess").text("Successfully saved.");
    $("#alertSuccess").show();
    
    $("#divUserGrid").html(resultSet.data);
  } else if (resultSet.status.trim() == "error")
  {
    $("#alertError").text(resultSet.data);
    $("#alertError").show();
  }
   } else if (status == "error")
   {
     $("#alertError").text("Error while saving.");
     $("#alertError").show();
   } else
   {
     $("#alertError").text("Unknown error while saving..");
     $("#alertError").show();
   }
    $("#hididSave").val("");
    $("#formUser")[0].reset();
}

//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 $("#hididSave").val($(this).closest("tr").find('#hididUpdate').val());
 $("#Customer_Name").val($(this).closest("tr").find('td:eq(1)').text());
 $("#Customer_Account").val($(this).closest("tr").find('td:eq(2)').text());
 $("#Date").val($(this).closest("tr").find('td:eq(3)').text());
 $("#Units_Used").val($(this).closest("tr").find('td:eq(4)').text());
 $("#Amount").val($(this).closest("tr").find('td:eq(5)').text());
});


$(document).on("click", ".btnRemove", function(event)
    {
     $.ajax(
     {
     url : "BillAPI",
     type : "DELETE",
     data : "Bill_ID=" + $(this).data("itemid"),
     dataType : "text",
     complete : function(response, status)
     {
     onUserDeleteComplete(response.responseText, status);
     }
     });
    });

function onUserDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divUserGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}



//CLIENTMODEL=========================================================================
// Validating the form data===========================================================

function validateUserForm()
{
  
//first_name
if ($("#Customer_Name").val().trim() == "")
{
return "Insert customer name.";
}


//
if ($("#Customer_Account").val().trim() == "")
{
return "Insert customer account.";
}


if ($("#Date").val().trim() == "")
{
return "Insert date.";
}


// 
if ($("#Units_Used").val().trim() == "")
{
return "Insert units used.";
} 


// 
if ($("#Amount").val().trim() == "")
{
return "Insert amount.";
}
return true;
}
