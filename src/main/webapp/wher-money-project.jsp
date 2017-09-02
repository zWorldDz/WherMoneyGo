<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <!--  Title  -->
    <title>WherMoneyGoo</title>
    
    <!-- Avoid zoom-in and out from mobile view-->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    
    <!-- Latest compiled and minified CSS for bootstrap-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    
    <!-- icon lib-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
    <!-- icon lib-->
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    
    <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.15/css/jquery.dataTables.css">
  
    <script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.15/js/jquery.dataTables.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/responsive/2.1.1/js/dataTables.responsive.min.js"></script>
    

    <link rel="stylesheet" href="css/StandBy.css" type="text/css"/>
    
    <script src="js/StandBy.js"></script>
    
</head>
<body >
    <section id="top" style="background-image: url('images/bg--save.jpg');
                                                  background-size:cover;
                                                  background-position: center;
                                                  height: 300px;"  class="container text-center">
                                                  
      <div id="amount-container" class="container text-center">
        <div><h3>Available Budget In <span id="avdate"></span></h3></div> 
        <div><h2><span id="avamount">+ 2,345.64</span></h2></div> 
        <div>Income <span id="top_income"></span></div> 
        <div>Expenses <span id="top_expense">5,000.00</span></div> 
      </div>
    </section>
    
    <section id="middle" class="container text-center">
    <form id="detail-form" class="form-inline"role="form">
    <div class="container text-center">
        
    <div class="form-group">
      <select class="form-control" id="symbol" name="symbol">
        <option selected disabled>Select One</option>
        <option value="0">-</option>
        <option value="1">+</option>
      </select>
    </div>   
    
    <div class="form-group">
      <input type="text" class="form-control" id="description" name="description" placeholder="Description">
    </div>
        
    <div class="form-group">
      <input type="text" class="form-control" id="amount" name="amount" placeholder="Amount">
    </div>
        
    <div id="sbtn" class="form-group">
      <a class="form-control glyphicon glyphicon-play-circle btn-info" id="submit-btn"></a>
    </div>
        
    </div>
        
    </form>

    
    </section>
    
    <section id="bot" class="container text-center">
    <div class="col-md-6">
    <div id="tb-income" class="text-center">
    <h3 id="h3-income">Icome</h3>
    
    <form>
    <c:set var="total" value="${0}"/>
   
    <table id="income-table" class="table table-striped table-hover " cellspacing="0">
        <thead>
        <tr>
          <th class="text-center">ID.</th>    
          <th class="text-center all">Description</th>    
          <th class="text-center all">Amount</th>     
          <th class="text-center">Date</th>     
          <th class="text-center all">Action</th>     
        </tr>   
        </thead>
        <tbody>
          <c:forEach var="temp" items="${LIST_DETAIL}" >
          <!-- Link for list of details -->
          <tr id="${temp.detail_id}" >
              <td  data-toggle="modal" data-target="#myModal">${temp.detail_id}</td>
              <td  value="${temp.description}" data-toggle="modal" data-target="#myModal">${temp.description}</td>
              <td  value="${temp.amount}"data-toggle="modal" data-target="#myModal">${temp.amount}</td>
              <td data-toggle="modal" data-target="#myModal">${temp.date}</td>
              <td data-toggle="modal" data-target="#myModal"><a class="glyphicon glyphicon glyphicon-minus income-remove" name="REMOVE_INCOME" value="${temp.detail_id}"></a></td>                
          </tr>
              <c:set var="total" value="${total + temp.amount}" />
          </c:forEach>    
        </tbody>
    </table>
    </form>
      <script>
      	$("#top_income").html( <c:out value='${total}'/>);
      </script>
    </div>
    </div>    

    <div class="col-md-6">
    <div id="tb-expense"class="text-center ">
    <h3 id="h3-expenses">Expenses</h3>
        <form>
        <c:set var="total" value="${0}"/>
    
      <table id="expense-table" class="table table-striped table-hover" >
        <thead>
        <tr>
          <th class="text-center">ID.</th>    
          <th class="text-center all">Description</th>    
          <th class="text-center all">Amount</th>     
          <th class="text-center">Date</th>     
          <th class="text-center all">Action</th>     
        </tr>    
        </thead>
        <tbody>
          <c:forEach var="temp" items="${LIST_EXPENSE}" >
          <!-- Link for list of details -->
          <tr id="${temp.detail_id}" >
              <td  data-toggle="modal" data-target="#myModal">${temp.detail_id}</td>
              <td  value="${temp.description}" data-toggle="modal" data-target="#myModal">${temp.description}</td>
              <td  value="${temp.amount}"data-toggle="modal" data-target="#myModal">${temp.amount}</td>
              <td data-toggle="modal" data-target="#myModal">${temp.date}</td>
              <td data-toggle="modal" data-target="#myModal"><a class="glyphicon glyphicon glyphicon-minus expense-remove" name="REMOVE_EXPENSE" value="${temp.detail_id}"></a></td>                
          </tr>
          <c:set var="total" value="${total + temp.amount}" />
          
          </c:forEach> 
        </tbody>
      </table>
     </form>
           <script>
        $("#top_expense").html(<c:out value='${total}'/>);
        
     	var top_income = $("#top_income").html();
     	var top_expense = $("#top_expense").html();
     	console.log(top_income);
     	console.log(top_expense);
        var avamount = top_income - top_expense;
        if(isNaN(avamount))
        	avamount = 0;
        $("#avamount").html("$ " + avamount);
      </script>
    </div>    
    </div>    
        
    </section>
    
<!-- Modal -->

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-sm" role="document" >
    <div class="modal-content" style="width: 302px;margin:auto;">
                <form id="modal-form" >
                <input id="modal_to_ser_id" type="hidden" value=""/>
                <input id="modal_type" type="hidden" value=""/>
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Update Detail</h4>
      </div>
      
      <div class="modal-body">
        <div class="form-group">
  <label for="description_modal">Description:</label>
  <input type="text" class="form-control" id="description_modal" name="description_modal" placeholder="Description">
</div>
<div class="form-group">
  <label for="amount_modal">Amount:</label>
  <input type="text" class="form-control" id="amount_modal" name="amount_modal" placeholder="Amount">
</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="modal-submit-btn">Save changes</button>
      </div>
                  </form>  
    </div>
    
  </div>
</div>
      <script>
      
      $(".income-remove").click(function(){
          console.log("Income Remove");
		
          /*
           * Ajax Post method
           */
          var description = $("#description").val();
        var incomeId = $(this).attr("value");
          //JSON Format
          var toSer = {  //Data that need to be send to server
            command: "REMOVE_INCOME",
            incomeId: incomeId
            };
                
           $.ajax({
             
            type: "Get", //Method Used
            
            url:"DetailController", //API Path
            
            data:toSer, //Data that need to be send to server
                      
            success:function(data) {
              window.location.replace('/WherMoneyGoo/DetailController');
            },
            error:function(textStatus, errorThrown) {
              console.log(textStatus);//Display error in console log
            }
          });
        });

      
      $(".expense-remove").click(function(){
          console.log("Expense Remove");
		
          /*
           * Ajax Post method
           */
          var description = $("#description").val();
        var expenseId = $(this).attr("value");
          //JSON Format
          var toSer = {  //Data that need to be send to server
            command: "REMOVE_EXPENSE",
            expenseId: expenseId
            };
                
           $.ajax({
             
            type: "Get", //Method Used
            
            url:"DetailController", //API Path
            
            data:toSer, //Data that need to be send to server
                      
            success:function(data) {
              window.location.replace('/WherMoneyGoo/DetailController');
            },
            error:function(textStatus, errorThrown) {
              console.log(textStatus);//Display error in console log
            }
          });
        });

      
      $("#submit-btn").click(function(e){
        console.log("Form");
        /*
         * Ajax Post method
         */
     e.preventDefault();
         var description = $("#description").val();
         var amount = $("#amount").val();
         var symbol = $("#symbol").val();
                      
        //JSON Format
        var toSer = {  //Data that need to be send to server
          command: "ADD",
          symbol: symbol,
          description:description,
          amount:amount,
          date:callTodayDate()
          };
              
         $.ajax({
           
          type: "Post", //Method Used
          
          url:"DetailController", //API Path
          
          data:toSer, //Data that need to be send to server
                    
          success:function(data) {
            window.location.replace('/WherMoneyGoo/DetailController');
          },
          error:function(textStatus, errorThrown) {
            console.log(textStatus);//Display error in console log
          }
        });
      });
      
      $("#modal-submit-btn").click(function(e){
          console.log("Modal_Form");
          /*
           * Ajax Post method
           */
         e.preventDefault();
          
           var description = $("#description_modal").val();
           var amount = $("#amount_modal").val();
           var incomeId = $('#modal_to_ser_id').val();
           
          //JSON Format
          var toSer = {  //Data that need to be send to server
            command: "UPDATE"+$("#modal_type").val(),
            description:description,
            amount:amount,
            incomeId: incomeId
            };
           $.ajax({
             
            type: "Post", //Method Used
            
            url:"DetailController", //API Path
            
            data:toSer, //Data that need to be send to server
                      
            success:function(data) {
              window.location.replace('/WherMoneyGoo/DetailController');
            },
            error:function(textStatus, errorThrown) {
              console.log(textStatus);//Display error in console log
            }
          });
        });

      $(document).ready(function() {
        callTodayDate();
          var income = $('#income-table').DataTable({
            "searching": false,
            "responsive": true

          });
          
          // Activate an inline edit 
          $('#income-table').on( 'click', 'tbody tr', function (e) {
            var desc = $(this).find('td:nth-child(2)').html();
              var amount = $(this).find('td:nth-child(3)').html();
             $("#modal_to_ser_id").val($(this).attr("id"));           
            
          $("#description_modal").val(desc);
        $("#amount_modal").val(amount);
        $("#modal_type").val("INCOME");

          } );
          
          var expense =  $('#expense-table').DataTable({
            "searching": false,
            "responsive": true
          });
          
          
          // Activate an inline edit 
          $('#expense-table').on( 'click', 'tbody tr', function (e) {
            var desc = $(this).find('td:nth-child(2)').html();
              var amount = $(this).find('td:nth-child(3)').html();
             $("#modal_to_ser_id").val($(this).attr("id"));           
            
        $("#description_modal").val(desc);
        $("#amount_modal").val(amount);
        $("#modal_type").val("EXPENSE");
          } );
      } );
    </script>
</body>
</html>
